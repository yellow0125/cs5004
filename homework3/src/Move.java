/**
 * @ Program: homework03
 * @ Date: 2021/10/21
 * @ Description: This class represents a move mode of a chess piece.
 */
public class Move {
  /**
   * A method determines whether a chess piece can move vertically with a given position.
   *
   * @param chess a chess piece object
   * @param x     the row of the given position
   * @param y     the column of the given position
   * @return a boolean to determine the chess piece can move vertically or not.
   */
  public static boolean vertically(AbstractChess chess, int x, int y) {
    return chess.getRow() != x && chess.column == y;
  }

  /**
   * A method determines whether a chess piece can move horizontally with a given position.
   *
   * @param chess a chess piece object
   * @param x     the row of the given position
   * @param y     the column of the given position
   * @return a boolean to determine the chess piece can move horizontally or not
   */
  public static boolean horizontally(AbstractChess chess, int x, int y) {
    return chess.getRow() == x && chess.column != y;
  }

  /**
   * A method determines whether a chess piece can move diagonally with a given position.
   *
   * @param chess a chess piece object
   * @param x     the row of the given position
   * @param y     the column of the given position
   * @return a boolean to determine the chess piece can move diagonally or not
   */
  public static boolean diagonally(AbstractChess chess, int x, int y) {
    return Math.abs(chess.getRow() - x) == Math.abs(chess.column - y);
  }

  /**
   * A method determines whether a pawn can move ahead with a given position.
   *
   * @param chess a chess piece object
   * @param x     the row of the given position
   * @param y     the column of the given position
   * @return a boolean to determine the chess piece can move diagonally or not
   */
  public static boolean ahead(AbstractChess chess, int x, int y) {
    if (chess.getColor() == Color.BLACK) {
      return x - chess.getRow() == -1 && chess.getColumn() == y;
    }
    if (chess.getColor() == Color.WHITE) {
      return x - chess.getRow() == 1 && chess.getColumn() == y;
    }
    return true;
  }

  /**
   * A method determines whether a knight piece can move L-pattern with a given position.
   *
   * @param chess a chess piece object
   * @param x     the row of the given position
   * @param y     the column of the given position
   * @return a boolean to determine the chess piece can move diagonally or not
   */
  public static boolean pattern(ChessPiece chess, int x, int y) {
    return Math.abs(chess.getRow() - x) == 2 && Math.abs(chess.getColumn() - y) == 1
            || Math.abs(chess.getRow() - x) == 1 && Math.abs(chess.getColumn() - y) == 2;
  }
}
