package de.m4rc3l.luna.cacti.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import de.m4rc3l.nova.jpa.model.TableModelAutoId;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cacti_genus")
public class GenusModel extends TableModelAutoId {

  @Column(name = "name", nullable = false, updatable = false,length = 128, unique = true)
  private String name;
}
