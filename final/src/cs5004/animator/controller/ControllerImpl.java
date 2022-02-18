package cs5004.animator.controller;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.view.AnimationView;

import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @ Program: final
 * @ Date: 2021/12/15
 * @ Description: This class represents a Controller of an easy animation.
 *
 *
 * <p>To be completed.
 * set actions for the animator.
 * write private functions for executing the behavior of the button.
 * write function play visual view.
 */
public class ControllerImpl implements AnimationController, ActionListener {
  private AnimationView view;
  private StringBuilder log;
  private AnimationModel model;
  private int speed;
  private String fileName;

  /**
   * Constructor of the controller.
   */
  public ControllerImpl(AnimationModel model, AnimationView view, int speed, String fileName)
          throws IllegalArgumentException {
    if (fileName.equals("")) {
      throw new IllegalArgumentException("File name can't be empty!");
    } else if (model == null) {
      throw new IllegalArgumentException("Model can't be null!");
    } else if (speed <= 0) {
      throw new IllegalArgumentException("Speed must be positive!");
    } else if (view == null) {
      throw new IllegalArgumentException("View can't be null!");
    }
    this.view = view;
    this.log = new StringBuilder();
    this.model = model;
    this.speed = speed;
    this.fileName = fileName;
  }

  @Override
  public void playAnimation() {
    switch (this.view.getViewType()) {
      case TEXTUAL:
        view.setViewSpeed(this.speed);
        view.setModel(this.model);
        view.outputOfView(this.fileName, speed, false);//
        break;
      case SVG:
        view.setViewSpeed(this.speed);
        view.setModel(this.model);
        view.setCanvas(this.model.getCanvasX(), this.model.getCanvasY(),
                this.model.getCanvasWidth(), this.model.getCanvasHeight());
        view.outputOfView(this.fileName, speed, false);
        break;
      case VISUAL:
        view.setViewSpeed(this.speed);
        view.setModel(this.model);
        view.setCanvas(this.model.getCanvasX(), this.model.getCanvasY(),
                this.model.getCanvasWidth() + this.model.getCanvasX() * 3,
                this.model.getCanvasHeight() + model.getCanvasY() * 3);
        view.makeVisible();
        break;
      case EDITOR:
        view.setViewSpeed(this.speed);
        view.setModel(this.model);
        this.view.setActionListener(this);
        this.view.makeVisible();
        break;
      default:
        JOptionPane.showMessageDialog(null,
                "Invalid view type", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  @Override
  public String getLog() {
    return this.log.toString();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String action = e.getActionCommand();
    switch (action) {
      case "PLAY":
        this.log.append("Play button pressed\n");
        break;
      case "PAUSE":
        this.log.append("Pause button pressed\n");
        break;
      case "RESUME":
        this.log.append("Resume button pressed\n");
        break;
      case "RESTART":
        this.log.append("Restart button pressed\n");
        break;
      case "INCREASE SPEED":
        this.log.append("Increase speed button pressed\n");
        break;
      case "DECREASE SPEED":
        this.log.append("Decrease speed button pressed\n");
        break;
      case "LOOP":
        this.log.append("Loop checkbox selected\n");
        break;
      case "TEXT":
        this.log.append("Export text file button pressed\n");
        break;
      case "SVG":
        this.log.append("Export SVG file button pressed\n");
        break;
      case "OPEN":
        this.log.append("Open file button pressed\n");
        break;
      default:
        this.log.append("Unsupported feature called\n");
        JOptionPane.showMessageDialog(null,
                "Unsupported feature!", "Error", JOptionPane.ERROR_MESSAGE);
    }
    this.view.setFeature(action);
  }
}
