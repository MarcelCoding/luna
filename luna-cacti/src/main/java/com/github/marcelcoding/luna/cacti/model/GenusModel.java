package com.github.marcelcoding.luna.cacti.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.getnova.framework.jpa.model.TableModelAutoId;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "cacti_genus")
public class GenusModel extends TableModelAutoId {

  @Column(name = "name", nullable = false, updatable = true, length = 128, unique = true)
  private String name;

  public GenusModel(final String name) {
    this.name = name;
  }
}
