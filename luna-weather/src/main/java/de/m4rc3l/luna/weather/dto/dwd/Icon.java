package de.m4rc3l.luna.weather.dto.dwd;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Icon {
  @JsonProperty("clear-day") CLEAR_DAY,
  @JsonProperty("clear-night") CLEAR_NIGHT,
  @JsonProperty("partly-cloudy-day") PARTY_CLOUDY_DAY,
  @JsonProperty("partly-cloudy-night") PARTY_CLOUDY_NIGHT,
  @JsonProperty("cloudy") CLOUDY,
  @JsonProperty("fog") FOG,
  @JsonProperty("wind") WIND,
  @JsonProperty("rain") RAIN,
  @JsonProperty("sleet") SLEET,
  @JsonProperty("snow") SNOW,
  @JsonProperty("hail") HAIL,
  @JsonProperty("thunderstorm") THUNDERSTORM
}
