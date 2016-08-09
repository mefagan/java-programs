package edu.nyu.pqs.connectfour;

import java.util.ArrayList;
import java.util.List;

/**
 * Class implements the Connect Four game board, players, and stores a list of listeners.
 * Throughout the game, column and row numbers are treated as index numbers in an array. 
 * For example, the first column is represented by 0.
 * @author maryeileenfagan
 *
 */
public class Model implements ModelInterface {
  private static final int numRows = 6;
  private static final int numColumns = 7;
  private static final int numToWin = 4;
  private List<Listener> listeners = new ArrayList<Listener>();
  private final Board gameBoard;
  private boolean playing = false;

  private Player player1;
  private Player player2;
  private Player currentPlayer;
  
  Model(Player player1, Player player2) {
   gameBoard = Board.getInstance(numRows, numColumns, numToWin); 
   this.player1 = player1;
   this.player2 = player2;
   currentPlayer = player1;
  }
  
  @Override
  public void addListener(Listener listener) {
    if (listener == null) {
      throw new NullPointerException("Listener object cannot be null");
    }
    listeners.add(listener);
  }
  
  @Override
  public void removeListener(Listener listener) {
    if (listener == null) {
      throw new NullPointerException("Listener object cannot be null");
    }
    listeners.remove(listener);
  }
  
  @Override
  public void makeMove(int column) {
    PlayerType type = currentPlayer.getPlayerType();
    Chip color = currentPlayer.getColor();
    if (!(type == PlayerType.COMPUTER) &&
            !(gameBoard.winningMoveExists(color))) {
      gameBoard.insertChip(column, color);
      int row = gameBoard.getNextAvailRow(column)- 1;
      fireChipMovedEvent(row, column, currentPlayer);
    } 
    if (!checkGameOver(column, color)){
      switchPlayer();
    }
  }
  
  /**
   * Checks if the last move made won the game and ends the game if it was.
   * @param column number of last move made
   * @param color of the last chip placed on the board
   * @return true if the last move made was a winning move
   * @return false if the last move made was not a winning move
   */
  private boolean checkForWin(int column, Chip color) {
    if (gameBoard.isWinningMove(column, color)) {
      fireGameWonEvent(currentPlayer);
      endGame();
      return true;
   }
    return false;
  }
  
  /**
   * Checks if all cells on the board have been taken. Used for finding games
   * that end in a draw.
   * Known bug is that there's an out of bounds error when the game is full. 
   * Needs to be fixed
   * @return boolean true if all cells on the board are full
   * @return boolean false if there are still available cells on the board
   */
  public boolean checkForFullBoard(){
    if (gameBoard.isBoardFull()) {
      fireGameTiedEvent();
      endGame();
      return true;
    }
    return false;
  }
  
  /**
   * Checks if the game is over because someone has won or because the board is full.
   * @param column number of the last move made
   * @param color of the chip last placed on the board
   * @return true if the game is over 
   * @return false if the game is still going on
   */
  public boolean checkGameOver(int column, Chip color) {
   return ((checkForFullBoard()) || (checkForWin(column, color)));
  }
  
  /**
   * @return number of rows
   */
  public int getNumRows() {
    return numRows;
  }
  
  /**
   * @return number of columns
   */
  public int getNumColumns() {
    return numColumns;
  }
  
  /**
   * @return number of connected chips of same kind needed to win the game
   */
  public int getNumToWin() {
    return numToWin;
  }
  
  @Override
  public void startNewGame(Player player1, Player player2) {
    if (playing) {
      throw new IllegalStateException("Game already in progress. End game first.");
    }
    gameBoard.reset();
    this.player1 = player1;
    this.player2 = player2;
    playing = true;
    fireGameStartedEvent(currentPlayer);
  }

  @Override
  public void endGame() {
    playing = false;
  }

  @Override
  public void switchPlayer() {
    if (currentPlayer == player1) {
      currentPlayer = player2;
    }
    else currentPlayer = player1;  
  }
  
  /**
   * Notifies view that a move has been made by a player
   * @param row of last move played
   * @param column of last move played
   * @param player that made the last move
   */
  private void fireChipMovedEvent(int row, int column, Player player) {
    for (Listener listener : listeners) {
      listener.chipMoved(row, column, player);
    }
  }
  
  /**
   * Notifies views that the game has begun.
   * @param player that made the last move
   */
  private void fireGameStartedEvent(Player player) {
    for (Listener listener : listeners) {
      listener.gameStarted(player);
    }
  }
  
  /**
   * Notifies view that the player has won the game
   * @param player that won the game
   */
  private void fireGameWonEvent(Player player) {
    for (Listener listener : listeners) {
      listener.gameWon(player);
    }
  }
  /**
   * Notifies view that the game is tied because all moves possible have been made
   */
  private void fireGameTiedEvent() {
    for (Listener listener : listeners) {
      listener.gameTied();
    }
  }
  
  /**
   * Function used for testing that returns the size of the listener list
   * @return the size of the listener list
   */
  public int getListeners() {
    return listeners.size();
  }
  
  /**
   * Changes the player type to computer after the game has started. 
   * Game automatically assigns both players to human and can then change.
   */
  public void switchToComputer() {
    player1.changePlayerType();
  }
  
  /**
   * @return the first player
   */
  public Player getPlayer1() {
    return player1;
  }
  
  /**
   * @return the second player
   */
  public Player getPlayer2() {
    return player2;
  }
  
  /**
   * @return current player
   */
  public Player getCurrentPlayer() {
    return currentPlayer;
  }
  
  /**
   * @return whether there's a game currently happening
   */
  public boolean getPlaying() {
    return playing;
  }
  
 }
