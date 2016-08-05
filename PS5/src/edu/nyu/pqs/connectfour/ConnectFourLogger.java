package edu.nyu.pqs.connectfour;

public class ConnectFourLogger implements Listener {
  
  ConnectFourLogger(ConnectFourModel model) {
    model.addListener(this);
  }

  @Override
  public void gameStart() {
    // TODO Auto-generated method stub

  }

  @Override
  public void makeMove() {
    // TODO Auto-generated method stub

  }

  @Override
  public void winGame() {
    // TODO Auto-generated method stub

  }

  @Override
  public void tieGame() {
    // TODO Auto-generated method stub

  }

}
