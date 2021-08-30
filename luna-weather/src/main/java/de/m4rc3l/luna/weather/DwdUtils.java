package de.m4rc3l.luna.weather;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public final class DwdUtils {

  private static final ZoneId DWD_OFFSET = ZoneId.of("Europe/Berlin");

  private DwdUtils() {
    throw new UnsupportedOperationException();
  }

  public static OffsetDateTime parse(final String data) {
    if (data == null) {
      return null;
    }

    return ZonedDateTime.of(
      LocalDate.parse(data.substring(0, 10)),
      LocalTime.parse(data.substring(11, 16)),
      DWD_OFFSET
    ).toOffsetDateTime();
  }

  public static byte convertPollenStrength(final String value) {
    return switch (value) {
      case "-1" -> -1;
      case "0" -> 0;
      case "0-1" -> 1;
      case "1" -> 2;
      case "1-2" -> 3;
      case "2" -> 4;
      case "2-3" -> 5;
      case "3" -> 6;
      default -> throw new IllegalArgumentException("Unknown pollen strength: " + value);
    };
  }
}
