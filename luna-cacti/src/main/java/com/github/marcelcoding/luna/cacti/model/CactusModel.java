package com.github.marcelcoding.luna.cacti.model;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Set;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "cacti_cactus")
public class CactusModel extends TableModelAutoId {

  @Column(name = "number", nullable = false, updatable = true, length = 128, unique = true)
  private String number;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "genus_id", nullable = true, updatable = true)
  private GenusModel genus;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "specie_id", nullable = true, updatable = true)
  private SpecieModel specie;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "form_id", nullable = true, updatable = true)
  private FormModel form;

  @Column(name = "field_number", nullable = true, updatable = true, length = 128)
  private String fieldNumber;

  @Column(name = "flower_color", nullable = true, updatable = true, length = 128)
  private String flowerColor;

  @ElementCollection(fetch = FetchType.EAGER)
  @Column(name = "images", nullable = true, updatable = true)
  private Set<String> images;

  @Column(name = "synonyms", nullable = true, updatable = true, length = 1024)
  private String synonyms;

  @Embedded
  private StateModel state;

  @Embedded
  private AcquisitionModel acquisition;

  @Embedded
  private CareGroupModel careGroup;

  @Getter
  @Setter
  @Embeddable
  @NoArgsConstructor
  @AllArgsConstructor
  public static class StateModel {

    @Column(name = "state_no_longer_in_possession_timestamp", nullable = true, updatable = true)
    private OffsetDateTime noLongerInPossessionTimestamp;

    @Column(name = "state_no_longer_in_possession_reason", nullable = true, updatable = true)
    private String noLongerInPossessionReason;

    @Column(name = "state_vitality", nullable = true, updatable = true, length = 128)
    private String vitality;
  }

  @Getter
  @Setter
  @Embeddable
  @NoArgsConstructor
  @AllArgsConstructor
  public static class AcquisitionModel {

    @Column(name = "acquisition_timestamp", nullable = true, updatable = true)
    private OffsetDateTime timestamp;

    @Column(name = "acquisition_age", nullable = true, updatable = true)
    private Duration age;

    @Column(name = "acquisition_place", nullable = true, updatable = true, length = 512)
    private String place;

    @Column(name = "acquisition_plant_type", nullable = true, updatable = true, length = 512)
    private String plantType;
  }

  @Getter
  @Setter
  @Embeddable
  @NoArgsConstructor
  @AllArgsConstructor
  public static class CareGroupModel {

    @Column(name = "care_group_id", nullable = true, updatable = true)
    private String id;

    @Column(name = "care_group_home", nullable = true, updatable = true, length = 512)
    private String home;

    @Column(name = "care_group_soil", nullable = true, updatable = true, length = 512)
    private String soil;

    @Embedded
    @AttributeOverride(name = "light", column = @Column(name = "care_group_grow_time_light", nullable = true, updatable = true, length = 512))
    @AttributeOverride(name = "air", column = @Column(name = "care_group_grow_time_air", nullable = true, updatable = true, length = 512))
    @AttributeOverride(name = "temperature", column = @Column(name = "care_group_grow_time_temperature", nullable = true, updatable = true, length = 512))
    @AttributeOverride(name = "humidity", column = @Column(name = "care_group_grow_time_humidity", nullable = true, updatable = true, length = 512))
    @AttributeOverride(name = "other", column = @Column(name = "care_group_grow_time_other", nullable = true, updatable = true, length = 1024))
    private TimeModel growTime;

    @Embedded
    @AttributeOverride(name = "light", column = @Column(name = "care_group_rest_time_light", nullable = true, updatable = true, length = 512))
    @AttributeOverride(name = "air", column = @Column(name = "care_group_rest_time_air", nullable = true, updatable = true, length = 512))
    @AttributeOverride(name = "temperature", column = @Column(name = "care_group_rest_time_temperature", nullable = true, updatable = true, length = 512))
    @AttributeOverride(name = "humidity", column = @Column(name = "care_group_rest_time_humidity", nullable = true, updatable = true, length = 512))
    @AttributeOverride(name = "other", column = @Column(name = "care_group_rest_time_other", nullable = true, updatable = true, length = 1024))
    private TimeModel restTime;

    @Getter
    @Setter
    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TimeModel {

      private String light;
      private String air;
      private String temperature;
      private String humidity;
      private String other;
    }
  }
}
