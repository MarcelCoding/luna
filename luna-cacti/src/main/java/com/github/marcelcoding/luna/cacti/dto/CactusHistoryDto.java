package com.github.marcelcoding.luna.cacti.dto;

import java.time.OffsetDateTime;
import java.util.UUID;
import lombok.Data;

@Data
public class CactusHistoryDto {

  private final UUID id;
  private final UUID cactusId;
  private final OffsetDateTime timestamp;
  private final String content;
}
