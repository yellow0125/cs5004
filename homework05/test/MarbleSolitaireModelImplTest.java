import cs5004.marblesolitaire.model.MarbleSolitaireModel;
import cs5004.marblesolitaire.model.MarbleSolitaireModelImpl;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * This is a test for marble solitaire modelImpl.
 */

public class MarbleSolitaireModelImplTest {
  MarbleSolitaireModel round1 = new MarbleSolitaireModelImpl(3);

  /**
   * Before test set up a game round un over.
   */
  @Before
  public void setUp() {
    round1.move(1, 3, 3, 3);
    round1.move(2, 5, 2, 3);
    round1.move(4, 5, 2, 5);
    round1.move(2, 6, 2, 4);
    round1.move(4, 6, 2, 6);
    round1.move(2, 3, 2, 5);
    round1.move(2, 6, 2, 4);
    round1.move(4, 3, 2, 3);
    round1.move(6, 3, 4, 3);
    round1.move(2, 3, 2, 5);
    round1.move(0, 4, 2, 4);
    round1.move(0, 2, 0, 4);
    round1.move(3, 4, 1, 4);
    round1.move(0, 4, 2, 4);
    round1.move(2, 5, 2, 3);
    round1.move(4, 3, 4, 5);
    round1.move(6, 4, 4, 4);
    round1.move(4, 5, 4, 3);
    round1.move(3, 1, 3, 3);
    round1.move(1, 2, 3, 2);
    round1.move(2, 0, 2, 2);
    round1.move(4, 0, 2, 0);
    round1.move(2, 3, 2, 1);
    round1.move(4, 2, 4, 4);
    round1.move(3, 2, 3, 4);
    round1.move(6, 2, 4, 2);
    round1.move(2, 0, 2, 2);

  }

  /**
   * Test for the first constructor.
   */
  @Test
  public void testConstructor01() {
    MarbleSolitaireModel test = new MarbleSolitaireModelImpl();
    String actual = test.getGameState();
    String expected = "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O";
    assertEquals(expected, actual);
  }

  /**
   * Test for the second constructor.
   */
  @Test
  public void testConstructor02() {
    MarbleSolitaireModel test1 = new MarbleSolitaireModelImpl(3, 3);
    String actual = test1.getGameState();
    String expected = "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O";
    assertEquals(expected, actual);
    MarbleSolitaireModel test2 = new MarbleSolitaireModelImpl(4, 6);
    actual = test2.getGameState();
    expected = "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O _\n"
            + "    O O O\n"
            + "    O O O";
    assertEquals(expected, actual);
  }

