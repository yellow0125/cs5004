import cs5004.tictactoe.TicTacToe;
import cs5004.tictactoe.TicTacToeConsoleController;
import cs5004.tictactoe.TicTacToeController;
import cs5004.tictactoe.TicTacToeModel;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test cases for the Tic tac toe controller, using mocks for readable and appendable.
 */
public class TicTacToeControllerTest {

  // ADDITIONAL TEST CASES TO IMPLEMENT:
  // Play game to completion, where there is a winner
  // Input where the q comes instead of an integer for the row
  // Input where the q comes instead of an integer for the column
  // Input where non-integer garbage comes instead of an integer for the row
  // Input where non-integer garbage comes instead of an integer for the column
  // Input where the move is integers, but outside the bounds of the board
  // Input where the move is integers, but invalid because the cell is occupied
  // Multiple invalid moves in a row of various kinds
  // Input including valid moves interspersed with invalid moves, game is played to completion
  // What happens when the input ends "abruptly" -- no more input, but not quit, and game not over
  // THIS IS NOT AN EXHAUSTIVE LIST

  /**
   * Test for single valid move.
   */

  @Test
  public void testSingleValidMove() throws IOException {
    TicTacToe m = new TicTacToeModel();
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(new StringReader("2 2 q"), gameLog);
    c.playGame(m);
    assertEquals("   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Invalid move, Enter a move for X:\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   | X |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "Invalid move, Enter a move for O:\n"
            + "Game quit! Ending game state:\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   | X |  \n"
            + "-----------\n"
            + "   |   |  \n", gameLog.toString());
  }

  /**
   * Test for bogus input as row.
   */
  @Test
  public void testBogusInputAsRow() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("!#$ 2 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    // split the output into an array of lines
    String[] lines = gameLog.toString().split("\n");
    // check that it's the correct number of lines
    assertEquals(13, lines.length);
    // check that the last 6 lines are correct
    String lastMsg = String.join("\n",
            Arrays.copyOfRange(lines, lines.length - 6, lines.length));
    assertEquals("Game quit! Ending game state:\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  ", lastMsg);
    // note no trailing \n here, because of the earlier split
  }

