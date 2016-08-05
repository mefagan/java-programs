package edu.nyu.pqs.connectfour;

import java.util.ArrayList;
import java.util.List;

public class ConnectFour {
  //used for randomizing somewhere
  private int num;
  private List<Listener> listeners = new ArrayList<Listener>();
  //singleton pattern
  private static ConnectFour INSTANCE;
  private int numColumns = 7;
  private int numRows = 6;
  private int numToWin = 4;
  private Chip[][] board;
  
  enum Chip {
    BLACK, RED, EMPTY;
  }
  
  private static class Builder {
    private final int numColumns;
    private final int numRows;
    private final int numToWin;
    private Chip[][] board;
   
    public Builder(int numColumns, int numRows, int numToWin) {
      this.numColumns = numColumns;
      this.numRows = numRows;
      this.numToWin = numToWin;
    }
    
    public ConnectFour build() {
      return ConnectFour.getInstance(numRows, numColumns, numToWin);
    }
  }
    
  private ConnectFour(Builder builder) {
    numRows = builder.numRows;
    numColumns = builder.numColumns;
    numToWin = builder.numToWin;
    this.emptyBoard();
  }
  
  public static ConnectFour getInstance(int numRows, int numColumns, int numToWin) {
    if (INSTANCE == null) {
      INSTANCE = new ConnectFour.Builder(numRows, numColumns, numToWin).build();
    }
    return INSTANCE;
  }
  
  public void addListener(Listener listener) throws NullPointerException {
    if (listener == null) {
      throw new NullPointerException("Listener object cannot be null");
    }
    listeners.add(listener);
  }
  
  
  
  public void emptyBoard() {
    board = new Chip[numRows][numColumns];
    for (int i = 0; i < numRows; i++) {
      for (int j = 0; j < numColumns; j++) {
        board[i][j] = Chip.EMPTY;
      }
    }
  }

  public Chip getCellState(int row, int column) {
    return board[row][column];
  }
 

}
