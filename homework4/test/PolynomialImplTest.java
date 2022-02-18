import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

/**
 * Test for the Polynomial.
 */
public class PolynomialImplTest {
  Polynomial p1;
  Polynomial p2;
  Polynomial p3;
  Polynomial p4;

  /**
   * Set up objects before test.
   */
  @Before
  public void setUp() {
    p1 = new PolynomialImpl();
    for (int j = 0; j < 5; j++) {
      int i = 10;
      p1.addTerm(i, j);  //10+10x^1+10x^2+10x^3+10x^4 in reverse.
    }
    p2 = new PolynomialImpl();  //empty node
    p3 = new PolynomialImpl("4x^3 +3x^1 -5");
    p4 = new PolynomialImpl("-3x^4 -2x^5 -5 +11x^1");
  }

  /**
   * Test constructor with no argument.
   */
  @Test
  public void testConstructor() {
    // with no argument.
    assertEquals("10x^4 +10x^3 +10x^2 +10x^1 +10", p1.toString());
    assertEquals("0", p2.toString());
    // with given string.
    assertEquals("4x^3 +3x^1 -5", p3.toString());
    assertEquals("-2x^5 -3x^4 +11x^1 -5", p4.toString());
  }

  /**
   * Test addTerm method when given power is invalid(<0).
   */
  @Test
  public void illegalAddTerm() {
    for (int i = -1; i > -1000; i--) {
      int coefficient = (int) (Math.random() * (300) - 100);
      try {
        p1.addTerm(coefficient, i);
        fail("Negative power is not allowed!");
      } catch (IllegalArgumentException ignored) {
        //System.out.println("Negative power is not allowed!");
      }
      try {
        p2.addTerm(coefficient, i);
        fail("Negative power is not allowed!");
      } catch (IllegalArgumentException ignored) {
        //System.out.println("Negative power is not allowed!");
      }
      try {
        p3.addTerm(coefficient, i);
        fail("Negative power is not allowed!");
      } catch (IllegalArgumentException ignored) {
        //System.out.println("Negative power is not allowed!");
      }
      try {
        p4.addTerm(coefficient, i);
        fail("Negative power is not allowed!");
      } catch (IllegalArgumentException ignored) {
        //System.out.println("Negative power is not allowed!");
      }
    }
  }

  /**
   * Test addTerm method.
   */
  @Test
  public void addTerm() {
    for (int j = 0; j < 100; j++) {
      p1.addTerm(0, j);
      assertEquals("10x^4 +10x^3 +10x^2 +10x^1 +10", p1.toString());
    }
    p1.addTerm(5, 6);  //larger power
    assertEquals("5x^6 +10x^4 +10x^3 +10x^2 +10x^1 +10", p1.toString());
    p1.addTerm(-8, 0); //power =0
    assertEquals("5x^6 +10x^4 +10x^3 +10x^2 +10x^1 +2", p1.toString());
    p1.addTerm(-20, 4); //same power
    assertEquals("5x^6 -10x^4 +10x^3 +10x^2 +10x^1 +2", p1.toString());
    p1.addTerm(10, 3); //same power
    assertEquals("5x^6 -10x^4 +20x^3 +10x^2 +10x^1 +2", p1.toString());
    p1.addTerm(-10, 2); //same power && new coef=0
    assertEquals("5x^6 -10x^4 +20x^3 +10x^1 +2", p1.toString());
    p1.addTerm(-5, 6); //same power && new coef=0
    assertEquals("-10x^4 +20x^3 +10x^1 +2", p1.toString());

    for (int j = 0; j < 5; j++) {
      int i = 2;
      p2.addTerm(i, j);
    }
    assertEquals("2x^4 +2x^3 +2x^2 +2x^1 +2", p2.toString());
    p3.addTerm(-10, 2);
    assertEquals("4x^3 -10x^2 +3x^1 -5", p3.toString());
    p4.addTerm(-5, 4);
    assertEquals("-2x^5 -8x^4 +11x^1 -5", p4.toString());

  }

