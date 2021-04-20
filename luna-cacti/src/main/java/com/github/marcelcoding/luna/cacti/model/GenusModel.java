package com.github.marcelcoding.luna.cacti.model;

import com.github.marcelcoding.luna.cacti.api.Genus;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.getnova.framework.core.ToDto;
import net.getnova.framework.jpa.model.TableModelAutoId;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "cacti_genus")
public class GenusModel extends TableModelAutoId implements ToDto<Genus> {

  @Column(name = "name", nullable = false, updatable = true, length = 128)
  private String name;

  public GenusModel(final Genus genus) {
    super(genus.getId());
    this.name = genus.getName();
  }

  @Override
  public Genus toDto() {
    return new Genus(
      this.getId(),
      this.name
    );
  }
}
