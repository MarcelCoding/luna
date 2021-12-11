package de.m4rc3l.luna.cacti.converter;

import de.m4rc3l.luna.cacti.dto.Specie;
import de.m4rc3l.luna.cacti.model.GenusModel;
import de.m4rc3l.luna.cacti.model.SpecieModel;
import de.m4rc3l.luna.cacti.repository.GenusRepository;
import lombok.RequiredArgsConstructor;
import de.m4rc3l.nova.core.Converter;
import de.m4rc3l.nova.core.exception.NotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpecieConverter implements Converter<SpecieModel, Specie> {

  private final GenusRepository genusRepository;

  @Override
  public SpecieModel toModel(final Specie dto) {
    final GenusModel genus = this.genusRepository.findById(dto.getGenusId())
      .orElseThrow(() -> new NotFoundException("GENUS_NOT_FOUND"));

    return new SpecieModel(
      dto.getName(),
      genus
    );
  }

  @Override
  public Specie toDto(final SpecieModel model) {
    return new Specie(
      model.getId(),
      model.getName(),
      model.getGenus().getId()
    );
  }

  @Override
  public void override(final SpecieModel model, final Specie dto) {
    final GenusModel genus = this.genusRepository.findById(dto.getGenusId())
      .orElseThrow(() -> new NotFoundException("GENUS_NOT_FOUND"));

    model.setName(dto.getName());
    model.setGenus(genus);
  }

  @Override
  public void merge(final SpecieModel model, final Specie dto) {
    if (dto.getName() != null) {
      model.setName(dto.getName());
    }

    if (dto.getGenusId() != null) {
      final GenusModel genus = this.genusRepository.findById(dto.getGenusId())
        .orElseThrow(() -> new NotFoundException("GENUS_NOT_FOUND"));

      model.setGenus(genus);
    }
  }
}
