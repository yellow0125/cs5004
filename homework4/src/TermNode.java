/**
 * @ Program: homework04
 * @ Date: 2021/10/27
 * @ Description: This class represents the term node in the list of nodes, and
 * the rest nodes of the list.
 */
public class TermNode implements Node {
  private int coefficient; //绝对值必须不是0
  private int power;
  private Node rest;

  /**
   * A Constructor that takes a coefficient, power, and the rest nodes of list
   * represents a term node object.
   *
   * @param coefficient an integer named coefficient of a term.
   * @param power       an integer named power of a term
   * @param rest        the rest of PolynomialNode.
   */
  public TermNode(int coefficient, int power, Node rest) {
    this.coefficient = coefficient;
    this.power = power;
    this.rest = rest;
  }

  @Override
  public int getCoefficient() {
    return this.coefficient;
  }

  @Override
  public int getCoefficient(int power) {
    if (this.power == power) {
      return this.coefficient;
    }
    return this.rest.getCoefficient(power);
  }

  @Override
  public int getPower() {
    return this.power;
  }

  @Override
  public Node getRest() {
    return this.rest;
  }

  @Override
  public Node addTerm(int coefficient, int power) throws IllegalArgumentException {
    if (power < 0) {
      throw new IllegalArgumentException("Negative power is not allowed!");
    }
    if (coefficient == 0) {
      return this;
    }
    if (power > this.power) {
      return new TermNode(coefficient, power, this);
    }
    if (this.power == power) {
      this.coefficient = this.coefficient + coefficient;
      if (this.coefficient == 0) {
        return this.rest;
      }
    } else {
      this.rest = this.rest.addTerm(coefficient, power);
    }
    return this;
  }

  @Override
  public Node removeTerm(int power) {
    if (this.power == power) {
      return this.rest;
    } else {
      this.rest = this.rest.removeTerm(power);
      return this;
    }
  }

  @Override
  public int getDegree() {
    return this.power;
  }

  @Override
  public double evaluate(double number) {
    return this.coefficient * Math.pow(number, power) + this.rest.evaluate(number);
  }

  @Override
  public Polynomial add(Polynomial other) throws IllegalArgumentException {
    if (other instanceof TermNode) {
      String thisOne = this.toString();
      String otherOne = other.toString();
      String resultOne = thisOne + " " + otherOne;
      Polynomial result = new PolynomialImpl(resultOne);
      return result;
    } else {
      throw new IllegalArgumentException("The given polynomial is of a "
              + "different class with this object.");
    }
  }

  @Override
  public String toString() {
    if (this.power == 0) {
      if (this.rest.getCoefficient() > 0) {
        return this.getCoefficient() + " + " + this.rest.toString();
      }
      if (this.rest instanceof EmptyNode) {
        return this.getCoefficient() + this.rest.toString();
      }
      return this.getCoefficient() + " " + this.rest.toString();
    }
    if (this.power != 0) {
      if (this.rest.getCoefficient() > 0) {
        return this.getCoefficient() + "x^" + this.getPower() + " +" + this.rest.toString();
      }
      if (this.rest instanceof EmptyNode) {
        return this.getCoefficient() + "x^" + this.getPower() + this.rest.toString();
      }
      return this.getCoefficient() + "x^" + this.getPower() + " " + this.rest.toString();
    }
    return null;
  }
}
