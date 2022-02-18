
import cs5004.animator.model.AnimationModelImpl;
import cs5004.animator.model.animation.Animation;
import cs5004.animator.model.animation.ChangeColor;
import cs5004.animator.model.animation.Move;
import cs5004.animator.model.animation.Scale;
import cs5004.animator.model.shape.Oval;
import cs5004.animator.model.shape.Point2D;
import cs5004.animator.model.shape.Rectangle;
import cs5004.animator.model.shape.Shape;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test for animation modelImpl.
 * all operations are offered by the easy animator model.
 * shapes and animations stored in list.
 */

public class AnimationModelImplTest {
  private AnimationModelImpl test;
  private AnimationModelImpl model;

  private Shape r;
  private Shape o;
  private Animation changeColorR;
  private Animation moveR;
  private Animation scaleR;
  private Animation changeColorO;
  private Animation moveO;
  private Animation scaleO;

  private Shape invalid;
  private Animation moveInvalid;

  /**
   * Set up the model initialization before the test.
   * Initialize shape Rectangle and Oval, also animations move, changeColor and scale.
   */
  @org.junit.Before
  public void setUp() {
    test = new AnimationModelImpl();
    model = new AnimationModelImpl();
    r = new Rectangle("R", 0, 0, new Color(255, 20, 147),
            10, 5, 2, 20);

    o = new Oval("O", 0, 0, new Color(30, 144, 255),
            10, 5, 1, 20);

    moveR = new Move(r, 3, 8, new Point2D(0, 0), new Point2D(10, 10));
    scaleR = new Scale(r, 8, 13, 9, 4);
    changeColorR = new ChangeColor(r, 13, 18, new Color(0, 100, 0));

    moveO = new Move(o, 3, 8, new Point2D(0, 0), new Point2D(2, 2));
    scaleO = new Scale(o, 8, 13, 5, 10);
    changeColorO = new ChangeColor(o, 13, 18, new Color(0, 100, 0));
    model.addShape(r);
    //model.addShape(o);
    model.setCanvas(-100, -50, 1200, 1200);
    model.addAnimation(moveR);
    model.addAnimation(changeColorR);
    model.addAnimation(scaleR);
    model.addAnimation(moveO);

    //invalid = null;
    //moveInvalid=null;
  }

  /**
   * Test for model initialization constructor.
   */
  @org.junit.Test
  public void testConstructor() {
    assertTrue(test.getShapes().isEmpty());
    assertTrue(test.getAnimations().isEmpty());
    assertEquals(1000, test.getCanvasWidth());
    assertEquals(1000, test.getCanvasHeight());
    assertEquals(0, test.getCanvasX());
    assertEquals(0, test.getCanvasY());

    assertEquals(0, test.getMinTick());
    assertEquals(0, test.getMaxTick());
    assertEquals(0, test.getMinAnimationTime());
    assertEquals(0, test.getMaxAnimationTime());
    String expected =
            "Create canvas with X: 0 Y: 0 Width: 1000 Height: 1000\n"
                    + "There is no shape!";
    assertEquals(expected, test.getModelDescription());
    expected = "There is no shape!";
    assertEquals(expected, test.showShapeLog("r"));
    expected = "There is no any animation in the model!";
    assertEquals(expected, test.getAnimationAtTick(20));
  }

  /**
   * Test for invalid add shape to model.
   */
  @org.junit.Test
  public void testInvalidAddShape() {
    String expected = "Shape is null.";
    try {
      test.addShape(invalid);
    } catch (IllegalArgumentException e) {
      assertEquals(expected, e.getMessage());
      //System.out.println(e.getMessage());
    }
    try {
      model.addShape(r);
    } catch (IllegalArgumentException e) {
      expected = "Shape is already exist.";
      assertEquals(expected, e.getMessage());
      //System.out.println(e.getMessage());
    }
  }

  /**
   * Test for add shape to model.
   */
  @org.junit.Test
  public void addShape() {
    test.addShape(r);
    test.addShape(o);
    assertEquals(1, test.getMinTick());
    assertEquals(20, test.getMaxTick());
    assertEquals(0, test.getMinAnimationTime());
    assertEquals(0, test.getMaxAnimationTime());

    String expected = "Create canvas with X: 0 Y: 0 Width: 1000 Height: 1000\n"
            + "Create #FF1493 rectangle R with corner at (0.0,0.0) , width 10.0 and height 5.0\n"
            + "Create #1E90FF oval O with center at (0.0,0.0) , radius 10.0 and 5.0\n"
            + "\n" + "R appears at time t=2 and disappears at time t=20\n"
            + "O appears at time t=1 and disappears at time t=20\n\n";
    assertEquals(expected, test.getModelDescription());
    //test for other output
    expected = "The shape doesn't exist";
    assertEquals(expected, test.showShapeLog("w"));
    expected = "No animation for R";
    assertEquals(expected, test.showShapeLog("r"));
    expected = "No animation for O";
    assertEquals(expected, test.showShapeLog("O"));

    expected = "There is no any animation in the model!";
    assertEquals(expected, test.getAnimationAtTick(20));
  }

