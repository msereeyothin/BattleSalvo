package cs3500.pa04.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Tests for the MessageJson record class.
 */
public class MessageJsonTest {

  /**
   * Test the creation and serialization of a MessageJson.
   */
  @Test
  public void testMessageJson() throws Exception {
    String messageName = "sendMessage";
    JsonNode arguments = new ObjectMapper().createObjectNode()
        .put("text", "Hello, world!")
        .put("sender", "John");

    MessageJson messageJson = new MessageJson(messageName, arguments);

    Assertions.assertEquals(messageName, messageJson.messageName());
    Assertions.assertEquals(arguments, messageJson.arguments());

    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(messageJson);

    Assertions.assertTrue(json.contains("\"method-name\":\"sendMessage\""));
    Assertions.assertTrue(
        json.contains("\"arguments\":{\"text\":\"Hello, world!\",\"sender\":\"John\"}"));
  }
}
