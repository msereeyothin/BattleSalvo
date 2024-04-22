package cs3500.pa04.Model.Player;

import static org.junit.jupiter.api.Assertions.*;

import cs3500.pa04.GameData.Coord;
import cs3500.pa04.GameData.GameResult;
import cs3500.pa04.GameData.Ship;
import cs3500.pa04.GameData.ShipType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for a HumanPlayer.
 */
class HumanPlayerTest {

  /**
   * Set up the HumanPlayer.
   */
  private HumanPlayer humanPlayer;

  /**
   * Set up the HumanPlayer.
   */
  @BeforeEach
  public void setup() {
    humanPlayer = new HumanPlayer();
    int height = 10;
    int width = 10;
    Map<ShipType, Integer> specifications = new HashMap<>();
    specifications.put(ShipType.BATTLESHIP, 1);
    specifications.put(ShipType.DESTROYER, 2);

    List<Ship> expectedShips = humanPlayer.setup(height, width, specifications);

    Assertions.assertNotNull(expectedShips);
    Assertions.assertEquals(3, expectedShips.size());
  }

  /**
   * Test the name method of HumanPlayer.
   */
  @Test
  public void testGetName() {
    String expectedName = "Local Player";
    String actualName = humanPlayer.name();
    Assertions.assertEquals(expectedName, actualName);
  }

  /**
   * Test the reportDamage method of HumanPlayer.
   */
  @Test
  public void testReportDamage() {
    List<Coord> opponentShotsOnBoard = new ArrayList<>();
    List<Coord> damage = humanPlayer.reportDamage(opponentShotsOnBoard);
    Assertions.assertNotNull(damage);
  }

  /**
   * Test the successfulHits method of HumanPlayer.
   */
  @Test
  public void testSuccessfulHits() {
    List<Coord> shotsThatHitOpponentShips = new ArrayList<>();
    humanPlayer.successfulHits(shotsThatHitOpponentShips);
  }

  /**
   * Test the endGame method of HumanPlayer.
   */
  @Test
  public void testEndGame() {
    GameResult result = GameResult.WIN;
    String reason = "You sunk all my ships!";
    humanPlayer.endGame(result, reason);
  }


}