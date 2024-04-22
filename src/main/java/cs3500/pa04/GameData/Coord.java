package cs3500.pa04.GameData;

/**
 * Represent a coordinate in a game of BattleSalvo
 */
public class Coord {
  private int x;
  private int y;

  /**
   * Create a Coord object
   *
   * @param x The x coordinate of this coord
   * @param y The y coordinate of this coord
   */
  public Coord(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Get the X coordinate of this coord.
   *
   * @return The X coordinate of this coord
   */
  public int getX() {
    return x;
  }


  /**
   * Get the Y coordinate of this coord.
   *
   * @return The Y coordinate of this coord
   */
  public int getY() {
    return y;
  }

  /**
   * Compare an object to this coord
   *
   * @param o The object to compare to
   * @return if the given object is the same as the current one
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) {
      return true;
    }

    if (!(o instanceof Coord)) {
      return false;
    }

    Coord c = (Coord) o;

    return c.getX() == this.getX()
        && c.getY() == this.getY();

  }
}
