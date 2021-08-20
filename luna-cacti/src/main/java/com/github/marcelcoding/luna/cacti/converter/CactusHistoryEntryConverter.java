package com.github.marcelcoding.luna.cacti.converter;

import com.github.marcelcoding.luna.cacti.dto.CactusHistoryEntry;
import com.github.marcelcoding.luna.cacti.model.CactusHistoryEntryModel;
import com.github.marcelcoding.luna.cacti.model.CactusHistoryEntryModel.IdModel;
import net.getnova.framework.core.Converter;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Component;

@Component
public class CactusHistoryEntryConverter implements Converter<CactusHistoryEntryModel, CactusHistoryEntry> {

  @Override
  public CactusHistoryEntryModel toModel(final CactusHistoryEntry dto) {
    return new CactusHistoryEntryModel(
      new IdModel(
        null,
        dto.getDate()
      ),
      dto.getContent()
    );
  }

  @Override
  public CactusHistoryEntry toDto(final CactusHistoryEntryModel model) {
    return new CactusHistoryEntry(
      model.getId().getDate(),
      model.getContent()
    );
  }

  @Override
  public void override(final CactusHistoryEntryModel model, final CactusHistoryEntry dto) {
    model.getId().setDate(dto.getDate());
    model.setContent(dto.getContent());
  }

  @Override
  public void merge(final CactusHistoryEntryModel model, final CactusHistoryEntry dto) {
    throw new NotImplementedException();
  }
}
