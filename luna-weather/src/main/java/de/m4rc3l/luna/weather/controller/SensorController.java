package de.m4rc3l.luna.weather.controller;

import de.m4rc3l.luna.weather.dto.Resolution;
import de.m4rc3l.luna.weather.dto.Sensor;
import de.m4rc3l.luna.weather.dto.Sensor.Data;
import de.m4rc3l.nova.core.controller.CrudController;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Tag(name = "Sensor")
@RequestMapping("/weather/sensor")
public interface SensorController extends CrudController<Sensor, UUID> {

  @PostMapping("{id}/data")
  Mono<Void> publishData(@PathVariable("id") UUID id, @RequestBody Data data);

  @GetMapping("{id}/data")
  Flux<Data> fetchData(
    @PathVariable("id") UUID id,
    @RequestParam("from") Instant from,
    @RequestParam("to") Optional<Instant> to,
    @RequestParam("resolution") Resolution resolution
  );
}
