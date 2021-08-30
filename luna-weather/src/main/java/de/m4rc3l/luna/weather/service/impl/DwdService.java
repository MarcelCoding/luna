package de.m4rc3l.luna.weather.service.impl;

import de.m4rc3l.luna.weather.Utils;
import de.m4rc3l.luna.weather.dto.dwd.PollenData;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class DwdService {

  private static final String BASE_URL = "https://opendata.dwd.de/";
  private static final String POLLEN_URI = "/climate_environment/health/alerts/s31fg.json";

  private final WebClient client;

  public DwdService() {
    this.client = WebClient.create(BASE_URL);
  }

  public Mono<PollenData> fetchPollen() {
    return this.client.get()
      .uri(POLLEN_URI)
      .exchangeToMono(response -> Utils.handleError(response, PollenData.class));
  }
}
