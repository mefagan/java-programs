package edu.nyu.pqs.connectfour;

public interface Listener {
  public void gameStart();
  public void makeMove();
  public void winGame();
  public void tieGame();
}
