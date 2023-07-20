package cs3500.pa04.GameData;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests for a Coord object.
 */
public class CoordTest {

  /**
   * Tests the coord getters.
   */
  @Test
  public void testGetters() {
    Coord coord = new Coord(3, 5);
    assertEquals(3, coord.getX());
    assertEquals(5, coord.getY());
  }

  /**
   * Tests coord equality.
   */
  @Test
  public void testEquals() {
    Coord coord1 = new Coord(3, 5);
    Coord coord2 = new Coord(3, 5);
    Coord coord3 = new Coord(2, 4);


    assertTrue(coord1.equals(coord2));
    assertTrue(coord1.equals(coord1));
    assertFalse(coord1.equals(coord3));
    assertFalse(coord1.equals("Not a Coord"));
  }
}
