package cs3500.pa04.Controller;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import cs3500.pa04.GameData.Coord;
import cs3500.pa04.GameData.GameType;
import cs3500.pa04.GameData.Ship;
import cs3500.pa04.GameData.ShipType;
import cs3500.pa04.Model.Player.AiPlayer;
import cs3500.pa04.json.CoordJson;
import cs3500.pa04.json.CoordinatesJson;
import cs3500.pa04.json.FleetJson;
import cs3500.pa04.json.JsonUtils;
import cs3500.pa04.json.MessageJson;
import cs3500.pa04.json.SetupJson;
import cs3500.pa04.json.ShipJson;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A controller for an online game of BattleSalvo
 */
public class ProxyController implements Controller {
  private final Socket server;
  private final InputStream in;
  private final PrintStream out;
  private final AiPlayer player;
  private final ObjectMapper mapper = new ObjectMapper();
  private static final JsonNode VOID_RESPONSE =
      new ObjectMapper().getNodeFactory().textNode("void");


  /**
   * Construct an instance of a ProxyPlayer.
   *
   * @param server the socket connection to the server
   * @param player the instance of the player
   * @throws IOException if
   */
  public ProxyController(Socket server, AiPlayer player) throws IOException {
    this.server = server;
    this.in = server.getInputStream();
    this.out = new PrintStream(server.getOutputStream());
    this.player = player;
  }

  /**
   * Run a game of online BattleSalvo.
   */
  @Override
  public void run() {
    try {
      JsonParser parser = this.mapper.getFactory().createParser(this.in);
      while (!this.server.isClosed()) {
        MessageJson message = parser.readValueAs(MessageJson.class);
        delegateMessage(message);
      }
    } catch (IOException e) {
      // Disconnected from server or parsing exception
    }
  }

  /**
   * Delegate the handling of the message received from the server.
   *
   * @param message The message from the server.
   */
  private void delegateMessage(MessageJson message) {
    String name = message.messageName();
    JsonNode arguments = message.arguments();

    if ("join".equals(name)) {
      handleJoin();
    } else if ("setup".equals(name)) {
      handleSetup(arguments);
    } else if ("take-shots".equals(name)) {
      handleTakeShots();
    } else if ("report-damage".equals(name)) {
      handleReportDamage(arguments);
    } else if ("successful-hits".equals(name)) {
      handleSuccessfulHits(arguments);
    } else if ("end-game".equals(name)) {
      handleEndGame(arguments);
    } else {
      throw new IllegalStateException("Invalid message name");
    }
  }

  /**
   * Handling a join request from the server.
   */
  private void handleJoin() {
    System.out.println("handling join");

    ObjectNode args = mapper.createObjectNode();
    args.put("name", this.player.name());
    args.put("game-type", GameType.SINGLE.toString());

    MessageJson returnJoin = new MessageJson("join", args);
    JsonNode jsonResponse = JsonUtils.serializeRecord(returnJoin);
    this.out.println(jsonResponse);

  }

  /**
   * Handling setup arguments from the server.
   *
   * @param arguments The setup arguments.
   */
  private void handleSetup(JsonNode arguments) {
    // receiving setup info from server
    SetupJson setupArgs = this.mapper.convertValue(arguments, SetupJson.class);
    int width = setupArgs.width();
    int height = setupArgs.height();
    Map<String, Integer> stringFleetSpec = setupArgs.fleetSpec();
    Map fleetSpec = new HashMap<ShipType, Integer>();
    for (Map.Entry<String, Integer> set : stringFleetSpec.entrySet()) {
      fleetSpec.put(ShipType.getTypeByString(set.getKey()), set.getValue());
    }
    List<Ship> ships = this.player.setup(height, width, fleetSpec);

    JsonNodeFactory nodeFactory = mapper.getNodeFactory();
    ArrayNode jsonArray = nodeFactory.arrayNode();

    // sending setup info to server

    ArrayList<ShipJson> shipJsons = new ArrayList<>();

    for (Ship ship : ships) {
      CoordJson shipCoord = new CoordJson(
          ship.getCoords().get(0).getX(), ship.getCoords().get(0).getY());
      ShipJson shipDetails =
          new ShipJson(shipCoord, ship.getShipType().getSize(), ship.getDirection().toString());
      shipJsons.add(shipDetails);
    }
    FleetJson fleet = new FleetJson(shipJsons);
    JsonNode fleetNode = mapper.convertValue(fleet, JsonNode.class);
    MessageJson returnSetup = new MessageJson("setup", fleetNode);
    JsonNode jsonResponse = JsonUtils.serializeRecord(returnSetup);
    this.out.println(jsonResponse);
  }

