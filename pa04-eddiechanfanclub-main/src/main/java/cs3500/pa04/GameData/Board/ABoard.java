package cs3500.pa04.GameData.Board;

import cs3500.pa04.GameData.Coord;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents boards in BattleSalvo.
 */
abstract class ABoard {
  protected int height;
  protected int width;
  protected List<Coord> hitShots = new ArrayList<>();
  protected List<Coord> allShots = new ArrayList<>();
  protected String[][] board;

  /**
   * Construct a BattleSalvo board.
   *
   * @param height The height of the board
   * @param width  The width of the board
   */
  public ABoard(int height, int width) {
    this.height = height;
    this.width = width;
    board = new String[width][height];
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        board[i][j] = "0";
      }
    }
  }

  /**
   * Update the list of shots that hit.
   *
   * @param coords The shots that hit
   */
  public void updateHitShots(List<Coord> coords) {
    this.hitShots.addAll(coords);
  }

  /**
   * Update the list containing all shots.
   *
   * @param coords All the shots taken
   */
  public void updateAllShots(List<Coord> coords) {
    this.allShots.addAll(coords);
  }
}
