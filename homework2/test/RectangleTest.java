import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * A JUnit test class for the rectangle class.
 */

public class RectangleTest {

  /**
   * Test the Constructor creat a rectangle.
   */

  @Test
  public void testCreatRectangle() {
    int max = 100;
    int min = -100;
    for (int i = 0; i < 10000; i++) {
      int x = (int) Math.round(Math.random() * (max - min));
      int y = (int) Math.round(Math.random() * (max - min));
      int width = (int) Math.round(Math.random() * 100 + 1);
      int height = (int) Math.round(Math.random() * 100 + 1);

      try {
        new Rectangle(x, y, width, height);
      } catch (IllegalArgumentException e) {
        fail("Non-positive is not allowed");
      }
    }
  }
  /**
   * Test the Constructor if the width or height are non-positive(illegal).
   */

  @Test
  public void testIllRectangle() {
    int max = 100;
    int min = -100;
    for (int width = -100; width < 1; width++) {
      for (int height = -100; height < 1; height++) {
        try {
          int x = (int) Math.round(Math.random() * (max - min));
          int y = (int) Math.round(Math.random() * (max - min));
          new Rectangle(x, y, width, height);
          fail("Non-positive width or height is not allowed");
        } catch (IllegalArgumentException e) {
          assertTrue(width < 1 || height < 1);
        }
      }
    }
  }
  /**
   * Test the method when two rectangle have no overlap.
   * When the other is on the right of the test one.
   */

  @Test
  public void testOverlapFalseRight() {
    Rectangle test = new Rectangle(0, 0, 100, 100);
    int max = 100;
    int min = -100;

    for (int x1 = 100; x1 < 10000; x1++) {
      int y1 = (int) Math.round(Math.random() * (max - min));
      int width1 = (int) Math.round(Math.random() * 100 + 1);
      int height1 = (int) Math.round(Math.random() * 100 + 1);
      Rectangle other = new Rectangle(x1, y1, width1, height1);
      assertFalse(test.overlap(other));
      assertFalse(other.overlap(test));
    }
  }
  /**
   * Test the method when two rectangle have no overlap.
   * When the other is on the left of the test one.
   */

  @Test
  public void testOverlapFalseLeft() {
    Rectangle test = new Rectangle(0, 0, 100, 100);
    int max = 100;
    int min = -100;
    for (int x2 = 0; x2 > -10000; x2--) {
      int y1 = (int) Math.round(Math.random() * (max - min));
      int width1 = (int) Math.round(Math.random() * 100 + 1);
      int height1 = (int) Math.round(Math.random() * 100 + 1);
      int x1 = x2 - width1;
      Rectangle other = new Rectangle(x1, y1, width1, height1);
      assertFalse(test.overlap(other));
      assertFalse(other.overlap(test));
    }
  }
  /**
   * Test the method when two rectangle have no overlap.
   * When the other is on the up of the test one.
   */

  @Test
  public void testOverlapFalseUp() {
    Rectangle test = new Rectangle(0, 0, 100, 100);
    int max = 100;
    int min = -100;
    for (int y1 = 100; y1 < 10000; y1++) {
      int x1 = (int) Math.round(Math.random() * (max - min));
      int width1 = (int) Math.round(Math.random() * 100 + 1);
      int height1 = (int) Math.round(Math.random() * 100 + 1);
      Rectangle other = new Rectangle(x1, y1, width1, height1);
      assertFalse(test.overlap(other));
      assertFalse(other.overlap(test));
    }
  }

  /**
   * Test the method when two rectangle have no overlap.
   * When the other is on the down of the test one.
   */

  @Test
  public void testOverlapFalseDown() {
    Rectangle test = new Rectangle(0, 0, 100, 100);
    int max = 100;
    int min = -100;
    for (int y2 = 0; y2 > -10000; y2--) {
      int x1 = (int) Math.round(Math.random() * (max - min));
      int width1 = (int) Math.round(Math.random() * 100 + 1);
      int height1 = (int) Math.round(Math.random() * 100 + 1);
      int y1 = y2 - height1;
      Rectangle other = new Rectangle(x1, y1, width1, height1);
      assertFalse(test.overlap(other));
      assertFalse(other.overlap(test));
    }
  }

