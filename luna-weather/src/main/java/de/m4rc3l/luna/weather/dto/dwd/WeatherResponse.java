package de.m4rc3l.luna.weather.dto.dwd;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Set;
import lombok.Data;

@Data
public class WeatherResponse {

  private final List<Weather> weather;
  private final Set<Source> sources;

  @JsonCreator
  public WeatherResponse(
    @JsonProperty("weather") final List<Weather> weather,
    @JsonProperty("sources") final Set<Source> sources
  ) {
    this.weather = weather;
    this.sources = sources;
  }
}
