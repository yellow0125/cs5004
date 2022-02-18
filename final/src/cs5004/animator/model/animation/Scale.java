package cs5004.animator.model.animation;

import cs5004.animator.model.FormatString;
import cs5004.animator.model.shape.Shape;

import static java.lang.Math.round;

/**
 * @ Program: final
 * @ Date: 2021/12/04
 * @ Description: This class represents the Scaling Animation to shape object.
 * It extends the AbstractAnimation class and inherits all fields.
 * It overrides the constructor(),getOldStatus(), getNewStatus(), change(double time)
 * getAnimationTag() that are not implemented in the abstract class.
 */
public class Scale extends AbstractAnimation {
  private final double oldWidth;
  private final double oldHeight;
  private final double newWidth;
  private final double newHeight;
  private final AnimationType type;

  /**
   * Construct a scale animation using shape, start time, end time and new sizes.
   *
   * @param shape     shape of the behavior is working on.
   * @param start     start time of this behavior.
   * @param end       end time of this behavior.
   * @param newWidth  the width of one shape object.
   * @param newHeight the height of one shape object.
   * @throws IllegalArgumentException if the start time or the end time is negative.
   *                                  Or, Start time is after or equals the end time.
   *                                  Or the type of animation is null.
   *                                  Or if wight or height is not positive.
   */
  public Scale(Shape shape, int start, int end, double newWidth,
               double newHeight) throws IllegalArgumentException {
    super(shape, start, end);
    if (newWidth <= 0 || newHeight <= 0) {
      throw new IllegalArgumentException("Wight or height is not positive.");
    }
    this.newWidth = newWidth;
    this.newHeight = newHeight;
    this.oldWidth = shape.getWidth();
    this.oldHeight = shape.getHeight();
    this.type = AnimationType.SCALE;
  }

  /**
   * Get the old width and height status of the shape.
   *
   * @return the old width and height  status of the shape.
   */
  @Override
  public String getOldStatus() {
    return FormatString.getSizesString(this.shape, this.oldWidth, this.oldHeight);
  }

  /**
   * Get the new width and height status of the shape.
   *
   * @return the new width and height  status of the shape.
   */
  @Override
  public String getNewStatus() {
    return FormatString.getSizesString(this.shape, this.newWidth, this.newHeight);
  }

  /**
   * Return the string of scaling.
   *
   * @return the string of scaling.
   */
  @Override
  public String getAnimationTag() {
    return "resizes";
  }

  /**
   * Compute the intermediate state of Scale Animation at any time.
   * This is called tweening.
   *
   * @param time the given time to get the status during scaling of the shape.
   * @throws IllegalArgumentException if time smaller than the start time of scaling.
   */
  @Override
  public void change(double time) throws IllegalArgumentException {
    if (this.getStartTime() == this.getEndTime() && this.getStartTime() == time) {
      this.getShape().resize(this.newWidth, this.newHeight);
    }
    if (time >= this.getEndTime()) {
      this.getShape().resize(this.newWidth, this.newHeight);
    }

    if (time >= this.getStartTime() && time <= this.getEndTime()) {
      double widthChange = this.newWidth - this.oldWidth;
      double heightChange = this.newHeight - this.oldHeight;

      double changeInTime = (time - this.getStartTime())
              / (double) (this.getEndTime() - this.getStartTime());

      double tweenWidth = this.oldWidth + (changeInTime * widthChange);
      double tweenHeight = this.oldHeight + (changeInTime * heightChange);

      this.getShape().resize(tweenWidth, tweenHeight);
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
              + duration + "ms\" attributeName=\""
              + this.getShape().svgWidthTag()
              + "\" from=\""
              + round(this.oldWidth)
              + "\" to=\""
              + round(this.newWidth)
              + "\" fill=\"freeze\" />\n";
      str += "<animate attributeType=\"xml\" begin=\""
              + round(start) + "ms\" dur=\""
              + duration + "ms\" attributeName=\""
              + this.getShape().svgHeightTag()
              + "\" from=\""
              + round(this.oldHeight)
              + "\" to=\""
              + round(this.newHeight)
              + "\" fill=\"freeze\" />\n";
      return str;
    }
    str += "<animate attributeType=\"xml\" begin=\"base.begin+"
            + round(start) + "ms\" dur=\""
            + duration + "ms\" attributeName=\""
            + this.getShape().svgWidthTag()
            + "\" from=\""
            + round(this.oldWidth)
            + "\" to=\""
            + round(this.newWidth)
            + "\" fill=\"freeze\" />\n";
    str += "<animate attributeType=\"xml\" begin=\"base.begin+\""
            + round(start) + "ms\" dur=\""
            + duration + "ms\" attributeName=\""
            + this.getShape().svgHeightTag()
            + "\" from=\""
            + round(this.oldHeight)
            + "\" to=\""
            + round(this.newHeight)
            + "\" fill=\"freeze\" />\n";
    str += "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\""
            + this.getShape().svgWidthTag()
            + "\" to=\""
            + round(this.oldWidth)
            + "\" fill=\"freeze\" />\n"

            + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" attributeName=\""
            + this.getShape().svgHeightTag()
            + "\" to=\""
            + round(this.oldHeight)
            + "\" fill=\"freeze\" />\n";
    return str;
  }

  @Override
  public Animation cloneAnimation(Shape s) {
    return new Scale(shape, this.getStartTime(), this.getEndTime(), this.newWidth, this.newWidth);
  }

  @Override
  public AnimationType getAnimationType() {
    return this.type;
  }
}
