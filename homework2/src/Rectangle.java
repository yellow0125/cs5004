import java.util.NoSuchElementException;

/**
 * @ Program: homework2
 * @ Date: 2021/9/28
 * @ Description: This class represents a rectangle.
 * The rectangle is represented by its lower-left corner (x,y), its width and
 * its height.The above list corresponds to checking if two rectangles overlap,
 * determine the intersection of two rectangles and the union of two rectangles.
 */
public class Rectangle {
  private int x;
  private int y;
  private int width;
  private int height;

  /**
   * a constructor that creates a rectangle using the x, y coordina.
   *
   * @param x      the value x of this Rectangle.
   * @param y      the value yof this Rectangle.
   * @param width  the value width of this Rectangle.
   * @param height the value height of this Rectangle.
   * @throws IllegalStateException when width or height <1.
   */
  public Rectangle(int x, int y, int width, int height) throws IllegalArgumentException {
    if (width < 1 || height < 1) {
      throw new IllegalArgumentException("Non-positive width or height is not allowed");
    }
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  /**
   * This method should return true if this rectangle overlaps with other,
   * false otherwise.
   *
   * @return ture or false for different situations.
   */
  public boolean overlap(Rectangle other) {

    int x1 = this.x + this.width;
    int y1 = this.y + this.height;
    int x2 = other.x + other.width;
    int y2 = other.y + other.height;
    return other.x < x1 && other.y < y1 && x2 > this.x && y2 > this.y;
  }

  /**
   * This method put 4 numbers into an array and
   * sort them from smallest to largest.
   *
   * @return an array.
   */
  private int[] order(int num1, int num2, int num3, int num4) {
    int[] arr = {num1, num2, num3, num4};
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr.length - i - 1; j++) {
        if (arr[j] > arr[j + 1]) {
          int front = arr[j + 1];
          arr[j + 1] = arr[j];
          arr[j] = front;
        }
      }
    }
    return arr;
  }

  /**
   * This method return a Rectangle object that represents the overlap of the
   * two rectangles. If no intersection exists, it should throw a NoSuchElementException
   * with a helpful message.
   *
   * @return a Rectangle object
   */
  public Rectangle intersect(Rectangle other) throws NoSuchElementException {
    int x1 = this.x + this.width;
    int y1 = this.y + this.height;
    int x2 = other.x + other.width;
    int y2 = other.y + other.height;

    if (!overlap(other)) {
      throw new NoSuchElementException("No intersection exists");
    }
    int[] arrX = order(this.x, x1, other.x, x2);
    int[] arrY = order(this.y, y1, other.y, y2);
    int newWidth = arrX[2] - arrX[1];
    int newHeight = arrY[2] - arrY[1];
    int newX = arrX[1];
    int newY = arrY[1];

    return new Rectangle(newX, newY, newWidth, newHeight);
  }

  /**
   * Returns a Rectangle object that represents the
   * union of this rectangle and the other rectangle.
   * The union is the smallest rectangle that contains both rectangles.
   * The union always exists.
   *
   * @return a Rectangle object
   */
  public Rectangle union(Rectangle other) {
    int x1 = this.x + this.width;
    int y1 = this.y + this.height;
    int x2 = other.x + other.width;
    int y2 = other.y + other.height;
    int[] arrX = order(this.x, x1, other.x, x2);
    int[] arrY = order(this.y, y1, other.y, y2);
    int newWidth = arrX[3] - arrX[0];
    int newHeight = arrY[3] - arrY[0];
    int newX = arrX[0];
    int newY = arrY[0];
    return new Rectangle(newX, newY, newWidth, newHeight);
  }

  /**
   * Return a string that describes this rectangle.
   *
   * @return a string that describes this rectangle.
   */
  public String toString() {
    String str1 = "x:" + this.x + ", ";
    String str2 = "y:" + this.y + ", ";
    String str3 = "w:" + this.width + ", ";
    String str4 = "h:" + this.height;

    return str1 + str2 + str3 + str4;
  }

}