  /**
   * Test for the constructor when given position of empty slot is invalid(<0 or >size).
   */
  @Test
  public void testIllegalConstructor02() {
    for (int num = 1; num < 10000; num++) {
      int positive = (int) (Math.random() * 7);
      int x1 = (int) ((Math.random() * (-1 + 200) - 200)); //neg
      int y1 = (int) ((Math.random() * (-1 + 200) - 200));//neg
      int x2 = (int) (Math.random() * 100 + 7);//bigger than size
      int y2 = (int) (Math.random() * 100 + 7);
      int small = (int) (Math.random() * 2);// the small parameter
      int large = (int) (Math.random() * (7 - 5) + 5);//the large parameter
      //test slot invalid
      try {
        MarbleSolitaireModel test = new MarbleSolitaireModelImpl(small, large);
        fail("Invalid empty cell position(" + small + "," + large + ")");
      } catch (IllegalArgumentException e) {
        String actual = e.getMessage();
        String expected = "Invalid empty cell position(" + small + "," + large + ")";
        assertEquals(expected, actual);
        //System.out.println(e);
      }
      try {
        MarbleSolitaireModel test = new MarbleSolitaireModelImpl(small, small);
        fail("Invalid empty cell position(" + small + "," + small + ")");
      } catch (IllegalArgumentException e) {
        String actual = e.getMessage();
        String expected = "Invalid empty cell position(" + small + "," + small + ")";
        assertEquals(expected, actual);
        //System.out.println(e);
      }
      try {
        MarbleSolitaireModel test = new MarbleSolitaireModelImpl(large, large);
        fail("Invalid empty cell position(" + large + "," + large + ")");
      } catch (IllegalArgumentException e) {
        String actual = e.getMessage();
        String expected = "Invalid empty cell position(" + large + "," + large + ")";
        assertEquals(expected, actual);
        //System.out.println(e);
      }
      try {
        MarbleSolitaireModel test = new MarbleSolitaireModelImpl(large, small);
        fail("Invalid empty cell position(" + large + "," + small + ")");
      } catch (IllegalArgumentException e) {
        String actual = e.getMessage();
        String expected = "Invalid empty cell position(" + large + "," + small + ")";
        assertEquals(expected, actual);
        //System.out.println(e);
      }

      //<0
      try {
        MarbleSolitaireModel test = new MarbleSolitaireModelImpl(positive, y1);
        fail("Invalid empty cell position(" + positive + "," + y1 + ")");
      } catch (IllegalArgumentException e) {
        //System.out.println(e);
      }
      try {
        MarbleSolitaireModel test = new MarbleSolitaireModelImpl(x1, positive);
        fail("Invalid empty cell position(" + x1 + "," + positive + ")");
      } catch (IllegalArgumentException e) {
        //System.out.println(e);
      }
      try {
        MarbleSolitaireModel test = new MarbleSolitaireModelImpl(x1, y1);
        fail("Invalid empty cell position(" + x1 + "," + y1 + ")");
      } catch (IllegalArgumentException e) {
        //System.out.println(e);
      }
      //>size-1
      try {
        MarbleSolitaireModel test = new MarbleSolitaireModelImpl(positive, y1);
        fail("Invalid empty cell position(" + positive + "," + y2 + ")");
      } catch (IllegalArgumentException e) {
        //System.out.println(e);
      }
      try {
        MarbleSolitaireModel test = new MarbleSolitaireModelImpl(x1, positive);
        fail("Invalid empty cell position(" + x2 + "," + positive + ")");
      } catch (IllegalArgumentException e) {
        //System.out.println(e);
      }
      try {
        MarbleSolitaireModel test1 = new MarbleSolitaireModelImpl(x1, y1);
        fail("Invalid empty cell position(" + x2 + "," + y2 + ")");
      } catch (IllegalArgumentException e) {
        //System.out.println(e);
      }
    }
  }

  /**
   * Test for the third constructor with parameter arm thickness.
   */
  @Test
  public void testConstructor03() {
    MarbleSolitaireModel test1 = new MarbleSolitaireModelImpl(3);
    String actual = test1.getGameState();
    String expected = "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O";
    assertEquals(expected, actual);
    MarbleSolitaireModel test2 = new MarbleSolitaireModelImpl(5);
    actual = test2.getGameState();
    expected = "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O";
    assertEquals(expected, actual);
  }

  /**
   * Test for the constructor when given arm thickness is invalid(<3 or is even).
   */
  @Test
  public void testIllegalConstructor03() {
    for (int num = 1; num < 10000; num++) {
      int small = (int) ((Math.random() * (3 + 100) - 100)); //smaller than 3
      int even = (int) (Math.random() * 100) * 2;//even
      try {
        MarbleSolitaireModel test = new MarbleSolitaireModelImpl(small);
        fail("The arm thickness is invalid.");
      } catch (IllegalArgumentException e) {
        String actual = e.getMessage();
        String expected = "The arm thickness is invalid.";
        assertEquals(expected, actual);
        //System.out.println(small);
      }
      try {
        MarbleSolitaireModel test = new MarbleSolitaireModelImpl(even);
        fail("The arm thickness is invalid.");
      } catch (IllegalArgumentException e) {
        String actual = e.getMessage();
        String expected = "The arm thickness is invalid.";
        assertEquals(expected, actual);
        //System.out.println(even);
      }
    }
  }

