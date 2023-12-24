package de.m4rc3l.luna.cacti.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import de.m4rc3l.nova.jpa.model.TableModelAutoId;
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

  @Column(name = "field_number")
  private String fieldNumber;
}