  /**
   * Test for invalid remove shape from model.
   */
  @org.junit.Test
  public void testInvalidRemoveShape() {
    String expected = "Shape is null.";
    try {
      test.removeShape(invalid);
    } catch (IllegalArgumentException e) {
      assertEquals(expected, e.getMessage());
      //System.out.println(e.getMessage());
    }
    try {
      model.removeShape(r);
      model.removeShape(r);
    } catch (IllegalArgumentException e) {
      expected = "Shape is not exist.";
      assertEquals(expected, e.getMessage());
      //System.out.println(e.getMessage());
    }
  }

  /**
   * Test for remove shape from model.
   */
  @org.junit.Test
  public void removeShape() {
    test.addAnimation(moveR);
    test.addAnimation(changeColorR);
    test.addAnimation(scaleR);
    test.addAnimation(moveO);
    test.addAnimation(changeColorO);
    test.addAnimation(scaleO);
    test.removeShape(o);
    test.removeShape(r);
    assertEquals(0, test.getMinTick());
    assertEquals(0, test.getMaxTick());
    assertEquals(0, test.getMinAnimationTime());
    assertEquals(0, test.getMaxAnimationTime());
    assertTrue(test.getShapes().isEmpty());
    assertTrue(test.getAnimations().isEmpty());
    String expected =
            "Create canvas with X: 0 Y: 0 Width: 1000 Height: 1000\n"
                    + "There is no shape!";
    assertEquals(expected, test.getModelDescription());
  }

  /**
   * Test for invalid add animation to model.
   */
  @org.junit.Test
  public void testInvalidAddAnimation() {
    Animation overlap = new Move(r, 2, 9, new Point2D(0, 0), new Point2D(10, 10));
    String expected = "Animation is null.";
    try {
      test.addAnimation(moveInvalid);
    } catch (IllegalArgumentException e) {
      assertEquals(expected, e.getMessage());
      //System.out.println(e.getMessage());
    }
    try {
      model.addAnimation(overlap);
    } catch (IllegalArgumentException e) {
      expected = "Cannot execute the same behavior to a shape in an overlapping time.";
      assertEquals(expected, e.getMessage());
      //System.out.println(e.getMessage());
    }
  }

  /**
   * Test for add animation to model.
   */
  @org.junit.Test
  public void addAnimation() {

    test.addAnimation(moveR);
    test.addAnimation(changeColorR);
    test.addAnimation(scaleR);
    test.addShape(o);
    test.addAnimation(moveO);
    test.addAnimation(changeColorO);
    test.addAnimation(scaleO);

    assertEquals(3, test.getMinAnimationTime());
    assertEquals(18, test.getMaxAnimationTime());
    String expected = "Create canvas with X: 0 Y: 0 Width: 1000 Height: 1000\n"
            + "Create #FF1493 rectangle R with corner at (0.0,0.0) , width 10.0 and height 5.0\n"
            + "Create #1E90FF oval O with center at (0.0,0.0) , radius 10.0 and 5.0\n"
            + "\n" + "R appears at time t=2 and disappears at time t=20\n"
            + "O appears at time t=1 and disappears at time t=20\n\n"
            + "R moves from (0.0,0.0) to (10.0,10.0) from time t=3 to t=8\n"
            + "O moves from (0.0,0.0) to (2.0,2.0) from time t=3 to t=8\n"
            + "R resizes from Width: 10.0, Height: 5.0 to Width: 9.0, Height: 4.0 "
            + "from time t=8 to t=13\n"
            + "O resizes from X radius: 10.0, Y radius: 5.0 to X radius: 5.0, Y radius: 10.0 "
            + "from time t=8 to t=13\n"
            + "R changes color from (1.0,0.1,0.6) to (0.0,0.4,0.0) from time t=13 to t=18\n"
            + "O changes color from (0.1,0.6,1.0) to (0.0,0.4,0.0) from time t=13 to t=18\n";
    assertEquals(expected, test.getModelDescription());
    //test for other output
    expected = "The shape doesn't exist";
    assertEquals(expected, test.showShapeLog("w"));
    expected = "R moves from (0.0,0.0) to (10.0,10.0) from time t=3 to t=8\n"
            + "R resizes from Width: 10.0, Height: 5.0 to Width: 9.0, Height: 4.0 "
            + "from time t=8 to t=13\n"
            + "R changes color from (1.0,0.1,0.6) to (0.0,0.4,0.0) from time t=13 to t=18\n";
    assertEquals(expected, test.showShapeLog("r"));
    expected = "O moves from (0.0,0.0) to (2.0,2.0) from time t=3 to t=8\n"
            + "O resizes from X radius: 10.0, Y radius: 5.0 to X radius: 5.0, Y radius: 10.0 "
            + "from time t=8 to t=13\n"
            + "O changes color from (0.1,0.6,1.0) to (0.0,0.4,0.0) from time t=13 to t=18\n";
    assertEquals(expected, test.showShapeLog("O"));

    expected = "R changes color from (1.0,0.1,0.6) to (0.0,0.4,0.0) from time t=13 to t=18\n"
            + "O changes color from (0.1,0.6,1.0) to (0.0,0.4,0.0) from time t=13 to t=18\n";
    assertEquals(expected, test.getAnimationAtTick(18));

    expected = "There is no any animation at time node!";
    assertEquals(expected, test.getAnimationAtTick(2));

  }

