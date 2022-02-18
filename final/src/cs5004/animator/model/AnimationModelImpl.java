package cs5004.animator.model;

import cs5004.animator.model.animation.Animation;
import cs5004.animator.model.animation.AnimationType;
import cs5004.animator.model.animation.ChangeColor;
import cs5004.animator.model.animation.Move;
import cs5004.animator.model.animation.Scale;
import cs5004.animator.model.shape.Oval;
import cs5004.animator.model.shape.Point2D;
import cs5004.animator.model.shape.Rectangle;
import cs5004.animator.model.shape.Shape;
import cs5004.animator.model.shape.ShapeType;
import cs5004.animator.util.AnimationBuilder;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @ Program: final
 * @ Date: 2021/12/04
 * @ Description: This class represents an initialization for an easy animation model.
 * it implements the interface animation model,
 * all operations are offered by the easy animator model.
 */
public class AnimationModelImpl implements AnimationModel {
  private List<Shape> shapes;
  private List<Animation> animations;

  private int x;
  private int y;
  private int width;
  private int height;

  /**
   * Constructor that initializes the lists that store the shapes and animations.
   * And x, y, width, height initialize equal to zero.
   */
  public AnimationModelImpl() {
    this.shapes = new ArrayList<Shape>();
    this.animations = new ArrayList<Animation>();
    this.x = 0;
    this.y = 0;
    this.width = 1000;
    this.height = 1000;
  }

