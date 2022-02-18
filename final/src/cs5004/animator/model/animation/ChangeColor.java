package cs5004.animator.model.animation;

import cs5004.animator.model.FormatString;
import cs5004.animator.model.shape.Shape;

import java.awt.Color;

import static cs5004.animator.model.FormatString.getSVGColor;
import static java.lang.Math.round;

/**
 * @ Program: final
 * @ Date: 2021/12/04
 * @ Description: This class represents the Changing Color Animation to shape object.
 * It extends the AbstractAnimation class and inherits all fields.
 * It overrides the constructor(),getOldStatus(), getNewStatus(), change(double time)
 * getAnimationTag() that are not implemented in the abstract class.
 */
public class ChangeColor extends AbstractAnimation {
  private final Color newColor;
  private final Color oldColor;
  private final AnimationType type;

  /**
   * Construct a change color animation using shape, start time, end time and new color.
   *
   * @param shape    shape of the behavior is working on.
   * @param start    start time of this behavior.
   * @param end      end time of this behavior.
   * @param newColor the new color of change color animation.
   * @throws IllegalArgumentException if the start time or the end time is negative.
   *                                  Or start time is after or equals the end time.
   *                                  Or the type of animation is null.
   *                                  Or the new color is null
   */
  public ChangeColor(Shape shape, int start, int end, Color newColor)
          throws IllegalArgumentException {
    super(shape, start, end);
    if (newColor == null) {
      throw new IllegalArgumentException("New color is NULL.");
    }
    this.newColor = newColor;
    this.oldColor = shape.getColor();
    this.type = AnimationType.CHANG_COLOR;
  }

  /**
   * Get the old color status of the shape.
   *
   * @return the old color status of the shape.
   */
  @Override
  public String getOldStatus() {
    return FormatString.getColorString(this.oldColor);
  }

  /**
   * Get the new color status of the shape.
   *
   * @return the new color status of the shape.
   */
  @Override
  public String getNewStatus() {
    return FormatString.getColorString(this.newColor);
  }

  /**
   * Return the string of changing color.
   *
   * @return the string of changing color.
   */
  @Override
  public String getAnimationTag() {
    return "changes color";
  }

  /**
   * Compute the intermediate state of Change Color at any time.
   * This is called tweening.
   *
   * @param time the given time to get the status during changing color of the shape.
   * @throws IllegalArgumentException if time smaller than the start time of color changing.
   */
  @Override
  public void change(double time) throws IllegalArgumentException {
    if (this.getStartTime() == this.getEndTime() && this.getStartTime() == time) {
      this.getShape().setNewColor(newColor);
    }
    if (time >= this.getEndTime() && !getShape().getColor().equals(newColor)) {
      this.getShape().setNewColor(newColor);
    }

    if (time >= this.getStartTime() && time <= this.getEndTime()) {
      int changeRed = (this.newColor.getRed() - this.oldColor.getRed());
      int changeGreen = (this.newColor.getGreen() - this.oldColor.getGreen());
      int changeBlue = (this.newColor.getBlue() - this.oldColor.getBlue());

      double changeInTime = (time - this.getStartTime())
              / (this.getEndTime() - this.getStartTime());

      float newRed = (float) ((this.oldColor.getRed() + (changeRed * changeInTime)) / 255);
      float newGreen = (float) ((this.oldColor.getGreen() + (changeGreen * changeInTime)) / 255);
      float newBlue = (float) ((this.oldColor.getBlue() + (changeBlue * changeInTime)) / 255);

      this.getShape().setNewColor(new Color(newRed, newGreen, newBlue));
    }
  }

  @Override
  public String getAnimationSVG(double speed, boolean isLoop) {
    double start = (double) this.getStartTime() * 1000 / speed;
    double end = (double) this.getEndTime() * 1000 / speed;
    double duration = end - start;
    String str = "";

    if (!isLoop) {
      str += "<animate attributeType=\"xml\" begin=\""
              + round(start) + "ms\" dur=\""
              + duration + "ms\" attributeName=\"fill\" from=\""
              + getSVGColor(this.oldColor) + "\" to=\""
              + getSVGColor(this.newColor) + "\" fill=\"freeze\" />\n";
      return str;
    }
    str += "<animate attributeType=\"xml\" begin=\"base.begin+"
            + round(start) + "ms\" dur=\""
            + duration + "ms\" attributeName=\"fill\" from=\""
            + getSVGColor(this.oldColor) + "\" to=\""
            + getSVGColor(this.newColor) + "\" fill=\"freeze\" />\n";
    str += "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" "
            + "attributeName=\"fill\" to=\""
            + getSVGColor(this.oldColor) + " fill=\"freeze\" />\n";

    return str;
  }

  @Override
  public Animation cloneAnimation(Shape s) {
    return new ChangeColor(s, this.getStartTime(), this.getEndTime(), this.newColor);
  }

  @Override
  public AnimationType getAnimationType() {
    return this.type;
  }
}