  /**
   * Test for get the list of shapes for model.
   */
  @org.junit.Test
  public void getShapes() {
    List list = new ArrayList();
    assertEquals(list, test.getShapes());
    test.addShape(r);
    test.addShape(o);
    list.add(r);
    list.add(o);
    assertEquals(list, test.getShapes());

    List list2 = new ArrayList();
    list2.add(r);
    list2.add(o);
    assertEquals(list2, model.getShapes());
  }

  /**
   * Test for get the list of animations for model.
   */
  @org.junit.Test
  public void getAnimations() {
    List list = new ArrayList();
    assertEquals(list, test.getAnimations());
    test.addAnimation(moveR);
    test.addAnimation(changeColorR);
    test.addAnimation(scaleR);
    test.addAnimation(moveO);
    test.addAnimation(changeColorO);
    test.addAnimation(scaleO);

    // sequence based on start time of animation.
    list.add(moveR);
    list.add(moveO);
    list.add(scaleR);
    list.add(scaleO);
    list.add(changeColorR);
    list.add(changeColorO);
    assertEquals(list, test.getAnimations());

    List list2 = new ArrayList();
    list2.add(moveR);
    list2.add(moveO);
    list2.add(scaleR);
    list2.add(changeColorR);
    assertEquals(list2, model.getAnimations());
  }

  /**
   * Test for set the canvas for model.
   */
  @org.junit.Test
  public void setCanvas() {
    test.setCanvas(-50, -30, 300, 300);
    assertEquals(-50, test.getCanvasX());
    assertEquals(-30, test.getCanvasY());
    assertEquals(300, test.getCanvasWidth());
    assertEquals(300, test.getCanvasHeight());
    //test for model
    assertEquals(-100, model.getCanvasX());
    assertEquals(-50, model.getCanvasY());
    assertEquals(1200, model.getCanvasWidth());
    assertEquals(1200, model.getCanvasHeight());
  }

  /**
   * Test for set the x position canvas for model which is left most.
   */
  @org.junit.Test
  public void getCanvasX() {
    assertEquals(0, test.getCanvasX());
    assertEquals(-100, model.getCanvasX());
  }

  /**
   * Test for set the y position canvas for model which is down most.
   */
  @org.junit.Test
  public void getCanvasY() {
    assertEquals(0, test.getCanvasY());
    assertEquals(-50, model.getCanvasY());
  }

  /**
   * Test for set the width canvas for model.
   */
  @org.junit.Test
  public void getCanvasWidth() {
    assertEquals(1000, test.getCanvasWidth());
    assertEquals(1200, model.getCanvasWidth());

  }

  /**
   * Test for set the height canvas for model.
   */
  @org.junit.Test
  public void getCanvasHeight() {
    assertEquals(1000, test.getCanvasHeight());
    assertEquals(1200, model.getCanvasHeight());
  }

