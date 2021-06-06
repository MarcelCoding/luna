package com.github.marcelcoding.luna.weather.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.getnova.framework.jpa.model.TableModelAutoId;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "weather_sensor")
public class SensorModel extends TableModelAutoId {

  @Column(name = "name", nullable = false, updatable = true, length = 128)
  private String name;

  @Column(name = "unit", nullable = false, updatable = true, length = 8)
  private String unit;
}
