package edu.nyu.pqs.connectfour;

/**
 * Interface used as part of Observer pattern. When model performs certain functions,
 * it notified listeners and listeners act accordingly.
 * @author maryeileenfagan
 */
public interface Listener {
  /**
   * Notifies viewer that game has started and which player is beginning.
   * @param player that begins the game
   */
  public void gameStarted(Player player);
  /**
   * @param player current player
   * @return current player
   */
  public void currentPlayerTurn(Player player);
  /**
   * Notifies viewer that a chip was moved
   * @param row where chip was moved
   * @param column where chip was moved
   * @param player that made the move
   */
  public void chipMoved(int row, int column, Player player);
  /**
   * Notifies viewer that the game has been one by a player
   * @param player that won the game
   */
  public void gameWon(Player player);
  /**
   * Notified viewer that the game is tied because the board is full and no more moves are possible.
   */
  public void gameTied();
}
