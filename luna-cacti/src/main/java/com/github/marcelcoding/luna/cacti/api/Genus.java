package com.github.marcelcoding.luna.cacti.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class Genus {

  private final UUID id;
  @NotBlank
  private final String name;

  public Genus(
    @JsonProperty("name") final String name
  ) {
    this.id = null;
    this.name = name;
  }
}
