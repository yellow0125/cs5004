package cs5004.animator.view;

import cs5004.animator.model.shape.Shape;
import cs5004.animator.model.shape.ShapeType;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.List;
import javax.swing.JPanel;

/**
 * This is a private class that represents a JPanel component for the visual view.
 * It extends the JPanel class,
 * It's used to store shapes and fill all shapes to be played on canvas.
 */
class VisualPanel extends JPanel {
  private List<Shape> shapes;

  /**
   * Constructor initializes a panel.
   * It uses the super constructor in the JPanel class and reset the size and background color.
   * set the view visible.
   *
   * @param shapes the list of shapes to be played
   */
  VisualPanel(List<Shape> shapes) {
    super();
    this.shapes = shapes;

    this.setBackground(Color.WHITE);
    this.setPreferredSize(new Dimension(1200, 1200));
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    for (Shape shape : this.shapes) {
      int x = (int) shape.getPosition().getX();
      int y = (int) shape.getPosition().getY();
      int w = (int) shape.getWidth();
      int h = (int) shape.getHeight();
      Color color = shape.getColor();

      if (shape.getShapeType() == ShapeType.OVAL) {
        g2.setColor(color);
        g2.drawOval(x, y, w * 2, h * 2);
        g2.fillOval(x, y, w * 2, h * 2);
      }
      if (shape.getShapeType() == ShapeType.RECTANGLE) {
        g2.setColor(color);
        g2.fillRect(x, y, w, h);
        g2.drawRect(x, y, w, h);
      }
    }
  }

  /**
   * Set the new shapes as we receive them from the model.
   *
   * @param shapes A list of new shapes.
   */
  void setShapes(List<Shape> shapes) {
    this.shapes = shapes;
  }

  /**
   * Reset the new shapes as we receive them from the model.
   *
   * @param shapes A list of new shapes.
   */
  void resetShapes(List<Shape> shapes) {
    this.shapes = shapes;
  }

}
