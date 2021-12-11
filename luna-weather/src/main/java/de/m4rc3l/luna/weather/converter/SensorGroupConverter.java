package de.m4rc3l.luna.weather.converter;

import de.m4rc3l.luna.weather.dto.SensorGroup;
import de.m4rc3l.luna.weather.model.SensorGroupModel;
import lombok.RequiredArgsConstructor;
import de.m4rc3l.nova.core.Converter;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SensorGroupConverter implements Converter<SensorGroupModel, SensorGroup> {

  @Override
  public SensorGroupModel toModel(final SensorGroup dto) {
    return new SensorGroupModel(
      dto.getName(),
      dto.getDescription()
    );
  }

  @Override
  public SensorGroup toDto(final SensorGroupModel model) {
    return new SensorGroup(
      model.getId(),
      model.getName(),
      model.getDescription()
    );
  }

  @Override
  public void override(final SensorGroupModel model, final SensorGroup dto) {
    model.setName(dto.getName());
    model.setDescription(dto.getDescription());
  }

  @Override
  public void merge(final SensorGroupModel model, final SensorGroup dto) {
    throw new NotImplementedException();
  }
}