  /**
   * Test for bogus input as col.
   */
  @Test
  public void testBogusInputAsCol() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 !#$ q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(13, lines.length);
    String lastMsg = String.join("\n",
            Arrays.copyOfRange(lines, lines.length - 6, lines.length));
    assertEquals("Game quit! Ending game state:\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   |   |  ", lastMsg);
  }

  /**
   * Test for the tie game.
   */
  @Test
  public void testTieGame() {
    TicTacToe m = new TicTacToeModel();
    // note the entire sequence of user inputs for the entire game is in this one string:
    StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(60, lines.length);
    assertEquals("Game is over! Tie game.", lines[lines.length - 1]);
  }

  /**
   * Test for failing appendable.
   */
  @Test(expected = IllegalStateException.class)
  public void testFailingAppendable() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 1 1 3 3 1 2 1 3 2 3 2 1 3 1 3 2");
    Appendable gameLog = new FailingAppendable();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
  }

  // Play game to completion, where there is a winner

  /**
   * Test for game to completion, where there is a winner X.
   */
  @Test
  public void testWinnerX() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 1 1 1 3 2 1 3 1");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(36, lines.length);
    assertEquals("Game is over! X wins.", lines[lines.length - 1]);
    String lastMsg = String.join("\n",
            Arrays.copyOfRange(lines, lines.length - 6, lines.length));
    assertEquals(" O |   | X\n"
            + "-----------\n"
            + " O | X |  \n"
            + "-----------\n"
            + " X |   |  \n"
            + "Game is over! X wins.", lastMsg);
  }

  /**
   * Test for game to completion, where there is a winner O.
   */
  @Test
  public void testWinnerO() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 1 1 1 3 3 1 3 3 2 1");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(42, lines.length);
    assertEquals("Game is over! O wins.", lines[lines.length - 1]);
    String lastMsg = String.join("\n",
            Arrays.copyOfRange(lines, lines.length - 6, lines.length));
    assertEquals(" O |   | X\n"
            + "-----------\n"
            + " O | X |  \n"
            + "-----------\n"
            + " O |   | X\n"
            + "Game is over! O wins.", lastMsg);
  }

  // Input where the q comes instead of an integer for the column

  /**
   * Test for input where q comes instead of an integer for a row.
   */
  @Test
  public void testRowQ() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 q 3");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(18, lines.length);
    String lastMsg = String.join("\n",
            Arrays.copyOfRange(lines, lines.length - 6, lines.length));
    assertEquals("Game quit! Ending game state:\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   | X |  \n"
            + "-----------\n"
            + "   |   |  ", lastMsg);

  }

  /**
   * Test for input  where q comes instead of an integer for a column.
   */
  @Test
  public void testColumnQ() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 3 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(18, lines.length);
    String lastMsg = String.join("\n",
            Arrays.copyOfRange(lines, lines.length - 6, lines.length));
    assertEquals("Game quit! Ending game state:\n"
            + "   |   |  \n"
            + "-----------\n"
            + "   | X |  \n"
            + "-----------\n"
            + "   |   |  ", lastMsg);

  }

  /**
   * Test for Input where non-integer garbage comes instead of an integer for the row.
   */
  @Test
  public void testInputGarbageRow() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 garbage 1 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(19, lines.length);
    assertEquals("Row is invalid.", lines[lines.length - 7]);
  }

  /**
   * Test for Input where non-integer garbage comes instead of an integer for the column.
   */
  @Test
  public void testInputGarbageCol() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 3 garbage q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(19, lines.length);
    assertEquals("Column is invalid.", lines[lines.length - 7]);
  }

  /**
   * Test for Input where the move is integers, but outside the bounds of the board.
   * For row>3
   */
  @Test
  public void testOutOfBoundRow1() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 6 1 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(25, lines.length);
    assertEquals("Invalid move, Enter a move for O:", lines[lines.length - 7]);
  }

  /**
   * Test for Input where the move is integers, but outside the bounds of the board.
   * For row=0
   */
  @Test
  public void testOutOfBoundRow2() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 0 1 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(25, lines.length);
    assertEquals("Invalid move, Enter a move for O:", lines[lines.length - 7]);
  }

  /**
   * Test for Input where the move is integers, but outside the bounds of the board.
   * For row <0
   */
  @Test
  public void testOutOfBoundRow3() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 -5 1 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(25, lines.length);
    assertEquals("Invalid move, Enter a move for O:", lines[lines.length - 7]);
  }

  /**
   * Test for Input where the move is integers, but outside the bounds of the board.
   * For col>3
   */
  @Test
  public void testOutOfBoundCol1() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 9 6 1 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(26, lines.length);
    assertEquals("Invalid move, Enter a move for X:", lines[lines.length - 7]);
  }

  /**
   * Test for Input where the move is integers, but outside the bounds of the board.
   * For col=0
   */
  @Test
  public void testOutOfBoundCol2() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 0 2 1 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(25, lines.length);
    assertEquals("Invalid move, Enter a move for O:", lines[lines.length - 7]);
  }

  /**
   * Test for Input where the move is integers, but outside the bounds of the board.
   * For col <0
   */
  @Test
  public void testOutOfBoundCol3() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 -10 2 1 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(25, lines.length);
    assertEquals("Invalid move, Enter a move for O:", lines[lines.length - 7]);
  }

  /**
   * Test for Input where the move is integers, but invalid because the cell is occupied.
   * For player X.
   */
  @Test
  public void testOccupiedX() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 3 3 2 2 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(31, lines.length);
    assertEquals("Invalid move, Enter a move for X:", lines[lines.length - 7]);
  }

  /**
   * Test for Input where the move is integers, but invalid because the cell is occupied.
   * For player Y.
   */
  @Test
  public void testOccupiedO() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 3 3 2 1 2 1 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");
    assertEquals(37, lines.length);
    assertEquals("Invalid move, Enter a move for O:", lines[lines.length - 7]);
  }

  /**
   * Test for Multiple invalid moves in a row of various kinds.
   */
  @Test
  public void testMultipleInvalidMoves() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 9 0 0 99 -7 -3 1 1 q");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");

    assertEquals(45, lines.length);
    assertEquals("Invalid move, Enter a move for O:", lines[lines.length - 13]);

    String lastMsg = String.join("\n",
            Arrays.copyOfRange(lines, lines.length - 6, lines.length));
    assertEquals("Game quit! Ending game state:\n"
            + " O |   |  \n"
            + "-----------\n"
            + "   | X |  \n"
            + "-----------\n"
            + "   |   |  ", lastMsg);
  }

  /**
   * Test for Input including valid moves interspersed with invalid moves,
   * game is played to completion.
   */
  @Test
  public void testMoves() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 9 0 0 99 -7 -3 1 1 88 12 2 1 "
            + "0 0 2 3 66 77 32 -32 3 1 -99 -88 1 3 1 2 3 3");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
    String[] lines = gameLog.toString().split("\n");

    assertEquals(110, lines.length);
    assertEquals("Invalid move, Enter a move for X:", lines[lines.length - 13]);
    assertEquals("Invalid move, Enter a move for O:", lines[lines.length - 26]);

    String lastMsg = String.join("\n",
            Arrays.copyOfRange(lines, lines.length - 6, lines.length));
    assertEquals(" O | X | O\n"
            + "-----------\n"
            + " X | X | O\n"
            + "-----------\n"
            + " X |   | O\n"
            + "Game is over! O wins.", lastMsg);
  }

  // What happens when the input ends "abruptly" --
  // no more input, but not quit, and game not over
  //Error when trying to output game state.

  /**
   * Test for the input ends "abruptly".
   */
  @Test
  public void testHangingEnd() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 1 1");
    StringBuilder gameLog = new StringBuilder();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    Thread thread = new Thread() {
      @Override
      public void run() {
        c.playGame(m);
      }
    };
    thread.start();
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      //do nothing
    }
    assertTrue(thread.isAlive());
    //System.out.println(gameLog.toString());
    String[] lines = gameLog.toString().split("\n");
    assertEquals("Row is invalid.", lines[lines.length - 1]);
  }

  /**
   * Test for the input ends "abruptly".
   */
  @Test(expected = IllegalStateException.class)
  public void testInputAbruptly() {
    TicTacToe m = new TicTacToeModel();
    StringReader input = new StringReader("2 2 1 1 3 3 2 1");
    Appendable gameLog = new FailingAppendable();
    TicTacToeController c = new TicTacToeConsoleController(input, gameLog);
    c.playGame(m);
  }
}
