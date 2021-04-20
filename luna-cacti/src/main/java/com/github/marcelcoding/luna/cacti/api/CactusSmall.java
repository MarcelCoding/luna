package com.github.marcelcoding.luna.cacti.api;

import com.github.marcelcoding.luna.cacti.model.CactusSmallModel;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(access = AccessLevel.NONE)
public final class CactusSmall {

  private final UUID id;
  private final String number;
  private final UUID genusId;
  private final UUID specieId;
  private final UUID formId;

  public CactusSmall(final CactusSmallModel model) {
    this.id = model.getId();
    this.number = model.getNumber();

    this.genusId = model.getGenus() == null ? null : model.getGenus().getId();
    this.specieId = model.getSpecie() == null ? null : model.getSpecie().getId();
    this.formId = model.getForm() == null ? null : model.getForm().getId();
  }
}
