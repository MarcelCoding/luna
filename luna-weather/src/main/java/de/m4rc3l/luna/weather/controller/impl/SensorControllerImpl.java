package de.m4rc3l.luna.weather.controller.impl;

import de.m4rc3l.luna.weather.controller.SensorController;
import de.m4rc3l.luna.weather.dto.Resolution;
import de.m4rc3l.luna.weather.dto.Sensor;
import de.m4rc3l.luna.weather.dto.Sensor.Data;
import de.m4rc3l.luna.weather.service.SensorService;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import de.m4rc3l.nova.core.controller.AbstractCrudController;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class SensorControllerImpl
  extends AbstractCrudController<Sensor, UUID>
  implements SensorController {

  public SensorControllerImpl(
    final SensorService service
  ) {
    super(service);
  }

  @Override
  public Mono<Void> publishData(final UUID id, final Data data) {
    return ((SensorService) this.service).publishData(id, data);
  }

  @Override
  public Flux<Data> fetchData(
    final UUID id,
    final Instant from,
    final Optional<Instant> to,
    final Resolution resolution
  ) {
    return ((SensorService) this.service).fetchData(id, from, to, resolution);
  }
}
