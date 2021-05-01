package com.github.marcelcoding.luna.cacti.converter;

import com.github.marcelcoding.luna.cacti.api.Genus;
import com.github.marcelcoding.luna.cacti.model.GenusModel;
import java.util.UUID;
import net.getnova.framework.core.Converter;
import org.springframework.stereotype.Service;

@Service
public class GenusConverter implements Converter<UUID, GenusModel, Genus> {

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
