package cs3500.pa04.View;

import cs3500.pa04.GameData.Coord;
import cs3500.pa04.GameData.ShipType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a view class to get user input and display data
 */
public class LocalView {
  Scanner scanner;

  /**
   * Construct a LocalView object.
   */
  public LocalView() {
    this.scanner = new Scanner(System.in);
  }

  /**
   * Construct a LocalView object with a custom scanner.
   *
   * @param scanner A custom scanner
   */
  public LocalView(Scanner scanner) {
    this.scanner = scanner;
  }

  /**
   * Query the user for the board dimensions.
   *
   * @return The dimensions of the board in the form of a Coord
   */
  public Coord getDimensions() {
    System.out.println("Please enter a valid height and width below:");
    String input = scanner.nextLine();
    String[] coordinates = input.split(" ");
    int x = Integer.parseInt(coordinates[0]);
    int y = Integer.parseInt(coordinates[1]);
    Coord dimension = new Coord(x, y);
    if (!checkDimension(dimension)) {
      System.out.println("Dimensions must be in the range of 6 and 15 (inclusive)");
      getDimensions();
    }
    return dimension;
  }

  /**
   * Check if the dimension of the coordinate is valid.
   *
   * @param c The coordinate to be checked
   * @return The validity of the coordinate
   */
  private boolean checkDimension(Coord c) {
    return (c.getY() <= 15)
        && (c.getX() <= 15)
        && (c.getY() >= 6)
        && (c.getX() >= 6);
  }

  /**
   * Query the user's frequencies for each ship.
   *
   * @param height The height of the board
   * @param width  The width of the board
   * @return
   */
  public HashMap<ShipType, Integer> getUserInputShip(int height, int width) {
    HashMap<ShipType, Integer> userShips = new HashMap<>();
    int maxShips = Math.min(height, width);
    System.out.println(
        "Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].\n" +
            "Remember, your fleet may not exceed size " + maxShips + ".");
    String input = scanner.nextLine();
    try {
      String[] shipFreq = input.split(" ");
      userShips.put(ShipType.CARRIER, Integer.parseInt(shipFreq[0]));
      userShips.put(ShipType.BATTLESHIP, Integer.parseInt(shipFreq[1]));
      userShips.put(ShipType.DESTROYER, Integer.parseInt(shipFreq[2]));
      userShips.put(ShipType.SUBMARINE, Integer.parseInt(shipFreq[3]));
    } catch (Exception e) {
      System.out.println("Invalid input!");
    }
    return userShips;
  }

  /**
   * Query the user for their shots.
   *
   * @param numShots The amount of available shots
   * @return The user's shots
   */
  public List<Coord> getUserShots(int numShots) {
    ArrayList<Coord> userShots = new ArrayList<Coord>();
    System.out.println("Please Enter " + numShots + " shots:");
    for (int i = 0; i < numShots; i++) {
      String input = scanner.nextLine();
      int x = 0;
      int y = 0;
      try {
        String[] coordinates = input.split(" ");
        x = Integer.parseInt(coordinates[0]);
        y = Integer.parseInt(coordinates[1]);
      } catch (Exception e) {
        System.out.println("Input was invalid! Try again.");
        userShots = (ArrayList<Coord>) getUserShots(numShots);
      }
      userShots.add(new Coord(x - 1, y - 1));
    }
    return userShots;
  }

  /**
   * Display a board.
   *
   * @param board The given board
   */
  public static void showBoard(String[][] board) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        System.out.print(board[i][j] + " ");
      }
      System.out.println();
    }
  }

  /**
   * Display a win.
   *
   * @param reason The reason of the outcome
   */
  public static void showWin(String reason) {
    System.out.println("You Won!\n" + reason);
  }

  /**
   * Display a loss.
   *
   * @param reason The reason of the outcome
   */
  public static void showLose(String reason) {
    System.out.println("You Lost!\n" + reason);
  }

  /**
   * Display a tie.
   *
   * @param reason The reason of the outcome
   */
  public static void showTie(String reason) {
    System.out.println("Draw!\n" + reason);
  }

}
