package com.github.marcelcoding.luna.dvb.dto;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.marcelcoding.luna.dvb.DvbUtils;
import java.time.OffsetDateTime;
import java.util.Optional;
import lombok.Data;

@Data
public class Departure {

  private final String id;
  private final String line;
  private final String direction;
  private final Platform platform;
  private final String mot;
  private final OffsetDateTime realTime;
  private final OffsetDateTime scheduledTime;
  private final int[] routeChanges;

  public static Departure of(final JsonNode data) {
    final JsonNode jsonRouteChanges = data.get("RouteChanges");
    final int[] routeChange = new int[jsonRouteChanges.size()];

    int i = 0;
    for (final JsonNode jsonRouteChange : jsonRouteChanges) {
      routeChange[i] = jsonRouteChange.asInt();
      i++;
    }

    return new Departure(
      data.get("Id").asText(),
      data.get("LineName").asText(),
      data.get("Direction").asText(),
      Platform.of(data.get("Platform")),
      data.get("Mot").asText(),
      DvbUtils.getDate(Optional.ofNullable(data.get("RealTime")).map(JsonNode::asText).orElse(null)),
      DvbUtils.getDate(data.get("ScheduledTime").asText()),
      routeChange
    );
  }

  @Data
  private static class Platform {

    private final String name;
    private final String type;

    public static Platform of(final JsonNode data) {
      return new Platform(
        data.get("Name").asText(),
        data.get("Type").asText()
      );
    }
  }
}
