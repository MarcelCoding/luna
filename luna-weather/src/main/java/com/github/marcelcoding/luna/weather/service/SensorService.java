package com.github.marcelcoding.luna.weather.service;

import com.github.marcelcoding.luna.weather.dto.Resolution;
import com.github.marcelcoding.luna.weather.dto.Sensor;
import com.github.marcelcoding.luna.weather.dto.Sensor.Data;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import net.getnova.framework.core.service.CrudService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SensorService extends CrudService<Sensor, UUID> {

  Mono<Void> publishData(UUID id, Data value);

  Flux<Data> fetchData(UUID id, Instant from, Optional<Instant> to, Resolution resolution);
}
