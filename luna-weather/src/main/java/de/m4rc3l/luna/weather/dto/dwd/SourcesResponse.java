package de.m4rc3l.luna.weather.dto.dwd;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Data;

@Data
public class SourcesResponse {

  private final List<Source> sources;

  @JsonCreator
  public SourcesResponse(
    @JsonProperty("sources") final List<Source> sources
  ) {
    this.sources = sources;
  }
}
