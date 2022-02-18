/**
 * @ Program: homework03
 * @ Date: 2021/10/14
 * @ Description: This class represents a chess piece named Pawn.
 * It can move only “ahead,” not backwards towards where its color started.
 * It can move only one place forward in its own column.
 */
public class Pawn extends AbstractChess {
  /**
   * IInitialize the fields for the Queen chess piece. The input is given the position of the Pawn.
   * The input is given the position of the chess piece.
   *
   * @param initialRow    An integer representing the initial row of the chess piece Pawn.
   * @param initialColumn An integer representing the initial column of the chess piece Pawn.
   * @param initialColor  A String representing the initial color of the chess piece Pawn.
   * @throws IllegalArgumentException when input initial row and column are bigger
   *                                  than seven or smaller than zero
   */
  public Pawn(int initialRow, int initialColumn, Color initialColor) {
    super(initialRow, initialColumn, initialColor);
  }

  @Override
  public boolean canMove(int x, int y) {
    super.canMove(x, y);
    return Move.ahead(this, x, y);
  }

  @Override
  public boolean canKill(ChessPiece other) {
    super.canKill(other);
    int x = other.getRow() - this.row;   //this.getRow ()
    int y = other.getColumn() - this.column;

    if (this.getColor() == Color.BLACK) {
      if (x > 0) {
        System.out.println("illegal move");
        return false;
      }
      if (this.getColor() == other.getColor()) {
        System.out.println("SAME COLOR");
        return false;
      }
      if (x == -1 && Math.abs(y) == 1) {
        System.out.println("TRUE");
        return true;
      }
    }

    if (this.getColor() == Color.WHITE) {
      if (x < 0) {
        System.out.println("illegal move");
        return false;
      }
      if (this.getColor() == other.getColor()) {
        System.out.println("SAME COLOR");
        return false;
      }
      if (x == 1 && Math.abs(y) == 1) {
        System.out.println("TRUE");
        return true;
      }
    }
    System.out.println("cant move, it's too far");
    return false;
  }
}
