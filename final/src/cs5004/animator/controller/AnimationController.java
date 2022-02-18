package cs5004.animator.controller;

/**
 * This is an interface represents all operation of a controller.
 * The controller should be given the view and model,
 * and then control should be relinquished to the controller.
 */
public interface AnimationController {

  /**
   * Start an easy animation. based on the input from the EasyAnimator class.
   */
  void playAnimation();

  /**
   * Get the operation log of the controller.
   *
   * @return the operation log of the controller
   */
  String getLog();
}
