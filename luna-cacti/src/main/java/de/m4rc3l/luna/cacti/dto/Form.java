package de.m4rc3l.luna.cacti.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import de.m4rc3l.nova.core.Validatable;
import de.m4rc3l.nova.core.exception.ValidationException;

@Data
@AllArgsConstructor
public final class Form implements Validatable {

  private final UUID id;
  private final String name;
  private final UUID specieId;

  @JsonCreator
  public Form(
    @JsonProperty("name") final String name,
    @JsonProperty("specieId") final UUID specieId
  ) {
    this.id = null;
    this.name = name;
    this.specieId = specieId;
  }

  @Override
  public void validate() throws ValidationException {
    if (this.name == null || this.name.isBlank()) {
      throw new ValidationException("name", "NO_BLANK");
    }

    if (this.specieId == null) {
      throw new ValidationException("specieId", "NOT_NULL");
    }
  }
}
