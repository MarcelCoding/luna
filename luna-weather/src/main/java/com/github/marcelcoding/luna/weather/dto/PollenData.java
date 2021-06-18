package com.github.marcelcoding.luna.weather.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.marcelcoding.luna.weather.DwdUtils;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import lombok.Data;

@Data
public class PollenData {

  private final String name;
  private final String sender;
  private final OffsetDateTime lastUpdate;
  private final OffsetDateTime nextUpdate;

  @JsonIgnore
  private final Set<PollenRegion> regions;

  public PollenData(
    @JsonProperty("name") final String name,
    @JsonProperty("sender") final String sender,
    @JsonProperty("last_update") final String lastUpdate,
    @JsonProperty("next_update") final String nextUpdate,
    @JsonProperty("content") final Set<PollenRegion> regions
  ) {
    this.name = name;
    this.lastUpdate = DwdUtils.parse(lastUpdate);
    this.sender = sender;
    this.nextUpdate = DwdUtils.parse(nextUpdate).plus(5, ChronoUnit.MINUTES);
    this.regions = regions;
  }
}
