package de.m4rc3l.luna.dvb;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public final class DvbUtils {

  private DvbUtils() {
    throw new UnsupportedOperationException();
  }

  public static OffsetDateTime getDate(final String data) {
    if (data == null) {
      return null;
    }

    final String ms = data.substring(6, data.length() - 2);

    return Instant.ofEpochSecond(
      Long.parseLong(ms.substring(0, ms.length() - 5))
      + ZoneOffset.of(ms.substring(ms.length() - 5)).getTotalSeconds()
    ).atOffset(ZoneOffset.UTC);
  }
}
