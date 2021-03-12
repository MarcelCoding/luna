package com.github.marcelcoding.luna.cacti.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Genus {

  private final UUID id;
  @NotNull
  @NotBlank
  private final String name;

  public Genus(
    @JsonProperty("id") final UUID id,
    @JsonProperty("name") final String name
  ) {
    this.id = id;
    this.name = name;
  }
}