  /**
   * Test for the fourth constructor with arm thickness, row and column.
   */
  @Test
  public void testConstructor04() {
    MarbleSolitaireModel test1 = new MarbleSolitaireModelImpl(3, 3, 3);
    String actual = test1.getGameState();
    String expected = "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O";
    assertEquals(expected, actual);
    MarbleSolitaireModel test2 = new MarbleSolitaireModelImpl(5, 4, 6);
    actual = test2.getGameState();
    expected = "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "O O O O O O _ O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "O O O O O O O O O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O\n"
            + "        O O O O O";
    assertEquals(expected, actual);
  }

  /**
   * Test for the constructor when given position of empty slot is invalid(<0 or >size).
   * and the arm thickness is also invalid(<3 or is even)
   */
  @Test
  public void testIllegalConstructor04() {
    for (int num = 1; num < 10000; num++) {
      int positive = (int) (Math.random() * 7);
      int x1 = (int) ((Math.random() * (-1 + 200) - 200)); //neg
      int y1 = (int) ((Math.random() * (-1 + 200) - 200));//neg
      int x2 = (int) (Math.random() * 100 + 7);//bigger than size
      int y2 = (int) (Math.random() * 100 + 7);
      int small = (int) (Math.random() * 2);// the small parameter
      int large = (int) (Math.random() * (7 - 5) + 5);//the large parameter
      int arm = (int) ((Math.random() * (3 + 100) - 100)); //smaller than 3
      int even = (int) (Math.random() * 100) * 2;
      int odd = 3;
      //test slot invalid
      try {
        MarbleSolitaireModel test = new MarbleSolitaireModelImpl(odd, small, large);
        fail("Invalid empty cell position(" + small + "," + large + ")");
      } catch (IllegalArgumentException e) {
        String actual = e.getMessage();
        String expected = "Invalid empty cell position(" + small + "," + large + ")";
        assertEquals(expected, actual);
      }
      try {
        MarbleSolitaireModel test = new MarbleSolitaireModelImpl(odd, small, small);
        fail("Invalid empty cell position(" + small + "," + small + ")");
      } catch (IllegalArgumentException e) {
        String actual = e.getMessage();
        String expected = "Invalid empty cell position(" + small + "," + small + ")";
        assertEquals(expected, actual);
      }
      try {
        MarbleSolitaireModel test = new MarbleSolitaireModelImpl(odd, large, large);
        fail("Invalid empty cell position(" + large + "," + large + ")");
      } catch (IllegalArgumentException e) {
        String actual = e.getMessage();
        String expected = "Invalid empty cell position(" + large + "," + large + ")";
        assertEquals(expected, actual);
      }
      try {
        MarbleSolitaireModel test = new MarbleSolitaireModelImpl(odd, large, small);
        fail("Invalid empty cell position(" + large + "," + small + ")");
      } catch (IllegalArgumentException e) {
        String actual = e.getMessage();
        String expected = "Invalid empty cell position(" + large + "," + small + ")";
        assertEquals(expected, actual);
      }

      //even
      try {
        MarbleSolitaireModel test = new MarbleSolitaireModelImpl(even, positive, y1);
        fail("The arm thickness is invalid.");
      } catch (IllegalArgumentException e) {
        String actual = e.getMessage();
        String expected = "The arm thickness is invalid.";
        assertEquals(expected, actual);
      }
      try {
        MarbleSolitaireModel test = new MarbleSolitaireModelImpl(arm, x1, positive);
        fail("The arm thickness is invalid.");
      } catch (IllegalArgumentException e) {
        String actual = e.getMessage();
        String expected = "The arm thickness is invalid.";
        assertEquals(expected, actual);
      }
      try {
        MarbleSolitaireModel test = new MarbleSolitaireModelImpl(odd, x1, y1);
        fail("Invalid empty cell position(" + x1 + "," + y1 + ")");
      } catch (IllegalArgumentException e) {
        //System.out.println(e);
      }
      //>size-1
      try {
        MarbleSolitaireModel test = new MarbleSolitaireModelImpl(even, positive, y1);
        fail("The arm thickness is invalid.");
      } catch (IllegalArgumentException e) {
        String actual = e.getMessage();
        String expected = "The arm thickness is invalid.";
        assertEquals(expected, actual);
      }
      try {
        MarbleSolitaireModel test = new MarbleSolitaireModelImpl(arm, x1, positive);
        fail("The arm thickness is invalid.");
      } catch (IllegalArgumentException e) {
        String actual = e.getMessage();
        String expected = "The arm thickness is invalid.";
        assertEquals(expected, actual);
      }
      try {
        MarbleSolitaireModel test1 = new MarbleSolitaireModelImpl(odd, x1, y1);
        fail("Invalid empty cell position(" + x2 + "," + y2 + ")");
      } catch (IllegalArgumentException e) {
        //System.out.println(e);
      }
    }
  }

