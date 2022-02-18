package cs5004.animator.model.animation;

import cs5004.animator.model.FormatString;
import cs5004.animator.model.shape.Point2D;
import cs5004.animator.model.shape.Shape;

import static java.lang.Math.round;

/**
 * @ Program: final
 * @ Date: 2021/12/04
 * @ Description: This class represents the Moving Animation to shape object.
 * It extends the AbstractAnimation class and inherits all fields.
 * It overrides the constructor(),getOldStatus(), getNewStatus(), change(double time)
 * getAnimationTag() that are not implemented in the abstract class.
 */
public class Move extends AbstractAnimation {
  private final Point2D oldPos;
  private final Point2D newPos;
  private final AnimationType type;

  /**
   * Construct a move animation using shape, start time, end time and new position.
   *
   * @param shape  shape of the behavior is working on.
   * @param start  start time of this behavior.
   * @param end    end time of this behavior.
   * @param newPos the new position for shape object move to.
   * @throws IllegalArgumentException if the start time or the end time is negative.
   *                                  Or, Start time is after or equals the end time.
   *                                  Or the type of animation is null.
   *                                  Or the new position is null.
   */
  public Move(Shape shape, int start, int end, Point2D oldPos, Point2D newPos)
          throws IllegalArgumentException {
    super(shape, start, end);
    if (newPos == null) {
      throw new IllegalArgumentException("New position is NULL.");
    }
    this.oldPos = oldPos;
    this.newPos = newPos;
    this.type = AnimationType.MOVE;

  }

  /**
   * Get the old position status of the shape.
   *
   * @return the old position status of the shape.
   */
  @Override
  public String getOldStatus() {
    return FormatString.getPositionString(oldPos);
  }

  /**
   * Get the new position status of the shape.
   *
   * @return the new position status of the shape.
   */
  @Override
  public String getNewStatus() {
    return FormatString.getPositionString(newPos);
  }

  /**
   * Return the string of moving.
   *
   * @return the string of moving.
   */
  @Override
  public String getAnimationTag() {
    return "moves";
  }

  /**
   * Compute the intermediate state of Move Animation at any time.
   * This is called tweening.
   *
   * @param time the given time to get the status during moving of the shape.
   * @throws IllegalArgumentException if time smaller than the start time of moving.
   */
  @Override
  public void change(double time) throws IllegalArgumentException {
    if (this.getStartTime() == this.getEndTime() && this.getStartTime() == time) {
      this.getShape().setNewPos(newPos);
    }
    if (time >= this.getEndTime() && !getShape().getPosition().equals(newPos)) {
      this.getShape().setNewPos(newPos);
    }
    if (time >= this.getStartTime() && time <= this.getEndTime()) {
      double changeX = this.newPos.getX() - this.oldPos.getX();
      double changeY = this.newPos.getY() - this.oldPos.getY();

      double changeInTime = (time - this.getStartTime())
              / (double) (this.getEndTime() - this.getStartTime());
      double newX = this.oldPos.getX() + (changeX * changeInTime);
      double newY = this.oldPos.getY() + (changeY * changeInTime);

      this.getShape().setNewPos(new Point2D(newX, newY));
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
              + this.getShape().svgPosTagX()
              + "\" from=\""
              + round(this.oldPos.getX() + this.getShape().getWidth())
              + "\" to=\""
              + round(this.newPos.getX() + this.getShape().getWidth())
              + "\" fill=\"freeze\" />\n";
      str += "<animate attributeType=\"xml\" begin=\""
              + round(start) + "ms\" dur=\""
              + duration + "ms\" attributeName=\""
              + this.getShape().svgPosTagY()
              + "\" from=\""
              + round(this.oldPos.getY() + this.getShape().getHeight())
              + "\" to=\""
              + round(this.newPos.getY() + this.getShape().getHeight())
              + "\" fill=\"freeze\" />\n";
      return str;
    }
    str += "<animate attributeType=\"xml\" begin=\""
            + round(start) + "ms\" dur=\""
            + duration + "ms\" attributeName=\""
            + this.getShape().svgPosTagX()
            + "\" from=\""
            + this.oldPos.getX() + "\" to=\""
            + this.newPos.getX() + "\" fill=\"freeze\" />\n";
    str += "<animate attributeType=\"xml\" begin=\""
            + round(start) + "ms\" dur=\""
            + duration + "ms\" attributeName=\""
            + this.getShape().svgPosTagY()
            + "\" from=\""
            + this.oldPos.getY() + "\" to=\""
            + this.newPos.getY() + "\" fill=\"freeze\" />\n";
    return str;
  }

  @Override
  public Animation cloneAnimation(Shape s) {
    return new Move(shape, this.getStartTime(), this.getEndTime(), this.oldPos, this.newPos);
  }

  @Override
  public AnimationType getAnimationType() {
    return this.type;
  }
}

