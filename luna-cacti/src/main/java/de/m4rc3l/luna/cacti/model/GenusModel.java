package de.m4rc3l.luna.cacti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.getnova.framework.jpa.model.TableModelAutoId;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cacti_genus")
public class GenusModel extends TableModelAutoId {

  @Column(name = "name", nullable = false, length = 128, unique = true)
  private String name;
}
