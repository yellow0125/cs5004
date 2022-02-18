package cs5004.marblesolitaire.model;

/**
 * This is an enum class represents the type of the chess board slot of game
 * marble solitaire model.
 */
public enum ChessBoardSlot {
  CHESS, EMPTY, INVALID;

  /**
   * Empty constructor of class chess board slot.
   */

  ChessBoardSlot() {
    //empty
  }

  @Override
  public String toString() {
    switch (this) {

      case CHESS:
        return "O";
      case EMPTY:
        return "_";
      case INVALID:
        return " ";
      default:
        return null;
    }
  }
}