  /**
   * Test for get the description of model.
   */
  @org.junit.Test
  public void getModelDescription() {
    String expected = "Create canvas with X: -100 Y: -50 Width: 1200 Height: 1200\n"
            + "Create #FF1493 rectangle R with corner at (0.0,0.0) , width 10.0 and height 5.0\n"
            + "Create #1E90FF oval O with center at (0.0,0.0) , radius 10.0 and 5.0\n"
            + "\n" + "R appears at time t=2 and disappears at time t=20\n"
            + "O appears at time t=1 and disappears at time t=20\n\n"
            + "R moves from (0.0,0.0) to (10.0,10.0) from time t=3 to t=8\n"
            + "O moves from (0.0,0.0) to (2.0,2.0) from time t=3 to t=8\n"
            + "R resizes from Width: 10.0, Height: 5.0 to Width: 9.0, Height: 4.0 "
            + "from time t=8 to t=13\n"
            + "R changes color from (1.0,0.1,0.6) to (0.0,0.4,0.0) from time t=13 to t=18\n";
    assertEquals(expected, model.getModelDescription());
  }

  /**
   * Test for get the minimum time tick of shapes.
   * When the first shape appears.
   */
  @org.junit.Test
  public void getMinTick() {
    assertEquals(1, model.getMinTick());
  }

  /**
   * Test for get the maximum time tick of shapes.
   * When the last shape disappears.
   */
  @org.junit.Test
  public void getMaxTick() {
    assertEquals(20, model.getMaxTick());
  }

  /**
   * Test for get the minimum time tick of animations.
   * When the first animation start.
   */
  @org.junit.Test
  public void getMinAnimationTime() {
    assertEquals(3, model.getMinAnimationTime());
  }

  /**
   * Test for get the maximum time tick of shapes.
   * When the last animation end.
   */
  @org.junit.Test
  public void getMaxAnimationTime() {
    assertEquals(18, model.getMaxAnimationTime());

  }

  /**
   * Test for invalid show shape animation log.
   */
  @org.junit.Test
  public void testInvalidShowLog() {
    String n = null;
    String expected = "Shape Name is null.";
    try {
      model.showShapeLog(n);
    } catch (IllegalArgumentException e) {
      assertEquals(expected, e.getMessage());
      //System.out.println(e.getMessage());
    }
  }

  /**
   * Test for get the description of  log of one shape with given name string.
   */
  @org.junit.Test
  public void showShapeLog() {
    String expected = "The shape doesn't exist";
    assertEquals(expected, model.showShapeLog("a"));
    expected = "R moves from (0.0,0.0) to (10.0,10.0) from time t=3 to t=8\n"
            + "R resizes from Width: 10.0, Height: 5.0 to Width: 9.0, Height: 4.0 "
            + "from time t=8 to t=13\n"
            + "R changes color from (1.0,0.1,0.6) to (0.0,0.4,0.0) from time t=13 to t=18\n";
    assertEquals(expected, model.showShapeLog("r"));
    expected = "O moves from (0.0,0.0) to (2.0,2.0) from time t=3 to t=8\n";
    assertEquals(expected, model.showShapeLog("O"));

    expected = "There is no shape!";
    assertEquals(expected, test.showShapeInfo("r"));

  }

  /**
   * Test for invalid get Animation at time tick.
   */
  @org.junit.Test
  public void testInvalidGetAnimationAtTick() {
    String expected = "Time is out of bound.";
    for (int i = -100; i < 3; i++) {
      try {
        model.getAnimationAtTick(i);
      } catch (IllegalArgumentException e) {
        assertEquals(expected, e.getMessage());
        //System.out.println(e.getMessage());
      }
    }
    for (int i = 19; i < 100; i++) {
      try {
        model.getAnimationAtTick(i);
      } catch (IllegalArgumentException e) {
        assertEquals(expected, e.getMessage());
        //System.out.println(e.getMessage());
      }
    }
  }

  /**
   * Test for get the description of  animations at given time tick.
   */
  @org.junit.Test
  public void getAnimationAtTick() {
    String expected = "There is no any animation at time node!";
    assertEquals(expected, model.getAnimationAtTick(1));
    expected = "There is no any animation at time node!";
    assertEquals(expected, model.getAnimationAtTick(2));
    expected = "R moves from (0.0,0.0) to (10.0,10.0) from time t=3 to t=8\n"
            + "O moves from (0.0,0.0) to (2.0,2.0) from time t=3 to t=8\n";
    assertEquals(expected, model.getAnimationAtTick(5));
    expected = "R resizes from Width: 10.0, Height: 5.0 to Width: 9.0, Height: 4.0 "
            + "from time t=8 to t=13\n";
    assertEquals(expected, model.getAnimationAtTick(10));
    expected = "R changes color from (1.0,0.1,0.6) to (0.0,0.4,0.0) from time t=13 to t=18\n";
    assertEquals(expected, model.getAnimationAtTick(16));
  }

