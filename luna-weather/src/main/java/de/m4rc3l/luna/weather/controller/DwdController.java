package de.m4rc3l.luna.weather.controller;

import de.m4rc3l.luna.weather.dto.dwd.CurrentWeatherResponse;
import de.m4rc3l.luna.weather.dto.dwd.WeatherResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.OffsetDateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;

@Tag(name = "DWD")
@RequestMapping("weather/dwd")
public interface DwdController {

  @GetMapping
  Mono<WeatherResponse> weather(
    @RequestParam("from") OffsetDateTime from,
    @RequestParam("to") OffsetDateTime to,
    @RequestParam("lat") float lat,
    @RequestParam("lon") float lon
  );

  @GetMapping("current")
  Mono<CurrentWeatherResponse> currentWeather(
    @RequestParam("lat") float lat,
    @RequestParam("lon") float lon
  );
}
