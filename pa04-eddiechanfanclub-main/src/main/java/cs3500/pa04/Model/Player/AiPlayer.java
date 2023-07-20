package cs3500.pa04.Model.Player;

import static cs3500.pa04.Model.LocalModel.createShips;

import cs3500.pa04.GameData.Board.Board;
import cs3500.pa04.GameData.Coord;
import cs3500.pa04.GameData.GameResult;
import cs3500.pa04.GameData.Ship;
import cs3500.pa04.GameData.ShipType;
import java.util.List;
import java.util.Map;

/**
 * A cpu player for BattleSalvo.
 */
public class AiPlayer implements Player {

  Board aiBoard = null;

  /**
   * Construct a cpu player.
   */
  public AiPlayer() {
  }

  /**
   * Get the player's name.
   *
   * @return the player's name
   */
  @Override
  public String name() {
    return "msereeyothin";
  }

  /**
   * Given the specifications for a BattleSalvo board, return a list of ships with their locations
   * on the board.
   *
   * @param height         the height of the board, range: [6, 15] inclusive
   * @param width          the width of the board, range: [6, 15] inclusive
   * @param specifications a map of ship type to the number of occurrences each ship should
   *                       appear on the board
   * @return the placements of each ship on the board
   */
  @Override
  public List<Ship> setup(int height, int width, Map<ShipType, Integer> specifications) {
    List<Ship> ships = createShips(height, width, specifications);
    this.aiBoard = new Board(height, width, ships); // Initialize the board
    return ships;
  }

  /**
   * Returns this player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  @Override
  public List<Coord> takeShots() {
    return this.aiBoard.getRandomShots();
  }

  /**
   * Given the list of shots the opponent has fired on this player's board, report which
   * shots hit a ship on this player's board.
   *
   * @param opponentShotsOnBoard the opponent's shots on this player's board
   * @return a filtered list of the given shots that contain all locations of shots that hit a
   * ship on this board
   */
  @Override
  public List<Coord> reportDamage(List<Coord> opponentShotsOnBoard) {
    return this.aiBoard.receiveShots(opponentShotsOnBoard);
  }

  /**
   * Reports to this player what shots in their previous volley returned from takeShots()
   * successfully hit an opponent's ship.
   *
   * @param shotsThatHitOpponentShips the list of shots that successfully hit the opponent's ships
   */
  @Override
  public void successfulHits(List<Coord> shotsThatHitOpponentShips) {
    // No need to report to AI
  }

  /**
   * Notifies the player that the game is over.
   * Win, lose, and draw should all be supported
   *
   * @param result if the player has won, lost, or forced a draw
   * @param reason the reason for the game ending
   */
  @Override
  public void endGame(GameResult result, String reason) {
    // No need to report anything to the AI
  }
}
