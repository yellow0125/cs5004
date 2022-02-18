/**
 * @ Program: homework03
 * @ Date: 2021/10/14
 * @ Description: This class represents a chess piece named Rook.
 * A rook can move horizontally or vertically. I
 * t can kill any opponent’s piece if it can move to its place.
 */
public class Rook extends AbstractChess {
  /**
   * Initialize the fields for the Rook chess piece. The input is given the position of the Rook.
   *
   * @param initialRow    An integer representing the initial row of the chess piece Rook.
   * @param initialColumn An integer representing the initial column of the chess piece Rook.
   * @param initialColor  A String representing the initial color of the chess piece Rook.
   * @throws IllegalArgumentException when input initial row and column are bigger
   *                                  than seven or smaller than zero
   */
  public Rook(int initialRow, int initialColumn, Color initialColor) {
    super(initialRow, initialColumn, initialColor);
  }

  @Override
  public boolean canMove(int x, int y) {
    super.canMove(x, y);
    return Move.horizontally(this, x, y) || Move.vertically(this, x, y);
  }
}
