/**
 * @ Program: homework03
 * @ Date: 2021/10/14
 * @ Description: This class represents a chess piece named Queen.
 * A queen can move horizontally, vertically and diagonally.
 * It can kill any opponent’s piece if it can move to its place.
 */
public class Queen extends AbstractChess {
  /**
   * Initialize the fields for the Queen chess piece. The input is given the position of the Queen.
   *
   * @param initialRow    An integer representing the initial row of the Queen.
   * @param initialColumn An integer representing the initial column of the Queen.
   * @param initialColor  A String representing the initial color of the Queen.
   * @throws IllegalArgumentException when input initial row and column are bigger
   *                                  than seven or smaller than zero
   */
  public Queen(int initialRow, int initialColumn, Color initialColor) {
    super(initialRow, initialColumn, initialColor);
  }

  @Override
  public boolean canMove(int x, int y) {
    super.canMove(x, y);
    return Move.horizontally(this, x, y) || Move.vertically(this, x, y)
            || Move.diagonally(this, x, y);
  }
}