package edu.nyu.pqs.connectfour;

public interface ModelInterface {
  /**
   * Removes a view from the observer pattern
   * @param listener view of the model for observer pattern
   */
  public void removeListener(Listener listener);
  /**
   * Adds a view to the observer pattern
   * @param listener view of the model for observer pattern
   */
  public void addListener(Listener listener);
  /**
   * Switches player to the player that is not the current player.
   * Function is employed automatically after another player moves.
   */
  public void switchPlayer();
  /**
   * Function takes a column and places a chip corresponding to the current player
   * into the next available cell in the column.
   * Player can only place in a stack manner so there's no option to specify
   * which row to place the chip in. Row is determined by next row available.
   * After move has been made, the current player switches to the next player.
   * Function is used as part of controller features for making a human play
   * or making a computer play.
   * Functionality of the computer and human moves explained in controller javadocs.
   * @param column into which chip should be placed
   */
  public void makeMove(int column);
  /**
   * Starts game. Assigns first and second player. Since the board is a singleton,
   * starting the game clears the board from the previous game and sets the status 
   * of a playing boolean to true. 
   * @param player1 that's participating in the game
   * @param player2 that's participating in the game
   * @throws IllegalStateException if there's a game already happening
   */
  public void startNewGame(Player player1, Player player2);
  /**
   * Sets status of the boolean playing to false so that new games can be started
   */
  public void endGame();
}
