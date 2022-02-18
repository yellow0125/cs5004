/**
 * @ Program: homework03
 * @ Date: 2021/10/14
 * @ Description: AbstractChess is an abstract class for all chess piece of  chess game.
 * They are Bishop, Knight, Queen, Rook, Pawn
 * * We do the conversion in those methods.
 */
public abstract class AbstractChess implements ChessPiece {
  protected int row;
  protected int column;
  protected Color color;

  /**
   * Initialize the fields for the AbstractChess piece.
   * The input is given the position of the chess piece.
   *
   * @param initialRow    An integer representing the initial row of the chess piece
   * @param initialColumn An integer representing the initial column of the chess piece
   * @param initialColor  A String representing the initial color of the chess piece
   * @throws IllegalArgumentException when input initial row and column are bigger
   *                                  than seven or smaller than zero
   */

  public AbstractChess(int initialRow, int initialColumn, Color initialColor)
          throws IllegalArgumentException {
    if (!legalArgument(initialRow, initialColumn)) {
      throw new IllegalArgumentException("The argument is illegal");
    }
    this.row = initialRow;
    this.column = initialColumn;
    this.color = initialColor;
  }

  /**
   * A method determine whether the given position of cell is on the chess board or not.
   *
   * @return a boolean to determine tthe given position of cell is on the chess board or not.
   */

  private boolean legalArgument(int row, int col) {
    return row >= 0 && row <= 7 && col >= 0 && col <= 7;
  }

  @Override
  public int getRow() {
    return this.row;
  }

  @Override
  public int getColumn() {
    return this.column;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public boolean canMove(int row, int col) throws IllegalArgumentException {
    if (!legalArgument(row, col)) {
      throw new IllegalArgumentException("The argument is illegal");
    }
    return true;    //have to move  not doing the move. return false.
  }

  @Override
  public boolean canKill(ChessPiece other) {

    return this.canMove(other.getRow(), other.getColumn()) && this.getColor() != other.getColor();
  }

}
