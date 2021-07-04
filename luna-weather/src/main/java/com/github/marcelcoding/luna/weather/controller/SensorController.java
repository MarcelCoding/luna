package com.github.marcelcoding.luna.weather.controller;

import com.github.marcelcoding.luna.weather.dto.Sensor;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import net.getnova.framework.core.CrudController;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Sensor")
@RequestMapping("/weather/sensor")
public interface SensorController extends CrudController<Sensor, UUID> {

}
