package de.m4rc3l.luna.cacti.converter;

import de.m4rc3l.luna.cacti.dto.Genus;
import de.m4rc3l.luna.cacti.model.GenusModel;
import de.m4rc3l.nova.core.Converter;
import org.springframework.stereotype.Component;

@Component
public class GenusConverter implements Converter<GenusModel, Genus> {

  @Override
  public GenusModel toModel(final Genus dto) {
    return new GenusModel(
      dto.getName()
    );
  }

  @Override
  public Genus toDto(final GenusModel model) {
    return new Genus(
      model.getId(),
      model.getName()
    );
  }

  @Override
  public void override(final GenusModel model, final Genus dto) {
    model.setName(dto.getName());
  }

  @Override
  public void merge(final GenusModel model, final Genus dto) {
    if (dto.getName() != null) {
      model.setName(dto.getName());
    }
  }
}
