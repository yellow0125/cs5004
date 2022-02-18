import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*
 * @Program: lab1
 * @Date: 2021/9/26
 * @ Description:
 */public class DogTest {
  private Dog dog1;
  private Dog dog2;

  @Before
  public void setUp() {
    this.dog1 = new Dog("Sally", 2, "Border Collie", 12.5,
            50.0, true);
    this.dog2 = new Dog("John", 5, "French bulldog", 4.6,
            30, false);

  }

  @Test
  public void testName() {
    assertEquals("Sally", this.dog1.getName());
    assertEquals("John", this.dog2.getName());
  }

  @Test
  public void testAge() {
    assertEquals(2, this.dog1.getAge());
    assertEquals(5, this.dog2.getAge());
  }

  @Test
  public void testType() {
    assertEquals("Border Collie", this.dog1.getType());
    assertEquals("French bulldog", this.dog2.getType());
  }

  @Test
  public void testWeight() {
    assertEquals(12.5, this.dog1.getWeight(), 0.01);
    assertEquals(4.6, this.dog2.getWeight(), 0.01);
  }

  @Test
  public void testLength() {
    assertEquals(50, this.dog1.getLength(), 0.01);
    assertEquals(30, this.dog2.getLength(), 0.01);
  }

  @Test
  public void testToString() {

    String str1 = String.format("name: %s, age: %d, type: %s, weight: %.2f, "
                    + "length: %.2f, adopted: %b\n", "Sally", 2, "Border Collie", 12.5,
            50.0, true);
    String str2 = String.format("name: %s, age: %d, type: %s, weight: %.2f, "
                    + "length: %.2f, adopted: %b\n", "John", 5, "French bulldog", 4.6,
            30.0, false);

    assertEquals(str1, this.dog1.toString());
    assertEquals(str2, this.dog2.toString());
  }

}