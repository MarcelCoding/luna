package de.m4rc3l.luna.weather.dto.dwd;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import de.m4rc3l.luna.weather.DwdUtils;
import lombok.Data;

@Data
public class Polle {

  private final byte today;
  private final byte tomorrow;
  private final byte dayAfterTomorrow;

  @JsonCreator
  public Polle(
    @JsonProperty("today") final String today,
    @JsonProperty("tomorrow") final String tomorrow,
    @JsonProperty("dayafter_to") final String dayAfterTomorrow
  ) {
    this.today = DwdUtils.convertPollenStrength(today);
    this.tomorrow = DwdUtils.convertPollenStrength(tomorrow);
    this.dayAfterTomorrow = DwdUtils.convertPollenStrength(dayAfterTomorrow);
  }
}
