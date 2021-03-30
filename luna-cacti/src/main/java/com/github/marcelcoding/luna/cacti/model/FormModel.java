package com.github.marcelcoding.luna.cacti.model;

import com.github.marcelcoding.luna.cacti.api.Form;
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
@Table(name = "cacti_form")
public class FormModel extends TableModelAutoId {

  @Column(name = "name", nullable = false, updatable = true, length = 128)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "specie_id", nullable = false, updatable = false)
  private SpecieModel specie;

  public FormModel(final Form form, final SpecieModel specieModel) {
    super(form.getId());

    assert form.getSpecieId() == specieModel.getId();

    this.name = form.getName();
    this.specie = specieModel;
  }
}
