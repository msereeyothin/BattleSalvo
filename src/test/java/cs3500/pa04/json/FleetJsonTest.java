package cs3500.pa04.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for the FleetJson record class.
 */
public class FleetJsonTest {

  /**
   * Test the creation and serialization of a FleetJson.
   */
  @Test
  public void testFleetJson() throws Exception {
    CoordJson coordJson1 = new CoordJson(5, 5);
    int length1 = 3;
    String direction1 = "horizontal";
    ShipJson shipJson1 = new ShipJson(coordJson1, length1, direction1);

    CoordJson coordJson2 = new CoordJson(7, 8);
    int length2 = 4;
    String direction2 = "vertical";
    ShipJson shipJson2 = new ShipJson(coordJson2, length2, direction2);

    ArrayList<ShipJson> fleet = new ArrayList<>();
    fleet.add(shipJson1);
    fleet.add(shipJson2);

    FleetJson fleetJson = new FleetJson(fleet);

    Assertions.assertEquals(fleet, fleetJson.fleet());

    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(fleetJson);

    Assertions.assertTrue(json.contains("\"fleet\":["));
    Assertions.assertTrue(
        json.contains("{\"coord\":{\"x\":5,\"y\":5},\"length\":3,\"direction\":\"horizontal\"}"));
    Assertions.assertTrue(
        json.contains("{\"coord\":{\"x\":7,\"y\":8},\"length\":4,\"direction\":\"vertical\"}"));
  }
}
