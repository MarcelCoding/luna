package com.github.marcelcoding.luna.dwd.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.marcelcoding.luna.dwd.dto.CurrentWeatherResponse;
import com.github.marcelcoding.luna.dwd.dto.Source;
import com.github.marcelcoding.luna.dwd.dto.SourcesResponse;
import com.github.marcelcoding.luna.dwd.dto.WeatherResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.QueryStringEncoder;
import io.netty.util.AsciiString;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import net.getnova.framework.web.client.http.HttpClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.http.HttpResources;

@Service
public class BrightSkyService {

  private static final String BASE_URL = "https://api.brightsky.dev/";

  private final net.getnova.framework.web.client.http.HttpClient client;

  public BrightSkyService(final ObjectMapper objectMapper, final HttpResources resources) {
    this.client = new HttpClient(resources, BASE_URL, true, objectMapper,
      headers -> headers.add(HttpHeaderNames.ACCEPT, HttpHeaderValues.APPLICATION_JSON)
        .add(HttpHeaderNames.ACCEPT_CHARSET, AsciiString.of(StandardCharsets.UTF_8.toString()))
    );
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
    final QueryStringEncoder queryStringEncoder = new QueryStringEncoder("/weather");
    queryStringEncoder.addParam("date", from.format(DateTimeFormatter.ISO_DATE_TIME));
    queryStringEncoder.addParam("last_date", to.format(DateTimeFormatter.ISO_DATE_TIME));
    queryStringEncoder.addParam("lat", String.valueOf(lat));
    queryStringEncoder.addParam("lon", String.valueOf(lon));
    queryStringEncoder.addParam("units", "dwd");

    return this.client.get(queryStringEncoder.toString(), WeatherResponse.class);
  }

  /**
   * Retrieves the current weather at the given location.
   *
   * @param lat in degrees ({@code °})
   * @param lon in degrees ({@code °})
   * @return a {@link Mono} with a {@link CurrentWeatherResponse}
   */
  public Mono<CurrentWeatherResponse> weather(final float lat, final float lon) {
    final QueryStringEncoder queryStringEncoder = new QueryStringEncoder("/current_weather");
    queryStringEncoder.addParam("lat", String.valueOf(lat));
    queryStringEncoder.addParam("lon", String.valueOf(lon));
    queryStringEncoder.addParam("units", "dwd");

    return this.client.get(queryStringEncoder.toString(), CurrentWeatherResponse.class);
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

    return this.client.get(queryEncoder.toString(), SourcesResponse.class)
      .flatMapIterable(SourcesResponse::getSources);
  }
}
