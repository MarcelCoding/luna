package com.github.marcelcoding.luna.cacti.api;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public final class Cactus {

  private final UUID id;
  @NotBlank
  private final String number;

  private final UUID genusId;
  private final UUID specieId;
  private final UUID formId;

  private final String fieldNumber;
  private final String synonymes;

  private final Acquisition acquisition;
  private final State state;
  private final List<HistoryEntry> history;

  private final String careGroupId;

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
  }

  @Data
  public static final class State {

    private final OffsetDateTime noLongerInPossessionTimestamp;
    private final String noLongerInPossessionReason;
    private final String vitality;
  }

  @Data
  public static final class HistoryEntry {

    private final UUID id;
    private final OffsetDateTime timestamp;
    private final String content;
  }
}
