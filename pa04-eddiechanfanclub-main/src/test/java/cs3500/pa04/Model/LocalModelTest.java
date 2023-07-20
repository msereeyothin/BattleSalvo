package cs3500.pa04.Model;

import static cs3500.pa04.Model.LocalModel.createShips;
import static cs3500.pa04.Model.LocalModel.generateRandomShipMap;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa04.GameData.Coord;
import cs3500.pa04.GameData.Ship;
import cs3500.pa04.GameData.ShipType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * Tests for the Local Model.
 */
class LocalModelTest {
  /**
   * Tests ship creation.
   */
  @Test
  void testCreateShips() {
    LocalModel testCreateModel = new LocalModel();
    int height = 10;
    int width = 10;

    Map<ShipType, Integer> specifications = new HashMap<>();
    specifications.put(ShipType.SUBMARINE, 2);
    specifications.put(ShipType.DESTROYER, 1);
    specifications.put(ShipType.BATTLESHIP, 1);
    specifications.put(ShipType.CARRIER, 1);

    List<Ship> ships = createShips(height, width, specifications);

    // Verify that ships are within the board bounds
    for (Ship ship : ships) {
      for (Coord coord : ship.getCoords()) {
        int x = coord.getX();
        int y = coord.getY();
        assert x >= 0 && x < width;
        assert y >= 0 && y < height;
      }
    }

  }

  /**
   * Tests creating ships which span the length of the whole board.
   */
  @Test
  void testCreateShipMaxLength() {
    int height = 6;
    int width = 6;

    Map<ShipType, Integer> specifications = new HashMap<>();
    specifications.put(ShipType.SUBMARINE, 0);
    specifications.put(ShipType.DESTROYER, 0);
    specifications.put(ShipType.BATTLESHIP, 0);
    specifications.put(ShipType.CARRIER, 1);

    List<Ship> ships = createShips(height, width, specifications);
    // Verify that ships are within the board bounds
    for (Ship ship : ships) {
      for (Coord coord : ship.getCoords()) {
        int x = coord.getX();
        int y = coord.getY();
        assert x >= 0 && x < width;
        assert y >= 0 && y < height;
      }
    }
  }

  /**
   * Tests generating random ship frequencies.
   */
  @Test
  void testGenerateRandomShipMap() {
    int height = 10;
    int width = 8;
    HashMap<ShipType, Integer> shipMap = generateRandomShipMap(height, width);
    int shipSum = 0;
    for (int shipFreq : shipMap.values()) {
      shipSum += shipFreq;
    }
    assertTrue(shipSum <= Math.min(height, width));
  }
}