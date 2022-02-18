package cs5004.animator.model.animation;

import cs5004.animator.model.FormatString;
import cs5004.animator.model.shape.Shape;

/**
 * @ Program: final
 * @ Date: 2021/12/04
 * @ Description: This is an abstract class that implements the Animation interface.
 * AbstractAnimation is an abstract class for all different types of animation.
 * Current we will concentrate only on moving, scaling and changing color.
 */
public abstract class AbstractAnimation implements Animation {
  protected Shape shape;
  protected int start;
  protected int end;

  /**
   * Construct an animation object using shape, start time, end time and type.
   *
   * @param shape shape of the behavior is working on.
   * @param start start time of this behavior.
   * @param end   end time of this behavior.
   * @throws IllegalArgumentException if the start time or the end time is negative.
   *                                  Or, Start time is after or equals the end time.
   *                                  Or the type of animation is null.
   */
  public AbstractAnimation(Shape shape, int start, int end)
          throws IllegalArgumentException {
    if (start < 0 || end < 0) {
      throw new IllegalArgumentException("Start or end time can not be negative");
    }
    if (end < start) {
      throw new IllegalArgumentException("End time can not be smaller than start.");
    }

    this.shape = shape;
    this.start = start;
    this.end = end;
  }

  @Override
  public Shape getShape() {
    return this.shape;
  }

  @Override
  public int getStartTime() {
    return this.start;
  }

  @Override
  public int getEndTime() {
    return this.end;
  }

  @Override
  public String getMotionDescription() {
    return FormatString.getMotionDescription(this);
  }
}
