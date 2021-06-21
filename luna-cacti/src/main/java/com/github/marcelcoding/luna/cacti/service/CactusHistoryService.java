package com.github.marcelcoding.luna.cacti.service;

import com.github.marcelcoding.luna.cacti.api.CactusHistoryEntry;
import com.github.marcelcoding.luna.cacti.converter.CactusHistoryEntryConverter;
import com.github.marcelcoding.luna.cacti.model.CactusHistoryEntryModel;
import com.github.marcelcoding.luna.cacti.model.CactusHistoryEntryModel.IdModel;
import com.github.marcelcoding.luna.cacti.model.CactusSmallModel;
import com.github.marcelcoding.luna.cacti.repository.CactusHistoryRepository;
import com.github.marcelcoding.luna.cacti.repository.CactusSmallRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import net.getnova.framework.core.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CactusHistoryService {

  private final CactusSmallRepository cactusRepository;
  private final CactusHistoryRepository cactusHistoryRepository;
  private final CactusHistoryEntryConverter cactusHistoryEntryConverter;

  public List<CactusHistoryEntry> findHistory(final UUID cactusId) {
    return this.cactusHistoryRepository.findAllOrderByDate(cactusId)
      .stream()
      .map(this.cactusHistoryEntryConverter::toDto)
      .toList();
  }

  public boolean exist(final UUID cactusId, final LocalDate date) {
    final CactusSmallModel cactusModel = this.cactusRepository.findById(cactusId)
      .orElseThrow(() -> new NotFoundException("CACTUS_NOT_FOUND"));

    return this.cactusHistoryRepository.existsById(new IdModel(cactusModel, date));
  }

  public CactusHistoryEntry create(
    final UUID cactusId,
    final CactusHistoryEntry historyEntry
  ) {
    final CactusSmallModel cactusModel = this.cactusRepository.findById(cactusId)
      .orElseThrow(() -> new NotFoundException("CACTUS_NOT_FOUND"));

    final CactusHistoryEntryModel model = this.cactusHistoryEntryConverter.toModel(historyEntry);

    model.getId().setCactus(cactusModel);

    return this.cactusHistoryEntryConverter.toDto(
      this.cactusHistoryRepository.save(model)
    );
  }

  @Transactional
  public CactusHistoryEntry edit(
    final UUID cactusId,
    final LocalDate date,
    final CactusHistoryEntry historyEntry
  ) {
    final CactusSmallModel cactusModel = this.cactusRepository.findById(cactusId)
      .orElseThrow(() -> new NotFoundException("CACTUS_NOT_FOUND"));

    final IdModel id = new IdModel(cactusModel, date);

    if (!this.cactusHistoryRepository.existsById(id)) {
      throw new NotFoundException("CACTUS_HISTORY_ENTRY_NOT_FOUND");
    }

    this.cactusHistoryRepository.deleteById(id);

    final CactusHistoryEntryModel model = this.cactusHistoryEntryConverter.toModel(historyEntry);

    model.getId().setCactus(cactusModel);

    return this.cactusHistoryEntryConverter.toDto(
      this.cactusHistoryRepository.save(model)
    );
  }

  public void delete(final UUID cactusId, final LocalDate date) {
    final CactusSmallModel cactusModel = this.cactusRepository.findById(cactusId)
      .orElseThrow(() -> new NotFoundException("CACTUS_NOT_FOUND"));

    final IdModel id = new IdModel(cactusModel, date);

    if (!this.cactusHistoryRepository.existsById(id)) {
      throw new NotFoundException("CACTUS_HISTORY_ENTRY_NOT_FOUND");
    }

    this.cactusHistoryRepository.deleteById(id);
  }
}