  /**
   * Test for invalid show information of shape.
   */
  @org.junit.Test
  public void testInvalidShowInfo() {
    String n = null;
    String expected = "Shape Name is null.";
    try {
      model.showShapeLog(n);
    } catch (IllegalArgumentException e) {
      assertEquals(expected, e.getMessage());
      //System.out.println(e.getMessage());
    }
  }

  /**
   * Test for get the description of  one shape.
   */
  @org.junit.Test
  public void showShapeInfo() {
    String expected = "Name: R\nType: RECTANGLE\n"
            + "Min Corner: (0.0,0.0), Width: 10.0, Height: 5.0, Color: (1.0,0.1,0.6)\n"
            + "Appears at t=2\nDisappears at t=20\n";
    assertEquals(expected, model.showShapeInfo("r"));

    expected = "Name: O\nType: OVAL\n"
            + "Center: (0.0,0.0), X radius: 10.0, Y radius: 5.0, Color: (0.1,0.6,1.0)\n"
            + "Appears at t=1\nDisappears at t=20\n";
    assertEquals(expected, model.showShapeInfo("o"));

    expected = "The shape doesn't exist.";
    assertEquals(expected, model.showShapeInfo("p"));

    expected = "There is no shape!";
    assertEquals(expected, test.showShapeInfo("r"));
  }

  //Other test for animation.

  /**
   * Test for invalid constructor of  animations.
   */
  @org.junit.Test
  public void testInvalidConstructor() {
    Color c = null;
    Point2D p = null;
    String expected;
    try {
      moveO = new Move(r, 2, 10, new Point2D(0, 0), p);
    } catch (IllegalArgumentException e) {
      expected = "New position is NULL.";
      assertEquals(expected, e.getMessage());
      //System.out.println(e.getMessage());
    }
    try {
      moveO = new ChangeColor(r, 2, 10, c);
    } catch (IllegalArgumentException e) {
      expected = "New color is NULL.";
      assertEquals(expected, e.getMessage());
      //System.out.println(e.getMessage());
    }
    for (int i = 0; i > -100; i--) {
      try {
        moveO = new Scale(r, 2, 10, i, i);
      } catch (IllegalArgumentException e) {
        expected = "Wight or height is not positive.";
        assertEquals(expected, e.getMessage());
        //System.out.println(e.getMessage());
      }
    }
    //test when time out of bound.
    for (int j = -1; j > -100; j--) {
      try {
        moveO = new Move(r, j, 20, new Point2D(0, 0), new Point2D(10, 10));
      } catch (IllegalArgumentException e) {
        expected = "Start or end time can not be negative";
        assertEquals(expected, e.getMessage());
        //System.out.println(e.getMessage());
      }
    }
    for (int j = -1; j > -100; j--) {
      try {
        moveO = new Move(r, 2, j, new Point2D(0, 0), new Point2D(10, 10));
      } catch (IllegalArgumentException e) {
        expected = "Start or end time can not be negative";
        assertEquals(expected, e.getMessage());
        //System.out.println(e.getMessage());
      }
    }
    for (int j = -1; j > -100; j--) {
      try {
        moveO = new Move(r, 2, j, new Point2D(0, 0), new Point2D(10, 10));
      } catch (IllegalArgumentException e) {
        expected = "Start or end time can not be negative";
        assertEquals(expected, e.getMessage());
        //System.out.println(e.getMessage());
      }
    }
    try {
      moveO = new Move(r, 0, 1, new Point2D(0, 0), new Point2D(10, 10));
    } catch (IllegalArgumentException e) {
      expected = "Start or end time out of bounds of appearing or disappearing time  of shape.";
      assertEquals(expected, e.getMessage());
      //  System.out.println(e.getMessage());
    }
    try {
      moveO = new Move(r, 21, 22, new Point2D(0, 0), new Point2D(10, 10));
    } catch (IllegalArgumentException e) {
      expected = "Start or end time out of bounds of appearing or disappearing time  of shape.";
      assertEquals(expected, e.getMessage());
      // System.out.println(e.getMessage());
    }
    try {
      moveO = new Move(r, 10, 9, new Point2D(0, 0), new Point2D(10, 10));
    } catch (IllegalArgumentException e) {
      expected = "End time can not be smaller than start.";
      assertEquals(expected, e.getMessage());
      //System.out.println(e.getMessage());
    }
  }
}