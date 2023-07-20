package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;

/**
 * A Json object representing a fleet of BattleSalvo Ships.
 *
 * @param fleet The list of ships
 */
public record FleetJson(
    @JsonProperty("fleet") ArrayList<ShipJson> fleet
) {
}
