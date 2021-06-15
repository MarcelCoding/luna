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
  private final String unit;
  private final UUID group;

  public Sensor(
    @JsonProperty("name") final String name,
    @JsonProperty("unit") final String unit,
    @JsonProperty("group") final UUID group
  ) {
    this.id = null;
    this.name = name;
    this.unit = unit;
    this.group = group;
  }
}
