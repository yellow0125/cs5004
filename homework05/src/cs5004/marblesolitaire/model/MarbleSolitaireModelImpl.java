package cs5004.marblesolitaire.model;

/**
 * @ Program: homework05
 * @ Date: 2021/11/16
 * @ Description: This class represents a  model of a game called “Marble Solitaire”.
 */
public class MarbleSolitaireModelImpl implements MarbleSolitaireModel {
  protected int thickness;
  protected int sRow;
  protected int sCol;
  protected ChessBoardSlot[][] chessBoard;
  protected int size;
  protected int x;
  protected int y;

  /**
   * Constructor with no parameters,, initialize the game board as shown above
   * (arm thickness 3 with the empty slot at the center).
   */
  public MarbleSolitaireModelImpl() {
    this.thickness = 3;
    this.size = 3 * thickness - 2;
    this.x = thickness - 1;
    this.y = 2 * thickness - 1;

    this.sRow = 3;
    this.sCol = 3;

    this.chessBoard = new ChessBoardSlot[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        this.chessBoard[i][j] = ChessBoardSlot.INVALID;
      }
    }
    for (int i = x; i < y; i++) {
      for (int j = 0; j < size; j++) {
        chessBoard[i][j] = ChessBoardSlot.CHESS;
      }
    }

