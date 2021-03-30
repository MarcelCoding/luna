package com.github.marcelcoding.luna.cacti.service;

import com.github.marcelcoding.luna.cacti.NotFoundException;
import com.github.marcelcoding.luna.cacti.PropertyNotFoundException;
import com.github.marcelcoding.luna.cacti.api.Genus;
import com.github.marcelcoding.luna.cacti.model.GenusModel;
import com.github.marcelcoding.luna.cacti.repository.GenusRepository;
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
public final class GenusService {

  private final GenusRepository genusRepository;

  public Set<Genus> findAll() {
    return this.genusRepository.findAll()
      .stream()
      .map(Genus::new)
      .collect(Collectors.toSet());
  }

  public List<Genus> findAll(final Sort sort) throws PropertyNotFoundException {
    try {
      return this.genusRepository.findAll(sort)
        .stream()
        .map(Genus::new)
        .collect(Collectors.toList());
    }
    catch (PropertyReferenceException e) {
      throw new PropertyNotFoundException(e);
    }
  }

  public boolean exist(final UUID id) {
    return this.genusRepository.existsById(id);
  }

  public Genus save(final Genus genus) {
    final GenusModel model = new GenusModel(genus);

    return new Genus(this.genusRepository.save(model));
  }

  public void delete(final UUID id) throws NotFoundException {
    if (!this.genusRepository.existsById(id)) {
      throw new NotFoundException(id, "GENUS_NOT_FOUND");
    }

    this.genusRepository.deleteById(id);
  }
}
