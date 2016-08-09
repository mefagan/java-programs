package edu.nyu.pqs.connectfour;

import edu.nyu.pqs.connectfour.Chip;
import edu.nyu.pqs.connectfour.PlayerType;

/**
 * Class to store details about connect four players. Includes
 * whether the player is a human or computer and whether the player
 * is assigned a black chip or red chip.
 * @author maryeileenfagan
 *
 */
public class Player {
  
  private PlayerType type;
  private final Chip color;
  
  /**
   * @param type computer or human player
   * @param color whether the player is assigned red or black
   */
  Player (PlayerType type, Chip color) {
    this.type = type;
    this.color = color;
  }
  
  public void changePlayerType() {
    type = PlayerType.COMPUTER;
  }
  
  /**
   * @return the color that player is assigned
   */
  public Chip getColor() {
    return color;
  }
  
  /**
   * @return whether it's a human or computer player
   */
  public PlayerType getPlayerType() {
    return type;
  }
  
}
