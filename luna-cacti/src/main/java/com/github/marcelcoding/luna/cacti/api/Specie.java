package com.github.marcelcoding.luna.cacti.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class Specie {

  private final UUID id;
  @NotBlank
  private final String name;
  @NotNull
  private final UUID genusId;

  public Specie(
    @JsonProperty("name") final String name,
    @JsonProperty("genusId") final UUID genusId
  ) {
    this.id = null;
    this.name = name;
    this.genusId = genusId;
  }
}
