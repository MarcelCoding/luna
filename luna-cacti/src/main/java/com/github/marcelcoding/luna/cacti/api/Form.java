package com.github.marcelcoding.luna.cacti.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.marcelcoding.luna.cacti.model.FormModel;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public final class Form {

  private UUID id;
  @NotBlank
  private String name;
  @NotNull
  private UUID specieId;

  public Form(
    @JsonProperty("name") final String name,
    @JsonProperty("specieId") final UUID specieId
  ) {
    this.name = name;
    this.specieId = specieId;
  }

  public Form(final FormModel model) {
    this.id = model.getId();
    this.name = model.getName();
    this.specieId = model.getSpecie().getId();
  }
}
