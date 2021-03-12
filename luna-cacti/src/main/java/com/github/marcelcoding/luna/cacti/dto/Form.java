package com.github.marcelcoding.luna.cacti.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Form {

  private final UUID id;
  @NotNull
  @NotBlank
  private final String name;
  @NotNull
  private final UUID specieId;

  public Form(
    @JsonProperty("id") final UUID id,
    @JsonProperty("name") final String name,
    @JsonProperty("specieId") final UUID specieId
  ) {
    this.id = id;
    this.name = name;
    this.specieId = specieId;
  }
}
