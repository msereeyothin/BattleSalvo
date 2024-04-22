package cs3500.pa04.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for the CoordinatesJson record class.
 */
public class CoordinatesJsonTest {

  /**
   * Tests the creation and serialization CoordinatesJson.
   */
  @Test
  public void testCoordinatesJson() throws Exception {
    List<CoordJson> coordList = List.of(new CoordJson(10, 20), new CoordJson(30, 40));
    CoordinatesJson coordinatesJson = new CoordinatesJson(coordList);

    Assertions.assertEquals(coordList, coordinatesJson.coords());

    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(coordinatesJson);

    Assertions.assertTrue(json.contains("\"coordinates\":"));
    Assertions.assertTrue(json.contains("{\"x\":10,\"y\":20}"));
    Assertions.assertTrue(json.contains("{\"x\":30,\"y\":40}"));
  }
}
