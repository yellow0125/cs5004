package cs5004.animator.view;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.animation.Animation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

/**
 * @ Program: final
 * @ Date: 2021/12/12
 * @ Description: This class represents a visual view of animation.
 * It uses JFrame and JPanel to create a window to play the animation.
 * It only supports the refresh, setCanvas, setShapeList, and the getShapes methods.
 */

public class VisualView extends JFrame implements AnimationView, ActionListener {
  private VisualPanel panel;
  private AnimationModel model;
  private ViewType viewType;
  private double speed;

  private double currentTick = 0;
  private int maxTick = 100;
  private boolean first = true;
  private Timer timer;

  /**
   * Constructor that initializes the visual view object.
   * It creates an empty animator model to store the shapes and the animations.,
   * that initialization with all parameters of canvas are zero.
   * The actual model needs to be set up by calling the setModel method.
   *
   * <p>It sets up the size and title and other components in the JFrame.
   * The speed is set to 1 by default.
   */

  public VisualView() {
    super();
    this.model = new AnimationModelImpl();
    this.viewType = ViewType.VISUAL;
    this.speed = 1;
    this.timer = new Timer(1, this);

    this.setTitle("Easy Animator");
    this.setSize(1000, 1000);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    panel = new VisualPanel(this.model.getShapes());
    this.add(panel);

    JScrollPane scrollPane = new JScrollPane(panel);
    scrollPane.setPreferredSize(new Dimension(300, 300));
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    this.add(scrollPane, BorderLayout.CENTER);
    scrollPane.setBounds(10, 10, 50, 50);
    this.setVisible(false);
    this.pack();
  }

  @Override
  public void setModel(AnimationModel m) {
    if (m == null) {
      throw new IllegalArgumentException("The model you passed in is null");
    }
    this.model = m;
    this.panel.setShapes(this.model.getShapes());
    this.currentTick = this.model.getMinTick();
    this.maxTick = this.model.getMaxTick();
  }

  @Override
  public ViewType getViewType() {
    return this.viewType;
  }

  @Override
  public String showViewDescription(boolean isLoop)
          throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Can't get view string for visual view!");
  }

  @Override
  public void setViewSpeed(double speed) throws IllegalArgumentException {
    if (speed <= 0) {
      throw new IllegalArgumentException("Speed must be positive!");
    }
    this.speed = speed;
  }

  @Override
  public double getViewSpeed() {
    return this.speed;
  }

  @Override
  public void setCanvas(int x, int y, int width, int height) {
    this.setBounds(x, y, width, height);
  }

  @Override
  public void setActionListener(ActionListener listener)
          throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Can't set action listeners for visual view!");
  }

  @Override
  public void outputOfView(String file, int speed, boolean isLoop)
          throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Can't get output file for visual view!");
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
    this.timer.start();
  }

  @Override
  public void setFeature(String feature) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Can't set features for visual view");
  }

  @Override
  public void refresh() throws UnsupportedOperationException {
    this.repaint();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    for (Animation each : model.getAnimations()) {
      each.change(currentTick);
    }
    refresh();
    currentTick = currentTick + (0.01 * this.speed);
    if (currentTick > this.maxTick) {
      if (first) {
        currentTick = this.maxTick;
        first = false;
      } else {
        this.timer.stop();
      }
    }

  }
}
