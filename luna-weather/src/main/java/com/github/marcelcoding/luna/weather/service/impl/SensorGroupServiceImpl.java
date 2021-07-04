package com.github.marcelcoding.luna.weather.service.impl;

import com.github.marcelcoding.luna.weather.converter.SensorGroupConverter;
import com.github.marcelcoding.luna.weather.dto.SensorGroup;
import com.github.marcelcoding.luna.weather.model.SensorGroupModel;
import com.github.marcelcoding.luna.weather.repository.SensorGroupRepository;
import com.github.marcelcoding.luna.weather.service.SensorGroupService;
import java.util.UUID;
import net.getnova.framework.core.AbstractCommonIdCrudService;
import org.springframework.stereotype.Service;

@Service
public class SensorGroupServiceImpl
  extends AbstractCommonIdCrudService<SensorGroup, UUID, SensorGroupModel>
  implements SensorGroupService {

  public SensorGroupServiceImpl(
    final SensorGroupRepository repository,
    final SensorGroupConverter converter
  ) {
    super("SENSOR_GROUP", repository, converter);
  }
}
