package com.github.marcelcoding.luna.dvb.dto;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class RouteChange {

  private final String id;

  public static RouteChange of(final JsonNode data) {
    return new RouteChange(
      String.valueOf(data.get("Id").textValue())
    );
  }
}
