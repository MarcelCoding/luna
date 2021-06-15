package com.github.marcelcoding.luna.weather.controller.impl;

import com.github.marcelcoding.luna.weather.controller.SensorGroupController;
import com.github.marcelcoding.luna.weather.dto.SensorGroup;
import com.github.marcelcoding.luna.weather.service.SensorGroupService;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SensorGroupControllerImpl implements SensorGroupController {

  private final SensorGroupService sensorGroupService;

  @Override
  public Set<SensorGroup> findAll() {
    return this.sensorGroupService.findAll();
  }

  @Override
  public SensorGroup post(final SensorGroup group) {
    return this.sensorGroupService.save(group);
  }

  @Override
  public SensorGroup put(final UUID groupId, final SensorGroup group) {
    return this.sensorGroupService.save(groupId, group);
  }

  @Override
  public void delete(final UUID groupId) {
    this.sensorGroupService.delete(groupId);
  }
}
