

/**
 * @ Program: homework03
 * @ Date: 2021/10/14
 * @ Description: This class represents a chess piece named Bishop.
 * A bishop can only move diagonally and kill any opponent’s piece if it can move to its place.
 */
public class Bishop extends AbstractChess {
  /**
   * IInitialize the fields for the Bishop chess piece.
   * The input is given the position of the Bishop.
   *
   * @param initialRow    An integer representing the initial row of the chess piece Bishop
   * @param initialColumn An integer representing the initial column of the chess piece Bishop
   * @param initialColor  A String representing the initial color of the chess piece Bishop
   * @throws IllegalArgumentException when input initial row and column are bigger
   *                                  than seven or smaller than zero
   */
  public Bishop(int initialRow, int initialColumn, Color initialColor) {
    super(initialRow, initialColumn, initialColor);
  }

  AbstractChess test = new Bishop(3,3,Color.BLACK);


  @Override
  public boolean canMove(int x, int y) {
    //super.canMove(x, y);
    return Move.diagonally(this, x, y);
  }
}

