package de.m4rc3l.luna.cacti.converter;

import de.m4rc3l.luna.cacti.dto.Cactus;
import de.m4rc3l.luna.cacti.dto.CareGroup;
import de.m4rc3l.luna.cacti.model.CactusModel;
import de.m4rc3l.luna.cacti.model.CactusModel.CareGroupModel;
import de.m4rc3l.luna.cacti.model.FormModel;
import de.m4rc3l.luna.cacti.model.GenusModel;
import de.m4rc3l.luna.cacti.model.SpecieModel;
import de.m4rc3l.luna.cacti.repository.FormRepository;
import de.m4rc3l.luna.cacti.repository.GenusRepository;
import de.m4rc3l.luna.cacti.repository.SpecieRepository;
import de.m4rc3l.luna.cacti.service.CareGroupService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import de.m4rc3l.nova.core.Converter;
import de.m4rc3l.nova.core.exception.NotFoundException;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CactusConverter implements Converter<CactusModel, Cactus> {

  private final GenusRepository genusRepository;
  private final SpecieRepository specieRepository;
  private final FormRepository formRepository;
  private final CareGroupService careGroupService;
  private final CactusStateConverter stateConverter;
  private final CactusAcquisitionConverter acquisitionConverter;
  private final CactusCareGroupConverter careGroupConverter;

  @Override
  public CactusModel toModel(final Cactus dto) {
    final CactusHolder cactusHolder = this.extractCactusHolder(dto);

    return new CactusModel(
      dto.getNumber(),

      cactusHolder.getGenus(),
      cactusHolder.getSpecie(),
      cactusHolder.getForm(),

      dto.getFieldNumber(),
      dto.getFlowerColor(),
      dto.getImages(),
      dto.getSynonymes(),

      dto.getState() == null ? null : this.stateConverter.toModel(dto.getState()),
      dto.getAcquisition() == null ? null : this.acquisitionConverter.toModel(dto.getAcquisition()),

      this.extractCareGroup(dto.getCareGroup())
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
      model.getImages(),
      model.getSynonyms(),

      model.getState() == null ? null : this.stateConverter.toDto(model.getState()),
      model.getAcquisition() == null ? null : this.acquisitionConverter.toDto(model.getAcquisition()),

      model.getCareGroup() == null ? null : this.careGroupConverter.toDto(model.getCareGroup())
    );
  }

  @Override
  public void override(final CactusModel model, final Cactus dto) {
    model.setNumber(dto.getNumber());

    final CactusHolder cactusHolder = this.extractCactusHolder(dto);
    model.setGenus(cactusHolder.getGenus());
    model.setSpecie(cactusHolder.getSpecie());
    model.setForm(cactusHolder.getForm());

    model.setFieldNumber(dto.getFieldNumber());
    model.setFlowerColor(dto.getFlowerColor());
    model.setSynonyms(dto.getSynonymes());

    model.setState(dto.getState() == null ? null : this.stateConverter.toModel(dto.getState()));
    model.setAcquisition(dto.getAcquisition() == null ? null : this.acquisitionConverter.toModel(dto.getAcquisition()));

    model.setCareGroup(this.extractCareGroup(dto.getCareGroup()));
  }

  @Override
  public void merge(final CactusModel model, final Cactus dto) {
    throw new NotImplementedException();
  }

  private CactusHolder extractCactusHolder(final Cactus dto) {
    final GenusModel genus;
    final SpecieModel specie;
    final FormModel form;

    if (dto.getFormId() != null) {
      form = this.formRepository.findById(dto.getFormId())
        .orElseThrow(() -> new NotFoundException("FORM"));
      specie = form.getSpecie();
      genus = specie.getGenus();
    }
    else if (dto.getSpecieId() != null) {
      form = null;
      specie = this.specieRepository.findById(dto.getSpecieId())
        .orElseThrow(() -> new NotFoundException("SPECIE"));
      genus = specie.getGenus();
    }
    else if (dto.getGenusId() != null) {
      form = null;
      specie = null;
      genus = this.genusRepository.findById(dto.getGenusId())
        .orElseThrow(() -> new NotFoundException("GENUS"));
    }
    else {
      form = null;
      specie = null;
      genus = null;
    }

    return new CactusHolder(genus, specie, form);
  }

  private CareGroupModel extractCareGroup(final CareGroup dto) {
    if (dto == null) {
      return null;
    }

    if (dto.getId() == null) {
      return this.careGroupConverter.toModel(dto);
    }

    final CareGroup careGroup = this.careGroupService.findById(dto.getId());

    return this.careGroupConverter.toModel(careGroup, dto);
  }

  @Data
  private static final class CactusHolder {

    private final GenusModel genus;
    private final SpecieModel specie;
    private final FormModel form;
  }
}
