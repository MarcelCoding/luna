package com.github.marcelcoding.luna.weather.controller.impl;

import com.github.marcelcoding.luna.weather.controller.SensorGroupController;
import com.github.marcelcoding.luna.weather.dto.SensorGroup;
import com.github.marcelcoding.luna.weather.service.SensorGroupService;
import java.util.UUID;
import net.getnova.framework.core.AbstractCrudController;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SensorGroupControllerImpl
  extends AbstractCrudController<SensorGroup, UUID>
  implements SensorGroupController {

  public SensorGroupControllerImpl(
    final SensorGroupService service
  ) {
    super(service);
  }
}
