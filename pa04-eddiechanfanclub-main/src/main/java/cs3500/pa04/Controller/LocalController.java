package cs3500.pa04.Controller;

import cs3500.pa04.GameData.Coord;
import cs3500.pa04.GameData.GameResult;
import cs3500.pa04.GameData.ShipType;
import cs3500.pa04.Model.Player.Player;
import cs3500.pa04.View.LocalView;
import java.util.List;
import java.util.Map;

/**
 * A controller for a local game of BattleSalvo.
 */
public class LocalController implements Controller {

 LocalView view = new LocalView();
  Player p1;
  Player p2;
  boolean gameOver;

  /**
   * Construct a local game controller.
   *
   * @param p1 The principal player
   * @param p2 The secondary player, usually an AI
   */
  public LocalController(Player p1, Player p2) {
    this.p1 = p1;
    this.p2 = p2;
  }

  /**
   * Run the local game.
   */
  @Override
  public void run() {
    setup();

    while (!gameOver) {
      List<Coord> p1Shots = this.p1.takeShots(); // Player 1 takes shots
      List<Coord> p2Shots = this.p2.takeShots(); // Player 2 takes shots

      this.isGameOver(p1Shots.size(), p2Shots.size()); // Checking if the game is over
      if (this.gameOver) {
        break; // Terminate the loop if the game is over
      }

      List<Coord> p2ShotsThatHit =
          this.p1.reportDamage(p2Shots); // Putting p2's shots on p1's boards
      List<Coord> p1ShotsThatHit =
          this.p2.reportDamage(p1Shots); // Putting p1's shots on p2's boards

      this.p1.successfulHits(p1ShotsThatHit); // Show p1 their view of p2's board
      this.p2.successfulHits(p2ShotsThatHit); // Show p2 their view of p1's board
    }
  }

  /**
   * Set up the board for both players given the user input.
   */
  private void setup() {
    Coord dimension = view.getDimensions();
    int boardX = dimension.getX();
    int boardY = dimension.getY();
    Map<ShipType, Integer> shipMap = view.getUserInputShip(boardY, boardX);
    this.p1.setup(boardY, boardX, shipMap);
    this.p2.setup(boardY, boardX, shipMap);
  }

  /**
   * Detect if the game is over by checking the amount of remaining shots.
   *
   * @param remainingP1Shots The amount of shots for player 1
   * @param remainingP2Shots The amount of shots for player 2
   */
  void isGameOver(int remainingP1Shots, int remainingP2Shots) {
    if (remainingP2Shots == 0 && remainingP1Shots > 0) {
      this.p1.endGame(GameResult.WIN, "You sunk all your opponents ships!");
      this.gameOver = true;
    } else if (remainingP1Shots == 0 && remainingP2Shots > 0) {
      this.p1.endGame(GameResult.LOSE, "Your opponent sunk all your ships!");
      this.gameOver = true;
    } else if (remainingP1Shots == 0 && remainingP2Shots == 0) {
      this.p1.endGame(GameResult.TIE,
          "You and your opponent sunk all of each others' ships!");
      this.gameOver = true;
    }
  }


}
