package edu.nyu.pqs.connectfour;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {
  private Board gameBoard;
  
  @Before
  public void setUp() {
    gameBoard = Board.getInstance(6, 7, 4);
  }
  
  @Test
  public final void testEmptyBoard() {
    gameBoard.reset();
    int numRows = gameBoard.getNumRows();
    int numColumns = gameBoard.getNumColumns();
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numColumns; j++) {
        assertEquals(gameBoard.getCellState(i, j), Chip.EMPTY);
      }
    }
  }

  @Test
  public final void testInitializeColumnAvailability() {
    gameBoard.reset();
    assertEquals(gameBoard.getNumColumns(), 7);
    assertEquals(gameBoard.getNextAvailRow(0), 0);
    for (int j = 0; j < 3; j++) {
      assertEquals(gameBoard.getNextAvailRow(j), 0);
    }
  }

  @Test
  public final void testGetCellState() {
    gameBoard.reset();
    gameBoard.setCellState(0, 3, Chip.BLACK);
    gameBoard.setCellState(1, 3, Chip.RED);
    assertEquals(gameBoard.getCellState(1, 3), Chip.RED);
    assertEquals(gameBoard.getCellState(0, 3), Chip.BLACK);
  }
  
  @Test
  public final void testSetCellState() {
    gameBoard.reset();
    gameBoard.setCellState(0, 2, Chip.BLACK);
    assertEquals(gameBoard.getCellState(0, 2), Chip.BLACK);
  }

  @Test(expected=IndexOutOfBoundsException.class)
  public final void testSetCellState_outOfBounds() {
    Board gameBoard = Board.getInstance(6, 7, 4);
    gameBoard.setCellState(6, 2, Chip.BLACK);
  }
  
  @Test
  public final void testIsBoardFull() {
    gameBoard.reset();
    assertFalse(gameBoard.isBoardFull());
    for (int i = 0; i < gameBoard.getNumColumns(); i++) {
      for (int j = 0; j < gameBoard.getNumRows(); j++){
        gameBoard.insertChip(i, Chip.BLACK);
      }
    }
    assertTrue(gameBoard.isBoardFull());
  }
  
  
  @Test
  public final void testInsertChip() {
    gameBoard.reset();
    assertEquals(gameBoard.getCellState(0, 3), Chip.EMPTY);
    gameBoard.insertChip(3, Chip.BLACK);
    assertEquals(gameBoard.getCellState(0, 3), Chip.BLACK);
  }

  @Test
  public final void testRemoveChip() {
    gameBoard.reset();
    int row = gameBoard.getNextAvailRow(3);
    gameBoard.insertChip(3, Chip.BLACK);
    assertEquals(gameBoard.getCellState(0, 3), Chip.BLACK);
    gameBoard.removeChip(3, Chip.BLACK);
    assertEquals(gameBoard.getCellState(0, 3), Chip.EMPTY);
    assertEquals(gameBoard.getNextAvailRow(3), row);
  }
  
  @Test(expected=IllegalArgumentException.class)
  public final void testRemoveChip_wrongChip(){
    gameBoard.reset();
    gameBoard.insertChip(3, Chip.BLACK);
    gameBoard.removeChip(3, Chip.RED);
    
  }
 
  @Test
  public final void testIsSpotAvailable_overflow() {
   gameBoard.reset();
   for (int i = 0; i < gameBoard.getNumRows(); i++) {
     gameBoard.insertChip(1, Chip.RED);
   }
   assertFalse(gameBoard.isSpotAvailable(1));
  }
  
  @Test
  public final void testIsSpotAvailable() {
   gameBoard.reset();
   for (int i = 0; i < gameBoard.getNumRows(); i++) {
     gameBoard.insertChip(1, Chip.RED);
   }
   assertTrue(gameBoard.isSpotAvailable(6));
  }

  @Test
  public final void testConnectedHorizontal_false() {
    gameBoard.reset();
    gameBoard.insertChip(1, Chip.RED);
    gameBoard.insertChip(1, Chip.BLACK);
    gameBoard.insertChip(1, Chip.RED);
    gameBoard.insertChip(1, Chip.BLACK);
    gameBoard.insertChip(1, Chip.BLACK);
    gameBoard.insertChip(1, Chip.BLACK);
    assertEquals(gameBoard.getNextAvailRow(1), 6);
    assertFalse(gameBoard.connectedHorizontal(5, 1, Chip.BLACK, 0));
  }
  
  @Test
  public final void testConnectedHorizontal_true() {
    gameBoard.reset();
    gameBoard.insertChip(1, Chip.RED);
    gameBoard.insertChip(1, Chip.RED);
    gameBoard.insertChip(1, Chip.BLACK);
    gameBoard.insertChip(1, Chip.BLACK);
    gameBoard.insertChip(1, Chip.BLACK);
    gameBoard.insertChip(1, Chip.BLACK);
    assertTrue(gameBoard.connectedHorizontal(5, 1, Chip.BLACK, 0));
  }
  
  @Test
  public final void testConnectedVertical_true() {
    gameBoard.reset();
    gameBoard.insertChip(0, Chip.RED);
    assertEquals(gameBoard.getCellState(0, 0), Chip.RED);
    gameBoard.insertChip(1, Chip.RED);
    assertEquals(gameBoard.getCellState(0, 1), Chip.RED);
    gameBoard.insertChip(2, Chip.BLACK);
    assertEquals(gameBoard.getCellState(0, 2), Chip.BLACK);
    gameBoard.insertChip(3, Chip.BLACK);
    assertEquals(gameBoard.getCellState(0, 3), Chip.BLACK);
    gameBoard.insertChip(4, Chip.BLACK);
    assertEquals(gameBoard.getCellState(0, 4), Chip.BLACK);
    gameBoard.insertChip(5, Chip.BLACK);
    assertTrue(gameBoard.isWinningMove(5, Chip.BLACK));
    assertEquals(gameBoard.getCellState(0, 5), Chip.BLACK);
    assertTrue(gameBoard.connectedVertical(0, 5, Chip.BLACK, 0));
  }
  
  @Test
  public final void testConnectedVertical_false() {
    gameBoard.reset();
    gameBoard.insertChip(0, Chip.RED);
    assertEquals(gameBoard.getCellState(0, 0), Chip.RED);
    gameBoard.insertChip(1, Chip.RED);
    assertEquals(gameBoard.getCellState(0, 1), Chip.RED);
    gameBoard.insertChip(2, Chip.BLACK);
    assertEquals(gameBoard.getCellState(0, 2), Chip.BLACK);
    gameBoard.insertChip(3, Chip.BLACK);
    assertEquals(gameBoard.getCellState(0, 3), Chip.BLACK);
    gameBoard.insertChip(4, Chip.BLACK);
    assertEquals(gameBoard.getCellState(0, 4), Chip.BLACK);
    gameBoard.insertChip(5, Chip.BLACK);
    assertEquals(gameBoard.getCellState(0, 5), Chip.BLACK);
    gameBoard.insertChip(5, Chip.BLACK);
    assertEquals(gameBoard.getCellState(1, 5), Chip.BLACK);
    assertFalse(gameBoard.connectedVertical(1, 5, Chip.BLACK, 0));
  }
  
  @Test
  public final void testDoesMoveWinGame_true() {
    gameBoard.reset();
    gameBoard.insertChip(1, Chip.RED);
    gameBoard.insertChip(1, Chip.RED);
    gameBoard.insertChip(1, Chip.BLACK);
    gameBoard.insertChip(1, Chip.BLACK);
    gameBoard.insertChip(1, Chip.BLACK);
    assertTrue(gameBoard.doesMoveWinGame(1, Chip.BLACK));
  }

  @Test
  public final void testDoesMoveWinGame_false() {
    gameBoard.reset();
    gameBoard.insertChip(1, Chip.RED);
    gameBoard.insertChip(1, Chip.RED);
    gameBoard.insertChip(1, Chip.BLACK);
    gameBoard.insertChip(1, Chip.BLACK);
    gameBoard.insertChip(1, Chip.BLACK);
    assertFalse(gameBoard.doesMoveWinGame(3, Chip.BLACK));
  }
  
  @Test
  public final void winningMoveExists_true() {
    gameBoard.reset();
    gameBoard.insertChip(1, Chip.RED);
    gameBoard.insertChip(1, Chip.RED);
    gameBoard.insertChip(1, Chip.BLACK);
    gameBoard.insertChip(1, Chip.BLACK);
    gameBoard.insertChip(1, Chip.BLACK);
    assertTrue(gameBoard.winningMoveExists(Chip.BLACK));
  }

  @Test
  public final void testWinningMoveExists_false2() {
    gameBoard.reset();
    gameBoard.insertChip(1, Chip.RED);
    gameBoard.insertChip(1, Chip.RED);
    gameBoard.insertChip(1, Chip.BLACK);
    gameBoard.insertChip(1, Chip.BLACK);
    gameBoard.insertChip(1, Chip.BLACK);
    gameBoard.insertChip(1, Chip.BLACK);
    assertFalse(gameBoard.winningMoveExists(Chip.BLACK));
  }
  
  @Test
  public final void testWinningMoveExists_false() {
    gameBoard.reset();
    assertFalse(gameBoard.winningMoveExists(Chip.BLACK));
  }
  
  //HELPER 1 _TRUE
  @Test
  public final void testConnectedForwardDiagonal_true1() {
    gameBoard.reset();
    gameBoard.insertChip(3, Chip.BLACK);
    assertEquals(gameBoard.getNextAvailRow(3), 1);
    assertEquals(gameBoard.getCellState(0, 3), Chip.BLACK); 
    gameBoard.insertChip(1, Chip.BLACK);
    gameBoard.insertChip(1, Chip.BLACK);
    gameBoard.insertChip(1, Chip.BLACK);
    assertEquals(gameBoard.getCellState(2, 1), Chip.BLACK);
    gameBoard.insertChip(2, Chip.BLACK);
    gameBoard.insertChip(2, Chip.BLACK);
    assertEquals(gameBoard.getCellState(1, 2), Chip.BLACK);
    gameBoard.insertChip(0, Chip.BLACK);
    gameBoard.insertChip(0, Chip.BLACK);
    gameBoard.insertChip(0, Chip.BLACK);
    gameBoard.insertChip(0, Chip.BLACK);
    assertEquals(gameBoard.getCellState(3, 0), Chip.BLACK);
    assertTrue(gameBoard.fDiagonalHelper1(Chip.BLACK, 0));
    assertTrue(gameBoard.connectedForwardDiagonal(Chip.BLACK, 0));
  }
  
  //HELPER1 FALSE
  @Test
  public final void testConnectedForwardDiagonal_false() {
    gameBoard.reset();
    gameBoard.insertChip(0, Chip.BLACK);
    gameBoard.insertChip(0, Chip.BLACK);
    gameBoard.insertChip(0, Chip.BLACK);
    gameBoard.insertChip(0, Chip.BLACK);
    gameBoard.insertChip(0, Chip.BLACK);
    assertEquals(gameBoard.getCellState(4, 0), Chip.BLACK);
    assertFalse(gameBoard.fDiagonalHelper1(Chip.BLACK, 0));
    assertFalse(gameBoard.connectedForwardDiagonal(Chip.BLACK, 0));
  }
  
  //HELPER2 TRUE
  @Test
  public final void testConnectedForwardDiagonal_true2() {
    gameBoard.reset();
    gameBoard.insertChip(5, Chip.BLACK);
    assertEquals(gameBoard.getNextAvailRow(5), 1);
    assertEquals(gameBoard.getCellState(0, 5), Chip.BLACK);  
    gameBoard.insertChip(5, Chip.BLACK);
    gameBoard.insertChip(5, Chip.BLACK);
    gameBoard.insertChip(5, Chip.BLACK);
    assertEquals(gameBoard.getCellState(3, 5), Chip.BLACK); 
    gameBoard.insertChip(4, Chip.RED);
    gameBoard.insertChip(4, Chip.RED);
    gameBoard.insertChip(4, Chip.RED);
    gameBoard.insertChip(4, Chip.RED);
    gameBoard.insertChip(4, Chip.BLACK);
    assertEquals(gameBoard.getCellState(4, 4), Chip.BLACK);
    for (int i = 0; i < 4; i++) {
      gameBoard.insertChip(3, Chip.RED);
    }
    gameBoard.insertChip(3, Chip.BLACK);
    gameBoard.insertChip(3, Chip.BLACK);
    assertEquals(gameBoard.getCellState(5, 3), Chip.BLACK);
    gameBoard.insertChip(6, Chip.BLACK);
    gameBoard.insertChip(6, Chip.BLACK);
    gameBoard.insertChip(6, Chip.BLACK);
    assertTrue(gameBoard.isWinningMove(6, Chip.BLACK));
    assertEquals(gameBoard.getCellState(2, 6), Chip.BLACK);
    assertTrue(gameBoard.fDiagonalHelper2(Chip.BLACK, 0));
    assertTrue(gameBoard.connectedForwardDiagonal(Chip.BLACK, 0));
  }
  
