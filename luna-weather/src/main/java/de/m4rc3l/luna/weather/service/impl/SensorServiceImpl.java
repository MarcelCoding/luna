package de.m4rc3l.luna.weather.service.impl;

import de.m4rc3l.luna.weather.converter.SensorConverter;
import de.m4rc3l.luna.weather.dto.Resolution;
import de.m4rc3l.luna.weather.dto.Sensor;
import de.m4rc3l.luna.weather.dto.Sensor.Data;
import de.m4rc3l.luna.weather.model.SensorModel;
import de.m4rc3l.luna.weather.repository.SensorRepository;
import de.m4rc3l.luna.weather.service.SensorService;
import de.m4rc3l.nova.core.exception.NotFoundException;
import de.m4rc3l.nova.core.exception.ValidationException;
import de.m4rc3l.nova.core.service.AbstractCommonIdCrudService;
import de.m4rc3l.nova.core.utils.ValidationUtils;
import de.m4rc3l.nova.influx.InfluxClient;
import de.m4rc3l.nova.influx.Measurement;
import de.m4rc3l.nova.influx.Measurement.DoubleField;
import de.m4rc3l.nova.influx.WritePrecision;
import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
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
      new DoubleField("value", Math.round(value.getValue() * 1000) / 1000D),
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
        Math.round(measurement.getField().getValue() * 10) / 10D
      ));
  }
}