  /**
   * Test removeTerm method.
   */
  @Test
  public void removeTerm() {
    for (int j = 6; j < 1000; j++) {
      p1.removeTerm(j);
      p2.removeTerm(j);
      p3.removeTerm(j);
      p4.removeTerm(j);
      assertEquals("10x^4 +10x^3 +10x^2 +10x^1 +10", p1.toString());
      assertEquals("0", p2.toString());
      assertEquals("4x^3 +3x^1 -5", p3.toString());
      assertEquals("-2x^5 -3x^4 +11x^1 -5", p4.toString());
    }
    for (int j = -1; j > -1000; j--) {
      p1.removeTerm(j);
      p2.removeTerm(j);
      p3.removeTerm(j);
      p4.removeTerm(j);
      assertEquals("10x^4 +10x^3 +10x^2 +10x^1 +10", p1.toString());
      assertEquals("0", p2.toString());
      assertEquals("4x^3 +3x^1 -5", p3.toString());
      assertEquals("-2x^5 -3x^4 +11x^1 -5", p4.toString());
    }

    for (int j = 0; j < 5; j++) {
      p1.removeTerm(j);
    }
    assertEquals("0", p1.toString());

    p3.removeTerm(0);
    assertEquals("4x^3 +3x^1", p3.toString());
    p3.removeTerm(3);
    assertEquals("3x^1", p3.toString());

    p4.removeTerm(0);
    assertEquals("-2x^5 -3x^4 +11x^1", p4.toString());
    p4.removeTerm(4);
    assertEquals("-2x^5 +11x^1", p4.toString());

  }

  /**
   * Test getDegree method.
   */
  @Test
  public void getDegree() {
    assertEquals(4, p1.getDegree());
    assertEquals(0, p2.getDegree());
    assertEquals(3, p3.getDegree());
    assertEquals(5, p4.getDegree());
  }

  /**
   * Test getCoefficient method.
   */
  @Test
  public void getCoefficient() {
    for (int j = 0; j < 5; j++) {
      assertEquals(10, p1.getCoefficient(j));
    }

    assertEquals(4, p3.getCoefficient(3));
    assertEquals(3, p3.getCoefficient(1));
    assertEquals(-5, p3.getCoefficient(0));
    assertEquals(-2, p4.getCoefficient(5));
    assertEquals(-3, p4.getCoefficient(4));
    assertEquals(11, p4.getCoefficient(1));
    assertEquals(-5, p4.getCoefficient(0));

    for (int j = 6; j < 1000; j++) {
      assertEquals(0, p1.getCoefficient(j));
      assertEquals(0, p2.getCoefficient(j));
      assertEquals(0, p3.getCoefficient(j));
      assertEquals(0, p4.getCoefficient(j));
    }

    for (int j = -1; j > -1000; j--) {
      assertEquals(0, p1.getCoefficient(j));
      assertEquals(0, p2.getCoefficient(j));
      assertEquals(0, p3.getCoefficient(j));
      assertEquals(0, p4.getCoefficient(j));
    }
  }

  /**
   * Test evaluate method.
   */
  @Test
  public void evaluate() {
    for (int i = 1; i < 10000; i++) {
      double number = (Math.random() * (300) - 100);
      double expected1 = 10 * Math.pow(number, 4) + 10 * Math.pow(number, 3)
              + 10 * Math.pow(number, 2) + 10 * Math.pow(number, 1) + 10;
      double expected2 = 0;
      double expected3 = 4 * Math.pow(number, 3) + 3 * Math.pow(number, 1) - 5;
      double expected4 = -2 * Math.pow(number, 5) - 3 * Math.pow(number, 4)
              + 11 * Math.pow(number, 1) - 5;
      assertEquals(expected1, p1.evaluate(number), 0.001);
      assertEquals(expected2, p2.evaluate(number), 0.001);
      assertEquals(expected3, p3.evaluate(number), 0.001);
      assertEquals(expected4, p4.evaluate(number), 0.001);
    }

    assertEquals(10, p1.evaluate(0), 0.001);
    assertEquals(0, p2.evaluate(0), 0.001);
    assertEquals(-5, p3.evaluate(0), 0.001);
    assertEquals(-5, p4.evaluate(0), 0.001);
  }