  /**
   * Helper function return this shape s is in the shapes list or not.
   *
   * @param s one given shape.
   * @return true if s in the list. Otherwise, return no.
   */
  private boolean inShapes(Shape s) {
    if (this.shapes.isEmpty()) {
      return false;
    }
    for (Shape each : this.shapes) {
      if (each.getName().equals(s.getName())) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void addShape(Shape s) throws IllegalArgumentException {
    if (inShapes(s)) {
      throw new IllegalArgumentException("Shape is already exist.");
    }
    this.shapes.add(s);
  }

  @Override
  public void removeShape(Shape s) throws IllegalArgumentException {
    if (s == null) {
      throw new IllegalArgumentException("Shape is null.");
    }
    if (!inShapes(s)) {
      throw new IllegalArgumentException("Shape is not exist.");
    }
    //remove from shapes and animations.
    //means shape disappear
    this.shapes.remove(s);
    this.animations.removeIf(each -> each.getShape().getName().equals(s.getName()));
  }

  /**
   * Helper function compare two animation if they work on the same shape
   * in the overlapping time with the same animation type.
   *
   * @param a1 the first given animation.
   * @param a2 the second given animation.
   * @return if they are overlapping return true. Otherwise, return false.
   */
  private boolean overlapAnimation(Animation a1, Animation a2) {
    String name1 = a1.getShape().getName();
    String name2 = a2.getShape().getName();

    AnimationType type1 = a1.getAnimationType();
    AnimationType type2 = a2.getAnimationType();

    int firstEnd = a1.getEndTime();
    int secondStart = a2.getStartTime();
    if (name1.equalsIgnoreCase(name2) && type1 == type2) {
      return secondStart < firstEnd;
    }
    return false;
  }

  @Override
  public void addAnimation(Animation a) throws IllegalArgumentException {
    int flag = 0;
    for (Animation each : this.animations) {
      if (overlapAnimation(each, a)) {
        throw new IllegalArgumentException("Cannot execute the same behavior to a shape in an"
                + " overlapping time.");
      }
    }
    if (shapes.isEmpty()) {
      this.addShape(a.getShape());
    } else {
      if (!inShapes(a.getShape())) {
        this.addShape(a.getShape());
      }
    }

    //add to animations by the start time
    if (this.animations.size() > 0) {
      for (int i = 0; i < this.animations.size(); i++) {
        if (a.getStartTime() < this.animations.get(i).getStartTime()) {
          this.animations.add(i, a);
          flag = 1;
          break;
        }
      }
    }
    //add behind
    if (flag != 1) {
      this.animations.add(a);
    }
  }

  @Override
  public List<Shape> getShapes() {
    return this.shapes;
  }

  @Override
  public List<Animation> getAnimations() {
    return this.animations;
  }

  @Override
  public void setCanvas(int x, int y, int width, int height) {
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  @Override
  public int getCanvasX() {
    return this.x;
  }

  @Override
  public int getCanvasY() {
    return this.y;
  }

  @Override
  public int getCanvasWidth() {
    return this.width;
  }

  @Override
  public int getCanvasHeight() {
    return this.height;
  }

  @Override
  public String getModelDescription() {
    StringBuilder str = new StringBuilder();
    str.append("Create canvas with X: ").append(this.getCanvasX()).append(" Y: ")
            .append(this.getCanvasY()).append(" Width: ").append(this.getCanvasWidth())
            .append(" Height: ").append(this.getCanvasHeight()).append("\n");
    if (this.shapes.isEmpty() && this.animations.isEmpty()) {
      return str.append("There is no shape!").toString();
    }

    for (Shape each : this.shapes) {
      str.append(each.getCreatDescription());
    }
    str.append("\n");
    for (Shape each : this.shapes) {
      str.append(each.getAppearDescription());
    }
    str.append("\n");
    for (Animation eachA : this.animations) {
      str.append(eachA.getMotionDescription());
    }

    return str.toString();
  }

  @Override
  public int getMinTick() {
    if (this.shapes.isEmpty()) {
      return 0;
    }
    int min = this.shapes.get(0).getTimeAppear();
    for (Shape each : this.shapes) {
      if (min > each.getTimeAppear()) {
        min = each.getTimeAppear();
      }
    }
    return min;
  }

  @Override
  public int getMaxTick() {
    if (this.shapes.isEmpty()) {
      return 0;
    }
    int max = 0;
    for (Shape each : this.shapes) {
      if (max < each.getTimeDisappear()) {
        max = each.getTimeDisappear();
      }
    }
    return max;
  }

  @Override
  public int getMinAnimationTime() {
    if (this.animations.isEmpty()) {
      return 0;
    }
    int min = this.animations.get(0).getStartTime();
    for (Animation each : this.animations) {
      if (min > each.getStartTime()) {
        min = each.getStartTime();
      }
    }
    return min;
  }

  @Override
  public int getMaxAnimationTime() {
    if (this.animations.isEmpty()) {
      return 0;
    }
    int max = 0;
    for (Animation each : this.animations) {
      if (max < each.getEndTime()) {
        max = each.getEndTime();
      }
    }
    return max;
  }

  @Override
  public String showShapeLog(String name) throws IllegalArgumentException {
    String str = "";
    if (this.shapes.isEmpty()) {
      return str += "There is no shape!";
    }
    if (name == null) {
      throw new IllegalArgumentException("Shape Name is null.");
    }
    for (Shape each : this.shapes) {
      if (each.getName().equalsIgnoreCase(name)) {
        str = each.getName();
        break;
      }
    }

    if (str.isEmpty()) {
      return str = "The shape doesn't exist";
    }

    StringBuilder str0 = new StringBuilder();
    if (animations.isEmpty()) {
      return str = "No animation for " + str;
    }
    for (Animation eachA : this.animations) {
      if (eachA.getShape().getName().equalsIgnoreCase(name)) {
        str0.append(eachA.getMotionDescription());
      }
    }
    str = str0.toString();
    return str;
  }

  @Override
  public String getAnimationAtTick(int time) throws IllegalArgumentException {
    StringBuilder str = new StringBuilder();
    if (this.animations.isEmpty()) {
      return str.append("There is no any animation in the model!").toString();
    }
    if (time < this.getMinTick() || time > getMaxTick()) {
      throw new IllegalArgumentException("Time is out of bound.");
    }
    if (time < this.getMinAnimationTime() || time > getMaxAnimationTime()) {
      return str.append("There is no any animation at time node!").toString();
    }
    for (Animation each : this.animations) {
      if (time >= each.getStartTime() && time <= each.getEndTime()) {
        str.append(each.getMotionDescription());
      }
    }
    return str.toString();
  }

  @Override
  public AnimationModel cloneModel() {
    Shape newShape;
    AnimationModel newModel = new AnimationModelImpl();
    for (Shape each : this.shapes) {
      newShape = each.cloneShape();
      newModel.addShape(newShape);
      for (Animation eachA : this.animations) {
        if (eachA.getShape().getName().equals(each.getName())) {
          newModel.addAnimation(eachA.cloneAnimation(newShape));
        }
      }
    }
    newModel.setCanvas(this.x, this.y, this.width, this.height);
    return newModel;
  }

  @Override
  public String showShapeInfo(String name) throws IllegalArgumentException {
    String str = "";
    if (this.shapes.isEmpty()) {
      return str += "There is no shape!";
    }
    if (name == null) {
      throw new IllegalArgumentException("Shape Name is null.");
    }
    for (Shape each : this.shapes) {
      if (each.getName().equalsIgnoreCase(name)) {
        return str = each.getShapeDescription();
      }
    }
    return str = "The shape doesn't exist.";
  }

  /**
   * This inner class represents an initialization for animationBuilder
   * It implements the interface animationBuilder.
   */
  public static final class Builder implements AnimationBuilder<AnimationModel> {
    private AnimationModel m;
    private Map<String, ShapeType> shape;
    private Map<String, List<Transform>> shapeTrans;
    private Map<String, Integer> maxTicks;
    private Map<String, Integer> minTicks;

    /**
     * Constructor that represent a builder class.
     */
    public Builder() {
      this.m = new AnimationModelImpl();
      this.shape = new LinkedHashMap<>();
      this.shapeTrans = new LinkedHashMap<>();
      this.maxTicks = new HashMap<>();
      this.minTicks = new HashMap<>();
    }

    /**
     * Static class storing the parameter data.
     */
    static class Transform {
      int t1;
      int x1;
      int y1;
      int w1;
      int h1;
      int r1;
      int g1;
      int b1;
      int t2;
      int x2;
      int y2;
      int w2;
      int h2;
      int r2;
      int g2;
      int b2;

      public Transform(int t1, int x1, int y1, int w1, int h1, int r1, int g1, int b1,
                       int t2, int x2, int y2, int w2, int h2, int r2, int g2, int b2) {
        this.t1 = t1;
        this.x1 = x1;
        this.y1 = y1;
        this.w1 = w1;
        this.h1 = h1;
        this.r1 = r1;
        this.g1 = g1;
        this.b1 = b1;
        this.t2 = t2;
        this.x2 = x2;
        this.y2 = y2;
        this.w2 = w2;
        this.h2 = h2;
        this.r2 = r2;
        this.g2 = g2;
        this.b2 = b2;
      }
    }

    @Override
    public AnimationModel build() {
      Shape newShape;
      Transform transform;
      Animation animation;

      for (String each : shapeTrans.keySet()) {
        transform = shapeTrans.get(each).get(0);
        if (shape.get(each) == ShapeType.RECTANGLE) {
          newShape = new Rectangle(each, transform.x1, transform.y1,
                  new Color(transform.r1, transform.g1, transform.b1), transform.w1, transform.h1,
                  minTicks.get(each), maxTicks.get(each));
        } else {
          newShape = new Oval(each, transform.x1, transform.y1,
                  new Color(transform.r1, transform.g1, transform.b1), transform.w1 / 2,
                  transform.h1 / 2,
                  minTicks.get(each), maxTicks.get(each));
        }

        m.addShape(newShape);

        for (Transform i : shapeTrans.get(each)) {
          if (i.x1 != i.x2 || i.y1 != i.y2) {
            animation = new Move(newShape, i.t1, i.t2, new Point2D(i.x1, i.y1),
                    new Point2D(i.x2, i.y2));
            m.addAnimation(animation);
          }
          if (i.w1 != i.w2 || i.h1 != i.h2) {
            animation = new Scale(newShape, i.t1, i.t2, i.w2, i.h2);
            m.addAnimation(animation);
          }
          if (i.r1 != i.r2 || i.g1 != i.g2 || i.b1 != i.b2) {
            animation = new ChangeColor(newShape, i.t1, i.t2,
                    new Color(i.r2, i.g2, i.b2));
            m.addAnimation(animation);
          }
          if (i.x1 == i.x2 && i.y1 == i.y2 && i.w1 == i.w2 && i.h1 == i.h2 && i.r1 == i.r2
                  && i.g1 == i.g2 && i.b1 == i.b2) {
            animation = new Move(newShape, i.t1, i.t2, new Point2D(i.x1, i.y1),
                    new Point2D(i.x2, i.y2));
            m.addAnimation(animation);
          }
        }
      }
      return m;
    }

    @Override
    public AnimationBuilder<AnimationModel> setBounds(int x, int y, int width, int height) {
      if (width <= 0 || height <= 0) {
        throw new IllegalArgumentException("Width or height should be positive.");
      }
      m.setCanvas(x, y, width, height);
      return this;
    }

    /**
     * Helper function return this shape s is in the shapes list or not.
     *
     * @param s string name of  a shape.
     * @return true if s in the list. Otherwise, return no.
     */
    private boolean isNameInShapes(String s) {
      if (m.getShapes().isEmpty()) {
        return false;
      }
      for (Shape each : m.getShapes()) {
        if (each.getName().equals(s)) {
          return true;
        }
      }
      return false;
    }

    @Override
    public AnimationBuilder<AnimationModel> declareShape(String name, String type)
            throws IllegalArgumentException {
      ShapeType shapeType;

      if (isNameInShapes(name)) {
        throw new IllegalArgumentException("The name is already been used!");
      }
      if (type.equals("rectangle")) {
        shapeType = ShapeType.RECTANGLE;
      } else if (type.equals("oval") || type.equals("ellipse")) {
        shapeType = ShapeType.OVAL;
      } else {
        throw new IllegalArgumentException("Invalid type!");
      }
      this.shape.put(name, shapeType);
      return this;
    }

    @Override
    public AnimationBuilder<AnimationModel> addMotion(
            String name, int t1, int x1, int y1, int w1,
            int h1, int r1, int g1, int b1, int t2, int x2,
            int y2, int w2, int h2, int r2, int g2,
            int b2) throws IllegalArgumentException {
      if (t1 < 0 || t1 > t2) {
        throw new IllegalArgumentException("Invalid start time or end time!");
      }

      if (w1 <= 0 || w2 <= 0) {
        throw new IllegalArgumentException("Width should be positive!");
      }

      if (h1 <= 0 || h2 <= 0) {
        throw new IllegalArgumentException("Height cannot be positive!");
      }

      if (r1 < 0 || r2 < 0 || g1 < 0 || g2 < 0 || b1 < 0 || b2 < 0
              || r1 > 255 || r2 > 255 || g1 > 255 || g2 > 255 || b1 > 255 || b2 > 255) {
        throw new IllegalArgumentException("RGB range is between 0 to 255!");
      }

      // check the min ticks
      if (!minTicks.containsKey(name)) {
        minTicks.put(name, t1);
      } else {
        if (minTicks.get(name) > t1) {
          minTicks.put(name, t1);
        }
      }

      // check the max ticks
      if (!maxTicks.containsKey(name)) {
        maxTicks.put(name, t2);
      } else {
        if (maxTicks.get(name) < t2) {
          maxTicks.put(name, t2);
        }
      }

      // if name is not in the map, create a new list. Otherwise, add value in the existing list.
      if (this.shapeTrans.containsKey(name)) {
        this.shapeTrans.get(name).add(new Transform(t1, x1, y1, w1, h1, r1, g1, b1, t2, x2, y2, w2,
                h2, r2, g2, b2));
      } else {
        List<Transform> value = new ArrayList<>();
        this.shapeTrans.put(name, value);
        value.add(new Transform(t1, x1, y1, w1, h1, r1, g1, b1, t2, x2, y2, w2,
                h2, r2, g2, b2));
      }
      return this;
    }
  }
}
