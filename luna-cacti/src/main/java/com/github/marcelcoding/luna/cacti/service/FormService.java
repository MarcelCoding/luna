package com.github.marcelcoding.luna.cacti.service;

import com.github.marcelcoding.luna.cacti.NotFoundException;
import com.github.marcelcoding.luna.cacti.PropertyNotFoundException;
import com.github.marcelcoding.luna.cacti.api.Form;
import com.github.marcelcoding.luna.cacti.converter.FormConverter;
import com.github.marcelcoding.luna.cacti.model.FormModel;
import com.github.marcelcoding.luna.cacti.repository.FormRepository;
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
public final class FormService {

  private final FormRepository formRepository;
  private final FormConverter formConverter;

  public Set<Form> findAll() {
    return this.formRepository.findAll()
      .stream()
      .map(this.formConverter::toDto)
      .collect(Collectors.toSet());
  }

  public List<Form> findAll(final Sort sort) throws PropertyNotFoundException {
    try {
      return this.formRepository.findAll(sort)
        .stream()
        .map(this.formConverter::toDto)
        .toList();
    }
    catch (PropertyReferenceException e) {
      throw new PropertyNotFoundException(e);
    }
  }

  public boolean exist(final UUID id) {
    return this.formRepository.existsById(id);
  }

  public Form save(final Form form) throws NotFoundException {
    return this.formConverter.toDto(
      this.formRepository.save(
        this.formConverter.toModel(form)
      )
    );
  }

  public Form save(final UUID id, final Form form) throws NotFoundException {
    final FormModel model = this.formRepository.findById(id)
      .orElseThrow(() -> new NotFoundException(id, "FORM_NOT_FOUND"));

    this.formConverter.override(model, form);

    return this.formConverter.toDto(this.formRepository.save(model));
  }

  public void delete(final UUID id) throws NotFoundException {
    if (!this.formRepository.existsById(id)) {
      throw new NotFoundException(id, "FORM_NOT_FOUND");
    }

    this.formRepository.deleteById(id);
  }
}
