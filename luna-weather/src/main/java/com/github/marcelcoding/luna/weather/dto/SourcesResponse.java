package com.github.marcelcoding.luna.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class SourcesResponse {

  private final List<Source> sources;

  public SourcesResponse(
    @JsonProperty("sources") final List<Source> sources
  ) {
    this.sources = sources;
  }
}
