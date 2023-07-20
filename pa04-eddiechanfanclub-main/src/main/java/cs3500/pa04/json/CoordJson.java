package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A Json object representing a coordinate.
 *
 * @param xCoord The x coordinate
 * @param yCoord The y coordinate
 */
public record CoordJson(
    @JsonProperty("x") int xCoord,
    @JsonProperty("y") int yCoord
) {
}
