package cs5004.animator.model.shape;

/**
 * This class represents a 2D point of a shape object. This point is denoted in Cartesian
 * coordinates as (x,y).
 */
public class Point2D {
  private double x;
  private double y;

  /**
   * Construct a 2d point with the given coordinates.
   *
   * @param x the x-coordinate of this point
   * @param y the y-coordinate of this point
   */
  public Point2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Compute and return the Euclidean distance of this point to the origin.
   *
   * @return the euclidean distance
   */

  public double distToOrigin() {
    return Math.sqrt(x * x + y * y);
  }

  /**
   * Return the x-coordinate of this point.
   *
   * @return x-coordinate of this point
   */
  public double getX() {
    return this.x;
  }

  /**
   * Return the y-coordinate of this point.
   *
   * @return y-coordinate of this point
   */
  public double getY() {
    return this.y;
  }
}