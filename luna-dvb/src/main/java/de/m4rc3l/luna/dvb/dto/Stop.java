package de.m4rc3l.luna.dvb.dto;

import lombok.Data;

@Data
public class Stop {

  private final int id;
  private final String city;
  private final String name;

  private final int x;
  private final int y;

  public static Stop of(final String data) {
    final String[] parts = data.split("\\|");

    return new Stop(
      Integer.parseInt(parts[0]),
      parts[2].isEmpty() ? "Dresden" : parts[2],
      parts[3],
      Integer.parseInt(parts[4]),
      Integer.parseInt(parts[5])
    );
  }
}
