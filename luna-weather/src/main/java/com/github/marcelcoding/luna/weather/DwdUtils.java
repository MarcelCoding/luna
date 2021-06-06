package com.github.marcelcoding.luna.weather;

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

    System.out.println(data);

    return ZonedDateTime.of(
      LocalDate.parse(data.substring(0, 10)),
      LocalTime.parse(data.substring(11, 16)),
      DWD_OFFSET
    ).toOffsetDateTime();
  }
}
