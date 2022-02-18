
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A testing class for FibonacciCounter class.
 */
public class FibonacciCounterTest {
  //private int counter;
  private FibonacciCounter f0 = new FibonacciCounter(0);
  private FibonacciCounter f1 = new FibonacciCounter(1);
  private FibonacciCounter f21 = new FibonacciCounter(21);

  @Test
  public void incrementN() {
    FibonacciCounter f1 = f0.incrementN();
    assertEquals(1, f1.getCurrentCounter());
    assertEquals(1, f1.getFibonacciCounter());
    System.out.print(f1.getFibonacciCounter());
    FibonacciCounter f2 = f1.incrementN();
    assertEquals(2, f2.getCurrentCounter());
    assertEquals(1, f2.getFibonacciCounter());
    System.out.print(f2.getFibonacciCounter());
    FibonacciCounter f22 = f21.incrementN();
    assertEquals(22, f22.getCurrentCounter());
    assertEquals(17711, f22.getFibonacciCounter());
    System.out.print(f22.getFibonacciCounter());

  }

  @Test
  public void decrementCounter() {
    FibonacciCounter f0 = f1.decrementCounter();
    FibonacciCounter f20 = f21.decrementCounter();
    assertEquals(0, f0.getCurrentCounter());
    assertEquals(6765, f20.getFibonacciCounter());

  }

  @Test(expected = IllegalStateException.class)
  public void testIllegalMagnitude1() {
    f0.decrementCounter();

  }
}