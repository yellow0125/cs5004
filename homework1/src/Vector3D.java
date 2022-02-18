

/**
 * homework01
 * 2021/9/19
 * Description: This class represents a 3D vector.
 * It is commonly represented as three components: x, y and z.
 */
public class Vector3D {
  private double x;
  private double y;
  private double z;

  /**
   * This a constructor that takes in x, y, z components of the vector .
   * A vector 3d is signified by a direction and a length (magnitude)
   *
   * @param x one of components of a vector in 3d
   * @param y one of components of a vector in 3d
   * @param z one of components of a vector in 3d
   */

  public Vector3D(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  /**
   * Description These are the methods to get the values of individual components (x, y, z).
   *
   * @return: double
   */
  public double getX() {
    return this.x;
  }

  public double getY() {
    return this.y;
  }

  public double getZ() {
    return this.z;
  }

  /**
   * Description: This is a toString method that returns a string that describes this vector.
   *
   * @Return: java.lang.String
   */
  public String toString() {
    String str;
    str = String.format("(%.2f,%.2f,%.2f)", this.x, this.y, this.z);
    System.out.println(str);
    return str;
  }

  /**
   * Description: This is the method that returns vector's magnitude.
   *
   * @Return: double  the magnitude of the vector in 3d
   */

  public double getMagnitude() {
    double total;
    total = (this.x * this.x) + (this.y * this.y)
            + (this.z * this.z);
    return Math.abs(Math.sqrt(total));
  }

  /**
   * Description: A method normalize that returns a normalized version of this vector.
   *
   * @Param: v  a normalized version of this vector.
   * @Return: Vector3D
   */

  public Vector3D normalize() throws IllegalStateException {
    if (this.getMagnitude() == 0) {
      throw new IllegalStateException("Magnitude cannot be 0!");
    }
    double length = this.getMagnitude();
    return new Vector3D(this.x / length, this.y / length, this.z / length);
  }

  /**
   * Description: A method add that returns the result of adding this vector to another vector.
   *
   * @Param: a one vector  named a
   * @Param: b  one vector named b
   * @Return: Vector3D a new  vector3d
   */

  public Vector3D add(Vector3D other) {
    double newX = this.x + other.getX();
    double newY = this.y + other.getY();
    double newZ = this.z + other.getZ();

    return new Vector3D(newX, newY, newZ);
  }

  /**
   * Description: A method multiply that returns the result of multiplying this vector.
   *
   * @Param: num a constant
   * @Param: a  one vector  named a
   * @Return: Vector3D a new  vector3d
   */

  public Vector3D multiply(double num) {
    double newX = this.x * num;
    double newY = this.y * num;
    double newZ = this.z * num;
    return new Vector3D(newX, newY, newZ);
  }

  /**
   * Description: A method returns the dot product of this vector and another vector.
   *
   * @Param: a one vector  named a
   * @Param: b  one vector  named b
   * @Return: double  the dot product of this vector and another vector
   */

  public double dotProduct(Vector3D other) {
    double newX = this.x * other.getX();
    double newY = this.y * other.getY();
    double newZ = this.z * other.getZ();
    return newX + newY + newZ;
  }

  /**
   * Description: A method angleBetween that returns the angle between two vectors in degrees.
   *
   * @Param: from   one vector  named from
   * @Param: to  one vector  named to
   * @Return: double  the angle between two vectors in degrees
   */

  public double angleBetween(Vector3D other) throws IllegalStateException {
    if (this.getMagnitude() == 0 || other.getMagnitude() == 0) {
      throw new IllegalStateException("Magnitude cannot be 0!");
    }
    double dot = this.dotProduct(other);
    double number = dot / (this.getMagnitude() * other.getMagnitude());
    return Math.acos(number) / Math.PI * 180;
  }

}
