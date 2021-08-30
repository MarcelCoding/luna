package de.m4rc3l.luna.weather.controller.impl;

import de.m4rc3l.luna.weather.controller.PollenController;
import de.m4rc3l.luna.weather.dto.dwd.Polle;
import de.m4rc3l.luna.weather.dto.dwd.PollenData;
import de.m4rc3l.luna.weather.dto.dwd.PollenRegion;
import de.m4rc3l.luna.weather.service.impl.PollenService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class PollenControllerImpl implements PollenController {

  private final PollenService pollenService;

  @Override
  public Mono<PollenData> status() {
    return this.pollenService.findData();
  }

  @Override
  public Flux<PollenRegion> findRegions() {
    return this.pollenService.findRegions();
  }

  @Override
  public Mono<Map<String, Polle>> findPollen(final short regionId) {
    return this.pollenService.findPollen(regionId);
  }
}
