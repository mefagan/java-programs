package edu.nyu.pqs.connectfour;

import java.util.List;

import edu.nyu.pqs.connectfour.Chip;

/**
 * Class for a board on which players can play the game ConnectFour.
 * Board is a singleton class and can be reset for new games.
 * Class checks for winning moves, updates the board with each move, and
 * decides if the game has been won.
 * Number of rows are set to 6 and columns set to 6.
 * Player must connect 4 cells in order to win.
 * @author maryeileenfagan
 *
 */
/**
 * @author maryeileenfagan
 *
 */
public class Board {
  
  private static Board INSTANCE;
  private int numColumns = 7;
  private int numRows = 6;
  private int numToWin = 4;
  private Chip[][] board;
  private static int[] nextAvailRow;
  
  private static class Builder {
    private final int numColumns;
    private final int numRows;
    private final int numToWin;
 
    public Builder(int numRows, int numColumns, int numToWin) {
      this.numColumns = numColumns;
      this.numRows = numRows;
      this.numToWin = numToWin;
    }
    
    public Board build() {
      return new Board(this);
    }
  }
    
  private Board(Builder builder) {
    numRows = builder.numRows;
    numColumns = builder.numColumns;
    numToWin = builder.numToWin;
    emptyBoard();
    initializeColumnAvailability();
  }
  
  /**
   * All calls to function return the same object reference and no other instance
   * will be created. 
   * Singleton class and singleton property enforced by a static factory method.
   * @param numRows number of rows on board
   * @param numColumns number of columns on board
   * @param numToWin number needed in order to win the game
   * @return singleton board class
   */
  protected static Board getInstance(int numRows, int numColumns, int numToWin) {
    if (INSTANCE == null) {
      INSTANCE = new Board.Builder(numRows, numColumns, numToWin).build();
    }
    return INSTANCE;
  }
 
 /**
  * Board is a singleton so new games require the board to be reset.
 * Makes the state of every cell of the board empty.
 */
  public void reset() {
   INSTANCE.emptyBoard();
   INSTANCE.initializeColumnAvailability();
 }
     
