package com.github.marcelcoding.luna.weather.service.impl;

import com.github.marcelcoding.luna.weather.converter.SensorGroupConverter;
import com.github.marcelcoding.luna.weather.dto.SensorGroup;
import com.github.marcelcoding.luna.weather.model.SensorGroupModel;
import com.github.marcelcoding.luna.weather.repository.SensorGroupRepository;
import com.github.marcelcoding.luna.weather.service.SensorGroupService;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import net.getnova.framework.core.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SensorGroupServiceImpl implements SensorGroupService {

  private final SensorGroupRepository sensorGroupRepository;
  private final SensorGroupConverter sensorGroupConverter;

  @Override
  public Set<SensorGroup> findAll() {
    return this.sensorGroupRepository.findAll()
      .stream()
      .map(this.sensorGroupConverter::toDto)
      .collect(Collectors.toSet());
  }

  @Override
  public boolean exist(final UUID id) {
    return this.sensorGroupRepository.existsById(id);
  }

  @Override
  public SensorGroup save(final SensorGroup group) {
    return this.sensorGroupConverter.toDto(
      this.sensorGroupRepository.save(
        this.sensorGroupConverter.toModel(group)
      )
    );
  }

  @Override
  public SensorGroup save(final UUID id, final SensorGroup group) {
    final SensorGroupModel model = this.sensorGroupRepository.findById(id)
      .orElseThrow(() -> new NotFoundException("SENSOR_GROUP"));

    this.sensorGroupConverter.override(model, group);

    return this.sensorGroupConverter.toDto(model);
  }

  @Override
  public void delete(final UUID id) {
    if (!this.sensorGroupRepository.existsById(id)) {
      throw new NotFoundException("SENSOR_GROUP");
    }

    this.sensorGroupRepository.deleteById(id);
  }
}
