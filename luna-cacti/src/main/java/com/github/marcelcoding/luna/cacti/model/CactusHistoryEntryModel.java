package com.github.marcelcoding.luna.cacti.model;

import java.io.Serializable;
import java.time.LocalDate;
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
