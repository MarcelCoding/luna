package com.github.marcelcoding.luna.cacti.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.getnova.framework.core.Validatable;
import net.getnova.framework.core.exception.ValidationException;

@Data
@AllArgsConstructor
public final class Genus implements Validatable {

  private final UUID id;
  private final String name;

  public Genus(
    @JsonProperty("name") final String name
  ) {
    this.id = null;
    this.name = name;
  }

  @Override
  public void validate() throws ValidationException {
    if (this.name == null || this.name.isBlank()) {
      throw new ValidationException("name", "NO_BLANK");
    }
  }
}
