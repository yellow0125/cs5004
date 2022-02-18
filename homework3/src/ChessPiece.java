/**
 * This is an interface for Chess game. It will be  implemented by all chess piece
 */

public interface ChessPiece {
  /**
   * Get the chess piece the row of the current position on the board.
   *
   * @return the current row of the chess piece.
   */
  int getRow();

  /**
   * Get the chess piece the column of the current position on the board.
   *
   * @return the current column of the chess piece.
   */

  int getColumn();

  /**
   * Get the color of the chess piece.
   * enum color
   *
   * @return the color of the chess piece.
   */

  Color getColor();

  /**
   * The method determine if it can move to a given cell.
   *
   * @param row the move step in row of the chess piece.
   * @param col the move step in column of the chess piece.
   * @return a boolean to judge if the chess piece can or cannot move to a given cell
   */
  boolean canMove(int row, int col);

  /**
   * The method determine if the chess piece can kill a provided piece starting
   * from where it currently is.
   *
   * @return a boolean to determine the chess piece can kill a provided piece
   */
  boolean canKill(ChessPiece other);
}
