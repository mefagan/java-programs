package edu.nyu.pqs.connectfour;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * This is unfortunately incomplete because I wasn't able to figure out swing in enough time
 * What I do have done is influenced heavily from reading at
 * http://bit.ly/2aHL21h and 
 * http://bit.ly/2avPO0u
 * @author maryeileenfagan
 *
 */
/**
 * @author maryeileenfagan
 *
 */
public class View implements Listener {
  
  private Model gameModel;
  private Player player1;
  private Player player2;
  private Controller controller;
  private int numRows;
  private int numColumns;
  
  private JPanel main;
  private JPanel board[][];
  private JFrame frame;
  private GridLayout grid;
  private JButton button;
  
  /**
   * Default start for game is a board with two human players, but can switch
   * the first player to a computer player.
   * @param controller view receives the controller features
   */
  View(Controller controller) {
    Player player1 = new Player(PlayerType.HUMAN, Chip.BLACK);
    Player player2 = new Player(PlayerType.HUMAN, Chip.RED);
    Model gameModel = new Model(player1, player2);
    numRows = gameModel.getNumRows();
    numColumns = gameModel.getNumColumns();
    gameModel.addListener(this);
    this.controller = controller;
    start();
  }
  
  private void start() {
    buildBoard();
  }
  
  private void buildBoard() {
    board = new JPanel[numRows][numColumns];
    grid = new GridLayout(numRows, numColumns);
    frame = new JFrame("CONNECT FOUR");
    button = new JButton();
    main = new JPanel();
    frame.setSize(400,400);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new BorderLayout());
    frame.pack();
    frame.setVisible(true);
  }
  
  
  /**
   * Should display who goes first.
   * @param player
   */
  @Override
  public void gameStarted(Player player) { 
  }

  /**
   * Should update board to reflect the new chip that was placed in it
   * @param row of last move
   * @param column of last move
   * @param player of last move
   */
  @Override
  public void chipMoved(int row, int column, Player player) {    
  }

  /**
   * Should display that the game has been won.
   * @param player 
   */
  @Override
  public void gameWon(Player player) {    
  }

  /**
   * Should display that the game is tied when the board is full.
   */
  @Override
  public void gameTied() {    
  }

  /**
   * Should display whose turn it is.
   * @param player whose turn it is
   */
  @Override
  public void currentPlayerTurn(Player player) {
  }
  
  /**
   * Function should reset the board after a move has been made.
   */
  public void refreshBoard() {
  }
 
  public Model getModel() {
    return gameModel;
   }

}
