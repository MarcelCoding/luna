package de.m4rc3l.luna.weather.service.impl;

import de.m4rc3l.luna.weather.converter.SensorGroupConverter;
import de.m4rc3l.luna.weather.dto.SensorGroup;
import de.m4rc3l.luna.weather.model.SensorGroupModel;
import de.m4rc3l.luna.weather.repository.SensorGroupRepository;
import de.m4rc3l.luna.weather.service.SensorGroupService;
import java.util.UUID;
import de.m4rc3l.nova.core.service.AbstractCommonIdCrudService;
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
