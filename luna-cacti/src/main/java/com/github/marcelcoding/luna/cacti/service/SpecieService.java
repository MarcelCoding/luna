package com.github.marcelcoding.luna.cacti.service;

import com.github.marcelcoding.luna.cacti.NotFoundException;
import com.github.marcelcoding.luna.cacti.PropertyNotFoundException;
import com.github.marcelcoding.luna.cacti.api.Specie;
import com.github.marcelcoding.luna.cacti.converter.SpecieConverter;
import com.github.marcelcoding.luna.cacti.model.SpecieModel;
import com.github.marcelcoding.luna.cacti.repository.SpecieRepository;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class SpecieService {

  private final SpecieRepository specieRepository;
  private final SpecieConverter specieConverter;

  public Set<Specie> findAll() {
    return this.specieRepository.findAll()
      .stream()
      .map(this.specieConverter::toDto)
      .collect(Collectors.toSet());
  }

  public List<Specie> findAll(final Sort sort) throws PropertyNotFoundException {
    try {
      return this.specieRepository.findAll(sort)
        .stream()
        .map(this.specieConverter::toDto)
        .toList();
    }
    catch (PropertyReferenceException e) {
      throw new PropertyNotFoundException(e);
    }
  }

  public boolean exist(final UUID id) {
    return this.specieRepository.existsById(id);
  }

  public Specie save(final Specie specie) throws NotFoundException {
    return this.specieConverter.toDto(
      this.specieRepository.save(
        this.specieConverter.toModel(specie)
      )
    );
  }

  public Specie save(final UUID id, final Specie specie) throws NotFoundException {
    final SpecieModel model = this.specieRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(id, "SPECIE_NOT_FOUND"));

    this.specieConverter.override(model, specie);

    return this.specieConverter.toDto(this.specieRepository.save(model));
  }

  public void delete(final UUID id) throws NotFoundException {
    if (!this.specieRepository.existsById(id)) {
      throw new NotFoundException(id, "SPECIE_NOT_FOUND");
    }

    this.specieRepository.deleteById(id);
  }
}
