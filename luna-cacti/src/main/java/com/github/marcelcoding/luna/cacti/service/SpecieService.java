package com.github.marcelcoding.luna.cacti.service;

import com.github.marcelcoding.luna.cacti.NotFoundException;
import com.github.marcelcoding.luna.cacti.PropertyNotFoundException;
import com.github.marcelcoding.luna.cacti.api.Specie;
import com.github.marcelcoding.luna.cacti.model.GenusModel;
import com.github.marcelcoding.luna.cacti.model.SpecieModel;
import com.github.marcelcoding.luna.cacti.repository.GenusRepository;
import com.github.marcelcoding.luna.cacti.repository.SpecieRepository;
import java.util.List;
import java.util.Optional;
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

  private final GenusRepository genusRepository;
  private final SpecieRepository specieRepository;

  public Set<Specie> findAll() {
    return this.specieRepository.findAll()
      .stream()
      .map(Specie::new)
      .collect(Collectors.toSet());
  }

  public List<Specie> findAll(final Sort sort) throws PropertyNotFoundException {
    try {
      return this.specieRepository.findAll(sort)
        .stream()
        .map(Specie::new)
        .collect(Collectors.toList());
    }
    catch (PropertyReferenceException e) {
      throw new PropertyNotFoundException(e);
    }
  }

  public boolean exist(final UUID id) {
    return this.specieRepository.existsById(id);
  }

  public Specie save(final Specie specie) throws NotFoundException {
    final Optional<GenusModel> genusModel = this.genusRepository.findById(specie.getGenusId());

    if (genusModel.isEmpty()) {
      throw new NotFoundException(specie.getGenusId(), "GENUS_NOT_FOUND");
    }

    final SpecieModel model = new SpecieModel(
      specie,
      genusModel.get()
    );

    return new Specie(this.specieRepository.save(model));
  }

  public void delete(final UUID id) throws NotFoundException {
    if (!this.specieRepository.existsById(id)) {
      throw new NotFoundException(id, "SPECIE_NOT_FOUND");
    }

    this.specieRepository.deleteById(id);
  }
}