  /**
   * Test for move method with given from and to of row & column. move to right
   */
  @Test
  public void testMoveRight() {
    MarbleSolitaireModelImpl test = new MarbleSolitaireModelImpl();
    test.move(3, 1, 3, 3);

    String actual = test.getGameState();
    String expected = "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O _ _ O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O";
    assertEquals(expected, actual);
  }

  /**
   * Test for move method with given from and to of row & column. move to left
   */

  @Test
  public void testMoveLeft() {
    MarbleSolitaireModelImpl test = new MarbleSolitaireModelImpl();
    test.move(3, 5, 3, 3);
    String actual = test.getGameState();
    String expected = "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O _ _ O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O";
    assertEquals(expected, actual);
  }

  /**
   * Test for move method with given from and to of row & column. move to down
   */

  @Test
  public void testMoveDown() {
    MarbleSolitaireModelImpl test = new MarbleSolitaireModelImpl();
    test.move(1, 3, 3, 3);
    String actual = test.getGameState();
    String expected = "    O O O\n"
            + "    O _ O\n"
            + "O O O _ O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "    O O O\n"
            + "    O O O";
    assertEquals(expected, actual);
  }

  /**
   * Test for move method with given from and to of row & column. move to up
   */
  @Test
  public void testMoveUp() {
    MarbleSolitaireModelImpl test = new MarbleSolitaireModelImpl();
    test.move(5, 3, 3, 3);
    String actual = test.getGameState();
    String expected = "    O O O\n"
            + "    O O O\n"
            + "O O O O O O O\n"
            + "O O O O O O O\n"
            + "O O O _ O O O\n"
            + "    O _ O\n"
            + "    O O O";
    assertEquals(expected, actual);
  }

