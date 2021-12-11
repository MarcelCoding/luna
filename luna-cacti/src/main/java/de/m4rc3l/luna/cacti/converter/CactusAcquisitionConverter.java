package de.m4rc3l.luna.cacti.converter;

import de.m4rc3l.luna.cacti.dto.Cactus.Acquisition;
import de.m4rc3l.luna.cacti.model.CactusModel.AcquisitionModel;
import de.m4rc3l.nova.core.Converter;
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
