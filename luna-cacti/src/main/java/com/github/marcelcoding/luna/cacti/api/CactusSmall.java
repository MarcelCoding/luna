package com.github.marcelcoding.luna.cacti.api;

import java.util.UUID;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public final class CactusSmall {

  private final UUID id;
  private final String number;
  private final UUID genusId;
  private final UUID specieId;
  private final UUID formId;
}
