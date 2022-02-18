package cs5004.tictactoe;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * This class represents a  model of a game TicTacToe.
 */

public class TicTacToeModel implements TicTacToe {
  private Player[][] board;
  Player current;
  Player winner;

  /**
   * Constructor takes no arguments. Initialize a new TicTacToeModel.
   */
  public TicTacToeModel() {
    this.board = new Player[3][3];
    this.current = Player.X;
    this.winner = null;
  }

  @Override
  public String toString() {
    // Using Java stream API to save code:
    return Arrays.stream(this.getBoard())
            .map(row -> " " + Arrays.stream(row).map(p -> p == null ? " " : p.toString())
                    .collect(Collectors.joining(" | ")))
            .collect(Collectors.joining("\n-----------\n"));
    // This is the equivalent code as above, but using iteration, and still using
    // the helpful built-in String.join method.
  }

  @Override
  public void move(int r, int c) throws IllegalArgumentException, IllegalStateException {
    if (this.isGameOver()) {
      throw new IllegalStateException("Game is over");
    }
    testOfBounds(r, c);
    if (this.board[r][c] != null) {
      throw new IllegalArgumentException("Position occupied");
    } else {
      this.board[r][c] = getTurn();

      if (this.getTurn() == Player.X) {
        this.current = Player.O;
      } else {
        this.current = Player.X;
      }
    }
  }

  @Override
  public Player getTurn() {
    return this.current;
  }

  @Override
  public boolean isGameOver() {
    int spaceFilled = Arrays.stream(this.getBoard()).map(row -> Arrays.stream(row)
                    .map(p -> p == null ? 0 : 1).reduce(0, (a, b) -> a + b))
            .reduce(0, (a, b) -> a + b);
    if (spaceFilled >= 9) {
      return true;
    }
    return this.getWinner() != null;
  }

  @Override
  public Player getWinner() {
    boolean flag = false;
    for (Player name : Player.values()) {
      if (this.board[0][0] == name && this.board[0][1] == name && this.board[0][2] == name) {
        flag = true;
      } else if (this.board[1][0] == name && this.board[1][1] == name && this.board[1][2] == name) {
        flag = true;
      } else if (this.board[2][0] == name && this.board[2][1] == name && this.board[2][2] == name) {
        flag = true;
      } else if (this.board[0][0] == name && this.board[1][0] == name && this.board[2][0] == name) {
        flag = true;
      } else if (this.board[0][1] == name && this.board[1][1] == name && this.board[2][1] == name) {
        flag = true;
      } else if (this.board[0][2] == name && this.board[1][2] == name && this.board[2][2] == name) {
        flag = true;
      } else if (this.board[0][0] == name && this.board[1][1] == name && this.board[2][2] == name) {
        flag = true;
      } else if (this.board[2][0] == name && this.board[1][1] == name && this.board[0][2] == name) {
        flag = true;
      }
      if (flag) {
        this.winner = name;
        return this.winner;
      }
    }
    return null;
  }

  @Override
  public Player[][] getBoard() {
    return Arrays.stream(this.board).map((Player[] row) -> row.clone())
            .toArray((int length) -> new Player[length][]);
  }

  @Override
  public Player getMarkAt(int r, int c) {
    testOfBounds(r, c);
    Player mark = this.board[r][c];
    return mark;
  }

  /**
   * Method for test the bounds of given row and column.
   */
  private void testOfBounds(int r, int c) {
    if (r < 0 || r > 2) {
      throw new IllegalArgumentException("Row is invalid.");
    } else if (c < 0 || c > 2) {
      throw new IllegalArgumentException("Column is invalid.");
    }
  }
}
