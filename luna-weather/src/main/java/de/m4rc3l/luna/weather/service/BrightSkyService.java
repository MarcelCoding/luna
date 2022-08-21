package de.m4rc3l.luna.weather.service;

import de.m4rc3l.luna.weather.dto.dwd.CurrentWeatherResponse;
import de.m4rc3l.luna.weather.dto.dwd.WeatherResponse;
import java.time.OffsetDateTime;
import reactor.core.publisher.Mono;

/**
 * BrightSky Java Api v1.0.18
 *
 * @see <a href="https://brightsky.dev/docs/">BrightSky Api Docs</a>
 */
public interface BrightSkyService {

  /**
   * Retrieves the weather in the given time span at the given location.
   *
   * @param from start {@link OffsetDateTime}
   * @param to   end {@link OffsetDateTime}
   * @param lat  in degrees ({@code °})
   * @param lon  in degrees ({@code °})
   * @return a {@link Mono} with a {@link WeatherResponse}
   */
  Mono<WeatherResponse> weather(OffsetDateTime from, OffsetDateTime to, float lat, float lon);

  /**
   * Retrieves the current weather at the given location.
   *
   * @param lat in degrees ({@code °})
   * @param lon in degrees ({@code °})
   * @return a {@link Mono} with a {@link CurrentWeatherResponse}
   */
  Mono<CurrentWeatherResponse> weather(float lat, float lon);
}
