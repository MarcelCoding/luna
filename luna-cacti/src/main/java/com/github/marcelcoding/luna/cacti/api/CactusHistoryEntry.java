package com.github.marcelcoding.luna.cacti.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public final class CactusHistoryEntry {

  private final OffsetDateTime timestamp;
  @NotBlank
  private final String content;

  public CactusHistoryEntry(
    @JsonProperty("timestamp") final OffsetDateTime timestamp,
    @JsonProperty("content") final String content
  ) {
    this.timestamp = timestamp;
    this.content = content;
  }
}
