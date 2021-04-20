package com.github.marcelcoding.luna.cacti.model;

import com.github.marcelcoding.luna.cacti.api.CactusHistoryEntry;
import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.getnova.framework.core.ToDto;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "cacti_cactus_history")
public class CactusHistoryEntryModel implements ToDto<CactusHistoryEntry> {

  @EmbeddedId
  private Id id;

  @Column(name = "content", nullable = false, updatable = true, length = 2048)
  private String content;

  public CactusHistoryEntryModel(final CactusHistoryEntry historyEntry, final CactusModel cactusModel) {
    this.id = new Id(cactusModel, historyEntry.getTimestamp());
    this.content = historyEntry.getContent();
  }

  @Override
  public CactusHistoryEntry toDto() {
    return new CactusHistoryEntry(
      this.id.getTimestamp(),
      this.content
    );
  }

  @Data
  @Embeddable
  @NoArgsConstructor
  @AllArgsConstructor
  public static final class Id implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cactus_id", nullable = false, updatable = false)
    private CactusModel cactus;

    @Column(name = "timestamp", nullable = false, updatable = true)
    private OffsetDateTime timestamp;
  }
}
