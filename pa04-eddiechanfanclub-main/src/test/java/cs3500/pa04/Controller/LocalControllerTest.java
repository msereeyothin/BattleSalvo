package cs3500.pa04.Controller;


import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import cs3500.pa04.GameData.Coord;
import cs3500.pa04.Model.Player.AiPlayer;
import cs3500.pa04.Model.Player.HumanPlayer;
import cs3500.pa04.View.LocalView;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class LocalControllerTest {
  AiPlayer p1 = mock(AiPlayer.class);
  HumanPlayer p2 = mock(HumanPlayer.class);
  LocalController controller = new LocalController(p1, p2);

  @Test
  public void testRun() {
    when(p1.takeShots()).thenReturn(new ArrayList<>());
    when(p2.takeShots()).thenReturn(new ArrayList<>());
    when(p1.reportDamage(anyList())).thenReturn(new ArrayList<>());
    when(p2.reportDamage(anyList())).thenReturn(new ArrayList<>());

    LocalView viewerMock = mock(LocalView.class);
    when(viewerMock.getDimensions()).thenReturn(new Coord(10, 10));

    controller.view = viewerMock;
    controller.run();
  }

  @Test
  public void testWin() {
    Coord coord = new Coord(3, 0);
    List<Coord> coords = new ArrayList<>();
    coords.add(coord);
    when(p1.takeShots()).thenReturn(coords);
    when(p2.takeShots()).thenReturn(new ArrayList<>());
    when(p1.reportDamage(anyList())).thenReturn(new ArrayList<>());
    when(p2.reportDamage(anyList())).thenReturn(new ArrayList<>());

    LocalView viewerMock = mock(LocalView.class);
    when(viewerMock.getDimensions()).thenReturn(new Coord(10, 10));

    controller.view = viewerMock;
    controller.run();

    when(p2.takeShots()).thenReturn(coords);
    when(p1.takeShots()).thenReturn(new ArrayList<>());
    when(p1.reportDamage(anyList())).thenReturn(new ArrayList<>());
    when(p2.reportDamage(anyList())).thenReturn(new ArrayList<>());

    viewerMock = mock(LocalView.class);
    when(viewerMock.getDimensions()).thenReturn(new Coord(10, 10));

    controller.view = viewerMock;
    controller.run();
  }

  @Test
  public void testLose() {
    Coord coord = new Coord(3, 0);
    List<Coord> coords = new ArrayList<>();
    coords.add(coord);
    when(p1.takeShots()).thenReturn(new ArrayList<>());
    when(p2.takeShots()).thenReturn(coords);
    when(p1.reportDamage(anyList())).thenReturn(new ArrayList<>());
    when(p2.reportDamage(anyList())).thenReturn(new ArrayList<>());

    LocalView viewerMock = mock(LocalView.class);
    when(viewerMock.getDimensions()).thenReturn(new Coord(10, 10));

    controller.view = viewerMock;
    controller.run();
  }

  @Test
  public void testRunning() {
    Coord coord = new Coord(3, 0);
    List<Coord> coords = new ArrayList<>();
    coords.add(coord);
    when(p1.takeShots()).thenReturn(coords);
    when(p2.takeShots()).thenReturn(coords);
    when(p1.reportDamage(anyList())).thenReturn(new ArrayList<>());
    when(p2.reportDamage(anyList())).thenReturn(new ArrayList<>());

    when(p1.takeShots()).thenReturn(new ArrayList<>());
    when(p2.takeShots()).thenReturn(coords);
    when(p1.reportDamage(anyList())).thenReturn(new ArrayList<>());
    when(p2.reportDamage(anyList())).thenReturn(new ArrayList<>());

    LocalView viewerMock = mock(LocalView.class);
    when(viewerMock.getDimensions()).thenReturn(new Coord(10, 10));

    controller.view = viewerMock;
    controller.run();
  }
}