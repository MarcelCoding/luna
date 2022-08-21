package de.m4rc3l.luna.weather.converter;

import de.m4rc3l.luna.weather.dto.Sensor;
import de.m4rc3l.luna.weather.model.SensorModel;
import de.m4rc3l.luna.weather.repository.SensorGroupRepository;
import de.m4rc3l.nova.core.Converter;
import de.m4rc3l.nova.core.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
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
      dto.getDescription(),
      dto.getUnit(),
      dto.getIllustration(),
      dto.getGroupId() == null
        ? null
        : this.sensorGroupRepository.findById(dto.getGroupId())
          .orElseThrow(() -> new NotFoundException("SENSOR_GROUP"))
    );
  }

  @Override
  public Sensor toDto(final SensorModel model) {
    return new Sensor(
      model.getId(),
      model.getName(),
      model.getDescription(),
      model.getUnit(),
      model.getIllustration(),
      model.getGroup() == null ? null : model.getGroup().getId()
    );
  }

  @Override
  public void override(final SensorModel model, final Sensor dto) {
    model.setName(dto.getName());
    model.setDescription(dto.getDescription());
    model.setUnit(dto.getUnit());
    model.setIllustration(dto.getIllustration());
    model.setGroup(
      dto.getGroupId() == null
        ? null
        : this.sensorGroupRepository.findById(dto.getGroupId())
          .orElseThrow(() -> new NotFoundException("SENSOR_GROUP"))
    );
  }

  @Override
  public void merge(final SensorModel model, final Sensor dto) {
    throw new NotImplementedException();
  }
}
