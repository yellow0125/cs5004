package cs5004.animator.view;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.animation.Animation;
import cs5004.animator.model.shape.Shape;

import java.awt.event.ActionListener;

import static cs5004.animator.model.FormatString.getShapeSVG;

/**
 * @ Program: final
 * @ Date: 2021/12/12
 * @ Description: This class represents an SVG view of animation.
 * It's main feature is to produce a svg file that can be played directly in the internet browser.
 */
public class SvgView implements AnimationView {
  private AnimationModel model;
  private double speed;
  private final ViewType viewType;
  private int[] canvas;

  /**
   * Constructor that initializes the SVG view object.
   *
   * <p>It creates an empty animator model to store the shapes and the animations.,
   * that initialization with all parameters of canvas are zero.
   * The actual model needs to be set up by calling the setModel method.
   *
   * <p>The speed is set to 1 by default.
   * The canvas is set to a default bounds.
   */
  public SvgView() {
    this.model = new AnimationModelImpl();
    this.speed = 1.0;
    this.viewType = ViewType.TEXTUAL;
    this.canvas = new int[]{0, 0, 1200, 1000};

  }

  @Override
  public void setModel(AnimationModel m) throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("Model is null");
    }
    this.model = m;
  }

  @Override
  public ViewType getViewType() {
    return this.viewType;
  }

  @Override
  public String showViewDescription(boolean isLoop) {
    double duration = this.getDuration();

    StringBuilder str = new StringBuilder();
    str.append(
            "<svg viewBox=\""
                    + this.model.getCanvasX() + " " + this.model.getCanvasY() + " "
                    + this.model.getCanvasWidth() + " " + this.model.getCanvasHeight()
                    + "\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n");

    if (isLoop) {
      str.append(String.format("<rect>\n <animate id=\"base\" begin=\"0;base.end\" dur=\"%.1fms\""
                      + " attributeName=\"visibility\" from=\"hide\" to=\"hide\"/> \n</rect>\n\n",
              duration));
    }

    for (Shape each : this.model.getShapes()) {
      str.append(getShapeSVG(each, this.speed));

      for (Animation eachA : this.model.getAnimations()) {
        if (each.getName().equals(eachA.getShape().getName())) {
          str.append(eachA.getAnimationSVG(this.speed, isLoop));
        }
      }
      str.append(each.svgEndTag());
    }
    return str.toString() + "</svg>";
  }

  @Override
  public void setViewSpeed(double speed) throws IllegalArgumentException {
    if (speed <= 0) {
      throw new IllegalArgumentException("Speed must be positive.");
    }
    this.speed = speed;
  }

  @Override
  public double getViewSpeed() {
    return this.speed;
  }

  @Override
  public void setCanvas(int x, int y, int width, int height) {
    this.canvas[0] = x;
    this.canvas[1] = y;
    this.canvas[2] = width;
    this.canvas[3] = height;
  }

  @Override
  public void setActionListener(ActionListener listener)
          throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Can't set action listeners for svg view");
  }

  @Override
  public void outputOfView(String file, int speed, boolean isLoop) {
    String content = this.showViewDescription(isLoop);
    TextualView.output(file, content);
  }

  @Override
  public void makeVisible() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Can't make visible for svg view");
  }

  @Override
  public void setFeature(String feature) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Can't set feature for svg view");
  }

  @Override
  public void refresh() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Can't refresh for svg view");
  }

  /**
   * Calculate the duration time of the whole animation.
   *
   * @return the duration time of the whole animation
   */
  private double getDuration() {
    double duration = 0;
    for (Shape each : this.model.getShapes()) {
      if (each.getTimeDisappear() > duration) {
        duration = each.getTimeDisappear();
      }
    }
    return duration * 1000 / this.speed;
  }
}
