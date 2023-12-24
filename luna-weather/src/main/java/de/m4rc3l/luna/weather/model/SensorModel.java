package de.m4rc3l.luna.weather.model;

import de.m4rc3l.luna.weather.dto.Sensor.Illustration;
import de.m4rc3l.nova.jpa.model.TableModelAutoId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
