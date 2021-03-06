package com.github.marcelcoding.luna.weather.controller;

import com.github.marcelcoding.luna.weather.dto.dwd.CurrentWeatherResponse;
import com.github.marcelcoding.luna.weather.dto.dwd.Source;
import com.github.marcelcoding.luna.weather.dto.dwd.WeatherResponse;
import com.github.marcelcoding.luna.weather.service.impl.BrightSkyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Tag(name = "DWD")
@RestController
@RequiredArgsConstructor
@RequestMapping("weather/dwd")
public class DwdController {

  // ?lat=51.0274574F&lon=13.6454864F
  private final BrightSkyService brightSkyService;

  @GetMapping
  public Mono<WeatherResponse> weather(
    @RequestParam("from") final OffsetDateTime from,
    @RequestParam("to") final OffsetDateTime to,
    @RequestParam("lat") final float lat,
    @RequestParam("lon") final float lon
  ) {
    return this.brightSkyService.weather(from, to, lat, lon);
  }

  @GetMapping("current")
  public Mono<CurrentWeatherResponse> currentWeather(
    @RequestParam("lat") final float lat,
    @RequestParam("lon") final float lon
  ) {
    return this.brightSkyService.weather(lat, lon);
  }

  @GetMapping("stations")
  public Flux<Source> stations(
    @RequestParam("lat") final float lat,
    @RequestParam("lon") final float lon,
    @RequestParam("radius") final int radius
  ) {
    return this.brightSkyService.sources(lat, lon, radius);
  }
}
