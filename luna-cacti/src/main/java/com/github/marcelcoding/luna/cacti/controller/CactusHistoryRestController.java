package com.github.marcelcoding.luna.cacti.controller;

import com.github.marcelcoding.luna.cacti.NotFoundException;
import com.github.marcelcoding.luna.cacti.api.CactusHistoryEntry;
import com.github.marcelcoding.luna.cacti.service.CactusHistoryService;
import com.github.marcelcoding.luna.cacti.service.CactusService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "CactusHistory")
@RestController
@RequiredArgsConstructor
@RequestMapping("/cacti/cactus")
public class CactusHistoryRestController {

  private final CactusService cactusService;
  private final CactusHistoryService cactusHistoryService;

  @GetMapping("{cactusId}/history")
  public List<CactusHistoryEntry> findById(@PathVariable("cactusId") final UUID cactusId) {
    return this.cactusHistoryService.findHistory(cactusId);
  }

  @PostMapping("{cactusId}/history")
  public CactusHistoryEntry post(
    @PathVariable("cactusId") final UUID cactusId,
    @RequestBody @Valid final CactusHistoryEntry historyEntry
  ) {
    return this.cactusHistoryService.save(cactusId, historyEntry);
  }

  @PutMapping("{cactusId}/history/{timestamp}")
  public CactusHistoryEntry put(
    @PathVariable("cactusId") final UUID cactusId,
    @PathVariable("timestamp") final OffsetDateTime timestamp,
    @RequestBody @Valid final CactusHistoryEntry historyEntry
  ) {
    if (!this.cactusService.exist(cactusId)) {
      throw new NotFoundException(cactusId, "CACTUS_HISTORY_ENTRY_NOT_FOUND");
    }

    if (!this.cactusHistoryService.exist(cactusId, timestamp)) {
      throw new NotFoundException(cactusId + "__" + timestamp, "CACTUS_HISTORY_ENTRY_NOT_FOUND");
    }

//    historyEntry.setTimestamp(timestamp);
    return this.cactusHistoryService.save(cactusId, historyEntry);
  }

  @DeleteMapping("{cactusId}/history/{timestamp}")
  public void deleteById(
    @PathVariable("cactusId") final UUID cactusId,
    @PathVariable("timestamp") final OffsetDateTime timestamp
  ) {
    this.cactusHistoryService.delete(cactusId, timestamp);
  }
}
