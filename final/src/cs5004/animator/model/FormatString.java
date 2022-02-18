package cs5004.animator.model;

import cs5004.animator.model.animation.Animation;
import cs5004.animator.model.shape.Point2D;
import cs5004.animator.model.shape.Shape;

import java.awt.Color;

import static java.lang.Math.round;

/**
 * @ Program: final
 * @ Date: 2021/12/02
 * @ Description: This class represents a bunch of string format methods of  animation model.
 */
public class FormatString {

  /**
   * Return the string of color of a shape object.
   * For example, (1.0,12.0,33.0)
   *
   * @return the string of color of a shape object.
   */
  public static String getColorString(Color c) {
    return String.format("(%.1f,%.1f,%.1f)", c.getRed() / 255.0, c.getGreen() / 255.0,
            c.getBlue() / 255.0);
  }

  /**
   * Return the string of  Hexadecimal color of a shape object in 16.
   * For example, #FFFF00
   *
   * @return the string of color of a shape object.
   */
  public static String getColorString16(Color c) {
    int[] rgb = {c.getRed(), c.getGreen(), c.getBlue()};
    String str = "#";
    for (int i = 0; i < rgb.length; i++) {
      int rgbItem = rgb[i];
      if (rgbItem < 0) {
        rgbItem = 0;
      } else if (rgbItem > 255) {
        rgbItem = 255;
      }
      String[] code = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
      int lCode = rgbItem / 16; //255 / 16 == 15
      int rCode = rgbItem % 16; //255 % 16 == 15
      str += code[lCode] + code[rCode]; //FF
    }
    return str;
  }

  /**
   * Return the string of position of a shape object.
   * For example, (500.0,100.0)
   *
   * @return the string of position of a shape object.
   */
  public static String getPositionString(Point2D p) {
    return String.format("(%.1f,%.1f)", p.getX(), p.getY());
  }

  /**
   * Return the string of size of a shape object with the given size parameters.
   * For example, X radius: 60.0, Y radius: 30.0
   *
   * @param s one shape object.
   * @param i given width of the shape.
   * @param j given height of the shape.
   * @return the string of size of a shape object with the given size parameters.
   */
  public static String getSizesString(Shape s, double i, double j) {
    return String.format("%s%.1f, %s%.1f", s.getWidthTag(), i,
            s.getHeightTag(), j);
  }

  /**
   * Return the string of size of a shape object.
   * For example, X radius: 60.0, Y radius: 30.0
   *
   * @param s one shape object.
   * @return the string of size of a shape object.
   */
  public static String getSizesString(Shape s) {
    return String.format("%s%.1f, %s%.1f", s.getWidthTag(), s.getWidth(),
            s.getHeightTag(), s.getHeight());
  }

  /**
   * Return the string that contains all data of a shape.
   * Name: R
   * Type: rectangle
   * Min corner: (2.0,1.0), Width: 5.0, Height: 3.0, Color: (1.0,1.0,0.0)
   * Appears at t=2
   * Disappears at t=12
   *
   * @return the string that contains all data of a shape
   */
  public static String getShapeDescription(Shape s) {
    return String.format("Name: %s\nType: %s\n"
                    + "%s%s, %s, Color: %s\n"
                    + "Appears at t=%d\nDisappears at t=%d", s.getName(),
            s.getShapeType(), s.getPosTag(), s.getPosString(), s.getSizeString(),
            getColorString(s.getColor()), s.getTimeAppear(), s.getTimeDisappear()) + "\n";
  }

  /**
   * Return a string of description of animation.
   * For example:
   * R changes color from (2.0,4.0,122.0) to (2.0,99.0,122.0) from time t=50 to t=80
   * R moves from (300,300) to (200,200) from time t=70 to t=10
   * R resizes from Width: 200.0, Height: 100.0 to Width: 20.0, Height: 99.0 from time t=51 to t=70
   *
   * @param a the animation to be formatted
   * @return the string that describes an animation of shape object.
   */
  public static String getMotionDescription(Animation a) {
    return a.getShape().getName() + " " + a.getAnimationTag() + " from "
            + a.getOldStatus() + " to " + a.getNewStatus() + " from time t=" + a.getStartTime()
            + " to t=" + a.getEndTime() + "\n";
  }

  /**
   * Return a string of description of creating shape for animator.
   * For example:
   *
   * @param s the given shape.
   * @return the string that describes created of a shape object.
   */
  public static String getCreatDescription(Shape s) {
    String str = "";
    switch (s.getShapeType()) {
      case RECTANGLE:
        str += "Create " + getColorString16(s.getColor()) + " rectangle "
                + s.getName() + " with corner at " + s.getPosString()
                + " , width " + s.getWidth() + " and height "
                + s.getHeight() + "\n";
        break;
      case OVAL:
        str += "Create " + getColorString16(s.getColor()) + " oval "
                + s.getName() + " with center at " + s.getPosString()
                + " , radius " + s.getWidth()
                + " and " + s.getHeight() + "\n";
        break;
      default:
        str += "";
    }
    return str;
  }

  /**
   * Return a string of description of  shape appear and disappear.
   *
   * @param s the given shape.
   * @return the string that describes of shape object.
   */
  public static String getAppearDescription(Shape s) {
    return s.getName() + " appears at time t="
            + s.getTimeAppear() + " and disappears at time t=" + s.getTimeDisappear() + "\n";
  }

  /**
   * Return a string that can be used in the svg file to describe the color of a shape.
   *
   * @param c the color to be formatted
   * @return the string that can be used in the svg file to describe the color of a shape
   */
  public static String getSVGColor(Color c) {
    return "rgb(" + c.getRed() + "," + c.getGreen() + "," + c.getBlue() + ")\" ";
  }

  /**
   * Return a string that can be used in the svg file to describe a shape, including its reference
   * point, name, type, color, dimensions, appear and disappear time.
   *
   * @param s     the shape to be formatted
   * @param speed the speed of the animation
   * @return a string that can be used in the svg file to describe a shape
   */
  public static String getShapeSVG(Shape s, double speed) {
    double visibleTime = s.getTimeAppear() * 1000 / speed;
    String str = "";
    switch (s.getShapeType()) {
      case RECTANGLE:
        str += "<rect id=\"" + s.getName()
                + "\" " + s.svgPosTagX()
                + "=\"" + s.getPosition().getX()
                + "\" " + s.svgPosTagY()
                + "=\"" + s.getPosition().getY()

                + "\" " + s.svgWidthTag()
                + "=\"" + s.getWidth()
                + "\" " + s.svgHeightTag()
                + "=\"" + s.getHeight()

                + "\" fill=\"" + getSVGColor(s.getColor())
                + " visibility=\"visible\" >\n"
                + "<set attributeName=\"visibility\" attributeType=\"XML\"\n"
                + "         to=\"visible\"\n"
                + "         begin=\"" + visibleTime + "ms\" />\n";
        break;
      case OVAL:
        str += "<ellipse id=\"" + s.getName()
                + "\" cx=\"" + round(s.getPosition().getX() + s.getWidth())
                + "\" cy=\"" + round(s.getPosition().getY() + s.getHeight())
                + "\" rx=\"" + round(s.getWidth())
                + "\" ry=\"" + round(s.getHeight())
                + "\" fill=\"" + getSVGColor(s.getColor())
                + " visibility=\"visible\" >\n"
                + ")\" visibility=\"visible\" >\n"
                + "<set attributeName=\"visibility\" attributeType=\"XML\"\n"
                + "         to=\"visible\"\n"
                + "         begin=\"" + visibleTime + "ms\" />\n";
        break;
      default:
        str += "";
    }
    return str;
  }
}
