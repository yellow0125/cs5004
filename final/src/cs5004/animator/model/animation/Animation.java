package cs5004.animator.model.animation;

import cs5004.animator.model.shape.Shape;

/**
 * This is an interface for all animation for the shape object.
 * It will be implemented by abstract class animation.
 */
public interface Animation {

  /**
   * Return the shape of the animation.
   *
   * @return the shape of the animation.
   */
  Shape getShape();

  /**
   * Return the start time of the animation.
   *
   * @return the start time of the animation.
   */
  int getStartTime();

  /**
   * Return the end time of the animation.
   *
   * @return the end time of the animation.
   */
  int getEndTime();

  /**
   * Get the old status of the shape.
   *
   * @return the old status of the shape.
   */
  String getOldStatus();

  /**
   * Get the new status of the shape.
   *
   * @return the new status of the shape.
   */
  String getNewStatus();

  /**
   * Return the type of the animation.
   *
   * @return the type of the animation.
   */
  AnimationType getAnimationType();

  /**
   * Return the string of the animation type.
   *
   * @return the string of the animation type.
   */
  String getAnimationTag();

  /**
   * Change the shape by the animation for controller at current time.
   * Compute the intermediate state of Animation at any time
   *
   * @param time the current time to use change the shape.
   * @throws IllegalArgumentException if time smaller than the start time of shape changing.
   */
  void change(double time) throws IllegalArgumentException;

  /**
   * Return a string of description of animation.
   * For example:
   * R changes color from (2.0,4.0,122.0) to (2.0,99.0,122.0) from time t=50 to t=80
   * R moves from (300,300) to (200,200) from time t=70 to t=10
   * R resizes from Width: 200.0, Height: 100.0 to Width: 20.0, Height: 99.0 from time t=51 to t=70
   *
   * @return the string that describes an animation of shape object.
   */
  String getMotionDescription();

  /**
   * Return a string that can be used in the svg file to describe an animation.
   *
   * @param speed  the speed of the animation
   * @param isLoop whether the animation loops back after finish
   * @return a string that can be used in the svg file to describe an animation
   */
  String getAnimationSVG(double speed, boolean isLoop);

  /**
   * Copy an animation object and return it. It gives us a new object with the same
   * fields used to initialize the original object.
   *
   * @return a copy of the animation object
   */
  Animation cloneAnimation(Shape s);
}
