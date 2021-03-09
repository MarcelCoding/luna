package com.github.marcelcoding.luna.dwd.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.Data;

@Data
public class PollenRegion {

  private final short id;
  private final String name;

  private final short subId;
  private final String subName;

  @JsonIgnore
  private final Map<String, Polle> pollen;

  public PollenRegion(
    @JsonProperty("region_id") final short id,
    @JsonProperty("region_name") final String name,
    @JsonProperty("partregion_id") final short subId,
    @JsonProperty("partregion_name") final String subName,
    @JsonProperty("Pollen") final Map<String, Polle> pollen
  ) {
    this.id = id;
    this.name = name;
    this.subId = subId;
    this.subName = subName;
    this.pollen = pollen;
  }

  public short getRegionId() {
    return this.getSubId() == -1
      ? this.getId()
      : this.getSubId();
  }
}
