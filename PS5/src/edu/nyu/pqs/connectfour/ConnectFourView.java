package edu.nyu.pqs.connectfour;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ConnectFourView implements Listener {

  private ConnectFour game;
  private JTextArea textArea = new JTextArea();
  private JButton button = new JButton("Guess");
  private JTextField field = new JTextField();
  
  public ConnectFourView (ConnectFour game) {
    
    this.game = game;
    game.addConnectFourListener(this);
    
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    
    JPanel bottomPanel = new JPanel();
    bottomPanel.setLayout(new BorderLayout());
    bottomPanel.add(field, BorderLayout.CENTER);
    bottomPanel.add(button, BorderLayout.EAST);
    
    
    panel.add(bottomPanel, BorderLayout.SOUTH);
    panel.add(new JScrollPane(textArea), BorderLayout.CENTER);
    frame.getContentPane().add(panel);
    
    frame.setSize(200,200);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        buttonPressed();
      }
    });
  }
  
  private void buttonPressed() {
    int guess = Integer.parseInt(field.getText());
    textArea.append("Guessing " + guess + "\n");
    game.guess(guess);
  }
  
  @Override
  public void gameStart() {
    textArea.append("Game started!\n");
  }

  @Override
  public void makeMove() {
    // TODO Auto-generated method stub

  }

  @Override
  public void winGame() {
    textArea.append("Good work!\n");

  }

  @Override
  public void tieGame() {
    // TODO Auto-generated method stub

  }

}
