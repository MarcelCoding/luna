package com.github.marcelcoding.luna.dwd.controller;

import com.github.marcelcoding.luna.dwd.dto.Source;
import com.github.marcelcoding.luna.dwd.dto.WeatherResponse;
import com.github.marcelcoding.luna.dwd.service.BrightSkyService;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import net.getnova.framework.api.annotation.GetEndpoint;
import net.getnova.framework.api.rest.annotation.RestApiController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestApiController("dwd/weather")
public class WeatherController {

  private final BrightSkyService brightSkyService;

  @GetEndpoint
  public Mono<WeatherResponse> weather() {
    return this.brightSkyService
      .weather(OffsetDateTime.now().minus(3, ChronoUnit.MONTHS), OffsetDateTime.now().plus(48, ChronoUnit.HOURS),
        51.0274574F, 13.6454864F);
  }

  @GetEndpoint("stations")
  public Flux<Source> stations() {
    return this.brightSkyService.findSources(51.0274574F, 13.6454864F, 10000);
  }
}
