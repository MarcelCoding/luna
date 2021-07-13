package com.github.marcelcoding.luna.weather.controller;

import com.github.marcelcoding.luna.weather.dto.Sensor;
import com.github.marcelcoding.luna.weather.dto.Sensor.Data;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import net.getnova.framework.core.controller.CrudController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Sensor")
@RequestMapping("/weather/sensor")
public interface SensorController extends CrudController<Sensor, UUID> {

  @PostMapping("{id}/data")
  void publishData(@PathVariable("id") UUID id, @RequestBody Data data);

  @GetMapping("{id}/data")
  List<Data> fetchData(@PathVariable("id") UUID id);
}
