package cs3500.pa04.Model.Player;

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
 * Tests for an AI Player.
 */
public class AiPlayerTest {

  private AiPlayer aiPlayer;

  /**
   * Set up the AiPlayer.
   */
  @BeforeEach
  public void setup() {
    aiPlayer = new AiPlayer();
    int height = 10;
    int width = 10;
    Map<ShipType, Integer> specifications = new HashMap<>();
    specifications.put(ShipType.BATTLESHIP, 1);
    specifications.put(ShipType.DESTROYER, 2);

    List<Ship> expectedShips = aiPlayer.setup(height, width, specifications);

    Assertions.assertNotNull(expectedShips);
    Assertions.assertEquals(3, expectedShips.size());
  }

  /**
   * Test the name method of AiPlayer.
   */
  @Test
  public void testGetName() {
    String expectedName = "msereeyothin";
    String actualName = aiPlayer.name();
    Assertions.assertEquals(expectedName, actualName);
  }

  /**
   * Test the takeShots method of AiPlayer.
   */
  @Test
  public void testTakeShots() {
    List<Coord> shots = aiPlayer.takeShots();
    Assertions.assertNotNull(shots);
  }

  /**
   * Test the reportDamage method of AiPlayer.
   */
  @Test
  public void testReportDamage() {
    List<Coord> opponentShotsOnBoard = new ArrayList<>();
    List<Coord> damage = aiPlayer.reportDamage(opponentShotsOnBoard);
    Assertions.assertNotNull(damage);
  }

  /**
   * Test the successfulHits method of AiPlayer.
   */
  @Test
  public void testSuccessfulHits() {
    List<Coord> shotsThatHitOpponentShips = new ArrayList<>();
    aiPlayer.successfulHits(shotsThatHitOpponentShips);
  }

  /**
   * Test the endGame method of AiPlayer.
   */
  @Test
  public void testEndGame() {
    GameResult result = GameResult.WIN;
    String reason = "You sunk all my ships!";
    aiPlayer.endGame(result, reason);
  }
}