    for (int j = x; j < y; j++) {
      for (int i = 0; i < size; i++) {
        chessBoard[i][j] = ChessBoardSlot.CHESS;
      }
    }
    chessBoard[sRow][sCol] = ChessBoardSlot.EMPTY;
  }

  /**
   * Constructor with two parameters: sRow and sCol.
   * Initialize the game board so that the arm thickness is 3
   * and the empty slot is at the position (sRow, sCol).
   *
   * @param sRow the row of the given position empty slot
   * @param sCol the column of  the given position of  empty slot.
   * @throws IllegalArgumentException if the given position of empty slot is invalid.
   */
  public MarbleSolitaireModelImpl(int sRow, int sCol) throws IllegalArgumentException {
    this.thickness = 3;
    this.size = 3 * thickness - 2;
    this.x = thickness - 1;
    this.y = 2 * thickness - 1;
    if (sRow < 0 || sCol < 0 || sRow > this.size - 1 || sCol > this.size - 1
            || ((sRow < this.x || sRow >= this.y) && (sCol < this.x || sCol >= this.y))) {
      throw new IllegalArgumentException("Invalid empty cell position(" + sRow + "," + sCol + ")");
    }
    this.sRow = sRow;
    this.sCol = sCol;

    this.chessBoard = new ChessBoardSlot[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        chessBoard[i][j] = ChessBoardSlot.INVALID;
      }
    }
    for (int i = x; i < y; i++) {
      for (int j = 0; j < size; j++) {
        chessBoard[i][j] = ChessBoardSlot.CHESS;
      }
    }

    for (int j = x; j < y; j++) {
      for (int i = 0; i < size; i++) {
        chessBoard[i][j] = ChessBoardSlot.CHESS;
      }
    }
    chessBoard[sRow][sCol] = ChessBoardSlot.EMPTY;
  }

  /**
   * Constructor with a parameter arm thickness.
   * Initialize a game board with the empty slot at the center.
   *
   * @param thickness the arm thickness of a game board.
   * @throws IllegalArgumentException if the arm thickness is not a positive odd number.
   */
  public MarbleSolitaireModelImpl(int thickness) throws IllegalArgumentException {
    if (thickness < 3 || thickness % 2 == 0) {
      throw new IllegalArgumentException("The arm thickness is invalid.");
    }
    this.thickness = thickness;
    this.size = 3 * thickness - 2;
    this.x = thickness - 1;
    this.y = 2 * thickness - 1;
    this.sRow = (size - 1) / 2;  //center
    this.sCol = (size - 1) / 2;

    this.chessBoard = new ChessBoardSlot[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        chessBoard[i][j] = ChessBoardSlot.INVALID;
      }
    }
    for (int i = x; i < y; i++) {
      for (int j = 0; j < size; j++) {
        chessBoard[i][j] = ChessBoardSlot.CHESS;
      }
    }

    for (int j = x; j < y; j++) {
      for (int i = 0; i < size; i++) {
        chessBoard[i][j] = ChessBoardSlot.CHESS;
      }
    }
    chessBoard[sRow][sCol] = ChessBoardSlot.EMPTY;
  }

  /**
   * Constructor with the arm thickness, row and column of the empty slot.
   *
   * @param thickness the arm thickness of a game board.
   * @param sRow      the row of the given position empty slot
   * @param sCol      the column of  the given position of  empty slot.
   * @throws IllegalArgumentException if the arm thickness is not a positive
   *                                  odd number, or the empty cell position is invalid.
   */
  public MarbleSolitaireModelImpl(int thickness, int sRow, int sCol)
          throws IllegalArgumentException {
    if (thickness < 3 || thickness % 2 == 0) {
      throw new IllegalArgumentException("The arm thickness is invalid.");
    }
    this.thickness = thickness;
    this.size = 3 * thickness - 2;
    this.x = thickness - 1;
    this.y = 2 * thickness - 1;
    if (sRow < 0 || sCol < 0 || sRow > this.size - 1 || sCol > this.size - 1
            || ((sRow < this.x || sRow >= this.y) && (sCol < this.x || sCol >= this.y))) {
      throw new IllegalArgumentException("Invalid empty cell position(" + sRow + "," + sCol + ")");
    }

    this.sRow = sRow;
    this.sCol = sCol;

    this.chessBoard = new ChessBoardSlot[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        chessBoard[i][j] = ChessBoardSlot.INVALID;
      }
    }
    for (int i = x; i < y; i++) {
      for (int j = 0; j < size; j++) {
        chessBoard[i][j] = ChessBoardSlot.CHESS;
      }
    }

    for (int j = x; j < y; j++) {
      for (int i = 0; i < size; i++) {
        chessBoard[i][j] = ChessBoardSlot.CHESS;
      }
    }
    chessBoard[sRow][sCol] = ChessBoardSlot.EMPTY;

  }

  /**
   * Move a single marble from a given position to another given position.
   * A move is valid only if the from and to positions are valid. Specific
   * implementations may place additional constraints on the validity of a move.
   * When can't move throw illegal argument exception. such as:
   * if the position from or to is not exits(<0 or >size-1 or the type of slot is invalid"")
   * if from !=to  or between from and to position, the slot is bigger or smaller than 2
   * the slot type of from position is not chess or the slot type of to position if not empty
   * and the chess can't move with no jumped over chess piece.
   *
   * @param fromRow the row number of the position to be moved from
   *                (starts at 0)
   * @param fromCol the column number of the position to be moved from
   *                (starts at 0)
   * @param toRow   the row number of the position to be moved to
   *                (starts at 0)
   * @param toCol   the column number of the position to be moved to
   *                (starts at 0)
   * @throws IllegalArgumentException if the move is not possible,
   */
  @Override
  public void move(int fromRow, int fromCol, int toRow, int toCol) throws IllegalArgumentException {
    //from and to position out of the chess board.
    if (fromRow < 0 || fromCol < 0 || toRow < 0 || toCol < 0
            || fromRow > this.size - 1 || fromCol > this.size - 1
            || toRow > this.size - 1 || toCol > this.size - 1
            || ((fromRow < this.x || fromRow >= this.y) && (fromCol < this.x || fromCol >= this.y))
            || ((toRow < this.x || toRow >= this.y) && (toCol < this.x || toCol >= this.y))) {
      throw new IllegalArgumentException("Invalid Position");
    }
    //from !=to.  row or column !=2
    if (fromRow != toRow && fromCol != toCol
            || (fromRow == toRow && Math.abs(toCol - fromCol) != 2)
            || (fromCol == toCol && Math.abs(toRow - fromRow) != 2)) {
      throw new IllegalArgumentException("Invalid 'To' Position");
    }
    //from position has no chess, to position is not empty.
    if (this.chessBoard[fromRow][fromCol] != ChessBoardSlot.CHESS
            || this.chessBoard[toRow][toCol] != ChessBoardSlot.EMPTY) {
      throw new IllegalArgumentException("Invalid Position with Wrong Chess Type");
    }
    //there is not a chess between the 'from' to 'to'
    if (this.chessBoard[(fromRow + toRow) / 2][(fromCol + toCol) / 2] != ChessBoardSlot.CHESS) {
      throw new IllegalArgumentException("Can't move with no jumped over chess piece");
    }
    this.chessBoard[fromRow][fromCol] = ChessBoardSlot.EMPTY;
    this.chessBoard[toRow][toCol] = ChessBoardSlot.CHESS;
    this.chessBoard[(fromRow + toRow) / 2][(fromCol + toCol) / 2] = ChessBoardSlot.EMPTY;
  }

  @Override
  public boolean isGameOver() {
    if (getScore(==1))
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (chessBoard[i][j] == ChessBoardSlot.CHESS) {
          if (canMove(i, j)) {
            return false;
          }
        }
      }
    }
    return true;
  }

  @Override
  public String getGameState() {

    String s = "";
    int i = 0;
    while (i < this.size) {

      if (i < this.x) {
        //up left
        for (int j = 0; j < this.y; j++) {
          if (j == this.y - 1) {
            s = s + this.chessBoard[i][j];
          } else {
            s = s + this.chessBoard[i][j] + " ";
          }
        }
        i++;
        s = s + "\n";
      }

      if (i >= this.x && i < this.y) {
        //middle
        for (int j = 0; j < this.size; j++) {
          if (j == this.size - 1) {
            s = s + this.chessBoard[i][j];
          } else {
            s = s + this.chessBoard[i][j] + " ";
          }
        }
        i++;
        s = s + "\n";
      }

      if (i >= this.y) {
        //down right
        if (i == size - 1) {
          //last row
          for (int j = 0; j < this.y; j++) {
            if (j == this.y - 1) {
              s = s + this.chessBoard[i][j];
            } else {
              s = s + this.chessBoard[i][j] + " ";
            }
          }
          return s;
        }
        for (int j = 0; j < this.y; j++) {
          if (j == this.y - 1) {
            s = s + this.chessBoard[i][j];
          } else {
            s = s + this.chessBoard[i][j] + " ";
          }
        }
        i++;
        s = s + "\n";
      }
    }
    return s;
  }

  @Override
  public int getScore() {
    int count = 0;
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if (this.chessBoard[i][j] == ChessBoardSlot.CHESS) {
          count++;
        }
      }
    }//this.count;
    return count;
  }

  @Override
  public boolean canMove(int row, int col) throws IllegalArgumentException {
    if (row < 0 || col < 0 || row > this.size - 1 || col > this.size - 1
            || ((row < this.x || row >= this.y) && (col < this.x || col >= this.y))) {
      throw new IllegalArgumentException("Invalid Position");
    }
    //from position has no chess,
    if (this.chessBoard[row][col] == ChessBoardSlot.CHESS) {
      if (row > 1) {
        if (this.chessBoard[row - 1][col] == ChessBoardSlot.CHESS
                && this.chessBoard[row - 2][col] == ChessBoardSlot.EMPTY) {
          return true;
        }
      }
      if (row < this.size - 2) {
        if (this.chessBoard[row + 1][col] == ChessBoardSlot.CHESS
                && this.chessBoard[row + 2][col] == ChessBoardSlot.EMPTY) {
          return true;
        }
      }
      if (col > 1) {
        if (this.chessBoard[row][col - 1] == ChessBoardSlot.CHESS
                && this.chessBoard[row][col - 2] == ChessBoardSlot.EMPTY) {
          return true;
        }
      }
      if (col < this.size - 2) {
        if (this.chessBoard[row][col + 1] == ChessBoardSlot.CHESS
                && this.chessBoard[row][col + 2] == ChessBoardSlot.EMPTY) {
          return true;
        }
      }
    }
    return false;
  }
}
