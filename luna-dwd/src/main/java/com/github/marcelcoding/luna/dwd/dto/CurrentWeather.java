package com.github.marcelcoding.luna.dwd.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.time.OffsetDateTime;
import lombok.Data;

@Data
public class CurrentWeather {

  private final OffsetDateTime timestamp;
  private final int sourceId;
  private final float cloudCover;
  private final Condition condition;
  private final float dewPoint;
  private final Icon icon;
  private final float precipitation10;
  private final float precipitation30;
  private final float precipitation60;
  private final float pressureMsl;
  private final float relativeHumidity;
  private final float sunshine30;
  private final float sunshine60;
  private final float temperature;
  private final float visibility;
  private final float windDirection10;
  private final float windDirection30;
  private final float windDirection60;
  private final float windSpeed10;
  private final float windSpeed30;
  private final float windSpeed60;
  private final float windGustDirection10;
  private final float windGustDirection30;
  private final float windGustDirection60;
  private final float windGustSpeed10;
  private final float windGustSpeed30;
  private final float windGustSpeed60;
  private final JsonNode fallbackSourceIds;

  public CurrentWeather(
    @JsonProperty("timestamp") final OffsetDateTime timestamp,
    @JsonProperty("source_id") final int sourceId,
    @JsonProperty("cloud_cover") final float cloudCover,
    @JsonProperty("condition") final Condition condition,
    @JsonProperty("dew_point") final float dewPoint,
    @JsonProperty("icon") final Icon icon,
    @JsonProperty("precipitation_10") final float precipitation10,
    @JsonProperty("precipitation_30") final float precipitation30,
    @JsonProperty("precipitation_60") final float precipitation60,
    @JsonProperty("pressure_msl") final float pressureMsl,
    @JsonProperty("relative_humidity") final float relativeHumidity,
    @JsonProperty("sunshine_30") final float sunshine30,
    @JsonProperty("sunshine_60") final float sunshine60,
    @JsonProperty("temperature") final float temperature,
    @JsonProperty("visibility") final float visibility,
    @JsonProperty("wind_direction_10") final float windDirection10,
    @JsonProperty("wind_direction_30") final float windDirection30,
    @JsonProperty("wind_direction_60") final float windDirection60,
    @JsonProperty("wind_speed_10") final float windSpeed10,
    @JsonProperty("wind_speed_30") final float windSpeed30,
    @JsonProperty("wind_speed_60") final float windSpeed60,
    @JsonProperty("wind_gust_direction_10") final float windGustDirection10,
    @JsonProperty("wind_gust_direction_30") final float windGustDirection30,
    @JsonProperty("wind_gust_direction_60") final float windGustDirection60,
    @JsonProperty("wind_gust_speed_10") float windGustSpeed10,
    @JsonProperty("wind_gust_speed_30") float windGustSpeed30,
    @JsonProperty("wind_gust_speed_60") float windGustSpeed60,
    @JsonProperty("fallback_source_ids") final JsonNode fallbackSourceIds
  ) {
    this.timestamp = timestamp;
    this.sourceId = sourceId;
    this.cloudCover = cloudCover;
    this.condition = condition;
    this.dewPoint = dewPoint;
    this.icon = icon;
    this.precipitation10 = precipitation10;
    this.precipitation30 = precipitation30;
    this.precipitation60 = precipitation60;
    this.pressureMsl = pressureMsl;
    this.relativeHumidity = relativeHumidity;
    this.sunshine30 = sunshine30;
    this.sunshine60 = sunshine60;
    this.temperature = temperature;
    this.visibility = visibility;
    this.windDirection10 = windDirection10;
    this.windDirection30 = windDirection30;
    this.windDirection60 = windDirection60;
    this.windSpeed10 = windSpeed10;
    this.windSpeed30 = windSpeed30;
    this.windSpeed60 = windSpeed60;
    this.windGustDirection10 = windGustDirection10;
    this.windGustDirection30 = windGustDirection30;
    this.windGustDirection60 = windGustDirection60;
    this.windGustSpeed10 = windGustSpeed10;
    this.windGustSpeed30 = windGustSpeed30;
    this.windGustSpeed60 = windGustSpeed60;
    this.fallbackSourceIds = fallbackSourceIds;
  }

  public enum Condition {
    @JsonProperty("dry") DRY,
    @JsonProperty("fog") FOG,
    @JsonProperty("rain") RAIN,
    @JsonProperty("sleet") SLEET,
    @JsonProperty("snow") SNOW,
    @JsonProperty("hail") HAIL,
    @JsonProperty("thunderstorm") THUNDERSTORM
  }

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
}