  /**
   * Test the method when two rectangle have overlap.
   */

  @Test
  public void testOverlapTrue() {
    Rectangle test = new Rectangle(0, 0, 100, 100);
    int max = 99; // x1 & y1 should < 100.
    int min = 0; //x1 + width should>0&&y1+width should >0.
    int min1 = -99;
    for (int i = 0; i < 10000; i++) {
      int x1 = (int) Math.round(Math.random() * (max - min) + min);
      int y1 = (int) Math.round(Math.random() * (max - min) + min);
      int width1 = (int) Math.round(Math.random() * 100 + 1);
      int height1 = (int) Math.round(Math.random() * 100 + 1);
      Rectangle other = new Rectangle(x1, y1, width1, height1);
      assertTrue(test.overlap(other));
      assertTrue(other.overlap(test));
      int x2 = (int) Math.round(Math.random() * (max - min1) + min1);
      int y2 = (int) Math.round(Math.random() * (max - min1) + min1);
      int width2 = (int) Math.round(Math.random() * (200 - 100) + 100);
      int height2 = (int) Math.round(Math.random() * (200 - 100) + 100);
      Rectangle other1 = new Rectangle(x2, y2, width2, height2);
      assertTrue(test.overlap(other1));
      assertTrue(other1.overlap(test));
    }
  }

  /**
   * Test the method and return the intersection of two rectangle.
   */

  @Test
  public void testIntersect() {
    Rectangle test = new Rectangle(0, 0, 100, 100);
    int max = 99;
    int min = 0;
    int min1 = -99;
    for (int i = 0; i < 10000; i++) {
      int x1 = (int) Math.round(Math.random() * (max - min) + min);
      int y1 = (int) Math.round(Math.random() * (max - min) + min);
      int width1 = (int) Math.round(Math.random() * 100 + 1);
      int height1 = (int) Math.round(Math.random() * 100 + 1);
      Rectangle other = new Rectangle(x1, y1, width1, height1);

      int xLowLeft = Math.max(0, x1);
      int yLowLeft = Math.max(0, y1);
      int xUpRight = Math.min(100, x1 + width1);
      int yUpRight = Math.min(100, y1 + height1);
      int exceptW = xUpRight - xLowLeft;
      int exceptH = yUpRight - yLowLeft;
      Rectangle except = new Rectangle(xLowLeft, yLowLeft, exceptW, exceptH);

      int x2 = (int) Math.round(Math.random() * (max - min1) + min1);
      int y2 = (int) Math.round(Math.random() * (max - min1) + min1);
      int width2 = (int) Math.round(Math.random() * (200 - 100) + 100);
      int height2 = (int) Math.round(Math.random() * (200 - 100) + 100);
      Rectangle other2 = new Rectangle(x2, y2, width2, height2);
      int xLowLeft2 = Math.max(0, x2);
      int yLowLeft2 = Math.max(0, y2);
      int xUpRight2 = Math.min(100, x2 + width2);
      int yUpRight2 = Math.min(100, y2 + height2);
      int exceptW2 = xUpRight2 - xLowLeft2;
      int exceptH2 = yUpRight2 - yLowLeft2;
      Rectangle except2 = new Rectangle(xLowLeft2, yLowLeft2, exceptW2, exceptH2);

      try {
        Rectangle result = test.intersect(other);
        Rectangle result2 = test.intersect(other2);
        assertEquals(except.toString(), result.toString());
        assertEquals(except2.toString(), result2.toString());
      } catch (NoSuchElementException e) {
        fail("No intersection exists");
      }
    }
  }
  /**
   * Test the method when two rectangle have no overlap which
   * also do not have an intersection.
   * When the other is on the right of the test one.
   */

  @Test
  public void testIllIntersectRight() {
    Rectangle test = new Rectangle(0, 0, 100, 100);
    int max = 100;
    int min = -100;

    for (int x1 = 100; x1 < 10000; x1++) {
      int y1 = (int) Math.round(Math.random() * (max - min));
      int width1 = (int) Math.round(Math.random() * 100 + 1);
      int height1 = (int) Math.round(Math.random() * 100 + 1);
      Rectangle other = new Rectangle(x1, y1, width1, height1);
      try {
        Rectangle result = test.intersect(other);
        fail("No intersection exists");
      } catch (NoSuchElementException e) {
        //System.out.print(e); have caught the error.
      }
    }
  }
  /**
   * Test the method when two rectangle have no overlap which
   * also do not have an intersection.
   * When the other is on the left of the test one.
   */

