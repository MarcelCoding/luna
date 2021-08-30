package de.m4rc3l.luna.cacti.converter;

import de.m4rc3l.luna.cacti.CareGroupUtils;
import de.m4rc3l.luna.cacti.dto.CareGroup.Time;
import de.m4rc3l.luna.cacti.model.CactusModel.CareGroupModel.TimeModel;
import org.springframework.stereotype.Component;

@Component
public class CactusCareGroupTimeConverter {

  public TimeModel toModel(final Time orig, final Time dto) {
    return new TimeModel(
      CareGroupUtils.getValue(orig.getLight(), dto.getLight()),
      CareGroupUtils.getValue(orig.getAir(), dto.getAir()),
      CareGroupUtils.getValue(orig.getTemperature(), dto.getTemperature()),
      CareGroupUtils.getValue(orig.getHumidity(), dto.getHumidity()),
      CareGroupUtils.getValue(orig.getOther(), dto.getOther())
    );
  }

  public TimeModel toModel(final Time dto) {
    return new TimeModel(
      dto.getLight(),
      dto.getAir(),
      dto.getTemperature(),
      dto.getHumidity(),
      dto.getOther()
    );
  }

  public Time toDto(final TimeModel model) {
    return new Time(
      model.getLight(),
      model.getAir(),
      model.getTemperature(),
      model.getHumidity(),
      model.getOther()
    );
  }

  public Time toDto(final TimeModel model, final Time time) {
    return new Time(
      model.getLight() == null ? time.getLight() : model.getLight(),
      model.getAir() == null ? time.getAir() : model.getAir(),
      model.getTemperature() == null ? time.getTemperature() : model.getTemperature(),
      model.getHumidity() == null ? time.getHumidity() : model.getHumidity(),
      model.getOther() == null ? time.getOther() : model.getOther()
    );
  }
}
