package com.github.marcelcoding.luna.cacti.model;

import com.github.marcelcoding.luna.cacti.api.Cactus.Acquisition;
import com.github.marcelcoding.luna.cacti.api.Cactus.State;
import com.github.marcelcoding.luna.cacti.api.CareGroup;
import com.github.marcelcoding.luna.cacti.api.CareGroup.Time;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.UUID;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.getnova.framework.jpa.model.TableModelAutoId;

@Setter
@Getter
@Entity
@NoArgsConstructor
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

  @Column(name = "synonyms", nullable = true, updatable = true, length = 1024)
  private String synonyms;

  @Embedded
  private StateModel state;

  @Embedded
  private AcquisitionModel acquisition;

  @Embedded
  private CareGroupModel careGroup;

  public CactusModel(
    final UUID id,
    final String number,
    final GenusModel genus,
    final SpecieModel specie,
    final FormModel form,
    final String fieldNumber,
    final String flowerColor,
    final String synonyms,
    final StateModel state,
    final AcquisitionModel acquisition,
    final CareGroupModel careGroup
  ) {
    super(id);
    this.number = number;
    this.genus = genus;
    this.specie = specie;
    this.form = form;
    this.fieldNumber = fieldNumber;
    this.flowerColor = flowerColor;
    this.synonyms = synonyms;
    this.state = state;
    this.acquisition = acquisition;
    this.careGroup = careGroup;
  }

  @Getter
  @Setter
  @Embeddable
  @NoArgsConstructor
  public static class StateModel {

    @Column(name = "state_no_longer_in_possession_timestamp", nullable = true, updatable = true)
    private OffsetDateTime noLongerInPossessionTimestamp;

    @Column(name = "state_no_longer_in_possession_reason", nullable = true, updatable = true)
    private String noLongerInPossessionReason;

    @Column(name = "state_vitality", nullable = true, updatable = true, length = 128)
    private String vitality;

    public StateModel(final State state) {
      this.noLongerInPossessionTimestamp = state.getNoLongerInPossessionTimestamp();
      this.noLongerInPossessionReason = state.getNoLongerInPossessionReason();
      this.vitality = state.getVitality();
    }

    public State toDto() {
      return new State(
        this.noLongerInPossessionTimestamp,
        this.noLongerInPossessionReason,
        this.vitality
      );
    }
  }

  @Getter
  @Setter
  @Embeddable
  @NoArgsConstructor
  public static class AcquisitionModel {

    @Column(name = "acquisition_timestamp", nullable = true, updatable = true)
    private OffsetDateTime timestamp;

    @Column(name = "acquisition_age", nullable = true, updatable = true)
    private Duration age;

    @Column(name = "acquisition_place", nullable = true, updatable = true, length = 512)
    private String place;

    @Column(name = "acquisition_plant_type", nullable = true, updatable = true, length = 512)
    private String plantType;

    public AcquisitionModel(final Acquisition acquisition) {
      this.timestamp = acquisition.getTimestamp();
      this.age = acquisition.getAge();
      this.place = acquisition.getPlace();
      this.plantType = acquisition.getPlantType();
    }

    public Acquisition toDto() {
      return new Acquisition(
        this.timestamp,
        this.age,
        this.place,
        this.plantType
      );
    }
  }

  @Getter
  @Setter
  @Embeddable
  @NoArgsConstructor
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

    public CareGroupModel(final CareGroup careGroup) {
      this.id = careGroup.getId();
      this.home = careGroup.getHome();
      this.soil = careGroup.getSoil();

      this.growTime = careGroup.getGrowTime() == null ? null : new TimeModel(careGroup.getGrowTime());
      this.restTime = careGroup.getRestTime() == null ? null : new TimeModel(careGroup.getRestTime());
    }

    public CareGroupModel(final CareGroup cactus, final CareGroup careGroup) {
      this.id = careGroup.getId();
      this.home = check(cactus.getHome(), careGroup.getHome());
      this.soil = check(cactus.getSoil(), careGroup.getSoil());

      if (cactus.getGrowTime() != null) {
        this.growTime = new TimeModel(cactus.getGrowTime(), careGroup.getGrowTime());
      }

      if (cactus.getRestTime() != null) {
        this.restTime = new TimeModel(cactus.getRestTime(), careGroup.getRestTime());
      }
    }

    // TODO: clean up
    private String check(String s1, final String s2) {
      if (s1 == null) {
        return s2;
      }
      else {
        s1 = s1.strip();
        if (s1.equals(s2)) {
          return null;
        }
        else {
          return s1;
        }
      }
    }

    @Getter
    @Setter
    @Embeddable
    @NoArgsConstructor
    public static class TimeModel {

      private String light;
      private String air;
      private String temperature;
      private String humidity;
      private String other;

      public TimeModel(final Time time) {
        this.light = time.getLight();
        this.air = time.getAir();
        this.temperature = time.getTemperature();
        this.humidity = time.getHumidity();
        this.other = time.getOther();
      }

      public TimeModel(final Time time, final Time otherTime) {
        this.light = check(time.getLight(), otherTime.getLight());
        this.air = check(time.getAir(), otherTime.getAir());
        this.temperature = check(time.getTemperature(), otherTime.getTemperature());
        this.humidity = check(time.getHumidity(), otherTime.getHumidity());
        this.other = check(time.getOther(), otherTime.getOther());
      }

      // TODO: clean up
      private String check(String s1, final String s2) {
        if (s1 == null) {
          return s2;
        }
        else {
          s1 = s1.strip();
          if (s1.equals(s2)) {
            return null;
          }
          else {
            return s1;
          }
        }
      }
    }
  }
}
