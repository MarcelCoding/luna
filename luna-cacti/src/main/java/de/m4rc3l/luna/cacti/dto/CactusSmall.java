package de.m4rc3l.luna.cacti.dto;

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
  private final String fieldNumber;
}
