package de.m4rc3l.luna.weather.model;

import de.m4rc3l.luna.weather.dto.Sensor.Illustration;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import de.m4rc3l.nova.jpa.model.TableModelAutoId;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "weather_sensor")
public class SensorModel extends TableModelAutoId {

  @Column(name = "name", nullable = false, length = 128)
  private String name;

  @Column(name = "description", nullable = false, length = 512)
  private String description;

  @Column(name = "unit", nullable = false, length = 8)
  private String unit;

  @Column(name = "illustration", nullable = false)
  @Enumerated(EnumType.STRING)
  private Illustration illustration;

  @ManyToOne
  @JoinColumn(name = "group_id")
  private SensorGroupModel group;
}