  @Test
  public void testIllIntersectLeft() {
    Rectangle test = new Rectangle(0, 0, 100, 100);
    int max = 100;
    int min = -100;

    for (int x2 = 0; x2 > -10000; x2--) {
      int y1 = (int) Math.round(Math.random() * (max - min));
      int width1 = (int) Math.round(Math.random() * 100 + 1);
      int height1 = (int) Math.round(Math.random() * 100 + 1);
      int x1 = x2 - width1;
      Rectangle other = new Rectangle(x1, y1, width1, height1);
      try {
        Rectangle result = test.intersect(other);
        fail("No intersection exists");
      } catch (NoSuchElementException e) {
        //System.out.print(e); have caught the error.
      }
    }
  }

  /**
   * Test the method when two rectangle have no overlap which
   * also do not have an intersection.
   * When the other is on the up of the test one.
   */

  @Test
  public void testIllIntersectUp() {
    Rectangle test = new Rectangle(0, 0, 100, 100);
    int max = 100;
    int min = -100;

    for (int y1 = 100; y1 < 10000; y1++) {
      int x1 = (int) Math.round(Math.random() * (max - min));
      int width1 = (int) Math.round(Math.random() * 100 + 1);
      int height1 = (int) Math.round(Math.random() * 100 + 1);
      Rectangle other = new Rectangle(x1, y1, width1, height1);
      try {
        Rectangle result = test.intersect(other);
        fail("No intersection exists");
      } catch (NoSuchElementException e) {
        //System.out.print(e); have caught the error.
      }
    }
  }

  /**
   * Test the method when two rectangle have no overlap which
   * also do not have an intersection.
   * When the other is on the down of the test one.
   */

  @Test
  public void testIllIntersectDown() {
    Rectangle test = new Rectangle(0, 0, 100, 100);
    int max = 100;
    int min = -100;

    for (int y2 = 0; y2 > -10000; y2--) {
      int x1 = (int) Math.round(Math.random() * (max - min));
      int width1 = (int) Math.round(Math.random() * 100 + 1);
      int height1 = (int) Math.round(Math.random() * 100 + 1);
      int y1 = y2 - height1;
      Rectangle other = new Rectangle(x1, y1, width1, height1);
      try {
        Rectangle result = test.intersect(other);
        fail("No intersection exists");
      } catch (NoSuchElementException e) {
        //System.out.print(e); have caught the error.
      }
    }
  }

  /**
   * Test the method  and return the UNION of two rectangles.
   */

  @Test
  public void testUnion() {
    Rectangle test = new Rectangle(0, 0, 100, 100);
    int max = 100;
    int min = -100;
    for (int i = 0; i < 10000; i++) {
      int x1 = (int) Math.round(Math.random() * (max - min) + min);
      int y1 = (int) Math.round(Math.random() * (max - min) + min);
      int width1 = (int) Math.round(Math.random() * 100 + 1);
      int height1 = (int) Math.round(Math.random() * 100 + 1);
      Rectangle other = new Rectangle(x1, y1, width1, height1);

      int xLowLeft = Math.min(0, x1);
      int yLowLeft = Math.min(0, y1);
      int xUpRight = Math.max(100, x1 + width1);
      int yUpRight = Math.max(100, y1 + height1);
      int exceptW = xUpRight - xLowLeft;
      int exceptH = yUpRight - yLowLeft;
      Rectangle except = new Rectangle(xLowLeft, yLowLeft, exceptW, exceptH);
      Rectangle result = test.union(other);
      assertEquals(except.toString(), result.toString());
    }
  }

  @Test
  public void testToString() {
    Rectangle test = new Rectangle(0, 0, 100, 100);
    String str = "x:0, y:0, w:100, h:100";
    assertEquals(str, test.toString());
  }
}
