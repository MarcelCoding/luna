package de.m4rc3l.luna.weather.dto.dwd;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import lombok.Data;

@Data
public class PollenRegion {

  private final short id;
  private final String name;
  private final String subName;

  @JsonIgnore
  private final Map<String, Polle> pollen;

  @JsonCreator
  public PollenRegion(
    @JsonProperty("region_id") final short id,
    @JsonProperty("region_name") final String name,
    @JsonProperty("partregion_id") final short subId,
    @JsonProperty("partregion_name") final String subName,
    @JsonProperty("Pollen") final Map<String, Polle> pollen
  ) {
    this.id = subId == -1 ? id : subId;
    this.name = name.strip();
    this.subName = subName.isEmpty() ? null : subName.strip();
    this.pollen = pollen;
  }
}
