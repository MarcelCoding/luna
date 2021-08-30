package de.m4rc3l.luna.weather.service.impl;

import de.m4rc3l.luna.weather.Utils;
import de.m4rc3l.luna.weather.dto.dwd.CurrentWeatherResponse;
import de.m4rc3l.luna.weather.dto.dwd.Source;
import de.m4rc3l.luna.weather.dto.dwd.SourcesResponse;
import de.m4rc3l.luna.weather.dto.dwd.WeatherResponse;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BrightSkyService {

  private static final String BASE_URL = "https://api.brightsky.dev/";
  private final WebClient client;

  public BrightSkyService() {
    this.client = WebClient.create(BASE_URL);
  }

  /**
   * Retrieves the weather in the given time span at the given location.
   *
   * @param from start {@link OffsetDateTime}
   * @param to   end {@link OffsetDateTime}
   * @param lat  in degrees ({@code °})
   * @param lon  in degrees ({@code °})
   * @return a {@link Mono} with a {@link WeatherResponse}
   */
  public Mono<WeatherResponse> weather(
    final OffsetDateTime from,
    final OffsetDateTime to,
    final float lat,
    final float lon
  ) {
    return this.client.get()
      .uri(builder ->
        builder.path("/weather")
          .queryParam("date", from.format(DateTimeFormatter.ISO_DATE_TIME))
          .queryParam("last_date", to.format(DateTimeFormatter.ISO_DATE_TIME))
          .queryParam("lat", lat)
          .queryParam("lon", lon)
          .queryParam("tz", "Etc/UTC")
          .queryParam("units", "dwd")
          .build()
      )
      .exchangeToMono(response -> Utils.handleError(response, WeatherResponse.class));
  }

  /**
   * Retrieves the current weather at the given location.
   *
   * @param lat in degrees ({@code °})
   * @param lon in degrees ({@code °})
   * @return a {@link Mono} with a {@link CurrentWeatherResponse}
   */
  public Mono<CurrentWeatherResponse> weather(final float lat, final float lon) {
    return this.client.get()
      .uri(builder ->
        builder.path("/current_weather")
          .queryParam("lat", lat)
          .queryParam("lon", lon)
          .queryParam("tz", "Etc/UTC")
          .queryParam("units", "dwd")
          .build()
      )
      .exchangeToMono(response -> Utils.handleError(response, CurrentWeatherResponse.class));
  }

  /**
   * Searches in a specified location for {@link Source}, the sources are ordered by distance.
   *
   * @param lat    in degrees ({@code °})
   * @param lon    in degrees ({@code °})
   * @param radius is meters ({@code m})
   * @return a {@link Flux} with all matching {@link Source}s
   */
  public Flux<Source> sources(final float lat, final float lon, final int radius) {
    return this.client.get()
      .uri(builder ->
        builder.path("/sources")
          .queryParam("lat", lat)
          .queryParam("lon", lon)
          .queryParam("max_dist", radius)
          .queryParam("units", "dwd")
          .build()
      )
      .exchangeToMono(response -> Utils.handleError(response, SourcesResponse.class))
      .flatMapIterable(SourcesResponse::getSources);
  }
}
