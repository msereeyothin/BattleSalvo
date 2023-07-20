package cs3500.pa04.GameData;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for the GameResult enumeration.
 */
class GameResultTest {
  /**
   * Tests the GameResult enumeration.
   */
  @Test
  void testGameResult() {
    assertEquals("WIN", GameResult.WIN.toString());
    assertEquals("LOSE", GameResult.LOSE.toString());
    assertEquals("TIE", GameResult.TIE.toString());
  }
}