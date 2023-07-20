package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

/**
 * A Json object containing the setup details of a BattleSalvo game.
 *
 * @param width     The width of the game board
 * @param height    The height of the game board
 * @param fleetSpec The ship specifications of this game
 */
public record SetupJson(
    @JsonProperty("width") int width,
    @JsonProperty("height") int height,
    @JsonProperty("fleet-spec") Map fleetSpec
) {
}
