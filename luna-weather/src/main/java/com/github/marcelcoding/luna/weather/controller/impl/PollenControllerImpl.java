package com.github.marcelcoding.luna.weather.controller.impl;

import com.github.marcelcoding.luna.weather.controller.PollenController;
import com.github.marcelcoding.luna.weather.dto.dwd.Polle;
import com.github.marcelcoding.luna.weather.dto.dwd.PollenData;
import com.github.marcelcoding.luna.weather.dto.dwd.PollenRegion;
import com.github.marcelcoding.luna.weather.service.PollenService;
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
