package cs5004.animator;

import cs5004.animator.controller.AnimationController;
import cs5004.animator.controller.ControllerImpl;
import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.util.AnimationReader;
import cs5004.animator.view.AnimationView;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.util.Arrays;
import javax.swing.JOptionPane;

import static cs5004.animator.view.ViewFactory.createView;

/**
 * @ Program: final
 * @ Date: 2021/12/08
 * @ Description: This class represents a controller, it reads in command line.
 * And it decides which of views been called, pass input to model and view.
 */
public final class EasyAnimator {

  /**
   * The main method that reads the arguments command line and
   * creates the appropriate model and view.
   *
   * @param args commandline from users.
   */
  public static void main(String[] args) {
    AnimationModel model;
    Readable in = new StringReader("");
    int speed = 1;
    int newSpeed;
    String viewType = "";
    String fileWriter = "System.out";
    int i;

    // check if -in and -view are provided
    if ((Arrays.stream(args).noneMatch(s -> s.equals("-in"))
            || (Arrays.stream(args).noneMatch(s -> s.equals("-view"))))) {
      JOptionPane.showMessageDialog(null,
              "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
    }
    for (i = 0; i < args.length; i++) {
      String command = args[i];
      switch (command) {
        case "-in":
          try {
            in = new FileReader(args[i + 1]);
            i++;
          } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,
                    "File does not find", "Error", JOptionPane.ERROR_MESSAGE);
          } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null,
                    "Out of index", "Error", JOptionPane.ERROR_MESSAGE);
          }
          break;
        case "-view":
          try {
            viewType = args[i + 1];
            i++;
          } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null,
                    "Out of index", "Error", JOptionPane.ERROR_MESSAGE);
          }
          break;
        case "-out":
          try {
            // jump to the next arg if the file name is not provided
            // and use System.out as the default output
            if (!args[i + 1].equals("-speed") && !args[i + 1].equals("-view")
                    && !args[i + 1].equals("-in")) {
              fileWriter = args[i + 1];
              i++;
            }
          } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null,
                    "Out of index", "Error", JOptionPane.ERROR_MESSAGE);
          }
          break;
        case "-speed":
          try {
            newSpeed = Integer.parseInt(args[i + 1]);
            if (newSpeed > 0) {
              speed = newSpeed;
            } else {
              JOptionPane.showMessageDialog(null,
                      "Negative speed", "Error", JOptionPane.ERROR_MESSAGE);
            }
            i++;
          } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null,
                    "Out of index", "Error", JOptionPane.ERROR_MESSAGE);
          } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null,
                    "Invalid number input", "Error", JOptionPane.ERROR_MESSAGE);
          }
          break;
        default:
          JOptionPane.showMessageDialog(null,
                  "Invalid input", "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
    model = AnimationReader.parseFile(in, new AnimationModelImpl.Builder()).cloneModel();
    AnimationView view = createView(viewType);
    AnimationController controller = new ControllerImpl(model, view, speed, fileWriter);
    controller.playAnimation();
  }
}
