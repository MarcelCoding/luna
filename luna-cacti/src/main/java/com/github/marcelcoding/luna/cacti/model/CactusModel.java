package com.github.marcelcoding.luna.cacti.model;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.function.Function;
import javax.persistence.Column;
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

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cacti_cactus")
public class CactusModel extends TableModelAutoId {

  @Column(name = "number", nullable = false, updatable = true, length = 128, unique = true)
  private String number;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "genus_id", nullable = true, updatable = true)
  private GenusModel genus;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "specie_id", nullable = true, updatable = true)
  private SpecieModel specie;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "form_id", nullable = true, updatable = true)
  private FormModel form;

  @Column(name = "field_number", nullable = true, updatable = true, length = 128)
  private String fieldNumber;

  @Column(name = "synonyms", nullable = true, updatable = true, length = 1024)
  private String synonyms;

  @Embedded
  private Acquisition acquisition;

  @Embedded
  private State state;

  @Column(name = "flower_color", nullable = true, updatable = true, length = 128)
  private String flowerColor;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "care_group_id", nullable = true, updatable = true)
  private CareGroupModel careGroup;

  @Column(name = "care_group_home", nullable = true, updatable = true, length = 512)
  private String careGroupHome;

  @Column(name = "care_group_soil", nullable = true, updatable = true, length = 512)
  private String careGroupSoil;

  @Column(name = "care_group_grow_time_light", nullable = true, updatable = true, length = 512)
  private String careGroupGrowTimeLight;

  @Column(name = "care_group_grow_time_air", nullable = true, updatable = true, length = 512)
  private String careGroupGrowTimeAir;

  @Column(name = "care_group_grow_time_temperature", nullable = true, updatable = true, length = 512)
  private String careGroupGrowTimeTemperature;

  @Column(name = "care_group_grow_time_humidity", nullable = true, updatable = true, length = 512)
  private String careGroupGrowTimeHumidity;

  @Column(name = "care_group_grow_time_other", nullable = true, updatable = true, length = 1024)
  private String careGroupGrowTimeOther;

  @Column(name = "care_group_rest_time_light", nullable = true, updatable = true, length = 512)
  private String careGroupRestTimeLight;

  @Column(name = "care_group_rest_time_air", nullable = true, updatable = true, length = 512)
  private String careGroupRestTimeAir;

  @Column(name = "care_group_rest_time_temperature", nullable = true, updatable = true, length = 512)
  private String careGroupRestTimeTemperature;

  @Column(name = "care_group_rest_time_humidity", nullable = true, updatable = true, length = 512)
  private String careGroupRestTimeHumidity;

  @Column(name = "care_group_rest_time_other", nullable = true, updatable = true, length = 1024)
  private String careGroupRestTimeOther;

  public CactusModel(final String number) {
    this.number = number;
  }

//  public final ObjectNode serializeSmall() {
//    return JsonBuilder.create("id", this.getId())
//      .add("number", this.getNumber())
//
//      .add("genusId", this.getGenus() == null ? null : this.getGenus().getId())
//      .add("specieId", this.getSpecie() == null ? null : this.getSpecie().getId())
//      .add("formId", this.getForm() == null ? null : this.getForm().getId())
//      .build();
//  }

