package com.github.marcelcoding.luna.cacti.model;

import com.github.marcelcoding.luna.cacti.api.Specie;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.getnova.framework.jpa.model.TableModelAutoId;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "cacti_specie")
public class SpecieModel extends TableModelAutoId {

  @Column(name = "name", nullable = false, updatable = true, length = 128)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "genus_id", nullable = false, updatable = false)
  private GenusModel genus;

  public SpecieModel(final Specie specie, final GenusModel genusModel) {
    super(specie.getId());

    assert specie.getGenusId() == genusModel.getId();

    this.name = specie.getName();
    this.genus = genusModel;
  }
}
