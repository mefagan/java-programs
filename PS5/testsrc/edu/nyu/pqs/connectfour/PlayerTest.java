package edu.nyu.pqs.connectfour;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PlayerTest {

  @Test
  public final void testPlayerGetType() {
    Player player = new Player(PlayerType.COMPUTER, Chip.RED);
    assertEquals(player.getPlayerType(), PlayerType.COMPUTER);
  }
  
  @Test
  public final void testGetColor() {
    Player player = new Player(PlayerType.COMPUTER, Chip.RED);
    assertEquals(player.getColor(), Chip.RED);
  }

}
