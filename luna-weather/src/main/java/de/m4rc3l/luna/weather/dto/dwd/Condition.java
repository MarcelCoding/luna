package de.m4rc3l.luna.weather.dto.dwd;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Condition {
  @JsonProperty("dry") DRY,
  @JsonProperty("fog") FOG,
  @JsonProperty("rain") RAIN,
  @JsonProperty("sleet") SLEET,
  @JsonProperty("snow") SNOW,
  @JsonProperty("hail") HAIL,
  @JsonProperty("thunderstorm") THUNDERSTORM
}
