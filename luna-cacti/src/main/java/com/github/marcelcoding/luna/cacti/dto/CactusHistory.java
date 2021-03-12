package com.github.marcelcoding.luna.cacti.dto;

import java.time.OffsetDateTime;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CactusHistory {

  private final UUID id;
  @NotNull
  private final UUID cactusId;
  @NotNull
  private final OffsetDateTime timestamp;
  @NotNull
  @NotBlank
  private final String content;
}
