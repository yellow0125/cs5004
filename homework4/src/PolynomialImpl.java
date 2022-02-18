import java.util.Scanner;

/**
 * @ Program: homework04
 * @ Date: 2021/10/23
 * @ Description: This class implements a Polynomial ADT.
 */
public class PolynomialImpl implements Polynomial {
  private Node list;

  /**
   * A constructor with no parameters that creates a polynomial with no terms.
   */
  public PolynomialImpl() {
    this.list = new EmptyNode();
  }

  /**
   * A constructor that takes a polynomial as a string, parses it and creates the polynomial.
   *
   * @param str a given string contains the polynomial, with each term separated by a space.
   */
  public PolynomialImpl(String str) {

    list = new EmptyNode();
    Scanner scan = new Scanner(str);
    scan.useDelimiter(" ");

    while (scan.hasNext()) {
      String subStr = scan.next();
      if (subStr.contains("x^")) {
        String[] number;
        number = subStr.split("x\\^");
        int coefficient = Integer.parseInt(number[0]);
        int power = Integer.parseInt(number[1]);
        list = list.addTerm(coefficient, power);
      } else {
        int coefficient = Integer.parseInt(subStr);
        list = list.addTerm(coefficient, 0);
      }
    }
    scan.close();
  }

  @Override
  public Node addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Negative power is not allowed!");
    }
    if (coefficient != 0) {
      this.list = this.list.addTerm(coefficient, power);
    }
    return this.list;
  }

  @Override
  public Node removeTerm(int power) {
    return this.list = this.list.removeTerm(power);
  }

  @Override
  public int getDegree() {
    return this.list.getDegree();
  }

  @Override
  public int getCoefficient(int power) {
    return this.list.getCoefficient(power);
  }

  @Override
  public double evaluate(double number) {
    return this.list.evaluate(number);
  }

  @Override
  public Polynomial add(Polynomial other) throws IllegalArgumentException {
    if (other instanceof PolynomialImpl) {
      String thisOne = this.list.toString();
      String otherOne = other.toString();
      String resultOne = thisOne + " " + otherOne;
      Polynomial result = new PolynomialImpl(resultOne);
      return result;
    } else {
      throw new IllegalArgumentException(" TThe given polynomial is of a "
              + "different class with this object.");
    }
  }

  @Override
  public String toString() {
    if (this.list instanceof EmptyNode) {
      return "0";
    }
    return this.list.toString();
  }
}
