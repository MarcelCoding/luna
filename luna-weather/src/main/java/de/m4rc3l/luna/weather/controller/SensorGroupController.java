package de.m4rc3l.luna.weather.controller;

import de.m4rc3l.luna.weather.dto.SensorGroup;
import de.m4rc3l.nova.core.controller.CrudController;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Sensor Group")
@RequestMapping("/weather/sensor/group")
public interface SensorGroupController extends CrudController<SensorGroup, UUID> {

}
