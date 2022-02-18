package cs5004.animator.model.shape;

import java.awt.Color;

/**
 * This is an interface for all 2D shapes. It will be  implemented by abstract class shape.
 */
public interface Shape {

  /**
   * Returns the name of the shape object.
   *
   * @return the name of the shape object.
   */
  String getName();

  /**
   * Returns the color of the shape object.
   *
   * @return the color of the shape object.
   */
  Color getColor();

  /**
   * Returns the type of the shape object.
   *
   * @return the type of the shape object.
   */
  ShapeType getShapeType();

  /**
   * Returns the width of the shape object.
   *
   * @return the width of the shape object.
   */
  double getWidth();

  /**
   * Return the string tag for width For example, an oval's width tag is "x radius: ".
   *
   * @return the string tag for width
   */
  String getWidthTag();

  /**
   * Returns the height of the shape object.
   *
   * @return the height of the shape object.
   */
  double getHeight();

  /**
   * Return the string tag for height For example, an oval's height tag is "y radius: ".
   *
   * @return the string tag for height.
   */
  String getHeightTag();

  /**
   * Return the string parameters of width and height .
   * For example:  X radius: 60.0, Y radius: 30.0 or Width: 50.0, Height: 100.0
   *
   * @return the string tag for height.
   */
  String getSizeString();

  /**
   * Returns the time appear of the shape object.
   *
   * @return the time appear of the shape object.
   */
  int getTimeAppear();

  /**
   * Returns the time disappear of the shape object.
   *
   * @return the time disappear of the shape object.
   */
  int getTimeDisappear();

  /**
   * Returns the current position of the shape object.
   *
   * @return the current position of the shape object.
   */
  Point2D getPosition();

  /**
   * Return the string tag for position For example, an oval's position tag is "Center:".
   *
   * @return the string tag for position.
   */
  String getPosTag();

  /**
   * Return the position string, For example, an oval's position tag is "(500.0,100.0)".
   *
   * @return the string tag for position.
   */
  String getPosString();

  /**
   * Computes and returns the area of this shape.
   *
   * @return the area of the shape
   */
  double area();

  /**
   * Computes and returns the perimeter of this shape.
   *
   * @return the perimeter of the shape
   */
  double perimeter();

  /**
   * Copy a Shape object and return it. It gives us a new object with the same
   * fields used to initialize the original object.
   *
   * @return a copy of the Shape object
   */
  Shape cloneShape();

  /**
   * Create a shape of the same kind as this one, resized in width and height
   * by the provided factor.
   *
   * @param factor factor of resizing.
   * @throws IllegalArgumentException if factor is negative.
   */
  void resize(double factor) throws IllegalArgumentException;

  /**
   * Create a shape of the same kind as this one, resized
   * in width to the provided value of new width.
   *
   * @param newWidth  new value of resizing width.
   * @param newHeight new value of resizing height.
   * @throws IllegalArgumentException if newWidth or newHeight is negative.
   */
  void resize(double newWidth, double newHeight) throws IllegalArgumentException;

  /**
   * Set a new position for the shape object with the given x,y new,coordinate of the point.
   *
   * @param pos new position of the shape object.
   */
  void setNewPos(Point2D pos);

  /**
   * Set a new color for the shape object with the given color.
   *
   * @param color new color of the shape object.
   * @throws IllegalArgumentException if given new color is null, throw.
   */
  void setNewColor(Color color) throws IllegalArgumentException;

  /**
   * Return the string that contains all data of a shape.
   * Example:
   * Shapes:
   * Name: Rectangle1
   * Type: rectangle
   * Min corner: (2.0,1.0), Width: 5.0, Height: 3.0, Color: (1.0,1.0,0.0)
   * Appears at t=2
   * Disappears at t=12
   *
   * @return the string that contains all data of a shape
   */
  String getShapeDescription();

  /**
   * Return the created string description.
   * For example, Create red rectangle R with corner at (200,200), width 50 and height 10
   *
   * @return the created string description,
   */
  String getCreatDescription();

  /**
   * Return the appearing and disappearing string description.
   * For example, R appears at time t=1 and disappears at time t=100
   *
   * @return the appearing and disappearing string description,
   */
  String getAppearDescription();

  /**
   * Return the string tag for svg file end depends on different shape type.
   *
   * @return the string tag for svg file end depends on different shape type.
   */
  String svgEndTag();

  /**
   * Return the string tag for svg file end depends on different shape type.
   *
   * @return the string tag for svg file end depends on different shape type.
   */
  String svgPosTagX();

  /**
   * Return the string tag for svg file end depends on different shape type.
   *
   * @return the string tag for svg file end depends on different shape type.
   */
  String svgPosTagY();

  /**
   * Return the string tag for svg file end depends on different shape type.
   *
   * @return the string tag for svg file end depends on different shape type.
   */
  String svgWidthTag();

  /**
   * Return the string tag for svg file end depends on different shape type.
   *
   * @return the string tag for svg file end depends on different shape type.
   */
  String svgHeightTag();

}



