package com.github.marcelcoding.luna.weather.service.impl;

import com.github.marcelcoding.luna.weather.converter.SensorConverter;
import com.github.marcelcoding.luna.weather.dto.Sensor;
import com.github.marcelcoding.luna.weather.model.SensorModel;
import com.github.marcelcoding.luna.weather.repository.SensorRepository;
import com.github.marcelcoding.luna.weather.service.SensorService;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import net.getnova.framework.core.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService {

  private final SensorRepository sensorRepository;
  private final SensorConverter sensorConverter;

  @Override
  public Set<Sensor> findAll() {
    return this.sensorRepository.findAll()
      .stream()
      .map(this.sensorConverter::toDto)
      .collect(Collectors.toSet());
  }

  @Override
  public boolean exist(final UUID id) {
    return this.sensorRepository.existsById(id);
  }

  @Override
  public Sensor save(final Sensor sensor) {
    return this.sensorConverter.toDto(
      this.sensorRepository.save(
        this.sensorConverter.toModel(sensor)
      )
    );
  }

  @Override
  public Sensor save(final UUID id, final Sensor sensor) {
    final SensorModel model = this.sensorRepository.findById(id)
      .orElseThrow(() -> new NotFoundException("SENSOR"));

    this.sensorConverter.override(model, sensor);

    return this.sensorConverter.toDto(model);
  }

  @Override
  public void delete(final UUID id) {
    if (!this.sensorRepository.existsById(id)) {
      throw new NotFoundException("SENSOR");
    }

    this.sensorRepository.deleteById(id);
  }
}
