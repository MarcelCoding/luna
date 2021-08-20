package com.github.marcelcoding.luna.weather.dto.dwd;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Source {

  private final int id;
  // private final String dwdStationId;
  // private final String wmoStationId;
  private final String stationName;
  private final ObservationType observationType;
  private final float lat;
  private final float lon;
  private final float height;
  private final float distance;

  @JsonCreator
  public Source(
    @JsonProperty("id") final int id,
    // @JsonProperty("dwd_station_id") final String dwdStationId,
    // @JsonProperty("wmo_station_id") final String wmoStationId,
    @JsonProperty("station_name") final String stationName,
    @JsonProperty("observation_type") final ObservationType observationType,
    @JsonProperty("lat") final float lat,
    @JsonProperty("lon") final float lon,
    @JsonProperty("height") final float height,
    @JsonProperty("distance") final float distance
  ) {
    this.id = id;
    // this.dwdStationId = dwdStationId;
    // this.wmoStationId = wmoStationId;
    this.stationName = stationName;
    this.observationType = observationType;
    this.lat = lat;
    this.lon = lon;
    this.height = height;
    this.distance = distance;
  }

  public enum ObservationType {
    @JsonProperty("forecast") FORECAST,
    @JsonProperty("synop") SYNOP,
    @JsonProperty("current") CURRENT,
    @JsonProperty("recent") RECENT,
    @JsonProperty("historical") HISTORICAL
  }
}
