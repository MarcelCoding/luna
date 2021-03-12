package com.github.marcelcoding.luna.dvb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DvbUtilsTest {

  @Test
  void getDate() {
    assertEquals(1512563081000L + 3600, DvbUtils.getDate("/Date(1512563081000+0100)/").toEpochSecond());
  }
}
