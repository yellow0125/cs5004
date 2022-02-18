package cs5004.animator.view;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import cs5004.animator.model.AnimationModel;
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.animation.Animation;
import cs5004.animator.model.shape.Shape;
import cs5004.animator.util.AnimationReader;

/**
 * This is the concrete class that represents an editor view. It extends JFrame and implements
 * interface View and ActionListener. It's main feature is to show a GUI to interact with users.
 * First, users can play, pause, resume, restart and loop animations by clink buttons. Second, users
 * can control the speed of animations by increase and decrease speed buttons, but the speed can't
 * be less than 1. Current speed will be shown on the GUI. Third, uses can produce svg and text file
 * by export svg and export text buttons. Last, uses can use Open file button to open files and play
 * other animation.
 */
public class EditorView extends JFrame implements AnimationView, ActionListener {
  private VisualPanel panel;

  private JButton start;
  private JButton pause;
  private JButton resume;
  private JButton restart;
  private JButton speedUp;
  private JButton slowdown;
  private JButton exportText;
  private JButton openFile;
  private JButton exportSVG;
  private JCheckBox loop;
  private JLabel speedLabel;
  private double speed;
  private final Timer timer;
  private List<Shape> shapes;
  private AnimationModel model;
  private AnimationModel modelCopy;
  private double currentTick;
  private int maxTick;
  private boolean isLoop = false;
  private boolean firstTime = true;
  private ViewType viewType;

  /**
   * The constructor that initializes the editor view. It creates an empty animator model and a copy
   * of the model. Both need to be set up by calling the setModel method. It also initializes
   * JFrame, timer, current tick and set the default speed to be 1. The JFrame components are not
   * added until the private method setUpComponents is called.
   */
  public EditorView() {
    super();
    this.model = new AnimationModelImpl();
    this.modelCopy = model.cloneModel();
    this.setCanvas(0, 0, 1000, 1000);
    this.currentTick = this.model.getMinTick();
    this.timer = new Timer(1, this);
    this.setVisible(false);
    this.speed = 1;
    this.viewType = ViewType.EDITOR;
  }

  @Override
  public void setModel(AnimationModel m) throws IllegalArgumentException {
    if (m == null) {
      throw new IllegalArgumentException("Model can't be null!");
    }
    this.model = m;
    this.setUpComponents();
    this.panel.setShapes(this.model.getShapes());
    this.modelCopy = this.model.cloneModel();
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void setViewSpeed(double speed) throws IllegalArgumentException {
    if (speed <= 0) {
      throw new IllegalArgumentException("Speed must be positive!");
    }
    this.speed = speed;
  }

  @Override
  public void refresh() {
    this.repaint();
  }

  @Override
  public void setActionListener(ActionListener listener) {
    this.start.addActionListener(listener);
    this.pause.addActionListener(listener);
    this.resume.addActionListener(listener);
    this.restart.addActionListener(listener);
    this.loop.addActionListener(listener);
    this.speedUp.addActionListener(listener);
    this.slowdown.addActionListener(listener);
    this.exportText.addActionListener(listener);
    this.exportSVG.addActionListener(listener);
    this.openFile.addActionListener(listener);
  }

  @Override
  public void outputOfView(String file, int speed, boolean isLoop)
          throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Can't get output file for the editor view!");
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    for (Animation behavior : this.model.getAnimations()) {
      behavior.change(this.currentTick);
    }

    this.refresh();
    currentTick = currentTick + (0.01 * this.speed);
    if (currentTick > this.maxTick) {
      if (firstTime) {
        currentTick = this.maxTick;
        firstTime = false;
      } else {
        if (isLoop) {
          restart();
        } else {
          this.timer.stop();
        }
      }
    }
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
  public ViewType getViewType() {
    return this.viewType;
  }

  @Override
  public String showViewDescription(boolean isLoop) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Can't show description for the editor view");
  }

