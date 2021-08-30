package de.m4rc3l.luna.cacti.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.getnova.framework.core.Validatable;
import net.getnova.framework.core.exception.ValidationException;

@Data
@AllArgsConstructor
public final class Specie implements Validatable {

  private final UUID id;
  private final String name;
  private final UUID genusId;

  @JsonCreator
  public Specie(
    @JsonProperty("name") final String name,
    @JsonProperty("genusId") final UUID genusId
  ) {
    this.id = null;
    this.name = name;
    this.genusId = genusId;
  }

  @Override
  public void validate() throws ValidationException {
    if (this.name == null || this.name.isBlank()) {
      throw new ValidationException("name", "NO_BLANK");
    }

    if (this.genusId == null) {
      throw new ValidationException("genusId", "NOT_NULL");
    }
  }
}
