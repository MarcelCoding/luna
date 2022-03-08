package de.m4rc3l.luna.cacti.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.m4rc3l.nova.core.Validatable;
import de.m4rc3l.nova.core.exception.ValidationException;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class Cactus implements Validatable {

  private final UUID id;
  private final String number;
  private final UUID genusId;
  private final UUID specieId;
  private final UUID formId;
  private final String fieldNumber;
  private final String flowerColor;
  private final Set<String> images;
  private final String synonymes;
  private final State state;
  private final Acquisition acquisition;
  private final CareGroup careGroup;

  @JsonCreator
  public Cactus(
    @JsonProperty("number") final String number,
    @JsonProperty("genusId") final UUID genusId,
    @JsonProperty("specieId") final UUID specieId,
    @JsonProperty("formId") final UUID formId,
    @JsonProperty("fieldNumber") final String fieldNumber,
    @JsonProperty("flowerColor") final String flowerColor,
    @JsonProperty("images") final Set<String> images,
    @JsonProperty("synonymes") final String synonymes,
    @JsonProperty("state") final State state,
    @JsonProperty("acquisition") final Acquisition acquisition,
    @JsonProperty("careGroup") final CareGroup careGroup
  ) {
    this.id = null;
    this.number = number;
    this.genusId = genusId;
    this.specieId = specieId;
    this.formId = formId;
    this.fieldNumber = fieldNumber;
    this.flowerColor = flowerColor;
    this.images = images;
    this.synonymes = synonymes;
    this.state = state;
    this.acquisition = acquisition;
    this.careGroup = careGroup;
  }

  @Override
  public void validate() throws ValidationException {
    if (this.number == null || this.number.isBlank()) {
      throw new ValidationException("number", "NO_BLANK");
    }
  }

  @Data
  public static final class State {

    private final OffsetDateTime noLongerInPossessionTimestamp;
    private final String noLongerInPossessionReason;
    private final String vitality;

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

  @Data
  public static final class Acquisition {

    private final OffsetDateTime timestamp;
    private final Duration age;
    private final String place;
    private final String plantType;

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

    public OffsetDateTime getBorn() {
      if (this.timestamp == null || this.age == null) {
        return null;
      }

      return this.timestamp.minus(this.age);
    }
  }
}