  @Override
  public void setFeature(String feature) {
    switch (feature) {
      case "PLAY":
        this.play();
        break;
      case "PAUSE":
        this.pause();
        break;
      case "RESUME":
        this.resume();
        break;
      case "RESTART":
        this.restart();
        break;
      case "INCREASE SPEED":
        this.increaseSpeed();
        break;
      case "DECREASE SPEED":
        this.decreaseSpeed();
        break;
      case "LOOP":
        this.loop();
        break;
      case "TEXT":
        this.text();
        break;
      case "SVG":
        this.svg();
        break;
      case "OPEN":
        this.open();
        break;
      default:
        JOptionPane.showMessageDialog(null,
                "Unsupported feature!", "Error", JOptionPane.ERROR_MESSAGE);
    }
  }

  /**
   * A private method that sets up and add the JFrame components.
   */
  private void setUpComponents() {
    this.setTitle("Easy Animator");
    this.setPreferredSize(new Dimension(1200, 500));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.shapes = this.model.getShapes();
    this.maxTick = this.model.getMaxTick();
    this.panel = new VisualPanel(this.shapes);
    this.add(this.panel);

    this.start = new JButton("Play");
    this.start.setActionCommand("PLAY");

    this.pause = new JButton("Pause");
    this.pause.setActionCommand("PAUSE");

    this.resume = new JButton("Resume");
    this.resume.setActionCommand("RESUME");

    this.restart = new JButton("Restart");
    this.restart.setActionCommand("RESTART");

    this.loop = new JCheckBox("Loop");
    this.loop.setActionCommand("LOOP");

    this.speedUp = new JButton("Increase speed");
    this.speedUp.setActionCommand("INCREASE SPEED");

    this.slowdown = new JButton("Decrease speed");
    this.slowdown.setActionCommand("DECREASE SPEED");

    this.exportText = new JButton("Export text file");
    this.exportText.setActionCommand("TEXT");

    this.openFile = new JButton("Open file");
    this.openFile.setActionCommand("OPEN");

    this.exportSVG = new JButton("Export SVG file");
    this.exportSVG.setActionCommand("SVG");

    JLabel speedTag = new JLabel("Current speed");
    this.speedLabel = new JLabel("speed");
    this.speedLabel.setText(String.valueOf(this.speed));

    JPanel filesavePanel = new JPanel();
    filesavePanel.setLayout(new FlowLayout());
    filesavePanel.add(this.exportSVG);
    filesavePanel.add(this.exportText);
    filesavePanel.add(this.openFile);

    JPanel speedPanel = new JPanel();
    speedPanel.setLayout(new FlowLayout());
    speedPanel.add(this.speedUp);
    speedPanel.add(this.slowdown);
    speedPanel.add(speedTag);
    speedPanel.add(this.speedLabel);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    buttonPanel.add(this.start);
    buttonPanel.add(this.pause);
    buttonPanel.add(this.resume);
    buttonPanel.add(this.restart);
    buttonPanel.add(this.loop);
    buttonPanel.add(this.exportText);
    buttonPanel.add(this.exportSVG);
    buttonPanel.add(this.openFile);

    buttonPanel.add(speedPanel);
    buttonPanel.add(filesavePanel);
    this.add(buttonPanel, BorderLayout.SOUTH);
    this.setActionListener(this);

    this.pause.setEnabled(false);
    this.restart.setEnabled(false);
    this.resume.setEnabled(false);

    JScrollPane scrollPane = new JScrollPane(this.panel);
    scrollPane.setPreferredSize(new Dimension(1200, 1000));
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    this.add(scrollPane, BorderLayout.EAST);

    this.setVisible(false);
    this.pack();
  }

  /**
   * the Play button to start the timer.
   */
  private void play() {
    this.timer.start();
    this.start.setEnabled(false);
    this.pause.setEnabled(true);
    this.restart.setEnabled(true);
  }

  /**
   * the Pause button to stop the timer.
   */
  private void pause() {
    this.timer.stop();
    this.pause.setEnabled(false);
    this.resume.setEnabled(true);
  }

