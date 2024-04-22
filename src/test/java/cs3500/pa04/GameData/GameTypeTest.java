package cs3500.pa04.GameData;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests the enumeration GameType
 */
public class GameTypeTest {

  /**
   * Tests a single GameType.
   */
  @Test
  public void testSingleGameType() {
    GameType gameType = GameType.SINGLE;
    Assertions.assertEquals("SINGLE", gameType.name());
  }

  /**
   * Tests a multi GameType.
   */
  @Test
  public void testMultiGameType() {
    GameType gameType = GameType.MULTI;
    Assertions.assertEquals("MULTI", gameType.name());
  }
}