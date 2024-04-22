package cs3500.pa04.Model;

import cs3500.pa04.GameData.Coord;
import cs3500.pa04.GameData.Ship;
import cs3500.pa04.GameData.ShipType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * A Model class to store helper methods
 */
public class LocalModel {
  /**
   * Get a list of ships given the board dimensions and specifications.
   *
   * @param height         The height of the board
   * @param width          The width of the board
   * @param specifications The frequency of each ship
   * @return A list of ships
   */
  public static List<Ship> createShips(int height, int width,
                                       Map<ShipType, Integer> specifications) {
    Random random = new Random();
    List<Ship> shipList = new ArrayList<>();
    ArrayList<ArrayList<Coord>> allShipCoords =
        new ArrayList<ArrayList<Coord>>(); // Coords of previous ships to prevent overlap

    for (ShipType type : specifications.keySet()) {
      for (int i = 0; i < specifications.get(type); i++) {

        Coord randStartingCord = getRandomCoord(height, width, type);
        boolean horizontal = random.nextBoolean();
        ArrayList<Coord> curShipCoords = new ArrayList<>(); // Random coordinates of current ship
        addCoords(type, randStartingCord, horizontal, curShipCoords);
        while (!validShipCoords(curShipCoords,
            allShipCoords)) { // Checking if random coordinates are valid
          randStartingCord = getRandomCoord(height, width, type);
          horizontal = random.nextBoolean();
          curShipCoords.clear();
          addCoords(type, randStartingCord, horizontal, curShipCoords);
        }
        shipList.add(new Ship(type, curShipCoords));
        allShipCoords.add(curShipCoords);
      }
    }
    return shipList;
  }

  /**
   * Generate a random coordinate for a given ship type.
   *
   * @param height The height of the board
   * @param width  The width of the board
   * @param type   The type of ship
   * @return A random coordinate in bounds of the board
   */
  private static Coord getRandomCoord(int height, int width, ShipType type) {
    Random random = new Random();
    int maxY = height - type.getSize();
    int maxX = width - type.getSize();
    if (maxY == 0) {
      maxY++;
    }
    if (maxX == 0) {
      maxX++;
    }
    int randY = random.nextInt(maxY);
    int randX = random.nextInt(maxX);
    return new Coord(randX, randY);
  }

  /**
   * Adding coords to a ship.
   *
   * @param type             The type of ship
   * @param randStartingCord The starting coord of this ship
   * @param horizontal       If the ship is horizontally aligned
   * @param curShipCoords    The list of coords to add to
   */
  private static void addCoords(ShipType type, Coord randStartingCord, boolean horizontal,
                                ArrayList<Coord> curShipCoords) {
    for (int i = 0; i < type.getSize(); i++) {
      int xCoord;
      int yCoord;

      if (horizontal) {
        // Generate horizontal ship coordinates
        xCoord = randStartingCord.getX() + i;
        yCoord = randStartingCord.getY();
      } else {
        // Generate vertical ship coordinate
        xCoord = randStartingCord.getX();
        yCoord = randStartingCord.getY() + i;
      }
      curShipCoords.add(new Coord(xCoord, yCoord));
    }
  }

  /**
   * Determine if coordinates overlap - If they don't it is valid.
   *
   * @param curShipCoords The current ships coords
   * @param allShipCoords All the previous ships coords
   * @return If there is any overlap in the coords
   */
  private static boolean validShipCoords(ArrayList<Coord> curShipCoords,
                                         ArrayList<ArrayList<Coord>> allShipCoords) {
    for (ArrayList<Coord> shipCoords : allShipCoords) {
      for (Coord curCoord : shipCoords) {
        for (Coord curShipCoord : curShipCoords) {
          if (curShipCoord.equals(curCoord)) {
            return false;
          }
        }
      }
    }
    return true;
  }


  /**
   * Generate random frequencies of ships given the board dimensions.
   *
   * @param height The height of the board
   * @param width  The width of the board
   * @return The frequencies of each ship
   */
  public static HashMap<ShipType, Integer> generateRandomShipMap(int height, int width) {
    Random random = new Random();
    HashMap<ShipType, Integer> randomShipMap = new HashMap<>();
    int maxShips = Math.min(height, width) - 3;

    int subFreq = random.nextInt(maxShips);
    randomShipMap.put(ShipType.SUBMARINE, subFreq);
    maxShips -= subFreq;

    int destroyerFreq = random.nextInt(maxShips);
    randomShipMap.put(ShipType.DESTROYER, destroyerFreq);
    maxShips -= destroyerFreq;

    int battleshipFreq = random.nextInt(maxShips);
    randomShipMap.put(ShipType.BATTLESHIP, battleshipFreq);
    maxShips -= battleshipFreq;

    int carrierFreq = random.nextInt(maxShips);
    randomShipMap.put(ShipType.CARRIER, carrierFreq);

    return randomShipMap;
  }
}