//HELPER2 FALSE
  @Test
  public final void testConnectedForwardDiagonal_false2() {
    gameBoard.reset();
    gameBoard.insertChip(5, Chip.BLACK);
    gameBoard.insertChip(5, Chip.BLACK);
    gameBoard.insertChip(5, Chip.BLACK);
    assertEquals(gameBoard.getCellState(2, 5), Chip.BLACK);  
    gameBoard.insertChip(3, Chip.BLACK);
    gameBoard.insertChip(3, Chip.BLACK);
    gameBoard.insertChip(3, Chip.BLACK);
    gameBoard.insertChip(3, Chip.BLACK);
    gameBoard.insertChip(3, Chip.BLACK);
    assertEquals(gameBoard.getCellState(4, 3), Chip.BLACK);  
    gameBoard.insertChip(4, Chip.BLACK);
    gameBoard.insertChip(4, Chip.BLACK);
    gameBoard.insertChip(4, Chip.BLACK);
    gameBoard.insertChip(4, Chip.BLACK);
    assertEquals(gameBoard.getCellState(3, 4), Chip.BLACK);  
    gameBoard.insertChip(6, Chip.RED);
    gameBoard.insertChip(6, Chip.RED);
    gameBoard.insertChip(6, Chip.RED);
    assertEquals(gameBoard.getCellState(1, 6), Chip.RED);  
    assertFalse(gameBoard.fDiagonalHelper2(Chip.BLACK, 0));
    assertFalse(gameBoard.connectedForwardDiagonal(Chip.BLACK, 0));
  }
  
  @Test
  public final void testBackWardDiagonal_true() {
    gameBoard.reset();
    gameBoard.insertChip(0, Chip.RED);
    gameBoard.insertChip(0, Chip.RED);
    gameBoard.insertChip(0, Chip.RED);
    assertEquals(gameBoard.getCellState(2, 0), Chip.RED);
    gameBoard.insertChip(1, Chip.RED);
    gameBoard.insertChip(1, Chip.RED);
    gameBoard.insertChip(1, Chip.RED);
    gameBoard.insertChip(1, Chip.RED);
    assertEquals(gameBoard.getCellState(3, 1), Chip.RED);
    gameBoard.insertChip(2, Chip.RED);
    gameBoard.insertChip(2, Chip.RED);
    gameBoard.insertChip(2, Chip.RED);
    gameBoard.insertChip(2, Chip.RED);
    gameBoard.insertChip(2, Chip.RED);
    assertEquals(gameBoard.getCellState(4, 2), Chip.RED);
    gameBoard.insertChip(3, Chip.RED);
    gameBoard.insertChip(3, Chip.RED);
    gameBoard.insertChip(3, Chip.RED);
    gameBoard.insertChip(3, Chip.RED);
    gameBoard.insertChip(3, Chip.RED);
    gameBoard.insertChip(3, Chip.RED);
    assertTrue(gameBoard.isWinningMove(3, Chip.RED));
    assertEquals(gameBoard.getCellState(5, 3), Chip.RED);
    assertTrue(gameBoard.connectedBackwardDiagonal(5, 3, Chip.RED, 0));
  }
  
  @Test
  public final void testGetNextAvailRow() {
    gameBoard.reset();
    for (int i = 0; i < gameBoard.getNumColumns(); i++){
      assertEquals(gameBoard.getNextAvailRow(i), 0);
    }
    gameBoard.insertChip(3, Chip.BLACK);
    gameBoard.insertChip(3, Chip.BLACK);
    assertEquals(gameBoard.getNextAvailRow(3), 2);
  }
  
  @Test(expected=ArrayIndexOutOfBoundsException.class)
  public final void testGetNextAvailRow_illegalColumn() {
    gameBoard.reset();
    gameBoard.insertChip(7, Chip.BLACK);
    gameBoard.insertChip(3, Chip.BLACK);
  }

  @Test
  public final void testGetNumRows() {
    gameBoard.reset();
    assertEquals(gameBoard.getNumColumns(), 7);
  }

  @Test
  public final void testGetNumColumns() {
    gameBoard.reset();
    assertEquals(gameBoard.getNumRows(), 6);
  }

  @Test
  public final void testGetNumToWin() {
    gameBoard.reset();
    assertEquals(gameBoard.getNumToWin(), 4);
  }
  
  @Test
  public final void testSet() {
    gameBoard.reset();
    gameBoard.setCellState(5, 6, Chip.RED);
    assertEquals(gameBoard.getCellState(5, 6), Chip.RED);
  }

}