  /**
   * Test for move with given parameters are invalid. (<0 or >size or slot is "")
   * position not exit
   */
  @Test
  public void illegalMove01() {
    for (int num = 1; num < 100; num++) {
      int arm = 3;
      int small = (int) (Math.random() * 2);// the small parameter
      int large = (int) (Math.random() * (7 - 5) + 5);//the large parameter

      int positive = (int) (Math.random() * 7);
      int negative1 = (int) ((Math.random() * (-1 + 200) - 200));
      int bigger1 = (int) (Math.random() * 100 + 7);//bigger than size
      MarbleSolitaireModel test = new MarbleSolitaireModelImpl(arm);
      //less one <0
      try {
        test.move(negative1, positive, positive, positive);
        fail("Invalid Position");
      } catch (IllegalArgumentException e) {
        //System.out.println(e);
      }
      try {
        test.move(positive, negative1, positive, positive);
        fail("Invalid Position");
      } catch (IllegalArgumentException e) {
        //System.out.println(e);
      }
      try {
        test.move(positive, positive, negative1, positive);
        fail("Invalid Position");
      } catch (IllegalArgumentException e) {
        //System.out.println(e);
      }
      try {
        test.move(positive, positive, positive, negative1);
        fail("Invalid Position");
      } catch (IllegalArgumentException e) {
        //System.out.println(e);
      }
      //less one >size-1
      try {
        test.move(bigger1, positive, positive, positive);
        fail("Invalid Position");
      } catch (IllegalArgumentException e) {
        //System.out.println(e);
      }
      try {
        test.move(positive, bigger1, positive, positive);
        fail("Invalid Position");
      } catch (IllegalArgumentException e) {
        //System.out.println(e);
      }
      try {
        test.move(positive, positive, bigger1, positive);
        fail("Invalid Position");
      } catch (IllegalArgumentException e) {
        //System.out.println(e);
      }
      try {
        test.move(positive, positive, positive, bigger1);
        fail("Invalid Position");
      } catch (IllegalArgumentException e) {
        //System.out.println(e);
      }
      //from or to position are invalid slot. ""
      try {
        test.move(small, small, positive, positive);
        fail("Invalid Position");
      } catch (IllegalArgumentException e) {
        //System.out.println(e);
      }
      try {
        test.move(large, small, positive, positive);
        fail("Invalid Position");
      } catch (IllegalArgumentException e) {
        //System.out.println(e);
      }
      try {
        test.move(large, large, positive, positive);
        fail("Invalid Position");
      } catch (IllegalArgumentException e) {
        //System.out.println(e);
      }
      try {
        test.move(small, large, positive, positive);
        fail("Invalid Position");
      } catch (IllegalArgumentException e) {
        //System.out.println(e);
      }
      try {
        test.move(positive, positive, small, small);
        fail("Invalid Position");
      } catch (IllegalArgumentException e) {
        //System.out.println(e);
      }
      try {
        test.move(positive, positive, small, large);
        fail("Invalid Position");
      } catch (IllegalArgumentException e) {
        //System.out.println(e);
      }
      try {
        test.move(positive, positive, large, small);
        fail("Invalid Position");
      } catch (IllegalArgumentException e) {
        //System.out.println(e);
      }
      try {
        test.move(positive, positive, large, large);
        fail("Invalid Position");
      } catch (IllegalArgumentException e) {
        //System.out.println(e);
      }
    }
  }

  /**
   * Test for move with given to position parameters is invalid(math.abs(to-from!=2)).
   */
  @Test
  public void illegalMove02() {
    int arm = 3;
    MarbleSolitaireModel test = new MarbleSolitaireModelImpl(arm);
    try {
      test.move(1, 3, 2, 2);
      fail("Invalid 'To' Position");
    } catch (IllegalArgumentException e) {
      //System.out.println(e);
    }
    try {
      test.move(3, 1, 4, 0);
      fail("Invalid 'To' Position");
    } catch (IllegalArgumentException e) {
      //System.out.println(e);
    }
    try {
      test.move(3, 5, 4, 0);
      fail("Invalid 'To' Position");
    } catch (IllegalArgumentException e) {
      //System.out.println(e);
    }
    try {
      test.move(5, 3, 2, 4);
      fail("Invalid 'To' Position");
    } catch (IllegalArgumentException e) {
      //System.out.println(e);
    }
    //
    try {
      test.move(1, 3, 0, 3);
      fail("Invalid 'To' Position");
    } catch (IllegalArgumentException e) {
      //System.out.println(e);
    }
    try {
      test.move(3, 1, 3, 6);
      fail("Invalid 'To' Position");
    } catch (IllegalArgumentException e) {
      //System.out.println(e);
    }
    try {
      test.move(3, 5, 3, 4);
      fail("Invalid 'To' Position");
    } catch (IllegalArgumentException e) {
      //System.out.println(e);
    }
    try {
      test.move(5, 3, 6, 3);
      fail("Invalid 'To' Position");
    } catch (IllegalArgumentException e) {
      //System.out.println(e);
    }
    try {
      round1.move(4, 1, 4, 3);
      round1.move(4, 3, 2, 5);
      fail("Invalid 'To' Position");
    } catch (IllegalArgumentException e) {
      //System.out.println(e);
    }
  }

