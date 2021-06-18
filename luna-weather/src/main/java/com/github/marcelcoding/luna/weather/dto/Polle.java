package com.github.marcelcoding.luna.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.marcelcoding.luna.weather.DwdUtils;
import lombok.Data;

@Data
public class Polle {

  private final byte today;
  private final byte tomorrow;
  private final byte dayAfterTomorrow;

  public Polle(
    @JsonProperty("today") final String today,
    @JsonProperty("tomorrow") final String tomorrow,
    @JsonProperty("dayafter_to") final String dayAfterTomorrow
  ) {
    this.today = DwdUtils.convertPollenStrength(today);
    this.tomorrow = DwdUtils.convertPollenStrength(tomorrow);
    this.dayAfterTomorrow = DwdUtils.convertPollenStrength(dayAfterTomorrow);
  }
}
