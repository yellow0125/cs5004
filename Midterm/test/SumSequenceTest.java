import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @ Description:Tests for sum sequence,.
 */

public class SumSequenceTest {

  /**
   * Testing the recursive formulation for sumSequence.
   * This assumes sumSequence.sumSequenceEquation(n)
   * works correctly, and compares it to the results of the
   * recursive formulation.
   */
  @Test
  public void testSumSequence() {

    for (int input = 1; input < 10000; input++) {
      int expected = SumSequence.sumSequenceEquation(input);
      int actual = SumSequence.sumSequenceRecursive(input);

      assertEquals(expected, actual);
    }
  }

}
