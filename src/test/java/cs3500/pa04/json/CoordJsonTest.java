package cs3500.pa04.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for the CoordJson record class.
 */
public class CoordJsonTest {

  /**
   * Tests the creation and serialization of a CoordJson.
   */
  @Test
  public void testCoordJson() throws Exception {
    CoordJson coordJson = new CoordJson(10, 20);

    Assertions.assertEquals(10, coordJson.xCoord());
    Assertions.assertEquals(20, coordJson.yCoord());

    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(coordJson);

    Assertions.assertTrue(json.contains("\"x\":10"));
    Assertions.assertTrue(json.contains("\"y\":20"));
  }
}
