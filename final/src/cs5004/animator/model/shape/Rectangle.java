package cs5004.animator.model.shape;

import java.awt.Color;

/**
 * @ Program: final
 * @ Date: 2021/12/03
 * @ Description: This class represents a rectangle, it extends the AbstractShape
 * class and inherits all fields.
 * It overrides the copyShape(), toString() that are not implemented in the abstract class.
 */
public class Rectangle extends AbstractShape {

  /**
   * Constructor initializes shape rectangle object with given parameters.
   *
   * @param name          the name of a shape rectangle.
   * @param x             the x-coordinate position of a shape rectangle.
   * @param y             the y-coordinate position of a shape rectangle.
   * @param color         the color of a shape rectangle.
   * @param width         the width of a shape rectangle.
   * @param height        the height of a shape rectangle.
   * @param timeAppear    the time at which the shape rectangle appears in the animation.
   * @param timeDisappear the time at which the shape rectangle disappears in the animation.
   * @throws IllegalArgumentException if one of the name, type, color equals to null,
   *                                  if wight or height is not positive,
   *                                  if time appear or time disappear is negative ,
   *                                  or disappear time smaller than appear time we throw.
   */
  public Rectangle(
          String name, double x, double y, Color color, double width,
          double height, int timeAppear, int timeDisappear) throws IllegalArgumentException {
    super(name, ShapeType.RECTANGLE, x, y, color, width, height, timeAppear, timeDisappear);
  }

  /**
   * Copy a Shape Rectangle and return it. It gives us a new object with the same
   * fields used to initialize the original object.
   *
   * @return a copy of the Shape Rectangle.
   */
  @Override
  public Shape cloneShape() {
    return new Rectangle(
            this.getName(), this.pos.getX(), this.pos.getY(),
            this.getColor(), this.getWidth(), this.getHeight(), this.getTimeAppear(),
            this.getTimeDisappear());
  }

  @Override
  public String svgEndTag() {
    return "</rect>\n";
  }
}
