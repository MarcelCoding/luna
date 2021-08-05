package com.github.marcelcoding.luna.weather.service.impl;

import com.github.marcelcoding.luna.weather.converter.SensorConverter;
import com.github.marcelcoding.luna.weather.dto.Resolution;
import com.github.marcelcoding.luna.weather.dto.Sensor;
import com.github.marcelcoding.luna.weather.dto.Sensor.Data;
import com.github.marcelcoding.luna.weather.model.SensorModel;
import com.github.marcelcoding.luna.weather.repository.SensorRepository;
import com.github.marcelcoding.luna.weather.service.SensorService;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import net.getnova.framework.core.exception.NotFoundException;
import net.getnova.framework.core.exception.ValidationException;
import net.getnova.framework.core.service.AbstractCommonIdCrudService;
import net.getnova.framework.core.utils.ValidationUtils;
import net.getnova.framework.influx.InfluxClient;
import net.getnova.framework.influx.Measurement;
import net.getnova.framework.influx.Measurement.DoubleField;
import net.getnova.framework.influx.WritePrecision;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class SensorServiceImpl
  extends AbstractCommonIdCrudService<Sensor, UUID, SensorModel>
  implements SensorService {

  private static final String DATA_QUERY = """
    from(bucket: "%s")
      |> range(start: %s, stop: %s)
      |> filter(fn: (r) => r._measurement == "sensor" and r.sensor_id == "%s")
      |> drop(columns: ["sensor_id"])
      |> aggregateWindow(every: 1%s, fn: mean, createEmpty: false)
    """;

  private final InfluxClient influxClient;

  public SensorServiceImpl(
    final SensorRepository repository,
    final SensorConverter converter,
    final InfluxClient influxClient
  ) {
    super("SENSOR", repository, converter);
    this.influxClient = influxClient;
  }

  @Override
  public Mono<Void> publishData(final UUID id, final Data value) {
    ValidationUtils.validate(value);

    if (!this.exist(id)) {
      throw new NotFoundException(this.name);
    }

    final Measurement<?> measurement = new Measurement<>(
      "sensor",
      Map.of("sensor_id", id.toString()),
      new DoubleField("value", value.getValue()),
      value.getTimestamp() == null ? Instant.now() : value.getTimestamp()
    );

    return this.influxClient.write(Flux.just(measurement), WritePrecision.S);
  }

  @Override
  public Flux<Data> fetchData(
    final UUID id,
    final Instant from,
    final Optional<Instant> to,
    final Resolution resolution
  ) {
    final Instant toDate = to.orElseGet(Instant::now);

    if (!from.isBefore(toDate)) {
      throw new ValidationException("from", "before \"to\"");
    }

    if (!this.exist(id)) {
      throw new NotFoundException(this.name);
    }

    final String query = DATA_QUERY.formatted(
      this.influxClient.getBucket(), // bucket
      from.getEpochSecond(),         // start
      toDate.getEpochSecond(),       // range
      id,                            // sensor id
      resolution.getInfluxUnit()     // resolution
    );

    return this.influxClient.query(query, DoubleField.class)
      .map(measurement -> new Data(
        measurement.getTimestamp(),
        measurement.getField().getValue()
      ));
  }
}
