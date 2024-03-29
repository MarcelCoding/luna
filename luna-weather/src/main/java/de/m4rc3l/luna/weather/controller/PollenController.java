package de.m4rc3l.luna.weather.controller;

import de.m4rc3l.luna.weather.dto.dwd.Polle;
import de.m4rc3l.luna.weather.dto.dwd.PollenData;
import de.m4rc3l.luna.weather.dto.dwd.PollenRegion;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Tag(name = "Pollen")
@RequestMapping("weather/dwd/pollen")
public interface PollenController {

  @GetMapping
  Mono<PollenData> status();

  @GetMapping("region")
  Flux<PollenRegion> findRegions();

  @GetMapping("region/{regionId}")
  Mono<Map<String, Polle>> findPollen(@PathVariable("regionId") short regionId);
}
