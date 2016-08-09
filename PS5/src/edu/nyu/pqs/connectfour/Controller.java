package edu.nyu.pqs.connectfour;

import java.util.Random;

/**
 * Controller class that has functionality to make a human player move
 * or make a computer player move.
 * @author maryeileenfagan
 *
 */
public class Controller {
  
  private View gameView;
  private Model gameModel;
  private Player player1;
  private Player player2;
 
  Controller() {}
  
  public void setModel(Model gameModel) {
    this.gameModel = gameModel;
  }

  public void setView(View gameView) {
    this.gameView = gameView;
  }
  
  private void computerMoves() {
    Random rand = new Random();
    int value = rand.nextInt(7);
    gameModel.makeMove(value);
    gameView.refreshBoard();
  }
  
  private void humanMoves(int column) {
    gameModel.makeMove(column);
    gameView.refreshBoard();
  }
}
