package com.github.marcelcoding.luna.weather.dto.dwd;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;
import lombok.Data;

@Data
public class CurrentWeatherResponse {

  private final CurrentWeather weather;
  private final Set<Source> sources;

  public CurrentWeatherResponse(
    @JsonProperty("weather") final CurrentWeather weather,
    @JsonProperty("sources") final Set<Source> sources
  ) {
    this.weather = weather;
    this.sources = sources;
  }
}
