package de.m4rc3l.luna.cacti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.getnova.framework.jpa.model.TableModelAutoId;
import org.hibernate.annotations.Immutable;

@Getter
@Entity
@Immutable
@NoArgsConstructor
@Table(name = "cacti_cactus")
public class CactusSmallModel extends TableModelAutoId {

  @Column(name = "number")
  private String number;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "genus_id")
  private GenusModel genus;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "specie_id")
  private SpecieModel specie;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "form_id")
  private FormModel form;
}
