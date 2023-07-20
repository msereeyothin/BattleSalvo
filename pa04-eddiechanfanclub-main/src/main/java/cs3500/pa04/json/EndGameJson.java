package cs3500.pa04.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa04.GameData.GameResult;

/**
 * A Json object representing the outcome of a game.
 *
 * @param result The result of a game
 * @param reason The reason for the result
 */
public record EndGameJson(
    @JsonProperty("result") GameResult result,
    @JsonProperty("reason") String reason
) {
}
