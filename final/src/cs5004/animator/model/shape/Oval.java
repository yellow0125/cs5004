package cs5004.animator.model.shape;

import java.awt.Color;

/**
 * @ Program: final
 * @ Date: 2021/12/03
 * @ Description: This class represents an oval, it extends the AbstractShape
 * class and inherits all fields.
 * It overrides getWidthTag(), getHeightTag(), getPosTag(), the area() perimeter();
 * and copyShape() that is not implemented in the abstract class.
 */
public class Oval extends AbstractShape {

  /**
   * Constructor initializes one Shape Oval with given parameters.
   *
   * @param name          the name of one Shape Oval.
   * @param x             the x-coordinate position of one Shape Oval.
   * @param y             the y-coordinate position of one Shape Oval.
   * @param color         the color of one Shape Oval.
   * @param radius        the x-Radius of one Shape Oval.
   * @param radius2       the y-Radius of one Shape Oval.
   * @param timeAppear    the time at which the Shape Oval appears in the animation.
   * @param timeDisappear the time at which the Shape Oval disappears in the animation.
   * @throws IllegalArgumentException if one of the name, type, color equals to null,
   *                                  if wight or height is not positive,
   *                                  if time appear or time disappear is negative ,
   *                                  or disappear time smaller than appear time we throw.
   */
  public Oval(String name, double x, double y, Color color, double radius,
              double radius2, int timeAppear, int timeDisappear) throws IllegalArgumentException {
    super(name, ShapeType.OVAL, x, y, color, radius, radius2, timeAppear, timeDisappear);
  }

  /**
   * Copy a Shape Oval and return it. It gives us a new object with the same
   * fields used to initialize the original object.
   *
   * @return a copy of the Shape Oval.
   */
  @Override
  public Shape cloneShape() {
    return new Oval(
            this.getName(), this.pos.getX(), this.pos.getY(),
            this.getColor(), this.getWidth(), this.getHeight(), this.getTimeAppear(),
            this.getTimeDisappear());
  }

  /**
   * Return the string tag for width, an oval's width tag is "x radius: ".
   *
   * @return the string tag for width
   */
  @Override
  public String getWidthTag() {
    return "X radius: ";
  }

  /**
   * Return the string tag for height, an oval's height tag is "y radius: ".
   *
   * @return the string tag for height.
   */
  @Override
  public String getHeightTag() {
    return "Y radius: ";
  }

  /**
   * Return the string tag for position, an oval's position tag is "Center:".
   *
   * @return the string tag for position.
   */
  @Override
  public String getPosTag() {
    return "Center: ";
  }

  /**
   * Computes and returns the area of oval shape.
   * pi * x-radius * y-radius.
   *
   * @return the area of the oval shape.
   */
  @Override
  public double area() {
    return Math.PI * this.width * this.height;
  }

  /**
   * Computes and returns the perimeter of this shape.
   * Using the square mean C = 2 *pi * sqrt(a^2/2 + b^2/2 )
   *
   * @return the perimeter of the shape
   */
  @Override
  public double perimeter() {
    return 2 * Math.PI * Math.sqrt((Math.pow(this.width, 2) + Math.pow(this.height, 2)) / 2);
  }

  @Override
  public String svgEndTag() {
    return "</ellipse>\n";
  }

  @Override
  public String svgPosTagX() {
    return "cx";
  }

  @Override
  public String svgPosTagY() {
    return "cy";
  }

  @Override
  public String svgWidthTag() {
    return "rx";
  }

  @Override
  public String svgHeightTag() {
    return "ry";
  }
}
