package com.github.marcelcoding.luna.weather.service;

import com.github.marcelcoding.luna.weather.dto.Sensor;
import java.util.Set;
import java.util.UUID;

public interface SensorService {

  Set<Sensor> findAll();

  boolean exist(UUID id);

  Sensor save(Sensor sensor);

  Sensor save(UUID id, Sensor sensor);

  void delete(UUID id);
}
