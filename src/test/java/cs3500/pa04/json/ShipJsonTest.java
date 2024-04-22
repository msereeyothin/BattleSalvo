package cs3500.pa04.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for the ShipJson record class.
 */
public class ShipJsonTest {

  /**
   * Test the creation and serialization of a ShipJson.
   */
  @Test
  public void testShipJson() throws Exception {
    CoordJson coordJson = new CoordJson(5, 5);
    int length = 3;
    String direction = "horizontal";

    ShipJson shipJson = new ShipJson(coordJson, length, direction);

    Assertions.assertEquals(coordJson, shipJson.coord());
    Assertions.assertEquals(length, shipJson.length());
    Assertions.assertEquals(direction, shipJson.direction());

    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(shipJson);

    Assertions.assertTrue(json.contains("\"coord\":{\"x\":5,\"y\":5}"));
    Assertions.assertTrue(json.contains("\"length\":3"));
    Assertions.assertTrue(json.contains("\"direction\":\"horizontal\""));
  }
}
