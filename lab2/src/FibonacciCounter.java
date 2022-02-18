/**
 * lab2
 * 2021/9/19
 * @ Description: This class represents a FibonacciCounter which is a way to
 * determine the nth fibbonacci in the sequence.
 */
public class FibonacciCounter {
  private int counter;

  public FibonacciCounter(int initialCounter) {
    counter = initialCounter;
  }

  /**
   * Return a new FibonacciCounter that has a counter of one more that this
   * FibonacciCounter.
   *
   * @return A increment FibonacciCounter.
   */

  public FibonacciCounter incrementN() {
    return new FibonacciCounter(counter + 1);
  }

  /**
   * Return a new FibonacciCounter that has a counter of one less that this
   * FibonacciCounter.
   *
   * @return A decremented FibonacciCounter.
   * @throws IllegalStateException when trying to decrement below 1
   */
  public FibonacciCounter decrementCounter() {
    if (this.counter < 1) {
      throw new IllegalStateException();
    }
    return new FibonacciCounter(this.counter - 1);
  }

  /**
   * Return the current count of the counter.
   *
   * @return he current count of the counter.
   */

  public int getCurrentCounter() {
    return this.counter;
  }

  /**
   * Returns the Fibonacci number corresponding to the current count.
   *
   * @return: the Fibonacci number corresponding to the current count.
   */

  public int getFibonacciCounter() {
    int currentCounter = this.counter;
    double i = Math.sqrt(5);
    double num1 = (1 + i) / 2;
    double num2 = (1 - i) / 2;
    return (int) Math.floor((Math.pow(num1, currentCounter)
            - Math.pow(num2, currentCounter)) / i);

  }

}

