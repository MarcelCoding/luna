package com.github.marcelcoding.luna.cacti.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.marcelcoding.luna.cacti.model.SpecieModel;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public final class Specie {

  private UUID id;
  @NotBlank
  private String name;
  @NotNull
  private UUID genusId;

  public Specie(
    @JsonProperty("name") final String name,
    @JsonProperty("genusId") final UUID genusId
  ) {
    this.name = name;
    this.genusId = genusId;
  }

  public Specie(final SpecieModel model) {
    this.id = model.getId();
    this.name = model.getName();
    this.genusId = model.getGenus().getId();
  }
}
