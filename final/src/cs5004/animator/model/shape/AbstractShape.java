package cs5004.animator.model.shape;

import cs5004.animator.model.FormatString;
import java.awt.Color;

/**
 * @ Program: final
 * @ Date: 2021/12/02
 * @ Description: This is an abstract class that implements the Shape interface.
 * AbstractShape is an abstract class for all different types of shape
 * Currently We represent 2D shapes only IN rectangles and ovals.
 */

public abstract class AbstractShape implements Shape {
  protected String name;
  protected ShapeType type;
  protected Point2D pos;
  protected Color color;
  protected double width;
  protected double height;

  protected int timeAppear;
  protected int timeDisappear;

  /**
   * Constructor initializes one shape object with given parameters.
   *
   * @param name          the name of one shape object.
   * @param type          the shape type of one shape object.
   * @param x             the x-coordinate position of one shape object.
   * @param y             the y-coordinate position of one shape object.
   * @param color         the color of one shape object
   * @param width         the width of one shape object.
   * @param height        the height of one shape object.
   * @param timeAppear    the time at which the shape appears in the animation.
   * @param timeDisappear the time at which the shape disappears in the animation.
   * @throws IllegalArgumentException if one of the name, type, color equals to null,
   *                                  if wight or height is not positive,
   *                                  if time appear or time disappear is negative ,
   *                                  or disappear time smaller than appear time we throw.
   */
  public AbstractShape(
          String name, ShapeType type, double x, double y, Color color, double width,
          double height, int timeAppear, int timeDisappear) throws IllegalArgumentException {
    if (name == null) {
      throw new IllegalArgumentException("Name is null");
    }
    if (type == null) {
      throw new IllegalArgumentException("Shape type is null");
    }
    if (color == null) {
      throw new IllegalArgumentException("Color is null");
    }
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width or height must be positive.");
    }
    if (timeAppear < 0 || timeDisappear < 0) {
      throw new IllegalArgumentException("Time can not be negative.");
    }

    if (timeAppear > timeDisappear) {
      throw new IllegalArgumentException("Disappear Time Error.");
    }
    this.name = name;
    this.type = type;
    this.pos = new Point2D(x, y);
    this.color = color;
    this.width = width;
    this.height = height;
    this.timeAppear = timeAppear;
    this.timeDisappear = timeDisappear;

  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public ShapeType getShapeType() {
    return this.type;
  }

  @Override
  public double getWidth() {
    return this.width;
  }

  @Override
  public String getWidthTag() {
    return "Width: ";
  }

  @Override
  public double getHeight() {
    return this.height;
  }

  @Override
  public String getHeightTag() {
    return "Height: ";
  }

  @Override
  public String getSizeString() {
    return FormatString.getSizesString(this);
  }

  @Override
  public int getTimeAppear() {
    return this.timeAppear;
  }

  @Override
  public int getTimeDisappear() {
    return this.timeDisappear;
  }

  @Override
  public Point2D getPosition() {
    return this.pos;
  }

  @Override
  public String getPosTag() {
    return "Min Corner: ";
  }

  @Override
  public String getPosString() {
    return FormatString.getPositionString(this.pos);
  }

  @Override
  public double area() {
    return this.width * this.height;
  }

  @Override
  public double perimeter() {
    return 2 * (this.width + this.height);
  }

  @Override
  public void resize(double factor) throws IllegalArgumentException {
    if (factor <= 0) {
      throw new IllegalArgumentException("Resize factor must be positive");
    }
    this.width = this.width * factor;
    this.height = this.height * factor;
  }

  @Override
  public void resize(double newWidth, double newHeight) throws IllegalArgumentException {
    if (newWidth <= 0 || newHeight <= 0) {
      throw new IllegalArgumentException("Resize new width or height must be positive");
    }
    this.width = newWidth;
    this.height = newHeight;
  }

  @Override
  public void setNewPos(Point2D pos) {
    this.pos = pos;
  }

  @Override
  public void setNewColor(Color color) throws IllegalArgumentException {
    if (color == null) {
      throw new IllegalArgumentException("New color is null");
    }
    this.color = color;
  }

  @Override
  public String getShapeDescription() {
    return FormatString.getShapeDescription(this);
  }

  @Override
  public String getCreatDescription() {
    return FormatString.getCreatDescription(this);
  }

  @Override
  public String getAppearDescription() {
    return FormatString.getAppearDescription(this);
  }

  @Override
  public String svgPosTagX() {
    return "x";
  }

  @Override
  public String svgPosTagY() {
    return "y";
  }

  @Override
  public String svgWidthTag() {
    return "width";
  }

  @Override
  public String svgHeightTag() {
    return "height";
  }
}
