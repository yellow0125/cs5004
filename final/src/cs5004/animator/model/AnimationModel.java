package cs5004.animator.model;

import cs5004.animator.model.animation.Animation;
import cs5004.animator.model.shape.Shape;
import java.util.List;

/**
 * This interface represents the all operations offered by the easy animator model.
 * One object of the model represents the operation for a 2D shape of easy animator.
 * The shapes and animations are stored in array lists during the animation model.
 */
public interface AnimationModel {
  /**
   * Add a shape object to the shape list.
   * this function is also for the animation appear.
   *
   * @param s shape object
   * @throws IllegalArgumentException if the given shape is already in the list
   *                                  based the shape name.
   */
  void addShape(Shape s) throws IllegalArgumentException;

  /**
   * Remove a shape object to the shape list.
   * this function is for animation disappear.
   *
   * @param s shape object
   * @throws IllegalArgumentException if the given shape is not in the list
   *                                  based the shape name.
   */
  void removeShape(Shape s) throws IllegalArgumentException;

  /**
   * Add a new animation by start time.
   *
   * @param a new animation.
   * @throws IllegalArgumentException if the animation is overlap timing with
   *                                  the other one already exist.
   */
  void addAnimation(Animation a) throws IllegalArgumentException;

  /**
   * return the list storing shapes.
   *
   * @return the list storing shapes.
   */
  List<Shape> getShapes();

  /**
   * return the list storing behaviors.
   *
   * @return the list toring behaviors.
   */
  List<Animation> getAnimations();

  /**
   * Set bounding box with given x, y, width and height.
   *
   * @param x      set the leftmost x value.
   * @param y      set the down most y value.
   * @param width  width of bounding box.
   * @param height height of bounding box.
   */
  void setCanvas(int x, int y, int width, int height);

  /**
   * Return the leftmost x value of bounding box.
   *
   * @return the leftmost x value of bounding box.
   */
  int getCanvasX();

  /**
   * Return the down most y value of bounding box.
   *
   * @return the down most y value of bounding box.
   */
  int getCanvasY();

  /**
   * Return the width value of bounding box.
   *
   * @return width value of bounding box.
   */
  int getCanvasWidth();

  /**
   * Return height value of bounding box.
   *
   * @return height value of bounding box.
   */
  int getCanvasHeight();

  /**
   * returns a list of created shapes and list of animations in text form.
   * for example, creat a canvas with...  creat an oval R with... R moves from...
   *
   * @return a string of shapes and animations created
   */
  String getModelDescription();

  /**
   * Return the minimum start time of the shape list and return it.
   * When first shape appearing.
   *
   * @return the minimum start time of the shape list
   */
  int getMinTick();

  /**
   * Return the maximum end time of final animation time.
   * When last shape disappearing
   *
   * @return the final animation time as an int
   */
  int getMaxTick();

  /**
   * Return the minimum start time of the animation list and return it.
   * When first animation start.
   *
   * @return the minimum start time of the shape list
   */
  int getMinAnimationTime();

  /**
   * Return the maximum end time of final animation time.
   * When last animation end.
   *
   * @return the final animation time as an int
   */
  int getMaxAnimationTime();

  /**
   * Return a string represent the log of a shapes by given shape name.
   *
   * @param name The name of the shape.
   * @return a string represent the log of a shapes by given shape name.
   * @throws IllegalArgumentException if name is null.
   */
  String showShapeLog(String name) throws IllegalArgumentException;

  /**
   * Returns string of animation description at current time tick.
   *
   * @param time the given time.
   * @return a string of animation description at current time tick.
   * @throws IllegalArgumentException if time smaller than mintick or bigger than maxtick.
   */
  String getAnimationAtTick(int time) throws IllegalArgumentException;

  /**
   * Copy a model object and return it. It gives us a new object with the same
   * fields used to initialize the original object.
   *
   * @return a copy of the model object
   */
  AnimationModel cloneModel();

  /**
   * Return a string contains the information of a shapes by given shape name.
   *
   * @param name The name of the shape.
   * @return a string represent the log of a shapes by given shape name.
   * @throws IllegalArgumentException if name is null.
   */
  String showShapeInfo(String name) throws IllegalArgumentException;

}
