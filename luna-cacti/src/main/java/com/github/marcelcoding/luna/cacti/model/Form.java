package com.github.marcelcoding.luna.cacti.model;

import com.github.marcelcoding.luna.cacti.dto.FormDto;
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
@Table(name = "cacti_form")
public class Form extends TableModelAutoId implements ToDto<FormDto> {

  @Column(name = "name", nullable = false, updatable = true, length = 128)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "specie_id", nullable = false, updatable = false)
  private Specie specie;

  @Override
  public FormDto toDto() {
    return new FormDto(
      this.getId(),
      this.getName(),
      this.getSpecie().getId()
    );
  }
}
