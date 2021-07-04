package com.github.marcelcoding.luna.cacti.converter;

import com.github.marcelcoding.luna.cacti.CareGroupUtils;
import com.github.marcelcoding.luna.cacti.api.CareGroup;
import com.github.marcelcoding.luna.cacti.model.CactusModel.CareGroupModel;
import com.github.marcelcoding.luna.cacti.service.CareGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CactusCareGroupConverter {

  private final CareGroupService careGroupService;
  private final CactusCareGroupTimeConverter timeConverter;

  public CareGroupModel toModel(final CareGroup dto) {
    return new CareGroupModel(
      dto.getId(),
      dto.getHome(),
      dto.getSoil(),

      dto.getGrowTime() == null ? null : this.timeConverter.toModel(dto.getGrowTime()),
      dto.getRestTime() == null ? null : this.timeConverter.toModel(dto.getRestTime())
    );
  }

  public CareGroupModel toModel(final CareGroup orig, final CareGroup dto) {
    return new CareGroupModel(
      orig.getId(),
      CareGroupUtils.getValue(orig.getId(), dto.getHome()),
      CareGroupUtils.getValue(orig.getSoil(), dto.getHome()),

      dto.getGrowTime() == null ? null : this.timeConverter.toModel(orig.getGrowTime(), dto.getGrowTime()),
      dto.getRestTime() == null ? null : this.timeConverter.toModel(orig.getRestTime(), dto.getRestTime())
    );
  }

  @SuppressWarnings("checkstyle:TodoComment")
  public CareGroup toDto(final CareGroupModel model) {
    if (model.getId() == null) {
      return new CareGroup(
        null,
        null,
        model.getHome(),
        model.getSoil(),
        model.getGrowTime() == null ? null : this.timeConverter.toDto(model.getGrowTime()),
        model.getRestTime() == null ? null : this.timeConverter.toDto(model.getRestTime())
      );
    }

    // TODO: NotFoundException - CARE_GROUP??
    final CareGroup careGroup = this.careGroupService.findById(model.getId());

    return new CareGroup(
      careGroup.getId(),
      careGroup.getName(),
      model.getHome() == null ? careGroup.getHome() : model.getHome(),
      model.getSoil() == null ? careGroup.getSoil() : model.getSoil(),
      model.getGrowTime() == null ? careGroup.getGrowTime()
        : this.timeConverter.toDto(model.getGrowTime(), careGroup.getGrowTime()),
      model.getRestTime() == null ? careGroup.getRestTime()
        : this.timeConverter.toDto(model.getRestTime(), careGroup.getRestTime())
    );
  }
}
