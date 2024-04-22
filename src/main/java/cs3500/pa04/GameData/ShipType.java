package cs3500.pa04.GameData;

/**
 * Represents a type of ship:
 * Carrier Length 6,
 * Battleship Length 5,
 * Destroyer Length 4,
 * Submarine Length 3
 */
public enum ShipType {
  CARRIER(6),
  BATTLESHIP(5),
  DESTROYER(4),
  SUBMARINE(3);

  private final int size;

  /**
   * Initialize a ShipType.
   *
   * @param size The size of the ship.
   */
  ShipType(int size) {
    this.size = size;
  }

  /**
   * Get the size of the Ship.
   *
   * @return The size of the ship.
   */
  public int getSize() {
    return size;
  }

  /**
   * Get the enumeration type of ship by its string.
   *
   * @param type The type of ship in a string format
   * @return The enumeration type of ship
   */
  public static ShipType getTypeByString(String type) {
    switch (type) {
      case "SUBMARINE":
        return SUBMARINE;
      case "BATTLESHIP":
        return BATTLESHIP;
      case "CARRIER":
        return CARRIER;
      case "DESTROYER":
        return DESTROYER;
    }
    return null;
  }
}