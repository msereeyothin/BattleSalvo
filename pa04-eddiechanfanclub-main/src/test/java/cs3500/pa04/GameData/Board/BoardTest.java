package cs3500.pa04.GameData.Board;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa04.GameData.Coord;
import cs3500.pa04.GameData.Ship;
import cs3500.pa04.GameData.ShipType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Tests for the board class.
 */
class BoardTest {
  /**
   * Tests getting a board with ships and shots and misses.
   */
  @Test
  void testGetCurrentBoardAndReceiveShots() {
    List<Ship> ships = new ArrayList<>();
    Ship ship1 = new Ship(ShipType.CARRIER,
        Arrays.asList(new Coord(0, 0), new Coord(0, 1), new Coord(0, 2), new Coord(0, 3),
            new Coord(0, 4), new Coord(0, 5)));
    ships.add(ship1);
    Ship ship2 = new Ship(ShipType.BATTLESHIP,
        Arrays.asList(new Coord(2, 2), new Coord(2, 3), new Coord(2, 4), new Coord(2, 5),
            new Coord(2, 6)));
    ships.add(ship2);
    Board board = new Board(7, 7, ships);

    // Perform shots
    board.receiveShots(
        Arrays.asList(new Coord(0, 0), new Coord(2, 2), new Coord(2, 4), new Coord(5, 5)));

    String[][] expectedBoard = {
        {"H", "0", "0", "0", "0", "0", "0"},
        {"S", "0", "0", "0", "0", "0", "0"},
        {"S", "0", "H", "0", "0", "0", "0"},
        {"S", "0", "S", "0", "0", "0", "0"},
        {"S", "0", "H", "0", "0", "0", "0"},
        {"S", "0", "S", "0", "0", "M", "0"},
        {"0", "0", "S", "0", "0", "0", "0"}
    };
    String[][] actualBoard = board.getCurrentBoard();
    assertArrayEquals(expectedBoard, actualBoard);
  }

  /**
   * Tests the board's getters.
   */
  @Test
  void testGetBoardGetters() {
    List<Ship> ships = new ArrayList<>();
    Ship ship1 = new Ship(ShipType.BATTLESHIP,
        Arrays.asList(new Coord(2, 2), new Coord(2, 3), new Coord(2, 4), new Coord(2, 5),
            new Coord(2, 6)));
    ships.add(ship1);
    Board board = new Board(7, 7, ships);

    assertEquals(7, board.getHeight());
    assertEquals(7, board.getWidth());
    assertEquals(ships, board.getShips());
    assertEquals(1, board.getRemainingShips());
    assertEquals(1, board.getRandomShots().size());
  }
}