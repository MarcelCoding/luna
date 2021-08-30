package de.m4rc3l.luna.weather.model;

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
@Table(name = "weather_sensor_group")
public class SensorGroupModel extends TableModelAutoId {

  @Column(name = "name", nullable = false, length = 128)
  private String name;

  @Column(name = "description", nullable = false, length = 512)
  private String description;
}
