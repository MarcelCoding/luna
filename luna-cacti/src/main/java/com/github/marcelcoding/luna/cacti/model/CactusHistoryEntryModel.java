package com.github.marcelcoding.luna.cacti.model;

import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.getnova.framework.jpa.model.TableModelAutoId;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "cacti_cactus_history")
public class CactusHistoryEntryModel extends TableModelAutoId {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cactus_id", nullable = false, updatable = false)
  private CactusModel cactus;

  @Column(name = "timestamp", nullable = false, updatable = true)
  private OffsetDateTime timestamp;

  @Column(name = "content", nullable = false, updatable = true, length = 2048)
  private String content;
}
