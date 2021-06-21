package com.github.marcelcoding.luna.cacti.service;

import com.github.marcelcoding.luna.cacti.PropertyNotFoundException;
import com.github.marcelcoding.luna.cacti.api.Cactus;
import com.github.marcelcoding.luna.cacti.api.CactusSmall;
import com.github.marcelcoding.luna.cacti.converter.CactusConverter;
import com.github.marcelcoding.luna.cacti.model.CactusModel;
import com.github.marcelcoding.luna.cacti.repository.CactusRepository;
import com.github.marcelcoding.luna.cacti.repository.CactusSmallRepository;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import net.getnova.framework.core.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CactusService {

  private final CactusSmallRepository cactusSmallRepository;
  private final CactusRepository cactusRepository;
  private final CactusConverter cactusConverter;

  public Set<CactusSmall> findAll() {
    return this.cactusSmallRepository.findAll()
      .stream()
      .map(CactusSmall::new)
      .collect(Collectors.toSet());
  }

  public List<CactusSmall> findAll(final Sort sort) throws PropertyNotFoundException {
    try {
      return this.cactusSmallRepository.findAll(sort)
        .stream()
        .map(CactusSmall::new)
        .toList();
    }
    catch (PropertyReferenceException e) {
      throw new PropertyNotFoundException(e);
    }
  }

  public Optional<Cactus> findById(final UUID id) {
    return this.cactusRepository.findById(id)
      .map(this.cactusConverter::toDto);
  }

  public boolean exist(final UUID id) {
    return this.cactusRepository.existsById(id);
  }

  public Cactus save(final Cactus cactus) throws NotFoundException {
    return this.cactusConverter.toDto(
      this.cactusRepository.save(
        this.cactusConverter.toModel(cactus)
      )
    );
  }

  public Cactus save(final UUID id, final Cactus cactus) throws NotFoundException {
    final CactusModel model = this.cactusRepository.findById(id)
      .orElseThrow(() -> new NotFoundException("CACTUS_NOT_FOUND"));

    this.cactusConverter.override(model, cactus);

    return this.cactusConverter.toDto(this.cactusRepository.save(model));
  }

  public void delete(final UUID id) throws NotFoundException {
    if (!this.cactusRepository.existsById(id)) {
      throw new NotFoundException("CACTUS_NOT_FOUND");
    }

    this.cactusRepository.deleteById(id);
  }
}
