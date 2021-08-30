package de.m4rc3l.luna.weather.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.getnova.framework.core.Validatable;
import net.getnova.framework.core.exception.ValidationException;

@Data
@AllArgsConstructor
public class SensorGroup implements Validatable {

  private final UUID id;
  private final String name;
  private final String description;

  @JsonCreator
  public SensorGroup(
    @JsonProperty("name") final String name,
    @JsonProperty("description") final String description
  ) {
    this.id = null;
    this.name = name;
    this.description = description;
  }

  @Override
  public void validate() throws ValidationException {
    if (this.name == null || this.name.isBlank()) {
      throw new ValidationException("name", "NOT_BLANK");
    }
  }
}
