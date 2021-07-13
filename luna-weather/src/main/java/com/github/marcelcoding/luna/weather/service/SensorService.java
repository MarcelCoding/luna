package com.github.marcelcoding.luna.weather.service;

import com.github.marcelcoding.luna.weather.dto.Sensor;
import com.github.marcelcoding.luna.weather.dto.Sensor.Data;
import java.util.List;
import java.util.UUID;
import net.getnova.framework.core.service.CrudService;

public interface SensorService extends CrudService<Sensor, UUID> {

  void publishData(UUID id, Data value);

  List<Data> fetchData(UUID id);
}
