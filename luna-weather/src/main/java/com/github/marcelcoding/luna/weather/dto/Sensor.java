package com.github.marcelcoding.luna.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Sensor {

  private final UUID id;
  private final String name;
  private final String description;
  private final String unit;
  private final Illustration illustration;
  private final UUID group;

  public Sensor(
    @JsonProperty("name") final String name,
    @JsonProperty("description") final String description,
    @JsonProperty("unit") final String unit,
    @JsonProperty("illustration") final Illustration illustration,
    @JsonProperty("group") final UUID group
  ) {
    this.id = null;
    this.name = name;
    this.description = description;
    this.unit = unit;
    this.illustration = illustration;
    this.group = group;
  }

  public enum Illustration {
    @JsonProperty("LINE_CHART") LINE_CHART
  }
}
