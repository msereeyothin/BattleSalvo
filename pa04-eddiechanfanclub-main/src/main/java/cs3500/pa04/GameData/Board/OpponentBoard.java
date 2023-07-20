package cs3500.pa04.GameData.Board;

import cs3500.pa04.GameData.Coord;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an opponent's board in BattleSalvo.
 */
public class OpponentBoard extends ABoard {
  /**
   * Create an OpponentBoard.
   *
   * @param height The height of the board
   * @param width The width of the board
   */
  public OpponentBoard(int height, int width) {
    super(height, width);
  }

  /**
   * Get the opponent's board from the current player's POV.
   *
   * @return The opponent's board
   */
  public String[][] getCurrentBoard() {
//    List<Coord> missedShots = new ArrayList<>(this.allShots);
//    missedShots.removeAll(this.hitShots);

    for (Coord coord : this.allShots) {
      int x = coord.getX();
      int y = coord.getY();
      this.board[y][x] = "M";
    }
    for (Coord coord : this.hitShots) {
      int x = coord.getX();
      int y = coord.getY();
      this.board[y][x] = "H";
    }
    return this.board;
  }
}
