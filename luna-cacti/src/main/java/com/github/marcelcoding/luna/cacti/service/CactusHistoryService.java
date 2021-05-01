package com.github.marcelcoding.luna.cacti.service;

import com.github.marcelcoding.luna.cacti.NotFoundException;
import com.github.marcelcoding.luna.cacti.api.CactusHistoryEntry;
import com.github.marcelcoding.luna.cacti.converter.CactusHistoryEntryConverter;
import com.github.marcelcoding.luna.cacti.model.CactusHistoryEntryModel.Id;
import com.github.marcelcoding.luna.cacti.model.CactusModel;
import com.github.marcelcoding.luna.cacti.repository.CactusHistoryRepository;
import com.github.marcelcoding.luna.cacti.repository.CactusRepository;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CactusHistoryService {

  private final CactusRepository cactusRepository;
  private final CactusHistoryRepository cactusHistoryRepository;
  private final CactusHistoryEntryConverter cactusHistoryEntryConverter;

  public List<CactusHistoryEntry> findHistory(final UUID cactusId) {
    return this.cactusHistoryRepository.findAllOrderByTimestamp(cactusId)
      .stream()
      .map(this.cactusHistoryEntryConverter::toDto)
      .toList();
  }

  public boolean exist(final UUID cactusId, final OffsetDateTime timestamp) {
    final CactusModel cactusModel = this.cactusRepository.findById(cactusId)
      .orElseThrow(() -> new NotFoundException(cactusId, "CACTUS_NOT_FOUND"));

    return this.cactusHistoryRepository.existsById(new Id(cactusModel, timestamp));
  }


  public CactusHistoryEntry save(final UUID cactusId, final CactusHistoryEntry historyEntry) {
    throw new NotImplementedException();
  }

  public void delete(final UUID cactusId, final OffsetDateTime timestamp) {
    final CactusModel cactusModel = this.cactusRepository.findById(cactusId)
      .orElseThrow(() -> new NotFoundException(cactusId, "CACTUS_NOT_FOUND"));

    final Id id = new Id(cactusModel, timestamp);

    if (!this.cactusHistoryRepository.existsById(id)) {
      throw new NotFoundException(cactusId + "__" + timestamp, "CACTUS_HISTORY_ENTRY_NOT_FOUND");
    }

    this.cactusHistoryRepository.deleteById(id);
  }
}
