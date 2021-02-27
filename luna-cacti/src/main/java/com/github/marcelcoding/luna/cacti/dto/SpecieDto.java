package com.github.marcelcoding.luna.cacti.dto;

import java.util.UUID;
import lombok.Data;

@Data
public class SpecieDto {

  private final UUID id;
  private final String name;
  private final UUID genusId;
}