  /**
   * Test for move with given to position parameters is with wrong slot type.
   */
  @Test
  public void illegalMove03() {
    int arm = 3;
    MarbleSolitaireModel test = new MarbleSolitaireModelImpl(arm);
    test.move(1, 3, 3, 3);
    test.move(2, 5, 2, 3);
    test.move(4, 5, 2, 5);
    test.move(2, 6, 2, 4);
    test.move(3, 3, 3, 5);
    test.move(3, 6, 3, 4);

    //from position not chess type
    try {
      test.move(2, 5, 4, 5);
      fail("Invalid Position with Wrong Chess Type");
    } catch (IllegalArgumentException e) {
      //System.out.println(e);
    }
    try {
      test.move(2, 6, 4, 6);
      fail("Invalid Position with Wrong Chess Type");
    } catch (IllegalArgumentException e) {
      //System.out.println(e);
    }
    //to is not empty type
    try {
      test.move(4, 6, 4, 4);
      fail("Invalid Position with Wrong Chess Type");
    } catch (IllegalArgumentException e) {
      //System.out.println(e);
    }
    try {
      test.move(4, 4, 2, 4);
      fail("Invalid Position with Wrong Chess Type");
    } catch (IllegalArgumentException e) {
      //System.out.println(e);
    }
    try {
      test.move(2, 3, 4, 3);
      fail("Invalid Position with Wrong Chess Type");
    } catch (IllegalArgumentException e) {
      //System.out.println(e);
    }
    try {
      test.move(4, 0, 4, 2);
      fail("Invalid Position with Wrong Chess Type");
    } catch (IllegalArgumentException e) {
      //System.out.println(e);
    }
    //"Can't move with no jumped over chess piece"
    try {
      test.move(4, 6, 2, 6);
      fail("Can't move with no jumped over chess piece");
    } catch (IllegalArgumentException e) {
      //System.out.println(e);
    }

  }

  /**
   * Test for determine if the game is over.
   */
  @Test
  public void isGameOver() {
    assertFalse(round1.isGameOver());
    round1.move(4, 1, 4, 3);
    round1.move(4, 4, 2, 4);
    assertTrue(round1.isGameOver());
  }

  /**
   * Test for get the state of the game.
   */
  @Test
  public void getGameState() {
    String expected = "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ O _ _ _ _\n"
            + "_ _ _ _ O _ _\n"
            + "_ O O _ O _ _\n"
            + "    _ _ _\n"
            + "    _ _ _";

    String actual = round1.getGameState();
    assertEquals(expected, actual);
    round1.move(4, 1, 4, 3);
    round1.move(4, 4, 2, 4);
    expected = "    _ _ _\n"
            + "    _ _ _\n"
            + "_ _ O _ O _ _\n"
            + "_ _ _ _ _ _ _\n"
            + "_ _ _ O _ _ _\n"
            + "    _ _ _\n"
            + "    _ _ _";
    actual = round1.getGameState();
    assertEquals(expected, actual);
  }

  /**
   * Test for get the score of the game.
   */

  @Test
  public void getScore() {
    MarbleSolitaireModel test = new MarbleSolitaireModelImpl(3);
    int expected = 32;
    int actual = test.getScore();
    assertEquals(expected, actual);

    expected = 5;
    actual = round1.getScore();
    assertEquals(expected, actual);
    round1.move(4, 1, 4, 3);
    round1.move(4, 4, 2, 4);
    expected = 3;
    actual = round1.getScore();
    assertEquals(expected, actual);
  }

}