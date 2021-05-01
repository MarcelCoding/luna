package com.github.marcelcoding.luna.cacti.converter;

import com.github.marcelcoding.luna.cacti.NotFoundException;
import com.github.marcelcoding.luna.cacti.api.Specie;
import com.github.marcelcoding.luna.cacti.model.GenusModel;
import com.github.marcelcoding.luna.cacti.model.SpecieModel;
import com.github.marcelcoding.luna.cacti.repository.GenusRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.getnova.framework.core.Converter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecieConverter implements Converter<UUID, SpecieModel, Specie> {

  private final GenusRepository genusRepository;

  @Override
  public SpecieModel toModel(final Specie dto) {
    final GenusModel genus = this.genusRepository.findById(dto.getGenusId())
      .orElseThrow(() -> new NotFoundException(dto.getGenusId(), "GENUS_NOT_FOUND"));

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
      .orElseThrow(() -> new NotFoundException(dto.getGenusId(), "GENUS_NOT_FOUND"));

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
        .orElseThrow(() -> new NotFoundException(dto.getGenusId(), "GENUS_NOT_FOUND"));

      model.setGenus(genus);
    }
  }
}
