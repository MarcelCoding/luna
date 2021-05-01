package com.github.marcelcoding.luna.cacti.converter;

import com.github.marcelcoding.luna.cacti.api.CactusHistoryEntry;
import com.github.marcelcoding.luna.cacti.model.CactusHistoryEntryModel;
import com.github.marcelcoding.luna.cacti.repository.CactusRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.getnova.framework.core.Converter;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CactusHistoryEntryConverter implements Converter<UUID, CactusHistoryEntryModel, CactusHistoryEntry> {

  private final CactusRepository cactusRepository;

  @Override
  public CactusHistoryEntryModel toModel(final CactusHistoryEntry dto) {
//    final CactusModel cactus = this.cactusRepository.findById(dto)
//      .orElseThrow(() -> new NotFoundException(id, "CACTUS_NOT_FOUND"));
//
//    return new CactusHistoryEntryModel(
//      new Id(
//        cactus,
//        dto.getTimestamp()
//      ),
//      dto.getContent()
//    );
    throw new NotImplementedException();
  }

  @Override
  public CactusHistoryEntry toDto(final CactusHistoryEntryModel model) {
    return new CactusHistoryEntry(
      model.getId().getTimestamp(),
      model.getContent()
    );
  }

  @Override
  public void override(final CactusHistoryEntryModel model, final CactusHistoryEntry dto) {
    throw new NotImplementedException();
  }

  @Override
  public void merge(final CactusHistoryEntryModel model, final CactusHistoryEntry dto) {
    throw new NotImplementedException();
  }
}
