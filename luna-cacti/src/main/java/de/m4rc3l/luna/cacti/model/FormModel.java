package de.m4rc3l.luna.cacti.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import de.m4rc3l.nova.jpa.model.TableModelAutoId;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cacti_form")
public class FormModel extends TableModelAutoId {

  @Column(name = "name", nullable = false, updatable = false, length = 128)
  private String name;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "specie_id", nullable = false, updatable = false)
  private SpecieModel specie;
}
