import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * A testing class for pay check class.
 */
public class PayCheckTest {
  private PayCheck i;
  private PayCheck j;

  @Before
  public void setUp() {
    this.i = new PayCheck("Zhou", 32.33541, 33.22);
    this.j = new PayCheck("Lily", 20, 50);
  }

  @Test
  public void getTotalPay() {
    assertEquals(1074.18, this.i.getTotalPay(), 0.01);
    assertEquals(1100, this.j.getTotalPay(), 0.01);

  }

  @Test
  public void testToString() {
    assertEquals("$1074.18",  this.i.toString());
    assertEquals("$1100.00",  this.j.toString());

  }
}