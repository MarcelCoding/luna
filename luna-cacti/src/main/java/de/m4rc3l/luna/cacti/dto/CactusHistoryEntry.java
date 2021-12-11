package de.m4rc3l.luna.cacti.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;
import lombok.Data;
import de.m4rc3l.nova.core.Validatable;
import de.m4rc3l.nova.core.exception.ValidationException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.safety.Safelist;

@Data
public final class CactusHistoryEntry implements Validatable {

  private static final Safelist WHITELIST = Safelist.relaxed()
    .addEnforcedAttribute("a", "target", "_blank")
    .addEnforcedAttribute("a", "rel", "nofollow noopener noreferrer")
    .addTags("span")
    .addAttributes("span", "style");

  private static final OutputSettings OUTPUT_SETTINGS = new OutputSettings()
    .prettyPrint(false);

  private final LocalDate date;
  private final String content;

  @JsonCreator
  public CactusHistoryEntry(
    @JsonProperty("date") final LocalDate date,
    @JsonProperty("content") final String content
  ) {
    this.date = date;
    this.content = Jsoup.clean(content, "", WHITELIST, OUTPUT_SETTINGS);
  }

  @Override
  public void validate() throws ValidationException {
    if (this.content == null || this.content.isBlank()) {
      throw new ValidationException("content", "NO_BLANK");
    }
  }
}
