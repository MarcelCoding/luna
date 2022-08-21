package de.m4rc3l.luna.weather.dto.dwd;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import lombok.Data;

@Data
public class CurrentWeather {

  private final OffsetDateTime timestamp;
  private final int sourceId;
  private final Float cloudCover;
  private final Condition condition;
  private final Float dewPoint;
  private final Icon icon;
  private final Float precipitation10;
  private final Float precipitation30;
  private final Float precipitation60;
  private final Float pressureMsl;
  private final Float relativeHumidity;
  private final Float sunshine30;
  private final Float sunshine60;
  private final Float temperature;
  private final Float visibility;
  private final Float windDirection10;
  private final Float windDirection30;
  private final Float windDirection60;
  private final Float windSpeed10;
  private final Float windSpeed30;
  private final Float windSpeed60;
  private final Float windGustDirection10;
  private final Float windGustDirection30;
  private final Float windGustDirection60;
  private final Float windGustSpeed10;
  private final Float windGustSpeed30;
  private final Float windGustSpeed60;
  // private final JsonNode fallbackSourceIds;

  @JsonCreator
  public CurrentWeather(
    @JsonProperty("timestamp") final OffsetDateTime timestamp,
    @JsonProperty("source_id") final int sourceId,
    @JsonProperty("cloud_cover") final Float cloudCover,
    @JsonProperty("condition") final Condition condition,
    @JsonProperty("dew_point") final Float dewPoint,
    @JsonProperty("icon") final Icon icon,
    @JsonProperty("precipitation_10") final Float precipitation10,
    @JsonProperty("precipitation_30") final Float precipitation30,
    @JsonProperty("precipitation_60") final Float precipitation60,
    @JsonProperty("pressure_msl") final Float pressureMsl,
    @JsonProperty("relative_humidity") final Float relativeHumidity,
    @JsonProperty("sunshine_30") final Float sunshine30,
    @JsonProperty("sunshine_60") final Float sunshine60,
    @JsonProperty("temperature") final Float temperature,
    @JsonProperty("visibility") final Float visibility,
    @JsonProperty("wind_direction_10") final Float windDirection10,
    @JsonProperty("wind_direction_30") final Float windDirection30,
    @JsonProperty("wind_direction_60") final Float windDirection60,
    @JsonProperty("wind_speed_10") final Float windSpeed10,
    @JsonProperty("wind_speed_30") final Float windSpeed30,
    @JsonProperty("wind_speed_60") final Float windSpeed60,
    @JsonProperty("wind_gust_direction_10") final Float windGustDirection10,
    @JsonProperty("wind_gust_direction_30") final Float windGustDirection30,
    @JsonProperty("wind_gust_direction_60") final Float windGustDirection60,
    @JsonProperty("wind_gust_speed_10") final Float windGustSpeed10,
    @JsonProperty("wind_gust_speed_30") final Float windGustSpeed30,
    @JsonProperty("wind_gust_speed_60") final Float windGustSpeed60
    // @JsonProperty("fallback_source_ids") final JsonNode fallbackSourceIds
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
    // this.fallbackSourceIds = fallbackSourceIds;
  }
}
