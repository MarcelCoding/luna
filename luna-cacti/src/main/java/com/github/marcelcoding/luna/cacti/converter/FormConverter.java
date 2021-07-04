package com.github.marcelcoding.luna.cacti.converter;

import com.github.marcelcoding.luna.cacti.api.Form;
import com.github.marcelcoding.luna.cacti.model.FormModel;
import com.github.marcelcoding.luna.cacti.model.SpecieModel;
import com.github.marcelcoding.luna.cacti.repository.SpecieRepository;
import lombok.RequiredArgsConstructor;
import net.getnova.framework.core.Converter;
import net.getnova.framework.core.NotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FormConverter implements Converter<FormModel, Form> {

  private final SpecieRepository specieRepository;

  @Override
  public FormModel toModel(final Form dto) {
    final SpecieModel specie = this.specieRepository.findById(dto.getSpecieId())
      .orElseThrow(() -> new NotFoundException("SPECIE_NOT_FOUND"));

    return new FormModel(
      dto.getName(),
      specie
    );
  }

  @Override
  public Form toDto(final FormModel model) {
    return new Form(
      model.getId(),
      model.getName(),
      model.getSpecie().getId()
    );
  }

  @Override
  public void override(final FormModel model, final Form dto) {
    final SpecieModel specie = this.specieRepository.findById(dto.getSpecieId())
      .orElseThrow(() -> new NotFoundException("SPECIE_NOT_FOUND"));

    model.setName(dto.getName());
    model.setSpecie(specie);
  }

  @Override
  public void merge(final FormModel model, final Form dto) {
    if (dto.getName() != null) {
      model.setName(dto.getName());
    }

    if (dto.getSpecieId() != null) {
      final SpecieModel specie = this.specieRepository.findById(dto.getSpecieId())
        .orElseThrow(() -> new NotFoundException("SPECIE_NOT_FOUND"));

      model.setSpecie(specie);
    }
  }
}
