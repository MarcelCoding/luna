package com.github.marcelcoding.luna.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Polle {

  private final String today;
  private final String tomorrow;
  private final String dayAfterTomorrow;

  public Polle(
    @JsonProperty("today") final String today,
    @JsonProperty("tomorrow") final String tomorrow,
    @JsonProperty("dayafter_to") final String dayAfterTomorrow
  ) {
    this.today = today;
    this.tomorrow = tomorrow;
    this.dayAfterTomorrow = dayAfterTomorrow.equals("-1") ? null : dayAfterTomorrow;
  }
}
