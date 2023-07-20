package cs3500.pa04;

import static java.lang.Integer.parseInt;

import cs3500.pa04.Controller.Controller;
import cs3500.pa04.Controller.LocalController;
import cs3500.pa04.Controller.ProxyController;
import cs3500.pa04.Model.Player.AiPlayer;
import cs3500.pa04.Model.Player.HumanPlayer;
import java.io.IOException;
import java.net.Socket;

/**
 * The Driver is responsible for connecting to the server
 * and then running an entire game with a player.
 */
public class Driver {
  /**
   * This method connects to the server at the given host and port, builds a proxy referee
   * to handle communication with the server, and sets up a client player.
   *
   * @param host the server host
   * @param port the server port
   * @throws IOException if there is a communication issue with the server
   */
  private static void runClient(String host, int port)
      throws IOException, IllegalStateException {
    Socket server = new Socket(host, port);
    ProxyController proxyController = new ProxyController(server, new AiPlayer());
    proxyController.run();
  }

  private static void runLocal() {
    LocalController controller = new LocalController(new HumanPlayer(), new AiPlayer());
    controller.run();
  }

  /**
   * The main entrypoint into the code as the Client. Given a host and port as parameters, the
   * client is run. If there is an issue with the client or connecting,
   * an error message will be printed.
   *
   * @param args The expected parameters are the server's host and port
   */
  public static void main(String[] args) throws IOException {
    if (args.length == 2) {
      try {
        String host = args[0];
        int port = parseInt(args[1]);
        Driver.runClient(host, port);
      } catch (Exception e) {
        System.out.println("Invalid arguments!");
      }
    } else if (args.length == 0) {
      Driver.runLocal();
    } else {
      System.out.println("Must have either 0 or 2 valid arguments!");
    }
  }
}