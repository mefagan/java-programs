package edu.nyu.pqs.connectfour;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ModelTest {

  @Test
  public final void testAddListener() {
    Controller testController = new Controller();
    View testView = new View(testController);
    Player player1 = new Player(PlayerType.HUMAN, Chip.BLACK);
    Player player2 = new Player(PlayerType.COMPUTER, Chip.RED);
    Model gameModel = new Model(player1, player2);
    gameModel.startNewGame(player1, player2);
    gameModel.addListener(testView);
    assertEquals(gameModel.getListeners(), 1);
  }

  @Test
  public final void testRemoveListener() {
    Controller testController = new Controller();
    View testView = new View(testController);
    Player player1 = new Player(PlayerType.HUMAN, Chip.BLACK);
    Player player2 = new Player(PlayerType.COMPUTER, Chip.RED);
    Model gameModel = new Model(player1, player2);
    gameModel.startNewGame(player1, player2);
    gameModel.addListener(testView);
    gameModel.removeListener(testView);
    assertEquals(gameModel.getListeners(), 0);
  }

  @Test
  public final void testMakeMove() {
    Player player1 = new Player(PlayerType.HUMAN, Chip.BLACK);
    Player player2 = new Player(PlayerType.COMPUTER, Chip.RED);
    Model gameModel = new Model(player1, player2);
    gameModel.startNewGame(player1, player2);
    assertEquals(gameModel.getCurrentPlayer(), gameModel.getPlayer1());
    gameModel.makeMove(1);
    assertEquals(gameModel.getCurrentPlayer(), gameModel.getPlayer2());
    gameModel.makeMove(1);
    assertEquals(gameModel.getCurrentPlayer(), gameModel.getPlayer1());
  }

  @Test
  public final void testStartNewGame() {
    Player player1 = new Player(PlayerType.HUMAN, Chip.BLACK);
    Player player2 = new Player(PlayerType.COMPUTER, Chip.RED);
    Model gameModel = new Model(player1, player2);
    gameModel.startNewGame(player1, player2);
    assertTrue(gameModel.getPlaying());
  }
  
  @Test(expected=IllegalStateException.class)
  public final void testStartGameException() {
    Player player1 = new Player(PlayerType.HUMAN, Chip.BLACK);
    Player player2 = new Player(PlayerType.COMPUTER, Chip.RED);
    Model gameModel = new Model(player1, player2);
    gameModel.startNewGame(gameModel.getPlayer1(), gameModel.getPlayer2());
    assertTrue(gameModel.getPlaying());
    gameModel.startNewGame(gameModel.getPlayer1(), gameModel.getPlayer2());
  }

  @Test
  public final void testCheckGameOver_Winner() {
    Player player1 = new Player(PlayerType.HUMAN, Chip.BLACK);
    Player player2 = new Player(PlayerType.HUMAN, Chip.RED);
    Model gameModel = new Model(player1, player2);
    gameModel.startNewGame(player1, player2);
    assertEquals(gameModel.getCurrentPlayer(), gameModel.getPlayer1());
    gameModel.makeMove(1);
    assertEquals(gameModel.getCurrentPlayer(), gameModel.getPlayer2());
    gameModel.makeMove(0);
    gameModel.makeMove(1);
    gameModel.makeMove(4);
    Chip state = gameModel.getCurrentPlayer().getColor();
    assertFalse(gameModel.checkGameOver(4, state));
    gameModel.makeMove(1);
    gameModel.makeMove(5);
    gameModel.makeMove(1);
    state = gameModel.getCurrentPlayer().getColor();
    assertTrue(gameModel.checkGameOver(1, state));
  }
  
  @Test
  public final void testCheckGameOver_fullBoard() {
    Player player1 = new Player(PlayerType.HUMAN, Chip.BLACK);
    Player player2 = new Player(PlayerType.HUMAN, Chip.RED);
    Model gameModel = new Model(player1, player2);
    gameModel.startNewGame(player1, player2);
    int i = 0;
    for (int j = 0; j < gameModel.getNumRows(); j++) {
      gameModel.makeMove(i);
    }
    assertFalse(gameModel.checkForFullBoard());
    i = 1;
    for (int j = 0; j < gameModel.getNumRows(); j++) {
      gameModel.makeMove(i);
    }
    assertFalse(gameModel.checkForFullBoard());
    i = 2;
    for (int j = 0; j < gameModel.getNumRows(); j++) {
      gameModel.makeMove(i);
    }
    assertFalse(gameModel.checkForFullBoard());
    i = 4;
    for (int j = 0; j < gameModel.getNumRows(); j++) {
      gameModel.makeMove(i);
    }
    assertFalse(gameModel.checkForFullBoard());
  }
  
  @Test
  public final void testEndGame() {
    Player player1 = new Player(PlayerType.HUMAN, Chip.BLACK);
    Player player2 = new Player(PlayerType.HUMAN, Chip.RED);
    Model gameModel = new Model(player1, player2);
    gameModel.startNewGame(player1, player2);
    assertTrue(gameModel.getPlaying());
    gameModel.endGame();
    assertFalse(gameModel.getPlaying());
  }
  
  @Test
  public final void testGetNumbers() {
    Player player1 = new Player(PlayerType.HUMAN, Chip.BLACK);
    Player player2 = new Player(PlayerType.HUMAN, Chip.RED);
    Model gameModel = new Model(player1, player2);
    assertEquals(gameModel.getNumRows(), 6);
    assertEquals(gameModel.getNumColumns(), 7);
    assertEquals(gameModel.getNumToWin(), 4);
  }
  

}
