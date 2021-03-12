package com.github.marcelcoding.luna.cacti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.getnova.framework.jpa.model.TableModel;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cacti_care_group")
public class CareGroupModel extends TableModel {

  @Id
  @Column(name = "id", nullable = false, updatable = false, length = 2)
  private String id;

  @Column(name = "name", nullable = false, updatable = true, length = 128)
  private String name;

  @Column(name = "home", nullable = false, updatable = true, length = 512)
  private String home;

  @Column(name = "soil", nullable = false, updatable = true, length = 512)
  private String soil;

  @Column(name = "grow_time_light", nullable = true, updatable = true, length = 512)
  private String growTimeLight;

  @Column(name = "grow_time_air", nullable = true, updatable = true, length = 512)
  private String growTimeAir;

  @Column(name = "grow_time_temperature", nullable = true, updatable = true, length = 512)
  private String growTimeTemperature;

  @Column(name = "grow_time_humidity", nullable = true, updatable = true, length = 512)
  private String growTimeHumidity;

  @Column(name = "grow_time_other", nullable = true, updatable = true, length = 512)
  private String growTimeOther;

  @Column(name = "rest_time_light", nullable = true, updatable = true, length = 512)
  private String restTimeLight;

  @Column(name = "rest_time_air", nullable = true, updatable = true, length = 512)
  private String restTimeAir;

  @Column(name = "rest_time_temperature", nullable = true, updatable = true, length = 512)
  private String restTimeTemperature;

  @Column(name = "rest_time_humidity", nullable = true, updatable = true, length = 512)
  private String restTimeHumidity;

  @Column(name = "rest_time_other", nullable = true, updatable = true, length = 512)
  private String restTimeOther;

//  @Override
//  public void serialize(final JsonGenerator gen, final SerializerProvider serializers) throws IOException {
//    JsonWriter.create(gen)
//      .write("id", this.getId())
//      .write("name", this.getName())
//      .close();
//  }

//  @Override
//  public void serializeWithType(JsonGenerator gen, SerializerProvider serializers,
//    TypeSerializer typeSer) throws IOException {
//
//  }
}
