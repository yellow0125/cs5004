package cs5004.animator.view;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

/**
 * @ Program: final
 * @ Date: 2021/12/06
 * @ Description: This class represents a textual view of animation.
 * It's main feature is to produce a text file that describes the animation.
 * It doesn't support the refresh and the setCanvas method.
 */
public class TextualView implements AnimationView {
  private AnimationModel model;
  private double speed;
  private final ViewType viewType;

  /**
   * Constructor that initializes the textual view object.
   * It creates an empty animator model to store the shapes and the animations.,
   * that initialization with all parameters of canvas are zero.
   * The actual model needs to be set up by calling the setModel method.
   * The speed is set to 1 by default.
   */
  public TextualView() {
    this.model = new AnimationModelImpl();
    this.speed = 1.0;
    this.viewType = ViewType.TEXTUAL;
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
    return this.model.getModelDescription();
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
  public void setCanvas(int x, int y, int width, int height) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Can't set canvas for textual view");
  }

  @Override
  public void setActionListener(ActionListener listener)
          throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Can't set action listeners for textual view");
  }

  @Override
  public void outputOfView(String file, int speed, boolean isLoop) {
    String content = this.showViewDescription(isLoop);
    output(file, content);
  }

  @Override
  public void makeVisible() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Can't make visible for textual view");
  }

  @Override
  public void setFeature(String feature) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Can't set feature for textual view");
  }

  @Override
  public void refresh() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Can't refresh for textual view");
  }

  /**
   * Output the string description of an animation to a file.
   *
   * @param file    the name of the file to be output
   * @param content the string description of the view.
   */
  static void output(String file, String content) {
    BufferedWriter writer = null;
    try {
      if (file.equalsIgnoreCase("System.out")) {
        writer = new BufferedWriter(new OutputStreamWriter(System.out));
      } else {
        File newFile = new File(file);
        if (!newFile.exists()) {
          newFile.createNewFile();
        }
        writer = new BufferedWriter(new FileWriter(newFile));
      }
      writer.write(content);
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (writer != null) {
          writer.close();
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
