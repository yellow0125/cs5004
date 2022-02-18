/**
 * @ Program: homework03
 * @ Date: 2021/10/14
 * @ Description: This class represents a chess piece named Knight.
 * A knight can move only in an L pattern: two cells horizontally
 * and one vertically or vice versa. It can kill any opponent’s piece if it can move to its place.
 */
public class Knight extends AbstractChess {
  /**
   * Initialize the fields for the Knight chess piece.
   * The input is given the position of the Knight.
   *
   * @param initialRow    An integer representing the initial row of the chess piece Knight.
   * @param initialColumn An integer representing the initial column of the chess piece Knight.
   * @param initialColor  A String representing the initial color of the chess piece Knight.
   * @throws IllegalArgumentException when input initial row and column are bigger
   *                                  than seven or smaller than zero
   */
  public Knight(int initialRow, int initialColumn, Color initialColor) {
    super(initialRow, initialColumn, initialColor);
  }

  @Override
  public boolean canMove(int x, int y) {
    super.canMove(x, y);
    return Move.pattern(this, x, y);
  }
}
