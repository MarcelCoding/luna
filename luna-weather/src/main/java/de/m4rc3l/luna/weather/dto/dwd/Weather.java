package de.m4rc3l.luna.weather.dto.dwd;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import lombok.Data;

@Data
public class Weather {

  private final OffsetDateTime timestamp;
  private final int sourceId;
  private final float cloudCover;
  private final Condition condition;
  private final float dewPoint;
  private final Icon icon;
  private final float precipitation;
  private final float pressureMsl;
  private final float relativeHumidity;
  private final float sunshine;
  private final float temperature;
  private final float visibility;
  private final float windDirection;
  private final float windSpeed;
  private final float windGustDirection;
  private final float windGustSpeed;
  // private final JsonNode fallbackSourceIds;

  @JsonCreator
  public Weather(
    @JsonProperty("timestamp") final OffsetDateTime timestamp,
    @JsonProperty("source_id") final int sourceId,
    @JsonProperty("cloud_cover") final float cloudCover,
    @JsonProperty("condition") final Condition condition,
    @JsonProperty("dew_point") final float dewPoint,
    @JsonProperty("icon") final Icon icon,
    @JsonProperty("precipitation") final float precipitation,
    @JsonProperty("pressure_msl") final float pressureMsl,
    @JsonProperty("relative_humidity") final float relativeHumidity,
    @JsonProperty("sunshine") final float sunshine,
    @JsonProperty("temperature") final float temperature,
    @JsonProperty("visibility") final float visibility,
    @JsonProperty("wind_direction") final float windDirection,
    @JsonProperty("wind_speed") final float windSpeed,
    @JsonProperty("wind_gust_direction") final float windGustDirection,
    @JsonProperty("wind_gust_speed") final float windGustSpeed
    // @JsonProperty("fallback_source_ids") final JsonNode fallbackSourceIds
  ) {
    this.timestamp = timestamp;
    this.sourceId = sourceId;
    this.cloudCover = cloudCover;
    this.condition = condition;
    this.dewPoint = dewPoint;
    this.icon = icon;
    this.precipitation = precipitation;
    this.pressureMsl = pressureMsl;
    this.relativeHumidity = relativeHumidity;
    this.sunshine = sunshine;
    this.temperature = temperature;
    this.visibility = visibility;
    this.windDirection = windDirection;
    this.windSpeed = windSpeed;
    this.windGustDirection = windGustDirection;
    this.windGustSpeed = windGustSpeed;
    // this.fallbackSourceIds = fallbackSourceIds;
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
