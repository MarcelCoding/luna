package com.github.marcelcoding.luna.cacti.service;

import com.github.marcelcoding.luna.cacti.NotFoundException;
import com.github.marcelcoding.luna.cacti.PropertyNotFoundException;
import com.github.marcelcoding.luna.cacti.api.Cactus;
import com.github.marcelcoding.luna.cacti.api.CactusSmall;
import com.github.marcelcoding.luna.cacti.api.CareGroup;
import com.github.marcelcoding.luna.cacti.model.CactusModel;
import com.github.marcelcoding.luna.cacti.model.FormModel;
import com.github.marcelcoding.luna.cacti.model.GenusModel;
import com.github.marcelcoding.luna.cacti.model.SpecieModel;
import com.github.marcelcoding.luna.cacti.repository.CactusRepository;
import com.github.marcelcoding.luna.cacti.repository.CactusSmallRepository;
import com.github.marcelcoding.luna.cacti.repository.FormRepository;
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
public class CactusService {

  private final GenusRepository genusRepository;
  private final SpecieRepository specieRepository;
  private final FormRepository formRepository;
  private final CareGroupService careGroupService;
  private final CactusSmallRepository cactusSmallRepository;
  private final CactusRepository cactusRepository;

  public Set<CactusSmall> findAll() {
    return this.cactusSmallRepository.findAll()
      .stream()
      .map(CactusSmall::new)
      .collect(Collectors.toSet());
  }

  public List<Cactus> findAll(final Sort sort) throws PropertyNotFoundException {
    try {
      return this.cactusRepository.findAll(sort)
        .stream()
        .map(CactusModel::toDto)  // new Cactus(model, Optional.ofNullable(model.getCareGroup())
        //        .map(CareGroupModel::getId)
        //        .map(id -> this.careGroupService.findById(id).orElseThrow())
        //        .orElse(null))
        .collect(Collectors.toList());
    }
    catch (PropertyReferenceException e) {
      throw new PropertyNotFoundException(e);
    }
  }

  public Optional<Cactus> findById(final UUID id) {
    return this.cactusRepository.findById(id)
      .map(CactusModel::toDto); //  new Cactus(model, Optional.ofNullable(model.getCareGroup())
    //      .map(CareGroupModel::getId)
    //      .map(careGroupId -> this.careGroupService.findById(careGroupId).orElseThrow())
    //      .orElse(null))
  }

//  public Optional<Cactus> findByNumber(final String number) {
//    return this.cactusRepository.findByNumber(number).map(Cactus::new);
//  }

  public boolean exist(final UUID id) {
    return this.cactusRepository.existsById(id);
  }

  public Cactus save(final Cactus cactus) throws NotFoundException {
    final Optional<GenusModel> genusModel = Optional.ofNullable(cactus.getGenusId())
      .map(genusId -> this.genusRepository.findById(genusId)
        .orElseThrow(() -> new NotFoundException(genusId, "GENUS_NOT_FOUND")));

    final Optional<SpecieModel> specieModel = Optional.ofNullable(cactus.getSpecieId())
      .map(specieId -> this.specieRepository.findById(specieId)
        .orElseThrow(() -> new NotFoundException(specieId, "SPECIE_NOT_FOUND")));

    final Optional<FormModel> formModel = Optional.ofNullable(cactus.getFormId())
      .map(formId -> this.formRepository.findById(formId)
        .orElseThrow(() -> new NotFoundException(formId, "FORM_NOT_FOUND")));

    final Optional<CareGroup> careGroup = Optional.ofNullable(cactus.getCareGroup())
      .map(CareGroup::getId)
      .map(careGroupId -> this.careGroupService.findById(careGroupId)
        .orElseThrow(() -> new NotFoundException(careGroupId, "CARE_GROUP_NOT_FOUND")));

    final CactusModel model = new CactusModel(
      cactus,
      genusModel.orElse(null),
      specieModel.orElse(null),
      formModel.orElse(null),
      careGroup.orElse(null)
    );

    return this.cactusRepository.save(model).toDto(); // , careGroup.orElse(null));
  }

  public void delete(final UUID id) throws NotFoundException {
    if (!this.cactusRepository.existsById(id)) {
      throw new NotFoundException(id, "CACTUS_NOT_FOUND");
    }

    this.cactusRepository.deleteById(id);
  }
}
