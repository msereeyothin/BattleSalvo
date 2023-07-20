package cs3500.pa04.GameData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a ship in BattleSalvo
 */
public class Ship {
  private boolean sunk = false;
  private ShipType type;
  private HashMap<Coord, String> segments = new HashMap<Coord, String>();
  private List<Coord> shipCoords;


  /**
   * Construct a new ship.
   *
   * @param type       The type of ship
   * @param shipCoords The ship's coordinates
   */
  public Ship(ShipType type, List<Coord> shipCoords) {
    this.type = type;
    this.shipCoords = shipCoords;
    for (Coord c : shipCoords) {
      segments.put(c, "afloat");
    }
  }

  /**
   * Is this ship sunk?
   *
   * @return the state of the ship
   */
  public boolean isSunk() {
    return sunk;
  }

  /**
   * Receive a volley and updates the ship segments
   * and returns a list of shots that hit.
   *
   * @param shotCoords The volley of shots from the opponent
   * @return The list of shots that hit
   */
  public ArrayList<Coord> receiveVolley(List<Coord> shotCoords) {
    ArrayList<Coord> hitShots = new ArrayList<>();
    for (Coord c : shotCoords) {
      sinkSegment(c, hitShots);
    }
    checkSegments();
    return hitShots;
  }

  /**
   * Sink the segment of the ship if it is shot
   * and update the list of hit shots.
   *
   * @param coord The shot coordinate
   */
  private void sinkSegment(Coord coord, ArrayList<Coord> hitShots) {
    for (Coord c : segments.keySet()) {
      if (coord.equals(c)) {
        segments.put(c, "sunk");
        hitShots.add(coord);
      }
    }
  }


  /**
   * Checks the status of each ship segment. If all are sunk, then change the
   * status of this ship to sunk
   */
  private void checkSegments() {
    boolean allSunk = true;
    for (String status : segments.values()) {
      if (!status.equals("sunk")) {
        allSunk = false;
      }
    }
    this.sunk = allSunk;
  }

  /**
   * Get the segments of this ship.
   *
   * @return The segments of this ship
   */
  public HashMap<Coord, String> getSegments() {
    return segments;
  }

  /**
   * Get the type of this ship.
   *
   * @return The type of this ship
   */
  public ShipType getShipType() {
    return type;
  }

  /**
   * Get the coordinates of this ship.
   *
   * @return The coordinates of this ship
   */
  public List<Coord> getCoords() {
    return shipCoords;
  }

  /**
   * Get the direction of this ship.
   *
   * @return The direction of this ship
   */
  public ShipDirection getDirection() {
    ShipDirection direction = ShipDirection.VERTICAL;
    if (shipCoords.get(0).getX() == shipCoords.get(1).getX() - 1) {
      direction = ShipDirection.HORIZONTAL;
    }
    return direction;
  }



}
