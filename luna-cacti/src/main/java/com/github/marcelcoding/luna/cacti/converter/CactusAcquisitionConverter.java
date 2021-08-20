package com.github.marcelcoding.luna.cacti.converter;

import com.github.marcelcoding.luna.cacti.dto.Cactus.Acquisition;
import com.github.marcelcoding.luna.cacti.model.CactusModel.AcquisitionModel;
import net.getnova.framework.core.Converter;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;

@Component
public class CactusAcquisitionConverter implements Converter<AcquisitionModel, Acquisition> {

  @Override
  public AcquisitionModel toModel(final Acquisition dto) {
    return new AcquisitionModel(
      dto.getTimestamp(),
      dto.getAge(),
      dto.getPlace(),
      dto.getPlantType()
    );
  }

  @Override
  public Acquisition toDto(final AcquisitionModel model) {
    return new Acquisition(
      model.getTimestamp(),
      model.getAge(),
      model.getPlace(),
      model.getPlantType()
    );
  }

  @Override
  public void override(final AcquisitionModel model, final Acquisition dto) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void merge(final AcquisitionModel model, final Acquisition dto) {
    throw new NotImplementedException();
  }
}
