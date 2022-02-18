package cs5004.animator.view;

/**
 * @ Program: final
 * @ Date: 2021/12/10
 * @ Description: This class represents a factory of view. It initializes different view type
 * according to the string passed in from the EasyAnimator class.
 */

public class ViewFactory {
  /**
   * Create different concrete view types depends on the string passed in.
   *
   * @param type the view type get from the EasyAnimator class.
   * @return the concrete view class that is initialized
   * @throws IllegalArgumentException if the view type is empty or isn't one of the valid view types
   */
  public static AnimationView createView(String type)
          throws IllegalArgumentException {
    switch (type) {
      case "text":
        return new TextualView();
      case "svg":
        return new SvgView();
      case "visual":
        return new VisualView();
      case "playback":
        return new EditorView();
      default:
        throw new IllegalArgumentException("Unknown view type");
    }
  }
}
