package com.github.marcelcoding.luna.dwd.controller;

import com.github.marcelcoding.luna.dwd.dto.CurrentWeatherResponse;
import com.github.marcelcoding.luna.dwd.dto.Source;
import com.github.marcelcoding.luna.dwd.dto.WeatherResponse;
import com.github.marcelcoding.luna.dwd.service.BrightSkyService;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import net.getnova.framework.api.annotation.GetEndpoint;
import net.getnova.framework.api.parameter.ApiQueryVariable;
import net.getnova.framework.api.rest.annotation.RestApiController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestApiController("dwd/weather")
public class WeatherController {

  // 51.0274574F, 13.6454864F
  private final BrightSkyService brightSkyService;

  @GetEndpoint
  public Mono<WeatherResponse> weather(
    @ApiQueryVariable("from") final OffsetDateTime from,
    @ApiQueryVariable("to") final OffsetDateTime to,
    @ApiQueryVariable("lat") final Float lat,
    @ApiQueryVariable("lon") final Float lon
  ) {
    return this.brightSkyService.weather(from, to, lat, lon);
  }

  @GetEndpoint("current")
  public Mono<CurrentWeatherResponse> currentWeather(
    @ApiQueryVariable("lat") final Float lat,
    @ApiQueryVariable("lon") final Float lon
  ) {
    return this.brightSkyService.weather(lat, lon);
  }

  @GetEndpoint("stations")
  public Flux<Source> stations(
    @ApiQueryVariable("lat") final Float lat,
    @ApiQueryVariable("lon") final Float lon,
    @ApiQueryVariable("radius") final Integer radius
  ) {
    return this.brightSkyService.sources(lat, lon, radius);
  }
}
