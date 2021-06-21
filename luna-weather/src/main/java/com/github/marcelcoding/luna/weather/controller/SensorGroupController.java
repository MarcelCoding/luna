package com.github.marcelcoding.luna.weather.controller;

import com.github.marcelcoding.luna.weather.dto.SensorGroup;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Sensor Group")
@RequestMapping("/weather/sensor/group")
public interface SensorGroupController {

  @GetMapping
  Set<SensorGroup> findAll();

  @PostMapping
  SensorGroup post(@RequestBody @Valid SensorGroup group);

  @PutMapping("{groupId}")
  SensorGroup put(@PathVariable UUID groupId, @RequestBody @Valid SensorGroup group);

  @DeleteMapping("{groupId}")
  void delete(@PathVariable UUID groupId);
}
