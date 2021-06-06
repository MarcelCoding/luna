package com.github.marcelcoding.luna.weather.service;

import com.github.marcelcoding.luna.weather.Utils;
import com.github.marcelcoding.luna.weather.dto.CurrentWeatherResponse;
import com.github.marcelcoding.luna.weather.dto.Source;
import com.github.marcelcoding.luna.weather.dto.SourcesResponse;
import com.github.marcelcoding.luna.weather.dto.WeatherResponse;
import io.netty.handler.codec.http.QueryStringEncoder;
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
    final QueryStringEncoder queryEncoder = new QueryStringEncoder("/weather");
    queryEncoder.addParam("date", from.format(DateTimeFormatter.ISO_DATE_TIME));
    queryEncoder.addParam("last_date", to.format(DateTimeFormatter.ISO_DATE_TIME));
    queryEncoder.addParam("lat", String.valueOf(lat));
    queryEncoder.addParam("lon", String.valueOf(lon));
    queryEncoder.addParam("units", "dwd");

    return this.client.get()
      .uri(queryEncoder.toString())
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
    final QueryStringEncoder queryEncoder = new QueryStringEncoder("/current_weather");
    queryEncoder.addParam("lat", String.valueOf(lat));
    queryEncoder.addParam("lon", String.valueOf(lon));
    queryEncoder.addParam("units", "dwd");

    return this.client.get()
      .uri(queryEncoder.toString())
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
    final QueryStringEncoder queryEncoder = new QueryStringEncoder("/sources");
    queryEncoder.addParam("lat", String.valueOf(lat));
    queryEncoder.addParam("lon", String.valueOf(lon));
    queryEncoder.addParam("max_dist", String.valueOf(radius));
    queryEncoder.addParam("units", "dwd");

    return this.client.get()
      .uri(queryEncoder.toString())
      .exchangeToMono(response -> Utils.handleError(response, SourcesResponse.class))
      .flatMapIterable(SourcesResponse::getSources);
  }
}
