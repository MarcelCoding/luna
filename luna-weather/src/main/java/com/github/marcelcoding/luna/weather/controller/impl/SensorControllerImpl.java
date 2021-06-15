package com.github.marcelcoding.luna.weather.controller.impl;

import com.github.marcelcoding.luna.weather.controller.SensorController;
import com.github.marcelcoding.luna.weather.dto.Sensor;
import com.github.marcelcoding.luna.weather.service.SensorService;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SensorControllerImpl implements SensorController {

  private final SensorService sensorService;

  @Override
  public Set<Sensor> findAll() {
    return this.sensorService.findAll();
  }

  @Override
  public Sensor post(final Sensor sensor) {
    return this.sensorService.save(sensor);
  }

  @Override
  public Sensor put(final UUID sensorId, final Sensor sensor) {
    return this.sensorService.save(sensorId, sensor);
  }

  @Override
  public void delete(final UUID sensorId) {
    this.sensorService.delete(sensorId);
  }
}
