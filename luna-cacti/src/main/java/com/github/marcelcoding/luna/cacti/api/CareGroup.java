package com.github.marcelcoding.luna.cacti.api;

import lombok.Data;

@Data
public final class CareGroup {

  private final String id;
  private final String name;

  private final String home;
  private final String soil;

  private final Time growTime;
  private final Time restTime;

  @Data
  public static final class Time {

    private final String light;
    private final String air;
    private final String temperature;
    private final String humidity;
    private final String other;
  }
}
