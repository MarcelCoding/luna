package com.github.marcelcoding.luna.cacti.service;

import com.github.marcelcoding.luna.cacti.NotFoundException;
import com.github.marcelcoding.luna.cacti.PropertyNotFoundException;
import com.github.marcelcoding.luna.cacti.api.Form;
import com.github.marcelcoding.luna.cacti.model.FormModel;
import com.github.marcelcoding.luna.cacti.model.SpecieModel;
import com.github.marcelcoding.luna.cacti.repository.FormRepository;
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
public final class FormService {

  private final SpecieRepository specieRepository;
  private final FormRepository formRepository;

  public Set<Form> findAll() {
    return this.formRepository.findAll()
      .stream()
      .map(Form::new)
      .collect(Collectors.toSet());
  }

  public List<Form> findAll(final Sort sort) throws PropertyNotFoundException {
    try {
      return this.formRepository.findAll(sort)
        .stream()
        .map(Form::new)
        .collect(Collectors.toList());
    }
    catch (PropertyReferenceException e) {
      throw new PropertyNotFoundException(e);
    }
  }

  public boolean exist(final UUID id) {
    return this.formRepository.existsById(id);
  }

  public Form save(final Form form) throws NotFoundException {
    final Optional<SpecieModel> specieModel = this.specieRepository.findById(form.getSpecieId());

    if (specieModel.isEmpty()) {
      throw new NotFoundException(form.getSpecieId(), "SPECIE_NOT_FOUND");
    }

    final FormModel model = new FormModel(
      form,
      specieModel.get()
    );

    return new Form(this.formRepository.save(model));
  }

  public void delete(final UUID id) throws NotFoundException {
    if (!this.formRepository.existsById(id)) {
      throw new NotFoundException(id, "FORM_NOT_FOUND");
    }

    this.formRepository.deleteById(id);
  }
}
