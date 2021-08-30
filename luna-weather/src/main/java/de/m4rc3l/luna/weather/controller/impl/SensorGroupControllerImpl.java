package de.m4rc3l.luna.weather.controller.impl;

import de.m4rc3l.luna.weather.controller.SensorGroupController;
import de.m4rc3l.luna.weather.dto.SensorGroup;
import de.m4rc3l.luna.weather.service.SensorGroupService;
import java.util.UUID;
import net.getnova.framework.core.controller.AbstractCrudController;
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
