import static org.junit.Assert.assertEquals;

/**
 * Program: homework01
 * Date: 2021/9/19
 *
 * @ Description: JUnit test class for Vectors.
 */
public class Vector3DTest {

  private double x1 = 2.00;
  private double y1 = 3.00;
  private double z1 = 4.00;
  private Vector3D game = new Vector3D(x1, y1, z1);

  private double x2 = 0.00;
  private double y2 = 6.00;
  private double z2 = 7.00;
  private Vector3D music = new Vector3D(x2, y2, z2);

  private double x3 = 0.00;
  private double y3 = 0.00;
  private double z3 = 0.00;
  private Vector3D illegal = new Vector3D(x3, y3, z3);

  private double x4 = 2.5;
  private double y4 = 4.9898989;
  private double z4 = 9.00;
  private Vector3D myVector = new Vector3D(x4, y4, z4);

  private double x5 = -2.00;
  private double y5 = -3.00;
  private double z5 = -4.00;
  private Vector3D play = new Vector3D(x5, y5, z5);

  private double x6 = 10.00;
  private double y6 = 0.00;
  private double z6 = 0.0263;
  private Vector3D angleV1 = new Vector3D(x6, y6, z6);

  private double x7 = -10.00;
  private double y7 = 0.00;
  private double z7 = -0.0263;
  private Vector3D angleV2 = new Vector3D(x7, y7, z7);

  /**
   * Test method getX.
   */

  @org.junit.Test
  public void getX() {
    assertEquals(x1, game.getX(), 0.01);
    assertEquals(x2, music.getX(), 0.01);
  }

  /**
   * Test method getY.
   */

  @org.junit.Test
  public void getY() {
    assertEquals(y1, game.getY(), 0.01);
    assertEquals(y2, music.getY(), 0.01);
  }

  /**
   * Test method getZ.
   */

  @org.junit.Test
  public void getZ() {
    assertEquals(z1, game.getZ(), 0.01);
    assertEquals(z2, music.getZ(), 0.01);
  }

  /**
   * Test method toString.
   */

  @org.junit.Test
  public void testToString() {
    String list = String.format("(%.2f,%.2f,%.2f)", x1, y1, z1);
    String list2 = String.format("(%.2f,%.2f,%.2f)", x4, y4, z4);
    assertEquals(list, game.toString());
    assertEquals(list2, myVector.toString());
  }

  /**
   * Test method getMagnitude.
   */

  @org.junit.Test
  public void testGetMagnitude() {
    double num1 = Math.sqrt(x1 * x1 + y1 * y1 + z1 * z1);
    assertEquals(num1, game.getMagnitude(), 0.01);
  }

  /**
   * Test method normalize.
   */

  @org.junit.Test
  public void testNormalize() {
    double len1 = Math.sqrt(29);
    double len2 = Math.sqrt(85);
    Vector3D v1 = new Vector3D(2 / len1, 3 / len1, 4 / len1);
    Vector3D v2 = new Vector3D(0 / len2, 6 / len2, 7 / len2);
    assertEquals(v1.getX(), game.normalize().getX(), 0.01);
    assertEquals(v1.getY(), game.normalize().getY(), 0.01);
    assertEquals(v1.getZ(), game.normalize().getZ(), 0.01);
    assertEquals(v2.getX(), music.normalize().getX(), 0.01);
    assertEquals(v2.getY(), music.normalize().getY(), 0.01);
    assertEquals(v2.getZ(), music.normalize().getZ(), 0.01);
  }

  /**
   * Test method normalize with illegal state exception when the vector's
   * magnitude equals 0.
   */

  @org.junit.Test
          (expected = IllegalStateException.class)
  public void testIllegalNormalize() {
    illegal.normalize();
  }

  /**
   * Test method add.
   */