//  @Override
//  public void serialize(final JsonGenerator gen, final SerializerProvider serializers) throws IOException {
//    final CareGroup careGroup = this.getCareGroup();
//
//    JsonWriter.create(gen)
//      .write(this.serializeSmall())
//      .write("fieldNumber", this.getFieldNumber())
//      .write("synonyms", this.getSynonyms())
//
//      .write("acquisition", this.getAcquisition())
//
//      .write("state",
//        this.getState() == null
//          ? JsonBuilder.create("age", this.getAge())
//          : JsonBuilder.create(this.getState()).add("age", this.getAge())
//      )
//
//      .write("flowerColor", this.getFlowerColor())
//
//      .write("careGroup", JsonBuilder
//        .create("id", careGroup == null ? null : careGroup.getId())
//        .add("name", careGroup == null ? null : careGroup.getName())
//
//        .add("home", this.getCareGroupValue(careGroup, this.getCareGroupHome(), CareGroup::getHome))
//        .add("soil", this.getCareGroupValue(careGroup, this.getCareGroupSoil(), CareGroup::getSoil))
//
//        .add("growTime", JsonBuilder
//          .create("light",
//            this.getCareGroupValue(careGroup, this.getCareGroupGrowTimeLight(), CareGroup::getGrowTimeLight))
//          .add("air", this.getCareGroupValue(careGroup, this.getCareGroupGrowTimeAir(), CareGroup::getGrowTimeAir))
//          .add("temperature", this
//            .getCareGroupValue(careGroup, this.getCareGroupGrowTimeTemperature(), CareGroup::getGrowTimeTemperature))
//          .add("humidity",
//            this.getCareGroupValue(careGroup, this.getCareGroupGrowTimeHumidity(), CareGroup::getGrowTimeHumidity))
//          .add("other",
//            this.getCareGroupValue(careGroup, this.getCareGroupGrowTimeOther(), CareGroup::getGrowTimeOther))
//        )
//
//        .add("restTime", JsonBuilder
//          .create("light",
//            this.getCareGroupValue(careGroup, this.getCareGroupRestTimeLight(), CareGroup::getRestTimeLight))
//          .add("air", this.getCareGroupValue(careGroup, this.getCareGroupRestTimeAir(), CareGroup::getRestTimeAir))
//          .add("temperature", this
//            .getCareGroupValue(careGroup, this.getCareGroupRestTimeTemperature(), CareGroup::getRestTimeTemperature))
//          .add("humidity",
//            this.getCareGroupValue(careGroup, this.getCareGroupRestTimeHumidity(), CareGroup::getRestTimeHumidity))
//          .add("other",
//            this.getCareGroupValue(careGroup, this.getCareGroupRestTimeOther(), CareGroup::getRestTimeOther))
//        )
//      )
//      .close();
//  }

  public final Duration getAge() {
    if (this.getAcquisition() == null
      || this.getAcquisition().getTimestamp() == null
      || this.getAcquisition().getAge() == null) {
      return null;
    }

    return Duration.between(
      this.getAcquisition().getTimestamp().minus(this.getAcquisition().getAge()),
      Optional.ofNullable(this.getState()).map(State::getNoLongerInPossessionTimestamp).orElseGet(OffsetDateTime::now)
    );
  }

  private <T> T getCareGroupValue(final CareGroupModel careGroup, final T cactusValue,
    final Function<CareGroupModel, T> careGroupValue) {
    return cactusValue == null ? careGroup == null ? null : careGroupValue.apply(careGroup) : cactusValue;
  }

  @Getter
  @Setter
  @Embeddable
  @NoArgsConstructor
  @AllArgsConstructor
  public static class Acquisition {

    @Column(name = "acquisition_timestamp", nullable = true, updatable = true)
    private OffsetDateTime timestamp;

    @Column(name = "acquisition_age", nullable = true, updatable = true)
    private Duration age;

    @Column(name = "acquisition_place", nullable = true, updatable = true, length = 512)
    private String place;

    @Column(name = "acquisition_plant_type", nullable = true, updatable = true, length = 512)
    private String plantType;

//    @Override
//    public void serialize(final JsonGenerator gen, final SerializerProvider serializers) throws IOException {
//      JsonWriter.create(gen)
//        .write("timestamp", this.getTimestamp())
//        .write("age", this.getAge())
//        .write("place", this.getPlace())
//        .write("plantType", this.getPlantType())
//        .close();
//    }
  }

  @Getter
  @Setter
  @Embeddable
  @NoArgsConstructor
  @AllArgsConstructor
  public static class State {

    @Column(name = "state_no_longer_in_possession_timestamp", nullable = true, updatable = true)
    private OffsetDateTime noLongerInPossessionTimestamp;

    @Column(name = "state_no_longer_in_possession_reason", nullable = true, updatable = true)
    private String noLongerInPossessionReason;

    @Column(name = "state_vitality", nullable = true, updatable = true, length = 128)
    private String vitality;

//    @Override
//    public void serialize(final JsonGenerator gen, final SerializerProvider serializers) throws IOException {
//      JsonWriter.create(gen)
//        .write("noLongerInPossessionTimestamp", this.getNoLongerInPossessionTimestamp())
//        .write("noLongerInPossessionReason", this.getNoLongerInPossessionReason())
//        .write("vitality", this.getVitality())
//        .close();
//    }
  }
}
