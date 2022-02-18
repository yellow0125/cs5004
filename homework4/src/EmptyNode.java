/**
 * @ Program: homework04
 * @ Date: 2021/10/27
 * @ Description: This class represents an empty node in the list of nodes
 * of polynomial ADT implementation.
 */
public class EmptyNode implements Node {

  /**
   * Constructor that takes a null representing empty node.
   */
  public EmptyNode() {
    //System.out.println("this is an empty node.");
  }

  @Override
  public int getCoefficient() {
    return 0;
  }

  @Override
  public int getCoefficient(int power) {
    return 0;
  }

  @Override
  public int getPower() {
    return 0;
  }

  @Override
  public Node getRest() {
    return this;
  }

  @Override
  public Node addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Negative power is not allowed!");
    }
    if (coefficient == 0) {
      return this;
    }
    return new TermNode(coefficient, power, this);
  }

  @Override
  public Node removeTerm(int power) {
    return this;
  }

  @Override
  public int getDegree() {
    return 0;
  }

  @Override
  public double evaluate(double number) {
    return 0;
  }

  @Override
  public Polynomial add(Polynomial other) throws IllegalArgumentException {
    if (other instanceof EmptyNode) {
      return other;
    } else {
      throw new IllegalArgumentException("The given polynomial is of a "
              + "different class with this object.");
    }
  }

  @Override
  public String toString() {
    return "";
  }
}
