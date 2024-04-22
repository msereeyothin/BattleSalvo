package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * A Json object representing a list of coordinates.
 *
 * @param coords A list of coordinates
 */
public record CoordinatesJson(
    @JsonProperty("coordinates") List<CoordJson> coords
) {
}
