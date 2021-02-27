package com.github.marcelcoding.luna.cacti.model;

import com.github.marcelcoding.luna.cacti.dto.CactusHistoryDto;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.getnova.framework.core.ToDto;
import net.getnova.framework.jpa.model.TableModelAutoId;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cacti_cactus_history")
public class CactusHistory extends TableModelAutoId implements ToDto<CactusHistoryDto> {

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "cactus_id", nullable = false, updatable = false)
  private Cactus cactus;

  @Column(name = "timestamp", nullable = false, updatable = true)
  private OffsetDateTime timestamp;

  @Column(name = "content", nullable = false, updatable = true, length = 2048)
  private String content;

  @Override
  public CactusHistoryDto toDto() {
    return new CactusHistoryDto(
      this.getId(),
      this.getCactus().getId(),
      this.getTimestamp(),
      this.getContent()
    );
  }
}
