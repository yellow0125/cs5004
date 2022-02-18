import org.junit.Test;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

/**
 * @ Description:Tests for ChessPiece, include Queen, Bishop, Knight, Rook, Pawn.
 */
public class AbstractChessTest {
  int x1 = (int) (Math.random() * 8);
  int y1 = (int) (Math.random() * 8);
  int x2 = (int) (Math.random() * 8);
  int y2 = (int) (Math.random() * 8);
  int x3 = (int) (Math.random() * 8);
  int y3 = (int) (Math.random() * 8);
  int x4 = (int) (Math.random() * 8);
  int y4 = (int) (Math.random() * 8);
  int x5 = (int) (Math.random() * 8);
  int y5 = (int) (Math.random() * 8);
  int negX = (-1) * (int) (Math.random() * 20 + 1);
  int negY = (-1) * (int) (Math.random() * 20 + 1);
  int bigX = (int) (Math.random() * 20 + 8);
  int bigY = (int) (Math.random() * 20 + 8);
  AbstractChess queen = new Queen(x1, y1, Color.BLACK);
  AbstractChess rook = new Rook(x2, y2, Color.WHITE);
  AbstractChess knight = new Knight(x3, y3, Color.BLACK);
  AbstractChess bishop = new Bishop(x4, y4, Color.BLACK);
  AbstractChess pawnW = new Pawn(x5, y5, Color.WHITE);
  AbstractChess pawnB = new Pawn(x5, y5, Color.BLACK);
  AbstractChess queen0 = new Queen(3, 4, Color.BLACK);
  AbstractChess queen00 = new Queen(3, 4, Color.WHITE);
  AbstractChess rook0 = new Rook(4, 3, Color.BLACK);
  AbstractChess rook00 = new Rook(4, 3, Color.WHITE);
  AbstractChess bishop0 = new Bishop(4, 3, Color.BLACK);
  AbstractChess bishop00 = new Bishop(5, 4, Color.WHITE);
  AbstractChess knight0 = new Knight(4, 4, Color.WHITE);
  AbstractChess knight00 = new Knight(1, 0, Color.BLACK);
  AbstractChess pawn0 = new Pawn(2, 3, Color.WHITE);
  AbstractChess pawn00 = new Pawn(2, 5, Color.BLACK);

  /**
   * Test for invalid position that the chess piece cannot be created on the board.
   */

  @Test
  public void testIllegalSet() {
    for (int i = 0; i < 100; i++) {
      try {
        AbstractChess illegal0 = new Queen(negX, negY, Color.BLACK);
        fail("The argument is illegal");
      } catch (IllegalArgumentException e) {
        //System.out.println(e);
      }
      try {
        AbstractChess illegal1 = new Rook(negX, bigY, Color.BLACK);
        fail("The argument is illegal");
      } catch (IllegalArgumentException e) {
        //System.out.println(e);
      }
      try {
        AbstractChess illegal2 = new Bishop(bigX, bigY, Color.WHITE);
        fail("The argument is illegal");
      } catch (IllegalArgumentException e) {
        //System.out.println(e);
      }
      try {
        AbstractChess illegal3 = new Knight(bigX, negY, Color.BLACK);
        fail("The argument is illegal");
      } catch (IllegalArgumentException e) {
        //System.out.println(e);
      }
      try {
        AbstractChess illegal4 = new Pawn((negX - 1), (bigY + 1), Color.WHITE);
        fail("The argument is illegal");
      } catch (IllegalArgumentException e) {
        //System.out.println(e);
      }
    }
  }

  /**
   * Test for get the row of  the position of a chess piece.
   */
  @Test
  public void testGetRow() {
    assertEquals(x1, queen.getRow());
    assertEquals(x2, rook.getRow());
    assertEquals(x3, knight.getRow());
    assertEquals(x4, bishop.getRow());
    assertEquals(x5, pawnW.getRow());
  }

  /**
   * Test for get the column of  the position of a chess piece.
   */

  @Test
  public void testGetColumn() {
    assertEquals(y1, queen.getColumn());
    assertEquals(y2, rook.getColumn());
    assertEquals(y3, knight.getColumn());
    assertEquals(y4, bishop.getColumn());
    assertEquals(y5, pawnW.getColumn());
  }

  /**
   * Test for get the color of  the position of a chess piece.
   */

  @Test
  public void testGetColor() {
    assertEquals(Color.BLACK, queen0.getColor());
    assertEquals(Color.WHITE, rook00.getColor());
    assertEquals(Color.BLACK, knight00.getColor());
    assertEquals(Color.BLACK, bishop0.getColor());
    assertEquals(Color.WHITE, pawnW.getColor());
  }

  /**
   * Test for determine  whether a chess piece can move to a given position that is illegal.
   */
  @Test
  public void testFalseMove() {

    assertFalse(queen0.canMove(5, 5));
    assertFalse(rook0.canMove(2, 2));
    assertFalse(knight0.canMove(3, 0));
    assertFalse(knight00.canMove(0, 3));
    assertFalse(knight0.canMove(1, 1));
    assertFalse(bishop.canMove(1, 0));
    assertFalse(pawnW.canMove(0, 2));
    assertFalse(pawnB.canMove(0, 2));

  }

