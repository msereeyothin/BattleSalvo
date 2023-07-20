package cs3500.pa04.GameData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/**
 * Tests for the ShipType enumeration.
 */
class ShipTypeTest {

  /**
   * Tests the ShipType getters.
   */
  @Test
  void testShipTypeGetters() {
    assertEquals(3, ShipType.SUBMARINE.getSize());
    assertEquals(4, ShipType.DESTROYER.getSize());
    assertEquals(5, ShipType.BATTLESHIP.getSize());
    assertEquals(6, ShipType.CARRIER.getSize());

    assertEquals(ShipType.SUBMARINE, ShipType.getTypeByString("SUBMARINE"));
    assertEquals(ShipType.DESTROYER, ShipType.getTypeByString("DESTROYER"));
    assertEquals(ShipType.BATTLESHIP, ShipType.getTypeByString("BATTLESHIP"));
    assertEquals(ShipType.CARRIER, ShipType.getTypeByString("CARRIER"));
    assertNull(ShipType.getTypeByString("NULL"));

  }
}