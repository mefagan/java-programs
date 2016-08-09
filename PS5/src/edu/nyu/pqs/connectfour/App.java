package edu.nyu.pqs.connectfour;

public class App {
  public static void main(String[] args) {
    Controller gameController = new Controller();
    View gameView = new View(gameController);
    gameController.setView(gameView);
    gameController.setModel(gameView.getModel());
  }
}