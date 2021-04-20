package com.github.marcelcoding.luna.cacti.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.marcelcoding.luna.cacti.model.CactusModel.CareGroupModel;
import com.github.marcelcoding.luna.cacti.model.CactusModel.CareGroupModel.TimeModel;
import lombok.Data;

@Data
public final class CareGroup {

  private final String id;
  private final String name;

  private final String home;
  private final String soil;

  private final Time growTime;
  private final Time restTime;

  public CareGroup() {
    this.id = null;
    this.name = null;

    this.home = null;
    this.soil = null;

    this.growTime = new Time();
    this.restTime = new Time();
  }

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

  public CareGroup(final CareGroupModel model) {
    this.id = model.getId();
    this.name = null;
    this.home = model.getHome();
    this.soil = model.getSoil();
    this.growTime = model.getGrowTime() == null ? new Time() : new Time(model.getGrowTime());
    this.restTime = model.getRestTime() == null ? new Time() : new Time(model.getRestTime());
  }

  public CareGroup merge(final CareGroup other) {
    return new CareGroup(
      this.id == null ? other.getId() : this.id,
      this.name == null ? other.getName() : this.name,
      this.home == null ? other.getHome() : this.home,
      this.soil == null ? other.getSoil() : this.soil,
      this.growTime.merge(other.getGrowTime()),
      this.restTime.merge(other.getRestTime())
    );
  }

  @Data
  public static final class Time {

    private final String light;
    private final String air;
    private final String temperature;
    private final String humidity;
    private final String other;

    public Time() {
      this.light = null;
      this.air = null;
      this.temperature = null;
      this.humidity = null;
      this.other = null;
    }

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

    public Time(final TimeModel model) {
      this.light = model.getLight();
      this.air = model.getAir();
      this.temperature = model.getTemperature();
      this.humidity = model.getHumidity();
      this.other = model.getOther();
    }

    public Time merge(final Time other) {
      return new Time(
        this.light == null ? other.getLight() : this.light,
        this.air == null ? other.getAir() : this.getAir(),
        this.temperature == null ? other.getTemperature() : this.getTemperature(),
        this.humidity == null ? other.getHumidity() : this.getHumidity(),
        this.other == null ? other.getOther() : this.getOther()
      );
    }
  }
}