  /**
   * the Resume button.
   */
  private void resume() {
    this.timer.restart();
    this.pause.setEnabled(true);
    this.start.setEnabled(true);
    this.resume.setEnabled(false);
  }

  /**
   * the Restart button.
   */
  private void restart() {
    this.timer.stop();
    this.pause.setEnabled(true);
    this.resume.setEnabled(false);
    this.currentTick = this.model.getMinTick();
    this.model = this.modelCopy.cloneModel();
    this.panel.resetShapes(this.model.getShapes());
    this.firstTime = true;
    this.timer.start();
  }

  /**
   * speed up button.
   */
  private void increaseSpeed() {
    this.speed++;
    this.speedLabel.setText(String.valueOf(this.speed));
    if (!this.slowdown.isEnabled()) {
      this.slowdown.setEnabled(true);
    }
  }

  /**
   * Slow down button.
   */
  private void decreaseSpeed() {
    if (this.speed > 2) {
      this.speed--;
    } else if (this.speed == 2) {
      this.speed--;
      this.slowdown.setEnabled(false);
    }
    this.speedLabel.setText(String.valueOf(this.speed));
  }

  /**
   * the Loop button.
   */
  private void loop() {
    this.isLoop = !isLoop;
  }

  /**
   * the Export txt file button.
   */
  private void text() {
    final JFileChooser fchooser = new JFileChooser(".");
    int retvalue = fchooser.showSaveDialog(EditorView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      TextualView textualView = new TextualView();
      textualView.setModel(this.modelCopy.cloneModel());
      textualView.setViewSpeed(this.speed);
      textualView.outputOfView(f.toString(), (int) this.speed, this.isLoop);
      JOptionPane.showMessageDialog(EditorView.this,
              "Succeed!", "Export Text File", JOptionPane.PLAIN_MESSAGE);
    }
  }

  /**
   * the Export svg file button.
   */
  private void svg() {
    final JFileChooser fchooser = new JFileChooser(".");
    int retvalue = fchooser.showSaveDialog(EditorView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      SvgView svgView = new SvgView();
      svgView.setModel(this.modelCopy.cloneModel());
      svgView.setViewSpeed(this.speed);
      svgView.setCanvas(this.model.getCanvasX(), this.model.getCanvasY(),
              this.model.getCanvasWidth(),
              this.model.getCanvasHeight());
      svgView.outputOfView(f.toString(), (int) this.speed, this.isLoop);
      JOptionPane.showMessageDialog(EditorView.this,
              "Succeed!", "Export SVG File", JOptionPane.PLAIN_MESSAGE);
    }
  }

  /**
   * the Open file button.
   */
  private void open() {
    Readable in = null;
    final JFileChooser fchooser = new JFileChooser(".");
    int retvalue = fchooser.showSaveDialog(EditorView.this);
    if (retvalue == JFileChooser.APPROVE_OPTION) {
      File f = fchooser.getSelectedFile();
      try {
        in = new FileReader(f.toString());
      } catch (FileNotFoundException e) {
        JOptionPane.showMessageDialog(null,
                "File not found!", "Error", JOptionPane.ERROR_MESSAGE);
      }
      AnimationModel tempModel = AnimationReader.parseFile(in, new AnimationModelImpl.Builder());
      if (!tempModel.getShapes().isEmpty()) {
        this.model = tempModel;
        this.modelCopy = this.model.cloneModel();
        this.shapes = this.model.getShapes();
        this.panel.setShapes(this.shapes);
        this.maxTick = this.model.getMaxTick();
        restart();
        this.timer.stop();
        this.restart.setEnabled(false);
        this.pause.setEnabled(false);
        this.start.setEnabled(true);
        JOptionPane.showMessageDialog(EditorView.this,
                "Succeed opened file!", "Open File", JOptionPane.PLAIN_MESSAGE);
      } else {
        JOptionPane.showMessageDialog(null,
                "Invalid File!", "Error", JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}

