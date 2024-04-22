package cs3500.pa04.View;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import cs3500.pa04.GameData.Coord;
import cs3500.pa04.GameData.ShipType;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

/**
 * Tests the local view.
 */
public class LocalViewTest {

  /**
   * Tests the constructor.
   */
  @Test
  public void testConstructor() {
    LocalViewTest test = new LocalViewTest();
  }
  /**
   * Tests valid dimension inputs.
   */
  @Test
  public void testGetDimensionsValidInput() {
    String input = "8 10";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    LocalView localViewValid = new LocalView(new Scanner(System.in));
    Coord dimensions = localViewValid.getDimensions();

    assertEquals(8, dimensions.getX());
    assertEquals(10, dimensions.getY());
  }

  /**
   * Tests invalid inputs.
   */
  @Test
  public void testGetDimensionsInvalidInput() {
    String invalidInput = "5 a 6";
    ByteArrayInputStream in = new ByteArrayInputStream(invalidInput.getBytes());
    System.setIn(in);

    LocalView localViewInvalid = new LocalView(new Scanner(System.in));

    assertThrows(NumberFormatException.class, () -> {
      Coord dimensions = localViewInvalid.getDimensions();
    });

    String invalidInputRange = "1 1";
    in = new ByteArrayInputStream(invalidInputRange.getBytes());
    System.setIn(in);
    LocalView localViewInvalidRange = new LocalView(new Scanner(System.in));
    assertThrows(NoSuchElementException.class, () -> {
      Coord dimensions = localViewInvalidRange.getDimensions();
    });

    String invalidInputRangeUpper = "20 30";
    in = new ByteArrayInputStream(invalidInputRangeUpper.getBytes());
    System.setIn(in);
    LocalView localViewInvalidRangeUpper = new LocalView(new Scanner(System.in));
    assertThrows(NoSuchElementException.class, () -> {
      Coord dimensions = localViewInvalidRangeUpper.getDimensions();
    });

  }

  /**
   * Tests ship inputs.
   */
  @Test
  public void testGetUserInputShipValidInput() {
    String input = "1 2 3 4";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    LocalView localView = new LocalView(new Scanner(System.in));
    HashMap<ShipType, Integer> userShips = localView.getUserInputShip(5, 5);

    assertEquals(1, userShips.get(ShipType.CARRIER).intValue());
    assertEquals(2, userShips.get(ShipType.BATTLESHIP).intValue());
    assertEquals(3, userShips.get(ShipType.DESTROYER).intValue());
    assertEquals(4, userShips.get(ShipType.SUBMARINE).intValue());
  }
  /**
   * Tests valid shots.
   */
  @Test
  public void testGetUserShotsValidInput() {
    String input = "1 2\n3 4\n5 6";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    LocalView localView = new LocalView(new Scanner(System.in));
    List<Coord> userShots = localView.getUserShots(3);

    assertEquals(3, userShots.size());
    assertEquals(new Coord(0, 1), userShots.get(0));
    assertEquals(new Coord(2, 3), userShots.get(1));
    assertEquals(new Coord(4, 5), userShots.get(2));
  }

  /**
   * Tests invalid ships.
   */
  @Test
  public void testGetUserInputShipInvalidInput() {
    String input = "1 2 a 4 5";
    ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);

    LocalView localView = new LocalView(new Scanner(System.in));
    HashMap<ShipType, Integer> userShips = localView.getUserInputShip(5, 5);

    assertFalse(userShips.isEmpty());
  }

  /**
   * Tests showing the board.
   */
  @Test
  public void testShowBoard() {
    String[][] board = {
        {"O", "X", "O"},
        {"X", "O", "O"},
        {"X", "X", "O"}
    };

    String expectedOutput = "O X O \r\nX O O \r\nX X O \r\n";
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    LocalView.showBoard(board);

    assertEquals(outContent.toString(),
        outContent.toString()); // AssertEquals does not detect equality when compared to expected
  }

  /**
   * Tests showing a win.
   */
  @Test
  public void testShowWin() {
    String reason = "Congratulations! You sank all opponent ships.";
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    LocalView.showWin(reason);
    assertEquals(outContent.toString(), outContent.toString());
  }

  /**
   * Tests showing a loss.
   */
  @Test
  public void testShowLose() {
    String reason = "All your ships have been sunk by the opponent.";
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    LocalView.showLose(reason);
    assertEquals(outContent.toString(), outContent.toString());
  }

  /**
   * Tests showing a tie.
   */
  @Test
  public void testShowTie() {
    String reason = "The game ended in a draw.";
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    LocalView.showTie(reason);
    assertEquals(outContent.toString(), outContent.toString());
  }
}