  /**
   * Test for add method. add with other polynomial.
   */
  @Test
  public void add() {
    //add with other = 0; || this =0;
    Polynomial add01a = p1.add(p2);
    assertEquals("10x^4 +10x^3 +10x^2 +10x^1 +10", add01a.toString());
    assertNotEquals(add01a, p1);
    assertNotEquals(add01a, p2);

    Polynomial add01b = p2.add(p1);
    assertEquals("10x^4 +10x^3 +10x^2 +10x^1 +10", add01b.toString());
    assertNotEquals(add01b, p1);
    assertNotEquals(add01b, p2);

    Polynomial add02a = p2.add(p2);
    assertEquals("0", p2.toString());
    assertNotEquals(add02a, p2);

    Polynomial add03a = p3.add(p2);
    assertEquals("4x^3 +3x^1 -5", add03a.toString());
    assertNotEquals(add03a, p3);
    assertNotEquals(add03a, p2);

    Polynomial add03b = p2.add(p3);
    assertEquals("4x^3 +3x^1 -5", add03b.toString());
    assertNotEquals(add03b, p3);
    assertNotEquals(add03b, p2);

    Polynomial add04a = p4.add(p2);
    assertEquals("-2x^5 -3x^4 +11x^1 -5", add04a.toString());
    assertNotEquals(add04a, p4);
    assertNotEquals(add04a, p2);

    Polynomial add04b = p2.add(p4);
    assertEquals("-2x^5 -3x^4 +11x^1 -5", add04b.toString());
    assertNotEquals(add04b, p4);
    assertNotEquals(add04b, p2);

    //add with other !=0
    Polynomial other = new PolynomialImpl("-7x^6 +2x^5 -10x^4 +10x^3 +6x^2 -2x^1 +1");

    Polynomial result1a = p1.add(other);
    assertEquals("-7x^6 +2x^5 +20x^3 +16x^2 +8x^1 +11", result1a.toString());
    assertNotEquals(result1a, p1);
    assertNotEquals(result1a, other);

    Polynomial result1b = other.add(p1);
    assertEquals("-7x^6 +2x^5 +20x^3 +16x^2 +8x^1 +11", result1b.toString());
    assertNotEquals(result1b, p1);
    assertNotEquals(result1b, other);

    Polynomial result2a = p3.add(other);
    assertEquals("-7x^6 +2x^5 -10x^4 +14x^3 +6x^2 +1x^1 -4", result2a.toString());
    assertNotEquals(result2a, p3);
    assertNotEquals(result2a, other);

    Polynomial result2b = other.add(p3);
    assertEquals("-7x^6 +2x^5 -10x^4 +14x^3 +6x^2 +1x^1 -4", result2b.toString());
    assertNotEquals(result2b, p3);
    assertNotEquals(result2b, other);

    Polynomial result3a = p4.add(other);
    assertEquals("-7x^6 -13x^4 +10x^3 +6x^2 +9x^1 -4", result3a.toString());
    assertNotEquals(result3a, p3);
    assertNotEquals(result3a, other);

    Polynomial result3b = p4.add(other);
    assertEquals("-7x^6 -13x^4 +10x^3 +6x^2 +9x^1 -4", result3b.toString());
    assertNotEquals(result3b, p4);
    assertNotEquals(result3b, other);
  }

  /**
   * Test for add method. add with other polynomial
   * which is of a different concrete class with this object.
   */
  @Test
  public void illegalAdd() {
    Node rest = new EmptyNode();
    for (int num = 0; num < 10000; num++) {
      int x = (int) (Math.random() * ((200) - 100));
      int y = (int) (Math.random() * 100);
      Polynomial other = new TermNode(x, y, rest);
      try {
        Polynomial result01 = p1.add(other);
        fail("The given polynomial is of a different class with this object.");
      } catch (IllegalArgumentException ignored) {
        //System.out.println(ignored);
      }
      try {
        Polynomial result02 = other.add(p1);
        fail("The given polynomial is of a different class with this object.");
      } catch (IllegalArgumentException ignored) {
        //System.out.println(ignored);
      }
      try {
        Polynomial result03 = p2.add(other);
        fail("The given polynomial is of a different class with this object.");
      } catch (IllegalArgumentException ignored) {
        //System.out.println(ignored);
      }
      try {
        Polynomial result04 = p3.add(other);
        fail("The given polynomial is of a different class with this object.");
      } catch (IllegalArgumentException ignored) {
        //System.out.println(ignored);
      }
      try {
        Polynomial result05 = p4.add(other);
        fail("The given polynomial is of a different class with this object.");
      } catch (IllegalArgumentException ignored) {
        //System.out.println(ignored);
      }
    }
  }

  /**
   * Test toString method.
   */
  @Test
  public void testToString() {
    assertEquals("10x^4 +10x^3 +10x^2 +10x^1 +10", p1.toString());
    assertEquals("0", p2.toString());
    assertEquals("4x^3 +3x^1 -5", p3.toString());
    assertEquals("-2x^5 -3x^4 +11x^1 -5", p4.toString());
  }
}