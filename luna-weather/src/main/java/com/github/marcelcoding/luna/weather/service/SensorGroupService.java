package com.github.marcelcoding.luna.weather.service;

import com.github.marcelcoding.luna.weather.dto.SensorGroup;
import java.util.Set;
import java.util.UUID;

public interface SensorGroupService {

  Set<SensorGroup> findAll();

  boolean exist(UUID id);

  SensorGroup save(SensorGroup group);

  SensorGroup save(UUID id, SensorGroup group);

  void delete(UUID id);
}
