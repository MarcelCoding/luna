package com.github.marcelcoding.luna.cacti.model;

import com.github.marcelcoding.luna.cacti.api.Cactus;
import com.github.marcelcoding.luna.cacti.api.Cactus.Acquisition;
import com.github.marcelcoding.luna.cacti.api.Cactus.State;
import com.github.marcelcoding.luna.cacti.api.CareGroup;
import com.github.marcelcoding.luna.cacti.api.CareGroup.Time;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Optional;
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
import net.getnova.framework.core.ToDto;
import net.getnova.framework.jpa.model.TableModelAutoId;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "cacti_cactus")
public class CactusModel extends TableModelAutoId implements ToDto<Cactus> {

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
  private AcquisitionModel acquisition;

  @Embedded
  private StateModel state;

  @Embedded
  private CareGroupModel careGroup;

  public CactusModel(
    final Cactus cactus,
    final GenusModel genusModel,
    final SpecieModel specieModel,
    final FormModel formModel,
    final CareGroup careGroup
  ) {
    super(cactus.getId());

    if (formModel != null) {
      assert cactus.getFormId() == formModel.getId();
      this.form = formModel;

      if (specieModel != null) {
        assert cactus.getSpecieId() == specieModel.getId();
        this.specie = specieModel;
      }
      else {
        this.specie = this.form.getSpecie();
      }

      if (genusModel != null) {
        assert cactus.getGenusId() == genusModel.getId();
        this.genus = genusModel;
      }
      else {
        this.genus = this.specie.getGenus();
      }
    }
    else if (specieModel != null) {
      assert cactus.getSpecieId() == specieModel.getId();
      this.specie = specieModel;

      if (genusModel != null) {
        assert cactus.getGenusId() == genusModel.getId();
        this.genus = genusModel;
      }
      else {
        this.genus = this.specie.getGenus();
      }
    }
    else if (genusModel != null) {
      assert cactus.getGenusId() == genusModel.getId();
      this.genus = genusModel;
    }

    this.number = cactus.getNumber();

    this.fieldNumber = cactus.getFieldNumber();
    this.synonyms = cactus.getSynonymes();

    this.acquisition = cactus.getAcquisition() == null ? null : new AcquisitionModel(cactus.getAcquisition());
    this.state = cactus.getState() == null ? null : new StateModel(cactus.getState());

    this.flowerColor = cactus.getFlowerColor();
    this.careGroup = cactus.getCareGroup() == null ? null : new CareGroupModel(cactus.getCareGroup(), careGroup);
  }

  @Override
  public Cactus toDto() {
    return new Cactus(
      this.getId(),
      this.number,

      this.genus == null ? null : this.genus.getId(),
      this.specie == null ? null : this.specie.getId(),
      this.form == null ? null : this.form.getId(),

      this.fieldNumber,
      this.flowerColor,
      this.synonyms,

      this.acquisition == null ? new Acquisition() : this.acquisition.toDto(),
      this.state == null ? new State() : this.state.toDto(),

      this.careGroup == null ? new CareGroup() : this.careGroup.toDto()
    );
//    this.id = model.getId();
//    this.number = model.getNumber();
//
//    this.genusId = model.getGenus() == null ? null : model.getGenus().getId();
//    this.specieId = model.getSpecie() == null ? null : model.getSpecie().getId();
//    this.formId = model.getForm() == null ? null : model.getForm().getId();
//
//    this.fieldNumber = model.getFieldNumber();
//    this.synonymes = model.getSynonyms();
//
//    this.acquisition = model.getAcquisition() == null ? new Acquisition() : new Acquisition(model.getAcquisition());
//    this.state = model.getState() == null ? new State() : new State(model.getState());
//
//    this.flowerColor = model.getFlowerColor();
//    this.careGroup =
//      model.getCareGroup() == null ? new CareGroup() : new CareGroup(model.getCareGroup()).merge(careGroup);
  }

  @Getter
  @Setter
  @Embeddable
  @NoArgsConstructor
  public static class AcquisitionModel implements ToDto<Acquisition> {

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

    @Override
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
  public static class StateModel implements ToDto<State> {

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

    @Override
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
  public static class CareGroupModel implements ToDto<CareGroup> {

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

    public CareGroupModel(final CareGroup cactus, final CareGroup careGroup) {
      this.id = careGroup.getId();
      this.home = Optional.ofNullable(cactus.getHome()).map(String::strip).orElseGet(careGroup::getHome);
      this.soil = Optional.ofNullable(cactus.getSoil()).map(String::strip).orElseGet(careGroup::getSoil);

      if (cactus.getGrowTime() == null) {
        this.growTime = new TimeModel(careGroup.getGrowTime());
      }
      else {
        this.growTime = new TimeModel(cactus.getGrowTime(), careGroup.getGrowTime());
      }

      if (cactus.getRestTime() == null) {
        this.restTime = new TimeModel(careGroup.getRestTime());
      }
      else {
        this.restTime = new TimeModel(cactus.getRestTime(), careGroup.getRestTime());
      }
    }

    @Override
    public CareGroup toDto() {
      return new CareGroup(
        this.id,
        "name??",
        this.home,
        this.soil,
        this.growTime == null ? new Time() : this.growTime.toDto(),
        this.restTime == null ? new Time() : this.restTime.toDto()
      );
    }

    @Getter
    @Setter
    @Embeddable
    @NoArgsConstructor
    public static class TimeModel implements ToDto<Time> {

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
        final Optional<String> light = Optional.ofNullable(time.getLight()).map(String::strip);
        final Optional<String> air = Optional.ofNullable(time.getAir()).map(String::strip);
        final Optional<String> temperature = Optional.ofNullable(time.getTemperature()).map(String::strip);
        final Optional<String> humidity = Optional.ofNullable(time.getHumidity()).map(String::strip);
        final Optional<String> other = Optional.ofNullable(time.getOther()).map(String::strip);

        if (light.equals(Optional.ofNullable(otherTime.getLight()))) {
          this.light = null;
        }

        if (air.equals(Optional.ofNullable(otherTime.getAir()))) {
          this.air = null;
        }

        if (temperature.equals(Optional.ofNullable(otherTime.getTemperature()))) {
          this.temperature = null;
        }

        if (humidity.equals(Optional.ofNullable(otherTime.getHumidity()))) {
          this.humidity = null;
        }

        if (other.equals(Optional.ofNullable(otherTime.getOther()))) {
          this.other = null;
        }
      }

      @Override
      public Time toDto() {
        return new Time(
          this.light,
          this.air,
          this.temperature,
          this.humidity,
          this.other
        );
      }
    }
  }
}
