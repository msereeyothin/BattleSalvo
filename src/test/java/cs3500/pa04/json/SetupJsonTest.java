package cs3500.pa04.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for the SetupJson record class.
 */
public class SetupJsonTest {

  /**
   * Test the creation and serialization of a SetupJson.
   */
  @Test
  public void testSetupJson() throws Exception {
    int width = 10;
    int height = 8;

    Map<String, Integer> fleetSpec = new HashMap<>();
    fleetSpec.put("carrier", 1);
    fleetSpec.put("battleship", 2);
    fleetSpec.put("destroyer", 3);

    SetupJson setupJson = new SetupJson(width, height, fleetSpec);

    Assertions.assertEquals(width, setupJson.width());
    Assertions.assertEquals(height, setupJson.height());
    Assertions.assertEquals(fleetSpec, setupJson.fleetSpec());

    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(setupJson);

    Assertions.assertTrue(json.contains("\"width\":10"));
    Assertions.assertTrue(json.contains("\"height\":8"));
    Assertions.assertTrue(
        json.contains("\"fleet-spec\":{\"carrier\":1,\"battleship\":2,\"destroyer\":3}"));
  }
}
