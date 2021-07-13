package com.github.marcelcoding.luna.dvb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RouteChange {

  private final String id;

  public RouteChange(
    @JsonProperty("Id") final String id
  ) {
    this.id = id;
  }
}
