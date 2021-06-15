package com.github.marcelcoding.luna.weather.converter;

import com.github.marcelcoding.luna.weather.dto.SensorGroup;
import com.github.marcelcoding.luna.weather.model.SensorGroupModel;
import lombok.RequiredArgsConstructor;
import net.getnova.framework.core.Converter;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SensorGroupConverter implements Converter<SensorGroupModel, SensorGroup> {

  @Override
  public SensorGroupModel toModel(final SensorGroup dto) {
    return new SensorGroupModel(
      dto.getName()
    );
  }

  @Override
  public SensorGroup toDto(final SensorGroupModel model) {
    return new SensorGroup(
      model.getId(),
      model.getName()
    );
  }

  @Override
  public void override(final SensorGroupModel model, final SensorGroup dto) {
    model.setName(dto.getName());
  }

  @Override
  public void merge(final SensorGroupModel model, final SensorGroup dto) {
    throw new NotImplementedException();
  }
}
