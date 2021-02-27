package com.github.marcelcoding.luna.cacti.model;

import com.github.marcelcoding.luna.cacti.dto.GenusDto;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "cacti_genus")
public class Genus extends TableModelAutoId implements ToDto<GenusDto> {

  @Column(name = "name", nullable = false, updatable = true, length = 128)
  private String name;

  @Override
  public GenusDto toDto() {
    return new GenusDto(
      this.getId(),
      this.getName()
    );
  }
}
