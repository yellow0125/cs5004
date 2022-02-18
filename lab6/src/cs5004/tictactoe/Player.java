package cs5004.tictactoe;

/**
 * This is an enum class representing the players (X and O).
 */
public enum Player {
  X, O;

  /**
   * Empty constructor.
   */
  Player() {
  }

  /**
   * toString method for enum player.
   */
  public String toString() {
    switch (this) {
      case X:
        return "X";
      case O:
        return "O";
      default:
        return null;
    }
  }
  //  public String toString()
  //    return this.name();
}
