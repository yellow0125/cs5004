import cs5004.tictactoe.Player;
import cs5004.tictactoe.TicTacToe;
import cs5004.tictactoe.TicTacToeModel;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Test cases for the ticTacToe model. Verifying that game state is properly managed, and
 * all game actions are properly validated.
 */

public class TicTacToeModelTest {
  private TicTacToe test = new TicTacToeModel();
  private TicTacToe round = new TicTacToeModel();

  /**
   * Before test, play the game round to game over.
   */
  @Before
  public void setUp() {
    round.move(1, 1);
    round.move(0, 0);
    round.move(0, 2);
    round.move(2, 0);
    round.move(1, 0);
    round.move(1, 2);
    round.move(2, 1);
    round.move(0, 1);
    round.move(2, 2);
  }

  /**
   * Test for toString.
   */
  @Test
  public void testToString() {
    round.getBoard();
    assertEquals(" O | O | X\n"
            + "-----------\n"
            + " X | X | O\n"
            + "-----------\n"
            + " O | X | X", round.toString());
  }

  /**
   * Test for move method.
   */
  @Test
  public void move() {
    test.move(0, 0);
    assertEquals(Player.O, test.getTurn());
  }

  /**
   * Test for horizontal win the game.
   */
  @Test
  public void testHorizontalWin() {
    test.move(0, 0); // X takes upper left
    assertFalse(test.isGameOver());
    test.move(1, 0); // O takes middle left
    test.move(0, 1); // X takes upper middle
    assertNull(test.getWinner());
    test.move(2, 0); // O takes lower left
    test.move(0, 2); // X takes upper right
    assertTrue(test.isGameOver());
    assertEquals(Player.X, test.getWinner());
    assertEquals(" X | X | X\n"
            + "-----------\n"
            + " O |   |  \n"
            + "-----------\n"
            + " O |   |  ", test.toString());
  }

  /**
   * Test for diagonal win the game.
   */
  @Test
  public void testDiagonalWin() {
    diagonalWinHelper();
    assertTrue(test.isGameOver());
    assertEquals(Player.O, test.getWinner());
    assertEquals(" X | X | O\n"
            + "-----------\n"
            + " X | O |  \n"
            + "-----------\n"
            + " O |   |  ", test.toString());
  }

  /**
   * set up situation where game is over, O wins on the diagonal, board is not full.
   */
  private void diagonalWinHelper() {
    test.move(0, 0); // X takes upper left
    assertFalse(test.isGameOver());
    test.move(2, 0); // O takes lower left
    test.move(1, 0); // X takes middle left
    assertNull(test.getWinner());
    test.move(1, 1); // O takes center
    test.move(0, 1); // X takes upper middle
    test.move(0, 2); // O takes upper right
  }

  /**
   * Test for invalid move. such as position out of bounds, position occupied,
   * and the game is over already.
   */
  @Test
  public void testInvalidMove() {
    test.move(0, 0);
    assertEquals(Player.O, test.getTurn());
    assertEquals(Player.X, test.getMarkAt(0, 0));
    try {
      test.move(0, 0);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Position occupied", iae.getMessage());
      //assertTrue(iae.getMessage().length() > 0);
    }
    try {
      test.move(-1, 0);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Row is invalid.", iae.getMessage());
      //assertTrue(iae.getMessage().length() > 0);
    }
    try {
      test.move(0, -1);
      fail("Invalid move should have thrown exception");
    } catch (IllegalArgumentException iae) {
      assertEquals("Column is invalid.", iae.getMessage());
      //assertTrue(iae.getMessage().length() > 0);
    }
    try {
      round.move(0, 0);
      fail("Invalid move should have thrown exception");
    } catch (IllegalStateException iae) {
      assertEquals("Game is over", iae.getMessage());
    }
  }

  /**
   * Test for illegal state exception. when the game is already over.
   */
  @Test(expected = IllegalStateException.class)
  public void testMoveAttemptAfterGameOver() {
    diagonalWinHelper();
    test.move(2, 2); // 2,2 is an empty position
  }

  /**
   * Test for the game is dogfall.
   */
  @Test
  public void testCatsGame() {
    test.move(0, 0);
    assertEquals(Player.O, test.getTurn());
    test.move(1, 1);
    assertEquals(Player.X, test.getTurn());
    test.move(0, 2);
    test.move(0, 1);
    test.move(2, 1);
    test.move(1, 0);
    test.move(1, 2);
    test.move(2, 2);
    test.move(2, 0);
    assertTrue(test.isGameOver());
    assertNull(test.getWinner());
    assertEquals(" X | O | X\n"
            + "-----------\n"
            + " O | O | X\n"
            + "-----------\n"
            + " X | X | O", test.toString());
  }

  /**
   * Test for illegal row of position out of bounds.
   */

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetMarkAtRow() {
    test.getMarkAt(-12, 0);
  }

  /**
   * Test for illegal column of position out of bounds.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGetMarkAtCol() {
    test.getMarkAt(0, -30);
  }

  /**
   * Test for get the board player during the game.
   */

  @Test
  public void getBoard() {
    diagonalWinHelper();
    Player[][] bd = test.getBoard();
    assertEquals(Player.X, bd[0][0]);
    assertEquals(Player.O, bd[1][1]);
    assertEquals(Player.X, bd[0][1]);

    // attempt to cheat by mutating board returned by getBoard()
    // check correct preconditions
    assertEquals(Player.O, bd[2][0]);
    assertEquals(Player.O, test.getMarkAt(2, 0));
    bd[2][0] = Player.X;  // mutate

    // check correct post conditions
    assertEquals(Player.O, test.getMarkAt(2, 0));
    Player[][] bd2 = test.getBoard();
    assertEquals(Player.O, bd2[2][0]);
  }

  /**
   * Test for the board if full and there is a winner.
   */
  @Test
  public void testFullAndWinner() {
    // TODO: test case where board is full AND there is a winner
    test.move(0, 0);
    test.move(0, 1);
    test.move(2, 0);
    test.move(1, 0);
    test.move(1, 1);
    test.move(0, 2);
    test.move(2, 1);
    test.move(1, 2);
    test.move(2, 2);
    assertEquals(Player.X, test.getWinner());
    assertTrue(test.isGameOver());
  }
}