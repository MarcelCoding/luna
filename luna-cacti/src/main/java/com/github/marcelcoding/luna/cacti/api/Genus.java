package com.github.marcelcoding.luna.cacti.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.marcelcoding.luna.cacti.model.GenusModel;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public final class Genus {

  private UUID id;
  @NotBlank
  private String name;

  public Genus(
    @JsonProperty("name") final String name
  ) {
    this.name = name;
  }

  public Genus(final GenusModel model) {
    this.id = model.getId();
    this.name = model.getName();
  }
}
