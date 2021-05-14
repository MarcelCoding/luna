package com.github.marcelcoding.luna.cacti.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

@Data
public final class CactusHistoryEntry {

  private static final Whitelist WHITELIST = Whitelist.relaxed()
    .addEnforcedAttribute("a", "target", "_blank")
    .addEnforcedAttribute("a", "rel", "nofollow noopener noreferrer");

  private final LocalDate date;
  @NotBlank
  private final String content;

  public CactusHistoryEntry(
    @JsonProperty("date") final LocalDate date,
    @JsonProperty("content") final String content
  ) {
    this.date = date;
    this.content = Jsoup.clean(content, WHITELIST);
  }
}
