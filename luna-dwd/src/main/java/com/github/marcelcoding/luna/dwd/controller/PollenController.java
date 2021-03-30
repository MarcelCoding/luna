package com.github.marcelcoding.luna.dwd.controller;

import com.github.marcelcoding.luna.dwd.dto.Polle;
import com.github.marcelcoding.luna.dwd.dto.PollenData;
import com.github.marcelcoding.luna.dwd.dto.PollenRegion;
import com.github.marcelcoding.luna.dwd.service.PollenService;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("dwd/pollen")
public class PollenController {

  private final PollenService pollenService;

  @GetMapping
  public Mono<PollenData> status() {
    return this.pollenService.findData();
  }

  @GetMapping("regions")
  public Flux<PollenRegion> findRegions() {
    return this.pollenService.findRegions();
  }

  @GetMapping("regions/{regionId}")
  public Mono<Map<String, Polle>> findPollen(
    @PathVariable("regionId") final short regionId
  ) {
    return this.pollenService.findPollen(regionId)
      .map(Optional::orElseThrow);
  }
}
