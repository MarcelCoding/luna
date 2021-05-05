package com.github.marcelcoding.luna.cacti.converter;

import com.github.marcelcoding.luna.cacti.api.CareGroup;
import com.github.marcelcoding.luna.cacti.api.CareGroup.Time;
import com.github.marcelcoding.luna.cacti.model.CactusModel.CareGroupModel;
import com.github.marcelcoding.luna.cacti.model.CactusModel.CareGroupModel.TimeModel;
import com.github.marcelcoding.luna.cacti.service.CareGroupService;
import lombok.RequiredArgsConstructor;
import net.getnova.framework.core.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CactusCareGroupConverter implements Converter<CareGroupModel, CareGroup> {

  private final CareGroupService careGroupService;

  @Override
  public CareGroupModel toModel(final CareGroup dto) {
    return null;
  }

  @Override
  public CareGroup toDto(final CareGroupModel model) {
    if (model.getId() == null) {
      return new CareGroup(
        null,
        null,
        model.getHome(),
        model.getSoil(),
        model.getGrowTime() == null ? null : this.toDto(model.getGrowTime()),
        model.getRestTime() == null ? null : this.toDto(model.getRestTime())
      );
    }

    final CareGroup careGroup = this.careGroupService.findById(model.getId())
      .orElseThrow(IllegalStateException::new);

    return new CareGroup(
      careGroup.getId(),
      careGroup.getName(),
      model.getHome() == null ? careGroup.getHome() : model.getHome(),
      model.getSoil() == null ? careGroup.getSoil() : model.getSoil(),
      model.getGrowTime() == null ? careGroup.getGrowTime() : this.toDto(careGroup.getGrowTime(), model.getGrowTime()),
      model.getRestTime() == null ? careGroup.getRestTime() : this.toDto(careGroup.getRestTime(), model.getRestTime())
    );
  }

  @Override
  public void override(final CareGroupModel model, final CareGroup dto) {

  }

  @Override
  public void merge(final CareGroupModel model, final CareGroup dto) {

  }

  private Time toDto(final TimeModel model) {
    return new Time(
      model.getLight(),
      model.getAir(),
      model.getTemperature(),
      model.getHumidity(),
      model.getOther()
    );
  }

  private Time toDto(final Time time, final TimeModel model) {
    return new Time(
      model.getLight() == null ? time.getLight() : model.getLight(),
      model.getAir() == null ? time.getAir() : model.getAir(),
      model.getTemperature() == null ? time.getTemperature() : model.getTemperature(),
      model.getHumidity() == null ? time.getHumidity() : model.getHumidity(),
      model.getOther() == null ? time.getOther() : model.getOther()
    );
  }
}
