package cs3500.pa04.GameData.Board;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import cs3500.pa04.GameData.Coord;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * Tests for the opponent board.
 */
class OpponentBoardTest {

  /**
   * Tests getting the board and adding to shot lists.
   */
  @Test
  void testGetBoardAndReceiveShots() {
    List<Coord> allShots = new ArrayList<>();
    allShots.add(new Coord(0, 0));
    allShots.add(new Coord(1, 0));
    allShots.add(new Coord(2, 0));
    allShots.add(new Coord(3, 0));
    List<Coord> hitShots = new ArrayList<>();
    hitShots.add(new Coord(2, 0));
    hitShots.add(new Coord(3, 0));

    OpponentBoard testBoard = new OpponentBoard(4, 4);
    testBoard.updateAllShots(allShots);
    testBoard.updateHitShots(hitShots);

    String[][] expectedBoard = {
        {"M", "M", "H", "H"},
        {"0", "0", "0", "0"},
        {"0", "0", "0", "0"},
        {"0", "0", "0", "0"},
    };
    assertArrayEquals(expectedBoard, testBoard.getCurrentBoard());
  }
}