/**
 * An interface class represents all the operations to be supported by a Node.
 */
public interface Node extends Polynomial {
  /**
   * Get coefficient.
   *
   * @return coefficient
   */

  int getCoefficient();

  /**
   * Get power.
   *
   * @return power
   */

  int getPower();

  /**
   * Get rest of the list.
   *
   * @return rest of the nodes of a polynomial
   */

  Node getRest();
}




