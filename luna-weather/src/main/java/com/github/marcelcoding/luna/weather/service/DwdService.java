package com.github.marcelcoding.luna.weather.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class DwdService {

  private static final String BASE_URL = "https://opendata.dwd.de/";
  @Getter
  private final WebClient client;

  public DwdService() {
    this.client = WebClient.create(BASE_URL);
  }
}