  /**
   *Empties the singleton board so that each cell state is empty. 
   */
  private void emptyBoard() {
    board = new Chip[numRows][numColumns];
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numColumns; j++) {
        setCellState(i,j, Chip.EMPTY);
      }
    }
  }
  
  /**
   * Sets board so that the next free spot in each column is the first spot.
   */
  private void initializeColumnAvailability() {
    Board.nextAvailRow = new int[7];
    for (int i = 0; i < 7; i++) {
      nextAvailRow[i] = 0;
    }
  }
  
  /**
   * Gets the specified cell state at the given coordinates.
   * @param row
   * @param column
   * @return
   */
  protected Chip getCellState(int row, int column) {
    return board[row][column];
  }
  
  /**
   * Sets the given cell on the 2D matrix to a state of empty, black, or red, as specified.
   * @param row of specified cell
   * @param column of specified cell
   * @param state state the cell should be set to
   */
  public void setCellState(int row, int column, Chip state)  {
    board[row][column] = state;
  }
  
  /**
   * Checks if all cells on the board are full.
   * @return boolean true if none of the cells are empty
   * @return boolean false if there exists an empty cell on the board
   */
  public boolean isBoardFull() {
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numColumns; j++) {
        if (getCellState(i, j) == Chip.EMPTY) {
          return false;
        }
      }
    }
    return true;
  }
  
  /**
   * Inserts a chip of a given state in the next available row of a specified column.
   * @param column where chip should be inserted
   * @param state of the chip that will be inserted
   */
  public void insertChip(int column, Chip state) {
    int row = getNextAvailRow(column);
    setCellState(row, column, state);
    nextAvailRow[column] = nextAvailRow[column] + 1;
  }
  
  /**
   * Removes a chip from a column, but chip color must match color 
   * of top chip in column since that is the only chip that can be removed.
   * Function acts a utility for AI when human plays a computer
   * @param column from which chip will be removed
   * @param state type of chip that should be removed
   * @throws illegal argument exception when removed chip isn't top chip
   */
  public void removeChip(int column, Chip state) {
    int row = nextAvailRow[column] - 1;
    if (getCellState(row, column) != state) {
      throw new IllegalArgumentException("Chip state must correspond to current player state.");
    }
    setCellState(row, column, Chip.EMPTY);
    nextAvailRow[column] = nextAvailRow[column] - 1;
  }
  
  /**
   * Checks to see if all available spots in a given column are full
   * @param column that is being checked for availability
   * @return boolean true if all spots in column are not full
   * @return boolean false if all spots in column are full
   */
  public boolean isSpotAvailable(int column) {
    if (nextAvailRow[column] >= numRows) {
      return false;
    } else {
      return true;
    }
  }
  
  /**
   * Checks to see if there are four cells of similar state consecutively and returns 
   * the int 4 if there are. Otherwise, returns number between 0 and 3, inclusive.
   * @param row of last move played
   * @param column of last move played
   * @param state of last cell played
   * @param count utility to check if 4 in a row
   * @return count of four in a row if there are or zero otherwise
   */
  private int incrementCounter (int row, int column, Chip state, int count) {
    if (getCellState(row, column) == state) {
      count++;
    } else {
      count = 0;
    }
    return count;
  }
  
  /**
   * @param row of last move
   * @param column of last move
   * @param state of last cell played
   * @param count number that will be updated to check for 4 in a row
   * @return
   */
  public boolean connectedBackwardDiagonal(int row, int column, Chip state, int count) {
    int difference = Math.abs(row - column);
    int i = 0;
    int j = 0;
    if (column > row) {
      j =+ difference;
      return checkBackwardDiagonal(i, j, state, count);
      } else {
       i =+ difference;
       return checkBackwardDiagonal(i, j, state, count);
    }
  }
  
  /**
   * Checks if there are four in a row along a backward diagonal after last move played.
   * @param row of last move
   * @param column of last move
   * @param state of last cell played
   * @param count number that will be updated to check for 4 in a row
   * @return
   */
  private boolean checkBackwardDiagonal(int row, int column, Chip state, int count) {
    while (row < numRows && column < numColumns) {
      count = incrementCounter(row, column, state, count);
      if (count == numToWin) {
        return true;
      }
      row++;
      column++;
    }
    return false;
  }
  
  /**
   * Checks if there are four in a row along a forward diagonal after last move played.
   * @param state of the chip that was last placed on the board
   * @param count number that will be updated to check for 4 in a row
   * @return boolean true if there's a forward diagonal connection of four
   * @return boolean false if there's no forward diagonal connection of four
   */
  public boolean connectedForwardDiagonal(Chip state, int count) {
    return (fDiagonalHelper1(state, count) || 
           fDiagonalHelper2(state, count));
  }
    
  /**
   * Helper function to check if there are four chips of the same type in a forward diagoanal
   * @param state of the chip, which is red, black, or empty
   * @param count integer used to compare to number needed to win 
   * @return boolean true if a forward diagonal 4 with a column number 3 or 4 is found
   * @return boolean false if a connection of four of the same kind is not found
   */
  public boolean fDiagonalHelper1(Chip state, int count)  {
    for (int i = 3; i < 5; i++) {
      for (int j = 0; j < 4; j++) {
        if (checkForwardDiagonal(i, j, state, count)) {
          return true;
        }
      }
     }
    return false;
    }
  
  /**
   * @param state of the chip, which is red, black, or empty
   * @param count integer used to compare to number needed to win 
   * @return boolean true if a forward diagonal 4 with a column number 5 is found
   * @return boolean false if a connection of four of the same kind is not found
   */
  public boolean fDiagonalHelper2(Chip state, int count)  {
    int i = 5;
    for (int j = 0; j < 4; j++) {
      if (checkForwardDiagonal(i, j, state, count)) {
        return true;
      }
    }
    return false;
  }
  
  /**
   * Checks if there are four chips of the same kind in a row on a forward diagonal
   * @param row of current move
   * @param column of current move
   * @param state of cell just played
   * @param count utility int to see if there are four in a row
   * @return boolean true if a four in a row exist on a forward diagonal
   * @return boolean false if four in a row do no exist on a forward diagonal
   */
  private boolean checkForwardDiagonal(int row, int column, Chip state, int count) {
    while (row >= 0 && column < numColumns) {
      count = incrementCounter(row, column, state, count);
      if (count == numToWin) {
        return true;
      }
      row--;
      column++;
    }
    return false;
  }
  
  /**
   * Checks to see if the row of the move just made is a connect four.
   * @param row of the move just made
   * @param column of the move just made
   * @param state of the chip that was just placed
   * @param count initialized to zero, used to check for numToWin
   * @return
   */
  public boolean connectedHorizontal(int row, int column, Chip state, int count) {
    for (int j = 0; j < numRows; j++) {
      if (getCellState(j, column) == state){
        count++;
       }
       else {
         count = 0;
        }
       if (count == numToWin){
         return true;
        }
      } 
   return false;
  }
  
  /**
   * Checks if there's a vertical connection of four in a row given last move.
   * @param row of last move
   * @param column of last move
   * @param state of the chip of the last move
   * @param count utility to see if there are four in a row
   * @return boolean true if there are four in a row vertically
   * @return boolean false if there are not four in a row vertically
   */
  public boolean connectedVertical(int row, int column, Chip state, int count) {
    for (int i = 0; i < numColumns; i++) {
     if (getCellState(row, i) == state){
      count++;
     }
     else {
       count = 0;
      }
     if (count == numToWin){
       return true;
      }
    } 
   return false;
  }
  
  /**
   * Checks if a specified move will result in a winning game
   * It checks if the move wins the game only after the move has already been made.
   * @param column of last move made
   * @param state of the cell of the last move made
   * @return boolean true if the move wins the game
   * @return boolean false if the move does not win the game
   */
  public boolean isWinningMove(int column, Chip state) {
    int row = nextAvailRow[column] -1 ;
    int count = 0;
    return ((connectedBackwardDiagonal(row, column, state, count)) ||
            (connectedForwardDiagonal(state, count)) ||
            (connectedHorizontal(row, column, state, count)) ||
            (connectedVertical(row, column, state, count)));
  }
  
  public boolean doesMoveWinGame(int column, Chip state) {
    insertChip(column, state);
    if (isWinningMove(column, state)) {
      removeChip(column, state);
      return true;
    }
    removeChip(column, state);
    return false;
  }
  
  /**
   * Checks if a winning move exists by first checking if a spot is available in each column.
   * If it's available, function checks if that move wins the game then makes the move.
   * If not, it does nothing.
   * @param state of the chip whose turn it is, will be AI in this case
   * @return boolean true if there's a move the AI can make to win the game
   * @return boolean false if there's not a move the AI can make to win game
   */
  public boolean winningMoveExists(Chip state) {
    for (int j = 0; j < numColumns; j++){
      if (isSpotAvailable(j)){
        if (doesMoveWinGame(j, state)) {
          insertChip(j, state);
          return true;
        }
      }
    }
    return false;
  }
  
  /**
   * @param column of which you want next available row
   * @return int indicating index of next available row
   */
  public int getNextAvailRow(int column) {
    return nextAvailRow[column];
  }
  
  /**
   * @return number of rows on board
   */
  public int getNumRows() {
    return numRows;
  }
  
  /**
   * @return number of columns on board
   */
  public int getNumColumns() {
    return numColumns;
  }
  
  /**
   * @return number of same chip types in a row needed to win
   */
  public int getNumToWin() {
    return numToWin;
  }
}