package cs5004.animator.view;

import cs5004.animator.model.AnimationModel;
import java.awt.event.ActionListener;

/**
 * This interface represents the type of view for the easy animator application.
 * There are four types of views we want to implement for the project:,
 * A TextualView, SvgView, VisualView and EditorView.
 */
public interface AnimationView {

  /**
   * Set the animator model for the view.
   *
   * @param m the animator model to be set
   * @throws IllegalArgumentException if the model is null
   */
  void setModel(AnimationModel m) throws IllegalArgumentException;

  /**
   * Returns the type of the view.
   *
   * @return the type of the view.
   */
  ViewType getViewType();

  /**
   * Return a string of the description for the view to user.
   *
   * @return a string of the description for the view to user,
   * @throws UnsupportedOperationException the visual view calls the method
   */
  String showViewDescription(boolean isLoop)
          throws UnsupportedOperationException;

  /**
   * Set the speed of the view.
   *
   * @param speed the new speed of the view.
   * @throws IllegalArgumentException      if speed is not positive.
   * @throws UnsupportedOperationException if the visual view calls the method
   */
  void setViewSpeed(double speed) throws IllegalArgumentException, UnsupportedOperationException;

  /**
   * Return the current speed of the view.
   *
   * @return the current speed of the animation.
   * @throws UnsupportedOperationException if the visual view calls the metho
   */
  double getViewSpeed() throws UnsupportedOperationException;

  /**
   * Set canvas box with given x, y, width and height.
   *
   * @param x      set the leftmost x value.
   * @param y      set the down most y value.
   * @param width  width of bounding box.
   * @param height height of bounding box.
   * @throws UnsupportedOperationException if the textual view calls the method
   */
  void setCanvas(int x, int y, int width, int height) throws UnsupportedOperationException;

  /**
   * Set the action listeners for each button. It's only supported by the editor view and the
   * listener is the editor view itself.
   * listenForStart, listenForPause, listenForRestart, listenForSpeedUp, listenForSlowDown,
   * listenForLoop, listenForResume, listenForNewFile, listenForAddShape, removeShapeListener
   * save-as-text-file
   * "save-as-SVG-file"
   *
   * @param listener  the action listener to be set to the buttons.
   * @throws UnsupportedOperationException if views other than the editor view calls the method
   */
  void setActionListener(ActionListener listener) throws UnsupportedOperationException;

  /**
   * Output the string description of the animation to a file.
   * The output of the svg view is a file that can be played in an internet browser.
   *
   * <p>The visual view doesn't support this method.
   *
   * @param file the name of the file that is the target of the output
   * @throws UnsupportedOperationException if the visual view calls the method
   */
  void outputOfView(String file, int speed, boolean isLoop) throws UnsupportedOperationException;

  /**
   * Make the view visible.
   * The textual view and the SVG view don't support this method.
   *
   * @throws UnsupportedOperationException if the textual view or the Svg view calls the method
   */
  void makeVisible() throws UnsupportedOperationException;

  /**
   * Call different methods based on the string passed in. All feature methods are private, and this
   * method is only supported by the editor view. Features include play, pause, resume, restart,
   * export files, load files, and loop back the animation.
   *
   * @param feature the string that determines which feature will be called
   * @throws UnsupportedOperationException if views other than the editor view calls the method
   */
  void setFeature(String feature) throws UnsupportedOperationException;

  /**
   * Refresh the current frame and ask the JFrame to repaint all components. It's not supported
   * by the textual view and the svg view.
   *
   * @throws UnsupportedOperationException if the textual view or the svg view calls the method
   */
  void refresh() throws UnsupportedOperationException;

  // shape at current time.

}