  @org.junit.Test
  public void testAdd() {
    Vector3D v3 = new Vector3D(x1 + x2, y1 + y2, z1 + z2);
    assertEquals(v3.getX(), game.add(music).getX(), 0.01);
    assertEquals(v3.getY(), game.add(music).getY(), 0.01);
    assertEquals(v3.getZ(), game.add(music).getZ(), 0.01);
    Vector3D v4 = new Vector3D(x1 + x3, y1 + y3, z1 + z3);
    assertEquals(v4.getX(), game.add(illegal).getX(), 0.01);
    assertEquals(v4.getY(), game.add(illegal).getY(), 0.01);
    assertEquals(v4.getZ(), game.add(illegal).getZ(), 0.01);
    Vector3D v5 = new Vector3D(x1 + x5, y1 + y5, z1 + z5);
    assertEquals(v5.getX(), game.add(play).getX(), 0.01);
    assertEquals(v5.getY(), game.add(play).getY(), 0.01);
    assertEquals(v5.getZ(), game.add(play).getZ(), 0.01);
  }

  /**
   * Test method multiply.
   */

  @org.junit.Test
  public void testMultiply() {
    Vector3D v6 = game.multiply(10);
    assertEquals(10 * x1, v6.getX(), 0.01);
    assertEquals(10 * y1, v6.getY(), 0.01);
    assertEquals(10 * z1, v6.getZ(), 0.01);
    Vector3D v7 = game.multiply(0);
    assertEquals(0 * x1, v7.getX(), 0.01);
    assertEquals(0 * y1, v7.getY(), 0.01);
    assertEquals(0 * z1, v7.getZ(), 0.01);
  }

  /**
   * Test method dotProduct.
   */

  @org.junit.Test
  public void testDotProduct() {
    double dotProduct1 = game.dotProduct(music);
    double number = x1 * x2 + y1 * y2 + z1 * z2;
    assertEquals(number, dotProduct1, 0.01);
  }

  /**
   * Test method angelBetween.
   */

  @org.junit.Test
  public void testAngleBetween() {
    double angle1 = game.angleBetween(music);
    double sum1 = Math.pow(x1, 2) + Math.pow(y1, 2) + Math.pow(z1, 2);
    double sum2 = Math.pow(x2, 2) + Math.pow(y2, 2) + Math.pow(z2, 2);
    double num1 = Math.sqrt(sum1);
    double num2 = Math.sqrt(sum2);
    double dotP1 = x1 * x2 + y1 * y2 + z1 * z2;
    assertEquals(180 * Math.acos(dotP1 / (num1 * num2)) / Math.PI, angle1, 0.01);

    double angle2 = music.angleBetween(play);
    double sum4 = Math.pow(x2, 2) + Math.pow(y2, 2) + Math.pow(z2, 2);
    double sum5 = Math.pow(x5, 2) + Math.pow(y5, 2) + Math.pow(z5, 2);
    double num4 = Math.sqrt(sum4);
    double num5 = Math.sqrt(sum5);
    double dotP2 = x2 * x5 + y2 * y5 + z2 * z5;
    assertEquals(180 * Math.acos(dotP2 / (num4 * num5)) / Math.PI, angle2, 0.01);

    double angle3 = angleV1.angleBetween(angleV2);
    double sum6 = Math.pow(x6, 2) + Math.pow(y6, 2) + Math.pow(z6, 2);
    double sum7 = Math.pow(x7, 2) + Math.pow(y7, 2) + Math.pow(z7, 2);
    double num6 = Math.sqrt(sum6);
    double num7 = Math.sqrt(sum7);
    double dotP3 = x6 * x7 + y6 * y7 + z6 * z7;
    assertEquals(180 * Math.acos(dotP3 / (num6 * num7)) / Math.PI, angle3, 0.01);
  }

  /**
   * Test method angleBetween with illegal state exception when any of the two
   * vectors' magnitude equals 0.
   */

  @org.junit.Test
          (expected = IllegalStateException.class)
  public void testIllegalAngleBetween() {
    game.angleBetween(illegal);
    music.angleBetween(illegal);
    illegal.angleBetween(game);
  }

}