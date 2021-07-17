package com.github.marcelcoding.luna.weather.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * All influxdb2 available units.
 * <pre>
 * 1ns // 1 nanosecond
 * 1us // 1 microsecond
 * 1ms // 1 millisecond
 * 1s  // 1 second
 * 1m  // 1 minute
 * 1h  // 1 hour
 * 1d  // 1 day
 * 1w  // 1 week
 * 1mo // 1 calendar month
 * 1y  // 1 calendar year
 * </pre>
 */
@Getter
@RequiredArgsConstructor
public enum Resolution {

  MINUTELY("m"),
  HOURLY("h"),
  DAILY("d"),
  WEEKLY("w"),
  MONTHLY("mo"),
  YEARLY("y");

  private final String influxUnit;
}
