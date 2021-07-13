package com.github.marcelcoding.luna.weather.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.getnova.framework.core.Validatable;
import net.getnova.framework.core.exception.ValidationException;

@Data
@AllArgsConstructor
public class Sensor implements Validatable {

  private final UUID id;
  private final String name;
  private final String description;
  private final String unit;
  private final Illustration illustration;
  private final UUID groupId;

  public Sensor(
    @JsonProperty("name") final String name,
    @JsonProperty("description") final String description,
    @JsonProperty("unit") final String unit,
    @JsonProperty("illustration") final Illustration illustration,
    @JsonProperty("groupId") final UUID groupId
  ) {
    this.id = null;
    this.name = name;
    this.description = description;
    this.unit = unit;
    this.illustration = illustration;
    this.groupId = groupId;
  }

  @Override
  public void validate() throws ValidationException {
    if (this.name == null || this.name.isBlank()) {
      throw new ValidationException("name", "NOT_BLANK");
    }

    if (this.unit == null || this.unit.isBlank()) {
      throw new ValidationException("unit", "NOT_BLANK");
    }

    if (this.illustration == null) {
      throw new ValidationException("illustration", "NOT_BLANK");
    }
  }

  public enum Illustration {
    @JsonProperty("LINE_CHART") LINE_CHART
  }

  @lombok.Data
  public static final class Data implements Validatable {

    private final OffsetDateTime timestamp;
    private final double value;

    public Data(
      @JsonProperty("timestamp") final OffsetDateTime timestamp,
      @JsonProperty("value") final double value
    ) {
      this.timestamp = timestamp;
      this.value = value;
    }

    @Override
    public void validate() throws ValidationException {
      if (this.timestamp == null) {
        throw new ValidationException("timestamp", "NOT_NULL");
      }
    }
  }
}
