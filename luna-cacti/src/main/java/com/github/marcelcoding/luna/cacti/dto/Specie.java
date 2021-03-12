package com.github.marcelcoding.luna.cacti.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Specie {

  private final UUID id;
  @NotNull
  @NotBlank
  private final String name;
  @NotNull
  private final UUID genusId;

  public Specie(
    @JsonProperty("id") final UUID id,
    @JsonProperty("name") final String name,
    @JsonProperty("genusId") final UUID genusId
  ) {
    this.id = id;
    this.name = name;
    this.genusId = genusId;
  }
}
