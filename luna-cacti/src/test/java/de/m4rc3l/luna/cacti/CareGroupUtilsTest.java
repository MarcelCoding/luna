package de.m4rc3l.luna.cacti;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class CareGroupUtilsTest {

  @Test
  void getValue() {
    assertEquals("abc", CareGroupUtils.getValue("abc", null));
    assertEquals("abc", CareGroupUtils.getValue("abc", ""));
    assertEquals("abc", CareGroupUtils.getValue("abc", "  "));
    assertEquals("abc", CareGroupUtils.getValue(null, "abc"));
    assertNull(CareGroupUtils.getValue("abc", " abc "));
    assertEquals("abc", CareGroupUtils.getValue("cba", "abc"));
  }
}
