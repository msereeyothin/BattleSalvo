package cs3500.pa04.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa04.GameData.GameResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for the EndGameJson record class.
 */
public class EndGameJsonTest {

  /**
   * Tests the creation and serialization of an EndGameJson.
   */
  @Test
  public void testEndGameJsonCreationAndSerialization() throws Exception {
    GameResult gameResult = GameResult.WIN;
    String reason = "Congratulations! You won the game.";

    EndGameJson endGameJson = new EndGameJson(gameResult, reason);

    Assertions.assertEquals(gameResult, endGameJson.result());
    Assertions.assertEquals(reason, endGameJson.reason());

    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(endGameJson);

    Assertions.assertTrue(json.contains("\"result\":\"WIN\""));
    Assertions.assertTrue(json.contains("\"reason\":\"Congratulations! You won the game.\""));
  }
}
