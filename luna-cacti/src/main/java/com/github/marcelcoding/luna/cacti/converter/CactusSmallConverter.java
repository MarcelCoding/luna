package com.github.marcelcoding.luna.cacti.converter;

import com.github.marcelcoding.luna.cacti.api.CactusSmall;
import com.github.marcelcoding.luna.cacti.model.CactusSmallModel;
import net.getnova.framework.core.AbstractSmallConverter;
import org.springframework.stereotype.Component;

@Component
public class CactusSmallConverter extends AbstractSmallConverter<CactusSmallModel, CactusSmall> {

  @Override
  public CactusSmall toDto(final CactusSmallModel model) {
    return new CactusSmall(
      model.getId(),
      model.getNumber(),

      model.getGenus() == null ? null : model.getGenus().getId(),
      model.getSpecie() == null ? null : model.getSpecie().getId(),
      model.getForm() == null ? null : model.getForm().getId()
    );
  }
}
