package com.github.marcelcoding.luna.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SensorGroup {

  private final UUID id;
  private final String name;
  private final String description;

  public SensorGroup(
    @JsonProperty("name") final String name,
    @JsonProperty("description") final String description
  ) {
    this.id = null;
    this.name = name;
    this.description = description;
  }
}
