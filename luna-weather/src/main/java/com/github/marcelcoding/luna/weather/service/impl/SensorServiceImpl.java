package com.github.marcelcoding.luna.weather.service.impl;

import com.github.marcelcoding.luna.weather.converter.SensorConverter;
import com.github.marcelcoding.luna.weather.dto.Sensor;
import com.github.marcelcoding.luna.weather.dto.Sensor.Data;
import com.github.marcelcoding.luna.weather.model.SensorModel;
import com.github.marcelcoding.luna.weather.repository.SensorRepository;
import com.github.marcelcoding.luna.weather.service.SensorService;
import java.util.List;
import java.util.UUID;
import net.getnova.framework.core.exception.NotFoundException;
import net.getnova.framework.core.service.AbstractCommonIdCrudService;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class SensorServiceImpl
  extends AbstractCommonIdCrudService<Sensor, UUID, SensorModel>
  implements SensorService {

  private final MultiValueMap<UUID, Data> data;

  public SensorServiceImpl(
    final SensorRepository repository,
    final SensorConverter converter
  ) {
    super("SENSOR", repository, converter);
    this.data = new LinkedMultiValueMap<>();
  }

  @Override
  public void publishData(final UUID id, final Data value) {
    if (!this.exist(id)) {
      throw new NotFoundException(this.name);
    }

    this.data.add(id, value);
  }

  @Override
  public List<Data> fetchData(final UUID id) {
    if (!this.exist(id)) {
      throw new NotFoundException(this.name);
    }

    return this.data.get(id);
  }
}
