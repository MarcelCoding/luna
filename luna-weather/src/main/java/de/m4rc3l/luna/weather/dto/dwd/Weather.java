package de.m4rc3l.luna.weather.dto.dwd;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import lombok.Data;

@Data
public class Weather {

  private final OffsetDateTime timestamp;
  private final int sourceId;
  private final Float cloudCover;
  private final Condition condition;
  private final Float dewPoint;
  private final Icon icon;
  private final Float precipitation;
  private final Float pressureMsl;
  private final Float relativeHumidity;
  private final Float sunshine;
  private final Float temperature;
  private final Float visibility;
  private final Float windDirection;
  private final Float windSpeed;
  private final Float windGustDirection;
  private final Float windGustSpeed;
  // private final JsonNode fallbackSourceIds;

  @JsonCreator
  public Weather(
    @JsonProperty("timestamp") final OffsetDateTime timestamp,
    @JsonProperty("source_id") final int sourceId,
    @JsonProperty("cloud_cover") final Float cloudCover,
    @JsonProperty("condition") final Condition condition,
    @JsonProperty("dew_point") final Float dewPoint,
    @JsonProperty("icon") final Icon icon,
    @JsonProperty("precipitation") final Float precipitation,
    @JsonProperty("pressure_msl") final Float pressureMsl,
    @JsonProperty("relative_humidity") final Float relativeHumidity,
    @JsonProperty("sunshine") final Float sunshine,
    @JsonProperty("temperature") final Float temperature,
    @JsonProperty("visibility") final Float visibility,
    @JsonProperty("wind_direction") final Float windDirection,
    @JsonProperty("wind_speed") final Float windSpeed,
    @JsonProperty("wind_gust_direction") final Float windGustDirection,
    @JsonProperty("wind_gust_speed") final Float windGustSpeed
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

}
