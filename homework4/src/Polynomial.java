/**
 * An interface class represents all the operations to be supported by a Polynomial.
 */
public interface Polynomial {

  /**
   * The method adds new term to the polynomial with given coefficient and power.
   *
   * @param coefficient an integer named coefficient of a term.
   * @param power       an integer named power of a term
   * @return the first node of the list
   * @throws IllegalArgumentException if a negative power is passed in
   */
  Node addTerm(int coefficient, int power) throws IllegalArgumentException;

  /**
   * The method removes all terms in the polynomial with given power.
   *
   * @param power an integer named power of a term.
   * @return the first node of the list
   */
  Node removeTerm(int power);

  /**
   * Get the degree of a polynomial, which is the highest power of the variable in a term.
   *
   * @return an integer which is the degree of a polynomial.
   */
  int getDegree();

  /**
   * Get the coefficient for the term with given power of the polynomial.
   *
   * @param power integer named power of a term
   * @return a coefficient for the term with given power
   */

  int getCoefficient(int power);

  /**
   * Evaluate the polynomial for given value of the variable.
   *
   * @param number a double-precision decimal number
   * @return a double-precision result of the polynomial.
   */

  double evaluate(double number);

  /**
   * Add the polynomial with a given one.
   *
   * @param other another polynomial object.
   * @return a polynomial obtained by adding the two polynomials.
   * @throws IllegalArgumentException if the given Polynomial is not of the same
   *                                  concrete class as this object,throw it.
   */
  Polynomial add(Polynomial other) throws IllegalArgumentException;

}
