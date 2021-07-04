package com.github.marcelcoding.luna.cacti;

public final class CareGroupUtils {

  private CareGroupUtils() {
    throw new UnsupportedOperationException();
  }

  public static String getValue(final String orig, final String dto) {
    if (dto == null) {
      return orig;
    }

    final String val = dto.strip();

    if (val.isEmpty()) {
      return orig;
    }

    if (orig == null) {
      return val;
    }

    if (orig.equalsIgnoreCase(val)) {
      return null;
    }

    return dto;
  }
}
