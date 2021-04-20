package com.github.marcelcoding.luna.cacti.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class Cactus {

  private final UUID id;
  @NotBlank
  private final String number;
  private final UUID genusId;
  private final UUID specieId;
  private final UUID formId;
  private final String fieldNumber;
  private final String flowerColor;
  private final String synonymes;
  private final Acquisition acquisition;
  private final State state;
  private final CareGroup careGroup;

  public Cactus(
    @JsonProperty("number") final String number,
    @JsonProperty("genusId") final UUID genusId,
    @JsonProperty("specieId") final UUID specieId,
    @JsonProperty("formId") final UUID formId,
    @JsonProperty("fieldNumber") final String fieldNumber,
    @JsonProperty("flowerColor") final String flowerColor,
    @JsonProperty("synonymes") final String synonymes,
    @JsonProperty("acquisition") final Acquisition acquisition,
    @JsonProperty("state") final State state,
    @JsonProperty("careGroup") final CareGroup careGroup
  ) {
    this.id = null;
    this.number = number;
    this.genusId = genusId;
    this.specieId = specieId;
    this.formId = formId;
    this.fieldNumber = fieldNumber;
    this.flowerColor = flowerColor;
    this.synonymes = synonymes;
    this.acquisition = acquisition;
    this.state = state;
    this.careGroup = careGroup;
  }

  public Duration getAge() {
    if (this.acquisition == null
      || this.acquisition.getTimestamp() == null
      || this.acquisition.getAge() == null) {
      return null;
    }

    return Duration.between(
      // born
      this.acquisition.getTimestamp().minus(this.acquisition.getAge()),
      // death or now
      Optional.ofNullable(this.state)
        .map(State::getNoLongerInPossessionTimestamp)
        .orElseGet(OffsetDateTime::now)
    );
  }

  @Data
  public static final class Acquisition {

    private final OffsetDateTime timestamp;
    private final Duration age;
    private final String place;
    private final String plantType;

    public Acquisition() {
      this.timestamp = null;
      this.age = null;
      this.place = null;
      this.plantType = null;
    }

    public Acquisition(
      @JsonProperty("timestamp") final OffsetDateTime timestamp,
      @JsonProperty("age") final Duration age,
      @JsonProperty("place") final String place,
      @JsonProperty("plantType") final String plantType
    ) {
      this.timestamp = timestamp;
      this.age = age;
      this.place = place;
      this.plantType = plantType;
    }
  }

  @Data
  public static final class State {

    private final OffsetDateTime noLongerInPossessionTimestamp;
    private final String noLongerInPossessionReason;
    private final String vitality;

    public State() {
      this.noLongerInPossessionTimestamp = null;
      this.noLongerInPossessionReason = null;
      this.vitality = null;
    }

    public State(
      @JsonProperty("noLongerInPossessionTimestamp") final OffsetDateTime noLongerInPossessionTimestamp,
      @JsonProperty("noLongerInPossessionReason") final String noLongerInPossessionReason,
      @JsonProperty("vitality") final String vitality
    ) {
      this.noLongerInPossessionTimestamp = noLongerInPossessionTimestamp;
      this.noLongerInPossessionReason = noLongerInPossessionReason;
      this.vitality = vitality;
    }
  }
}
