package com.github.marcelcoding.luna.cacti.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public final class CareGroup {

  private final String id;
  private final String name;

  private final String home;
  private final String soil;

  private final Time growTime;
  private final Time restTime;

  public CareGroup(
    @JsonProperty("id") final String id,
    @JsonProperty("name") final String name,
    @JsonProperty("home") final String home,
    @JsonProperty("soil") final String soil,
    @JsonProperty("growTime") final Time growTime,
    @JsonProperty("restTime") final Time restTime
  ) {
    this.id = id;
    this.name = name;
    this.home = home;
    this.soil = soil;
    this.growTime = growTime;
    this.restTime = restTime;
  }

  @Data
  public static final class Time {

    private final String light;
    private final String air;
    private final String temperature;
    private final String humidity;
    private final String other;

    public Time(
      @JsonProperty("light") final String light,
      @JsonProperty("air") final String air,
      @JsonProperty("temperature") final String temperature,
      @JsonProperty("humidity") final String humidity,
      @JsonProperty("other") final String other
    ) {
      this.light = light;
      this.air = air;
      this.temperature = temperature;
      this.humidity = humidity;
      this.other = other;
    }
  }
}
