package de.m4rc3l.luna.cacti.model;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Set;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
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

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cacti_cactus")
public class CactusModel extends TableModelAutoId {

  @Column(name = "number", nullable = false, length = 128, unique = true)
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

  @Column(name = "field_number", length = 128)
  private String fieldNumber;

  @Column(name = "flower_color", length = 128)
  private String flowerColor;

  @ElementCollection(fetch = FetchType.EAGER)
  @Column(name = "images")
  private Set<String> images;

  @Column(name = "synonyms", length = 1024)
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

    @Column(name = "state_no_longer_in_possession_timestamp")
    private OffsetDateTime noLongerInPossessionTimestamp;

    @Column(name = "state_no_longer_in_possession_reason")
    private String noLongerInPossessionReason;

    @Column(name = "state_vitality", length = 128)
    private String vitality;
  }

  @Getter
  @Setter
  @Embeddable
  @NoArgsConstructor
  @AllArgsConstructor
  public static class AcquisitionModel {

    @Column(name = "acquisition_timestamp")
    private OffsetDateTime timestamp;

    @Column(name = "acquisition_age")
    private Duration age;

    @Column(name = "acquisition_place", length = 512)
    private String place;

    @Column(name = "acquisition_plant_type", length = 512)
    private String plantType;
  }

  @Getter
  @Setter
  @Embeddable
  @NoArgsConstructor
  @AllArgsConstructor
  public static class CareGroupModel {

    @Column(name = "care_group_id")
    private String id;

    @Column(name = "care_group_home", length = 512)
    private String home;

    @Column(name = "care_group_soil", length = 512)
    private String soil;

    @Embedded
    @AttributeOverride(name = "light", column = @Column(name = "care_group_grow_time_light", length = 512))
    @AttributeOverride(name = "air", column = @Column(name = "care_group_grow_time_air", length = 512))
    @AttributeOverride(name = "temperature", column = @Column(name = "care_group_grow_time_temperature", length = 512))
    @AttributeOverride(name = "humidity", column = @Column(name = "care_group_grow_time_humidity", length = 512))
    @AttributeOverride(name = "other", column = @Column(name = "care_group_grow_time_other", length = 1024))
    private TimeModel growTime;

    @Embedded
    @AttributeOverride(name = "light", column = @Column(name = "care_group_rest_time_light", length = 512))
    @AttributeOverride(name = "air", column = @Column(name = "care_group_rest_time_air", length = 512))
    @AttributeOverride(name = "temperature", column = @Column(name = "care_group_rest_time_temperature", length = 512))
    @AttributeOverride(name = "humidity", column = @Column(name = "care_group_rest_time_humidity", length = 512))
    @AttributeOverride(name = "other", column = @Column(name = "care_group_rest_time_other", length = 1024))
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
