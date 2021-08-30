package de.m4rc3l.luna.dvb.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.m4rc3l.luna.dvb.DvbUtils;
import java.time.OffsetDateTime;
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

  @JsonCreator
  public Departure(
    @JsonProperty("Id") final String id,
    @JsonProperty("LineName") final String line,
    @JsonProperty("Direction") final String direction,
    @JsonProperty("Platform") final Platform platform,
    @JsonProperty("Mot") final String mot,
    @JsonProperty("RealTime") final String realTime,
    @JsonProperty("ScheduledTime") final String scheduledTime,
    @JsonProperty("RouteChanges") final int[] routeChanges
  ) {
    this.id = id;
    this.line = line;
    this.direction = direction;
    this.platform = platform;
    this.mot = mot;
    this.realTime = DvbUtils.getDate(realTime);
    this.scheduledTime = DvbUtils.getDate(scheduledTime);
    this.routeChanges = routeChanges;
  }

  @Data
  private static class Platform {

    private final String name;
    private final String type;

    Platform(
      @JsonProperty("Name") final String name,
      @JsonProperty("Type") final String type
    ) {
      this.name = name;
      this.type = type;
    }
  }
}
