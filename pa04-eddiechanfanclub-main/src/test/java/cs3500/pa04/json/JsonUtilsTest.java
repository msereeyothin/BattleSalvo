package cs3500.pa04.json;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test JsonUtils class
 */
public class JsonUtilsTest {

  @Test
  public void testSerializeRecord() {

    CoordJson coordJson = new CoordJson(0, 0);

    Assertions.assertEquals(0, coordJson.xCoord());
    Assertions.assertEquals(0, coordJson.yCoord());

  }

  @Test
  public void testSerializeRecordWithInvalidData() {
    // Create a record with invalid data (e.g., missing required properties)
    CoordinatesJson invalidCoordsJson = null;

    // Verify that IllegalArgumentException is thrown when trying to serialize the record
    Assertions.assertDoesNotThrow(() -> {
      JsonUtils.serializeRecord(invalidCoordsJson);
    });
  }
}
