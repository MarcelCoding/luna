package com.github.marcelcoding.luna.cacti.converter;

import com.github.marcelcoding.luna.cacti.NotFoundException;
import com.github.marcelcoding.luna.cacti.api.Cactus;
import com.github.marcelcoding.luna.cacti.api.Cactus.Acquisition;
import com.github.marcelcoding.luna.cacti.api.Cactus.State;
import com.github.marcelcoding.luna.cacti.api.CareGroup;
import com.github.marcelcoding.luna.cacti.model.CactusModel;
import com.github.marcelcoding.luna.cacti.model.CactusModel.AcquisitionModel;
import com.github.marcelcoding.luna.cacti.model.CactusModel.CareGroupModel;
import com.github.marcelcoding.luna.cacti.model.CactusModel.StateModel;
import com.github.marcelcoding.luna.cacti.model.FormModel;
import com.github.marcelcoding.luna.cacti.model.GenusModel;
import com.github.marcelcoding.luna.cacti.model.SpecieModel;
import com.github.marcelcoding.luna.cacti.repository.FormRepository;
import com.github.marcelcoding.luna.cacti.repository.GenusRepository;
import com.github.marcelcoding.luna.cacti.repository.SpecieRepository;
import com.github.marcelcoding.luna.cacti.service.CareGroupService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.getnova.framework.core.Converter;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CactusConverter implements Converter<UUID, CactusModel, Cactus> {

  private final GenusRepository genusRepository;
  private final SpecieRepository specieRepository;
  private final FormRepository formRepository;
  private final CareGroupService careGroupService;

  @Override
  public CactusModel toModel(final Cactus dto) {
    final GenusModel genus;
    final SpecieModel specie;
    final FormModel form;

    if (dto.getFormId() != null) {
      form = this.formRepository.findById(dto.getFormId())
        .orElseThrow(() -> new NotFoundException(dto.getFormId(), "FORM_NOT_FOUND"));
      specie = form.getSpecie();
      genus = specie.getGenus();
    }
    else if (dto.getSpecieId() != null) {
      form = null;
      specie = this.specieRepository.findById(dto.getSpecieId())
        .orElseThrow(() -> new NotFoundException(dto.getSpecieId(), "SPECIE_NOT_FOUND"));
      genus = specie.getGenus();
    }
    else if (dto.getGenusId() != null) {
      form = null;
      specie = null;
      genus = this.genusRepository.findById(dto.getGenusId())
        .orElseThrow(() -> new NotFoundException(dto.getGenusId(), "GENUS_NOT_FOUND"));
    }
    else {
      form = null;
      specie = null;
      genus = null;
    }

    CareGroupModel careGroup;

    if (dto.getCareGroup() == null) {
      careGroup = null;
    }
    else if (dto.getCareGroup().getId() == null) {
      careGroup = new CareGroupModel(dto.getCareGroup());
    }
    else {
      final CareGroup local = this.careGroupService.findById(dto.getCareGroup().getId())
        .orElseThrow(() -> new NotFoundException(dto.getCareGroup().getId(), "CARE_GROUP_NOT_FOUND"));

      careGroup = new CareGroupModel(dto.getCareGroup(), local);
    }

    return new CactusModel(
      dto.getId(),
      dto.getNumber(),

      genus,
      specie,
      form,

      dto.getFieldNumber(),
      dto.getFlowerColor(),
      dto.getSynonymes(),

      dto.getState() == null ? null : new StateModel(dto.getState()),
      dto.getAcquisition() == null ? null : new AcquisitionModel(dto.getAcquisition()),
      careGroup
    );
  }

  @Override
  public Cactus toDto(final CactusModel model) {
    return new Cactus(
      model.getId(),
      model.getNumber(),

      model.getGenus() == null ? null : model.getGenus().getId(),
      model.getSpecie() == null ? null : model.getSpecie().getId(),
      model.getForm() == null ? null : model.getForm().getId(),

      model.getFieldNumber(),
      model.getFlowerColor(),
      model.getSynonyms(),

      model.getState() == null ? new State() : model.getState().toDto(),
      model.getAcquisition() == null ? new Acquisition() : model.getAcquisition().toDto(),

      // TODO: merge care group
      model.getCareGroup() == null ? new CareGroup() : model.getCareGroup().toDto()
    );
  }

  @Override
  public void override(final CactusModel model, final Cactus dto) {
    model.setNumber(dto.getNumber());

    if (dto.getFormId() != null) {
      model.setForm(this.formRepository.findById(dto.getFormId())
        .orElseThrow(() -> new NotFoundException(dto.getFormId(), "FORM_NOT_FOUND")));
      model.setSpecie(model.getForm().getSpecie());
      model.setGenus(model.getSpecie().getGenus());
    }
    else if (dto.getSpecieId() != null) {
      model.setForm(null);
      model.setSpecie(this.specieRepository.findById(dto.getSpecieId())
        .orElseThrow(() -> new NotFoundException(dto.getSpecieId(), "SPECIE_NOT_FOUND")));
      model.setGenus(model.getSpecie().getGenus());
    }
    else if (dto.getGenusId() != null) {
      model.setForm(null);
      model.setSpecie(null);
      model.setGenus(this.genusRepository.findById(dto.getGenusId())
        .orElseThrow(() -> new NotFoundException(dto.getGenusId(), "GENUS_NOT_FOUND")));
    }
    else {
      model.setForm(null);
      model.setSpecie(null);
      model.setGenus(null);
    }

    model.setFieldNumber(dto.getFieldNumber());
    model.setFlowerColor(dto.getFlowerColor());
    model.setSynonyms(dto.getSynonymes());

    model.setState(dto.getState() == null ? null : new StateModel(dto.getState()));
    model.setAcquisition(dto.getAcquisition() == null ? null : new AcquisitionModel(dto.getAcquisition()));

    if (dto.getCareGroup() == null) {
      model.setCareGroup(null);
    }
    else if (dto.getCareGroup().getId() == null) {
      model.setCareGroup(new CareGroupModel(dto.getCareGroup()));
    }
    else {
      final CareGroup local = this.careGroupService.findById(dto.getCareGroup().getId())
        .orElseThrow(() -> new NotFoundException(dto.getCareGroup().getId(), "CARE_GROUP_NOT_FOUND"));

      model.setCareGroup(new CareGroupModel(dto.getCareGroup(), local));
    }
  }

  @Override
  public void merge(final CactusModel model, final Cactus dto) {
    throw new NotImplementedException();
  }
}