  /**
   * Test for determine  whether a chess piece Queen can move to a given position.
   */
  @Test
  public void testQueenCanMove() {
    AbstractChess queenTest = new Queen(7, 0, Color.BLACK);
    for (int num = 0; num < 1000; num++) {
      for (int i = 0; i < 7; i++) {
        int j = queenTest.getColumn();
        assertTrue(queenTest.canMove(i, j));
      }
      for (int j = 1; j < 8; j++) {
        int i = queenTest.getRow();
        assertTrue(queenTest.canMove(i, j));
      }

      assertTrue(queen0.canMove(5, 6));
      assertTrue(queen0.canMove(1, 2));
    }
  }

  /**
   * Test for a chess piece Queen throw Illegal argument with a given position.
   */

  @Test
  public void testIllegalMove() {
    try {
      queen0.canMove(-5, 1);
      fail("The argument is illegal");
    } catch (IllegalArgumentException ignored) {

    }
    try {
      knight0.canMove(10, 1);
      fail("The argument is illegal");
    } catch (IllegalArgumentException ignored) {

    }
    try {
      bishop0.canMove(4, -7);
      fail("The argument is illegal");
    } catch (IllegalArgumentException ignored) {

    }
    try {
      rook0.canMove(4, 8);
      fail("The argument is illegal");
    } catch (IllegalArgumentException ignored) {

    }
  }

  /**
   * Test for determine  whether a chess piece Rook can move to a given position.
   */

  @Test
  public void testRookCanMove() {
    AbstractChess rookTest = new Rook(7, 0, Color.BLACK);
    for (int num = 0; num < 1000; num++) {
      for (int i = 0; i < 7; i++) {
        int j = rookTest.getColumn();
        assertTrue(rookTest.canMove(i, j));
      }
      for (int j = 1; j < 8; j++) {
        int i = rookTest.getRow();
        assertTrue(rookTest.canMove(i, j));
      }
    }
  }

  /**
   * Test for determine  whether a chess piece Bishop can move to a given position.
   */

  @Test
  public void testBishopCanMove() {
    for (int num = 0; num < 1000; num++) {
      int x = (int) (Math.random() * 8);
      int y = (int) (Math.random() * 8);
      AbstractChess bishopTest = new Bishop(x, y, Color.BLACK);
      int i = (int) (Math.random() * 8);
      int j = (int) (Math.random() * 8);
      if (Math.abs(x - i) == Math.abs(y - j)) {
        assertTrue(bishopTest.canMove(i, j));
      }
    }
  }

  /**
   * Test for determine  whether a chess piece Knight can move to a given position.
   */

  @Test
  public void testKnightCanMove() {
    assertTrue(knight0.canMove(6, 5));
    assertTrue(knight0.canMove(2, 5));
    assertTrue(knight0.canMove(2, 3));
    assertTrue(knight0.canMove(6, 3));
    assertTrue(knight0.canMove(5, 6));
    assertTrue(knight0.canMove(3, 6));
    assertTrue(knight0.canMove(5, 2));
    assertTrue(knight0.canMove(3, 2));

    assertFalse(knight0.canMove(1, 2));
    assertFalse(knight0.canMove(3, 3));

  }

  /**
   * Test for determine  whether a chess piece Pawn can move to a given position.
   * Divide into black pawn and white pawn.
   */
  @Test
  public void testPawnCanMove() {
    for (int num = 0; num < 10000; num++) {
      int row1 = (int) (Math.random() * 7 + 1);
      int col1 = (int) (Math.random() * 8);
      int row2 = (int) (Math.random() * 7 + 1);
      int col2 = (int) (Math.random() * 8);
      AbstractChess pawnTestB = new Pawn(row1, col1, Color.BLACK);
      AbstractChess pawnTestW = new Pawn(row2, col2, Color.WHITE);
      int i = (int) (Math.random() * 8);
      int j = (int) (Math.random() * 8);
      if (i == (pawnTestB.getRow() - 1) && j == pawnTestB.getColumn()) {
        assertTrue(pawnTestB.canMove(i, j));
      }
      if (i == (pawnTestW.getRow() + 1) && j == pawnTestW.getColumn()) {
        assertTrue(pawnTestW.canMove(i, j));
      }
    }
  }

  /**
   * Test for determine  whether a chess piece  can kill the other one.
   */

  @Test
  public void canKill() {
    assertTrue(queen0.canKill(rook00));
    assertTrue(queen0.canKill(knight0));
    assertTrue(queen0.canKill(pawn0));
    assertFalse(queen0.canKill(knight00));//cant move
    assertFalse(queen0.canKill(bishop0)); //same color

    assertTrue(rook0.canKill(knight0));
    assertFalse(rook0.canKill(pawn00));  //cant move
    assertFalse(rook00.canKill(pawn0));//same color

    assertTrue(knight0.canKill(pawn00));
    assertFalse(knight0.canKill(pawn0));//same color

    assertTrue(bishop0.canKill(bishop00));
    assertFalse(bishop00.canKill(queen0));//cant move
    assertFalse(bishop0.canKill(knight00));//same color

    assertTrue(pawn0.canKill(queen0));
    assertFalse(pawn0.canKill(queen00)); //same color
    assertFalse(pawn00.canKill(queen0));//cant move
    assertFalse(pawn00.canKill(knight0));//too far
  }
}