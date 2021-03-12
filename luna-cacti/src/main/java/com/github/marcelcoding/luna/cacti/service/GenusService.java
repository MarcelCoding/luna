package com.github.marcelcoding.luna.cacti.service;

import com.github.marcelcoding.luna.cacti.model.GenusModel;
import com.github.marcelcoding.luna.cacti.repository.GenusRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GenusService {

  private final GenusRepository genusRepository;

  public List<GenusModel> findAll() {
    return this.genusRepository.findAll();
  }

  public Optional<GenusModel> findById(final UUID id) {
    return this.genusRepository.findById(id);
  }

  @Transactional
  public GenusModel save(final GenusModel genus) {
    return this.genusRepository.save(genus);
  }

  public boolean deleteById(final UUID id) {
    if (this.genusRepository.existsById(id)) {
      this.genusRepository.deleteById(id);
      return true;
    }

    return false;
  }
}
