package de.m4rc3l.luna.cacti.model;

import java.io.Serializable;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cacti_cactus_history")
public class CactusHistoryEntryModel {

  @EmbeddedId
  private IdModel id;

  @Column(name = "content", nullable = false, length = 2048)
  private String content;

  @Data
  @Embeddable
  @NoArgsConstructor
  @AllArgsConstructor
  public static final class IdModel implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cactus_id", nullable = false, updatable = false)
    private CactusSmallModel cactus;

    @Column(name = "date", nullable = false)
    private LocalDate date;
  }
}
