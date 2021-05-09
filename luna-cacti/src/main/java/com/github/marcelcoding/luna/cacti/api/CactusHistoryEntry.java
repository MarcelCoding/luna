package com.github.marcelcoding.luna.cacti.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public final class CactusHistoryEntry {

  private final LocalDate date;
  @NotBlank
  private final String content;

  public CactusHistoryEntry(
    @JsonProperty("date") final LocalDate date,
    @JsonProperty("content") final String content
  ) {
    this.date = date;
    this.content = content;
  }
}
