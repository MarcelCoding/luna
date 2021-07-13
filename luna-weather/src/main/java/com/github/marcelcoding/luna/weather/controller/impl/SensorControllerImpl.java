package com.github.marcelcoding.luna.weather.controller.impl;

import com.github.marcelcoding.luna.weather.controller.SensorController;
import com.github.marcelcoding.luna.weather.dto.Sensor;
import com.github.marcelcoding.luna.weather.dto.Sensor.Data;
import com.github.marcelcoding.luna.weather.service.SensorService;
import java.util.List;
import java.util.UUID;
import net.getnova.framework.core.controller.AbstractCrudController;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SensorControllerImpl
  extends AbstractCrudController<Sensor, UUID>
  implements SensorController {

  public SensorControllerImpl(
    final SensorService service
  ) {
    super(service);
  }

  @Override
  public void publishData(final UUID id, final Data data) {
    ((SensorService) this.service).publishData(id, data);
  }

  @Override
  public List<Data> fetchData(final UUID id) {
    return ((SensorService) this.service).fetchData(id);
  }
}
