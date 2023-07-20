package cs3500.pa04.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import cs3500.pa04.GameData.Coord;
import cs3500.pa04.GameData.GameType;
import cs3500.pa04.GameData.ShipType;
import cs3500.pa04.Model.Player.AiPlayer;
import cs3500.pa04.json.CoordJson;
import cs3500.pa04.json.CoordinatesJson;
import cs3500.pa04.json.JsonUtils;
import cs3500.pa04.json.MessageJson;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class ProxyControllerTest {

  AiPlayer aiPlayer = new AiPlayer();

  ObjectMapper mapper = new ObjectMapper();
  ProxyController proxyController;

  private ByteArrayOutputStream testLog;

  @BeforeEach
  public void setup() {
    this.testLog = new ByteArrayOutputStream(2048);
    assertEquals("", logToString());
  }

  private String logToString() {
    return testLog.toString(StandardCharsets.UTF_8);
  }

  /**
   * Test Joining game
   *
   * @throws IOException for inputStream
   */
  @Test
  public void testJoin() throws IOException {
    ObjectNode joinArgs = mapper.createObjectNode();
    joinArgs.put("name", "Player 1");
    joinArgs.put("game-type", GameType.SINGLE.toString());
    MessageJson message = new MessageJson("join", joinArgs);
    JsonNode messageNode = JsonUtils.serializeRecord(message);

    Mocket socket = new Mocket(this.testLog, List.of(messageNode.toString()));
    proxyController = new ProxyController(socket, aiPlayer);
    proxyController.run();

    String output = socket.getOutputStream().toString();
    assertNotNull(output);
    socket.close();
  }

  /**
   * Test whole game on server
   *
   * @throws IOException for inputStream
   */
  @Test
  public void testGame() throws IOException {
    ObjectNode fleetSpecArgs = mapper.createObjectNode();
    fleetSpecArgs.put(ShipType.CARRIER.toString(), 2);
    fleetSpecArgs.put(ShipType.BATTLESHIP.toString(), 4);
    fleetSpecArgs.put(ShipType.DESTROYER.toString(), 1);
    fleetSpecArgs.put(ShipType.SUBMARINE.toString(), 3);

    ObjectNode args = mapper.createObjectNode();
    args.put("width", 10);
    args.put("height", 10);
    args.put("fleet-spec", fleetSpecArgs);

    MessageJson message = new MessageJson("setup", args);
    JsonNode messageNode = JsonUtils.serializeRecord(message);

    Mocket socket = new Mocket(this.testLog, List.of(messageNode.toString()));
    proxyController = new ProxyController(socket, aiPlayer);
    proxyController.run();

    String output = socket.getOutputStream().toString();
    assertNotNull(output);

    // Test takeShots()
    message = new MessageJson("take-shots", args);
    messageNode = JsonUtils.serializeRecord(message);

    socket = new Mocket(this.testLog, List.of(messageNode.toString()));
    proxyController = new ProxyController(socket, aiPlayer);

    proxyController.run();
    output = socket.getOutputStream().toString();
    assertNotNull(output);

    // Test reportDamage()
    List<Coord> shotsThatHit = new ArrayList<>();
    shotsThatHit.add(new Coord(3, 0));
    shotsThatHit.add(new Coord(7, 8));
    CoordinatesJson coordinates = convertCoordsToJson(shotsThatHit);
    JsonNode coordinatesNode = mapper.convertValue(coordinates, JsonNode.class);
    message = new MessageJson("report-damage", coordinatesNode);
    messageNode = JsonUtils.serializeRecord(message);

    socket = new Mocket(this.testLog, List.of(messageNode.toString()));
    proxyController = new ProxyController(socket, aiPlayer);
    proxyController.run();

    output = socket.getOutputStream().toString();
    assertNotNull(output);

    // Test successfulHits()
    message = new MessageJson("successful-hits", coordinatesNode);
    messageNode = JsonUtils.serializeRecord(message);

    socket = new Mocket(this.testLog, List.of(messageNode.toString()));
    proxyController = new ProxyController(socket, aiPlayer);
    proxyController.run();

    output = socket.getOutputStream().toString();
    assertNotNull(output);
    socket.close();
  }


  /**
   * Convert regular coord to Json coords objects
   *
   * @param coords The coordinates in Coord object format
   * @return The coordinates in Json format
   */
  private CoordinatesJson convertCoordsToJson(List<Coord> coords) {
    ArrayList<CoordJson> coordJsons = new ArrayList<>();
    for (Coord coord : coords) {
      CoordJson coordJson = new CoordJson(coord.getX(), coord.getY());
      coordJsons.add(coordJson);
    }
    return new CoordinatesJson(coordJsons);
  }




}