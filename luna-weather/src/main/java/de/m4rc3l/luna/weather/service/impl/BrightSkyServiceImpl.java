package de.m4rc3l.luna.weather.service.impl;

import de.m4rc3l.luna.weather.Utils;
import de.m4rc3l.luna.weather.dto.dwd.CurrentWeatherResponse;
import de.m4rc3l.luna.weather.dto.dwd.WeatherResponse;
import de.m4rc3l.luna.weather.service.BrightSkyService;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Service
public class BrightSkyServiceImpl implements BrightSkyService {

  private static final String BASE_URL = "https://api.brightsky.dev/";
  private final WebClient client;

  public BrightSkyServiceImpl() {
    this.client = WebClient.create(BASE_URL);
  }

  @Override
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

  @Override
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
}
