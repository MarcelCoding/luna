package de.m4rc3l.luna.weather.controller.impl;

import de.m4rc3l.luna.weather.controller.DwdController;
import de.m4rc3l.luna.weather.dto.dwd.CurrentWeatherResponse;
import de.m4rc3l.luna.weather.dto.dwd.WeatherResponse;
import de.m4rc3l.luna.weather.service.BrightSkyService;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class DwdControllerImpl implements DwdController {

  // ?lat=51.0274574F&lon=13.6454864F
  private final BrightSkyService brightSkyService;

  @Override
  @GetMapping
  public Mono<WeatherResponse> weather(
    final OffsetDateTime from,
    final OffsetDateTime to,
    final float lat,
    final float lon
  ) {
    return this.brightSkyService.weather(from, to, lat, lon);
  }

  @Override
  @GetMapping("current")
  public Mono<CurrentWeatherResponse> currentWeather(final float lat, final float lon) {
    return this.brightSkyService.weather(lat, lon);
  }
}
