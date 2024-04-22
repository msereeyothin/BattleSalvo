package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A Json object representing a ship in BattleSalvo.
 *
 * @param coord     The coordinate of the ship
 * @param length    The length of the ship
 * @param direction The direction of the ship
 */
public record ShipJson(
    @JsonProperty("coord") CoordJson coord,
    @JsonProperty("length") int length,
    @JsonProperty("direction") String direction
) {
}
