package de.m4rc3l.luna.weather.service;

import de.m4rc3l.luna.weather.dto.Resolution;
import de.m4rc3l.luna.weather.dto.Sensor;
import de.m4rc3l.luna.weather.dto.Sensor.Data;
import de.m4rc3l.nova.core.service.CrudService;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SensorService extends CrudService<Sensor, UUID> {

  Mono<Void> publishData(UUID id, Data value);

  Flux<Data> fetchData(UUID id, Instant from, Optional<Instant> to, Resolution resolution);
}
