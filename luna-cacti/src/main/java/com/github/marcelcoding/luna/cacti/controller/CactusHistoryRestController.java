package com.github.marcelcoding.luna.cacti.controller;

import com.github.marcelcoding.luna.cacti.api.CactusHistoryEntry;
import com.github.marcelcoding.luna.cacti.service.CactusHistoryService;
import com.github.marcelcoding.luna.cacti.service.CactusService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDate;
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
    return this.cactusHistoryService.create(cactusId, historyEntry);
  }

  @PutMapping("{cactusId}/history/{date}")
  public CactusHistoryEntry put(
    @PathVariable("cactusId") final UUID cactusId,
    @PathVariable("date") final LocalDate date,
    @RequestBody @Valid final CactusHistoryEntry historyEntry
  ) {
    return this.cactusHistoryService.edit(cactusId, date, historyEntry);
  }

  @DeleteMapping("{cactusId}/history/{date}")
  public void deleteById(
    @PathVariable("cactusId") final UUID cactusId,
    @PathVariable("date") final LocalDate date
  ) {
    this.cactusHistoryService.delete(cactusId, date);
  }
}
