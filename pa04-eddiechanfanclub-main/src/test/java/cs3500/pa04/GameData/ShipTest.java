package cs3500.pa04.GameData;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Tests for a Ship object.
 */
public class ShipTest {

  /**
   * Tests the sunk state of the ship.
   */
  @Test
  public void testIsSunk() {
    List<Coord> shipCoords = Arrays.asList(
        new Coord(3, 5),
        new Coord(3, 6),
        new Coord(3, 7)
    );
    Ship ship = new Ship(ShipType.BATTLESHIP, shipCoords);

    assertFalse(ship.isSunk());

    ArrayList<Coord> shotCoords = new ArrayList<>();
    shotCoords.add(new Coord(3, 5));
    shotCoords.add(new Coord(3, 6));
    shotCoords.add(new Coord(3, 7));
    ship.receiveVolley(shotCoords);

    assertTrue(ship.isSunk());
  }

  /**
   * Tests receiving a volley.
   */
  @Test
  public void testReceiveVolley() {
    List<Coord> shipCoords = Arrays.asList(
        new Coord(3, 5),
        new Coord(3, 6),
        new Coord(3, 7)
    );
    Ship ship = new Ship(ShipType.BATTLESHIP, shipCoords);

    ArrayList<Coord> shotCoords = new ArrayList<>();
    shotCoords.add(new Coord(3, 5));
    shotCoords.add(new Coord(3, 8));
    shotCoords.add(new Coord(4, 7));

    ArrayList<Coord> hitShots = ship.receiveVolley(shotCoords);

    assertEquals(1, hitShots.size());
    assertTrue(hitShots.contains(new Coord(3, 5)));
    assertFalse(hitShots.contains(new Coord(3, 8)));
    assertFalse(hitShots.contains(new Coord(4, 7)));

    HashMap<Coord, String> expectedSegments = new HashMap<>();
    expectedSegments.put(new Coord(3, 5), "sunk");
    expectedSegments.put(new Coord(3, 6), "afloat");
    expectedSegments.put(new Coord(3, 7), "afloat");

    assertEquals(expectedSegments.get(new Coord(3, 5)),
        ship.getSegments().get(new Coord(3, 5)));
    assertEquals(expectedSegments.get(new Coord(3, 6)),
        ship.getSegments().get(new Coord(3, 6)));
  }

  /**
   * Tests getting the type of the ship.
   */
  @Test
  public void testGetShipType() {
    List<Coord> shipCoords = Arrays.asList(
        new Coord(3, 5),
        new Coord(3, 6)
    );
    Ship ship = new Ship(ShipType.DESTROYER, shipCoords);

    assertEquals(ShipType.DESTROYER, ship.getShipType());
  }

  /**
   * Tests getting the coords of the ship.
   */
  @Test
  public void testGetCoords() {
    List<Coord> shipCoords = Arrays.asList(
        new Coord(3, 5),
        new Coord(3, 6)
    );
    Ship ship = new Ship(ShipType.DESTROYER, shipCoords);

    assertEquals(shipCoords, ship.getCoords());
  }

  /**
   * Tests getting the direction of the ship.
   */
  @Test
  public void testGetDirection() {
    List<Coord> verticalCoords = Arrays.asList(
        new Coord(0, 0),
        new Coord(0, 1),
        new Coord(0, 2)
    );
    Ship verticalShip = new Ship(ShipType.SUBMARINE, verticalCoords);
    assertEquals(ShipDirection.VERTICAL, verticalShip.getDirection());

    List<Coord> horizontalCoords = Arrays.asList(
        new Coord(0, 0),
        new Coord(1, 0),
        new Coord(2, 0)
    );
    Ship horizontalShip = new Ship(ShipType.DESTROYER, horizontalCoords);
    assertEquals(ShipDirection.HORIZONTAL, horizontalShip.getDirection());
  }
}
