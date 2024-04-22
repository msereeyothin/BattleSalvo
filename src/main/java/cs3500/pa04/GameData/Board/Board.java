package cs3500.pa04.GameData.Board;

import cs3500.pa04.GameData.Coord;
import cs3500.pa04.GameData.Ship;
import cs3500.pa04.GameData.ShipType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * Represent a game board of Battle Salvo
 */
public class Board extends ABoard {
  private final List<Ship> ships;
  private final Stack<Coord> usableShots = new Stack<>();

  /**
   * A constructor for a board.
   *
   * @param height The height of the board
   * @param width  The width of the board
   * @param ships  The list of ships on this board
   */
  public Board(int height, int width, List<Ship> ships) {
    super(height, width);
    this.ships = ships;
    for (int i = 0; i < width; i++) {
      for (int j = 0; j < height; j++) {
        Coord curCoord = new Coord(i, j);
        usableShots.add(curCoord);
      }
    }
  }

  /**
   * Get the user's current board.
   *
   * @return The user's board
   */
  public String[][] getCurrentBoard() {
    List<Coord> missedShots = new ArrayList<>(this.allShots);
    missedShots.removeAll(this.hitShots);
    for (Ship ship : this.ships) {
      ShipType curShipType = ship.getShipType();
      HashMap<Coord, String> curShipSegments = ship.getSegments();
      for (Coord coord : curShipSegments.keySet()) {
        int x = coord.getX();
        int y = coord.getY();
        if (curShipSegments.get(coord).equals("afloat")) {
          this.board[y][x] = "S";
        } else {
          this.board[y][x] = "H";
        }
      }
    }
    for (Coord coord : missedShots) {
      int x = coord.getX();
      int y = coord.getY();
      this.board[y][x] = "M";
    }
    return this.board;
  }

  /**
   * Get the amount of remaining ships on the board.
   *
   * @return The amount of remaining ships on the board
   */
  public int getRemainingShips() {
    int remainingShips = 0;
    for (Ship ship : this.ships) {
      if (!ship.isSunk()) {
        remainingShips++;
      }
    }
    return remainingShips;
  }

  /**
   * Receive a volley of shots.
   *
   * @param shots The shots to be sent to this board.
   * @return The list of shots that hit.
   */
  public List<Coord> receiveShots(List<Coord> shots) {
    ArrayList<Coord> shotsThatHit = new ArrayList<>();
    for (Ship ship : ships) {
      shotsThatHit.addAll(ship.receiveVolley(shots));
    }
    this.allShots.addAll(shots);
    this.hitShots.addAll(shotsThatHit);
    return shotsThatHit;
  }

  /**
   * Get this ship's boards.
   *
   * @return The board's ships
   */
  public List<Ship> getShips() {
    return this.ships;
  }

  /**
   * Get the height of this board.
   *
   * @return The height of this board
   */
  public int getHeight() {
    return this.height;
  }

  /**
   * Get the width of this board.
   *
   * @return The width of this board
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Generate a random list of shots, where the length is the amount of ships remaining
   * on the board.
   *
   * @return A random list of shots
   */
  public List<Coord> getRandomShots() {
    int availableShots = this.getRemainingShips();
    List<Coord> shots = new ArrayList<>();
    Collections.shuffle(this.usableShots);
    for (int i = 0; i < availableShots; i++) {
      if (!this.usableShots.isEmpty()) {
        Coord shot = this.usableShots.pop();
        shots.add(shot);
      }
    }
    return shots;
  }
}