package com.github.marcelcoding.luna.weather.service.impl;

import com.github.marcelcoding.luna.weather.converter.SensorConverter;
import com.github.marcelcoding.luna.weather.dto.Sensor;
import com.github.marcelcoding.luna.weather.model.SensorModel;
import com.github.marcelcoding.luna.weather.repository.SensorRepository;
import com.github.marcelcoding.luna.weather.service.SensorService;
import java.util.UUID;
import net.getnova.framework.core.AbstractCommonIdCrudService;
import org.springframework.stereotype.Service;

@Service
public class SensorServiceImpl
  extends AbstractCommonIdCrudService<Sensor, UUID, SensorModel>
  implements SensorService {

  public SensorServiceImpl(
    final SensorRepository repository,
    final SensorConverter converter
  ) {
    super("SENSOR", repository, converter);
  }
}
