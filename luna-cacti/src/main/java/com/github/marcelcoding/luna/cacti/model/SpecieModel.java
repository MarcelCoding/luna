package com.github.marcelcoding.luna.cacti.model;

import com.github.marcelcoding.luna.cacti.dto.Specie;
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
@Table(name = "cacti_specie")
public class SpecieModel extends TableModelAutoId implements ToDto<Specie> {

  @Column(name = "name", nullable = false, updatable = true, length = 128)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "genus_id", nullable = false, updatable = false)
  private GenusModel genus;

  @Override
  public Specie toDto() {
    return new Specie(
      this.getId(),
      this.getName(),
      this.getGenus().getId()
    );
  }
}