  /**
   * Handles a server's requests for shots.
   */
  private void handleTakeShots() {
    List<Coord> aiShots = this.player.takeShots();
    CoordinatesJson coordinates = this.convertCoordsToJson(aiShots);
    JsonNode coordinatesNode = mapper.convertValue(coordinates, JsonNode.class);
    MessageJson returnShots = new MessageJson("take-shots", coordinatesNode);
    JsonNode jsonResponse = JsonUtils.serializeRecord(returnShots);
    this.out.println(jsonResponse);
  }

  /**
   * Handle a server's shots.
   *
   * @param arguments The server shot arguments
   */
  private void handleReportDamage(JsonNode arguments) {
    // Handling coords shot by opponent by converting into a List<Coord>
    CoordinatesJson reportDamageArgs = this.mapper.convertValue(arguments,
        CoordinatesJson.class);
    List<Coord> opponentShotList = this.convertJsonToCoords(reportDamageArgs);

    // Returning the shots that hit to the server
    List<Coord> shotsThatHit = this.player.reportDamage(opponentShotList);
    CoordinatesJson coordinates = this.convertCoordsToJson(shotsThatHit);
    JsonNode coordinatesNode = mapper.convertValue(coordinates, JsonNode.class);
    MessageJson returnShotsThatHit = new MessageJson("report-damage", coordinatesNode);
    JsonNode jsonResponse = JsonUtils.serializeRecord(returnShotsThatHit);
    this.out.println(jsonResponse);
  }

  /**
   * Handle receive the successful hits from a server.
   *
   * @param arguments The server's successful hits
   */
  private void handleSuccessfulHits(JsonNode arguments) {
    CoordinatesJson successfulHitsArgs = this.mapper.convertValue(arguments,
        CoordinatesJson.class);
    List<Coord> successfulHits = this.convertJsonToCoords(successfulHitsArgs);
    // Add functionality to display opponent board, but since this is an AI
    // no need to display anything
    MessageJson returnSuccessfulHits = new MessageJson("successful-hits", VOID_RESPONSE);
    JsonNode jsonResponse = JsonUtils.serializeRecord(returnSuccessfulHits);
    this.out.println(jsonResponse);
  }

  /**
   * Handling the end of the game.
   *
   * @param arguments The server's end game arguments
   */
  private void handleEndGame(JsonNode arguments) {
    // EndGameJson endGameArgs = this.mapper.convertValue(arguments, EndGameJson.class);
    MessageJson returnEndGame = new MessageJson("end-game", VOID_RESPONSE);
    JsonNode jsonResponse = JsonUtils.serializeRecord(returnEndGame);
    this.out.println(jsonResponse);

  }

  /**
   * Convert Json coords to regular coord objects
   *
   * @param coordsJson The coordinates in Json format
   * @return The coordinates in Coord object format
   */
  private List<Coord> convertJsonToCoords(CoordinatesJson coordsJson) {
    List<CoordJson> opponentShotListJson = coordsJson.coords();
    List<Coord> coordList = new ArrayList<>();
    for (CoordJson coordJson : opponentShotListJson) {
      Coord curCoord = new Coord(coordJson.xCoord(), coordJson.yCoord());
      coordList.add(curCoord);
    }
    return coordList;
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
    CoordinatesJson coordinates = new CoordinatesJson(coordJsons);
    return coordinates;
  }
}
