package com.github.marcelcoding.luna.weather.controller;

import com.github.marcelcoding.luna.weather.dto.Sensor;
import java.util.Set;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/weather/sensor")
public interface SensorController {

  @GetMapping
  Set<Sensor> findAll();

  @PostMapping
  Sensor post(@RequestBody @Valid Sensor sensor);

  @PutMapping("{sensorId}")
  Sensor put(@PathVariable UUID sensorId, @RequestBody @Valid Sensor sensor);

  @DeleteMapping("{sensorId}")
  void delete(@PathVariable UUID sensorId);
}
