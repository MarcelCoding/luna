package com.github.marcelcoding.luna.weather.converter;

import com.github.marcelcoding.luna.weather.dto.Sensor;
import com.github.marcelcoding.luna.weather.model.SensorModel;
import com.github.marcelcoding.luna.weather.repository.SensorGroupRepository;
import lombok.RequiredArgsConstructor;
import net.getnova.framework.core.Converter;
import net.getnova.framework.core.NotFoundException;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SensorConverter implements Converter<SensorModel, Sensor> {

  private final SensorGroupRepository sensorGroupRepository;

  @Override
  public SensorModel toModel(final Sensor dto) {
    return new SensorModel(
      dto.getName(),
      dto.getUnit(),
      dto.getGroup() == null
        ? null
        : this.sensorGroupRepository.findById(dto.getGroup())
          .orElseThrow(() -> new NotFoundException("SENSOR_GROUP"))
    );
  }

  @Override
  public Sensor toDto(final SensorModel model) {
    return new Sensor(
      model.getId(),
      model.getName(),
      model.getUnit(),
      model.getGroup() == null ? null : model.getGroup().getId()
    );
  }

  @Override
  public void override(final SensorModel model, final Sensor dto) {
    model.setName(dto.getName());
    model.setUnit(dto.getUnit());
    model.setGroup(
      dto.getGroup() == null
        ? null
        : this.sensorGroupRepository.findById(dto.getGroup())
          .orElseThrow(() -> new NotFoundException("SENSOR_GROUP"))
    );
  }

  @Override
  public void merge(final SensorModel model, final Sensor dto) {
    throw new NotImplementedException();
  }
}
