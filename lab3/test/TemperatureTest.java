import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for Temperature, both Celsius and Fahrenheit representations.
 */
public class TemperatureTest {

  private Random r;
  private Temperature cTemp;
  private Temperature fTemp;
  private Temperature cTemp1;
  private Temperature fTemp1;
  private Temperature cTemp2;
  private Temperature fTemp2;
  private Temperature cTemp3;
  private Temperature fTemp3;

  /**
   * Set up for initialize the temperature of Celsius and Fahrenheit.
   */
  @Before
  public void init() {
    r = new Random(8888);
    //all temperatures are positive.
    cTemp = new CelsiusTemperature(100);
    fTemp = new FahrenheitTemperature(100, true);
    cTemp1 = new CelsiusTemperature(212, true);
    fTemp1 = new FahrenheitTemperature(212);

    //all temperatures are negative.
    cTemp2 = new CelsiusTemperature(-100.5);
    fTemp2 = new FahrenheitTemperature(-100.5, true);
    cTemp3 = new CelsiusTemperature(-300.5, true);
    fTemp3 = new FahrenheitTemperature(-300.5);
  }

  /**
   * Test for invalid temperature in Fahrenheit.
   * Temperature in Fahrenheit should be more than -459.67.
   */

  @Test
  public void testForInvalidFTemp() {
    for (int i = 0; i < 1000; i++) {
      double num = Math.random() * (-200) - 459.67;
      try {
        new FahrenheitTemperature(num);
        fail("you should have caught exception");
      } catch (IllegalArgumentException ignored) {

      }
      try {
        new CelsiusTemperature(num, true);
        fail("you should have caught exception");
      } catch (IllegalArgumentException ignored) {

      }
    }
  }

  /**
   * Test for  the invalid temperatures
   * Temperature in Celsius should be more than -275.15.
   */

  @Test
  public void testForInvalidCTemp() {
    for (int i = 0; i < 1000; i++) {
      double num = Math.random() * (-200) - 273.15;
      try {
        new CelsiusTemperature(num);
        fail("you should have caught exception");
      } catch (IllegalArgumentException ignored) {

      }
      try {
        new FahrenheitTemperature(num, true);
        fail("you should have caught exception");
      } catch (IllegalArgumentException ignored) {

      }
    }
  }

  /**
   * Test for boolean argument in constructor.
   * Boolean should be true.
   */
  @Test
  public void testForInvalidBoolean() {
    for (int i = 0; i < 1000; i++) {
      double num = Math.random() * (1000) - 273.15;
      try {
        new CelsiusTemperature(num, false);
        fail("you should have caught exception");
      } catch (IllegalArgumentException ignored) {

      }
    }
    for (int j = 0; j < 1000; j++) {
      double num0 = Math.random() * (2000) - 459.67;
      try {
        new FahrenheitTemperature(num0, false);
        fail("you should have caught exception");
      } catch (IllegalArgumentException ignored) {

      }
    }
  }

  /**
   * Test temperature computing methods in CelsiusTemperature class.
   */
  @Test
  public void testObservers() {
    assertEquals(100, cTemp.inCelsius(), 0.001);
    assertEquals(212, cTemp.inFahrenheit(), 0.001);
    assertEquals(373.15, cTemp.inKelvin(), 0.001);

    //negative
    assertEquals(-100.5, cTemp2.inCelsius(), 0.001);
    assertEquals(-148.9, cTemp2.inFahrenheit(), 0.001);
    assertEquals(172.649, cTemp2.inKelvin(), 0.001);

    assertEquals(-184.722, cTemp3.inCelsius(), 0.001);
    assertEquals(-300.5, cTemp3.inFahrenheit(), 0.001);
    assertEquals(88.427, cTemp3.inKelvin(), 0.001);

  }

  /**
   * Test temperature computing methods in FahrenheitTemperature class.
   */
  @Test
  public void testInF() {
    assertEquals(100, fTemp.inCelsius(), 0.001);
    assertEquals(212, fTemp.inFahrenheit(), 0.001);
    assertEquals(373.15, fTemp.inKelvin(), 0.001);

    assertEquals(-100.5, fTemp2.inCelsius(), 0.001);
    assertEquals(-148.9, fTemp2.inFahrenheit(), 0.001);
    assertEquals(172.649, fTemp2.inKelvin(), 0.001);

    assertEquals(-184.722, fTemp3.inCelsius(), 0.001);
    assertEquals(-300.5, fTemp3.inFahrenheit(), 0.001);
    assertEquals(88.427, fTemp3.inKelvin(), 0.001);
  }

  /**
   * Test add method.
   */
  @Test
  public void testAdd() {
    // test your add() method here according to its specification
    Temperature t1;
    Temperature t2;
    r = new Random(8888);
    for (int i = 0; i < 1000; i++) {
      t1 = new CelsiusTemperature(r.nextDouble() * 100);
      t2 = new FahrenheitTemperature(r.nextDouble() * 212);
      Temperature t3 = t1.add(t2);
      double expected1 = (t1.inCelsius() + t2.inCelsius()) / 2;
      double actual1 = t3.inCelsius();
      double expected2 = (t1.inFahrenheit() + t2.inFahrenheit()) / 2;
      double actual2 = t3.inFahrenheit();
      assertEquals(actual1, expected1, 0.001);
      assertEquals(actual2, expected2, 0.001);
    }
  }

  /**
   * Test toString method.
   */
  @Test
  public void testToString() {
    assertEquals("100.0° Celsius", cTemp.toString());
    assertEquals("212.0° Fahrenheit", fTemp.toString());

    assertEquals("-100.5° Celsius", cTemp2.toString());
    assertEquals("-148.9° Fahrenheit", fTemp2.toString());
  }

  /**
   * Test compareTo method.
   * Compare only in celsius or fahrenheit.
   * And  cross comparison
   */
  @Test
  public void testCompare() {
    Temperature c1;
    Temperature c2;
    Temperature c3;
    Temperature f1;
    Temperature f2;
    Temperature f3;
    r = new Random(8888);
    for (int i = 0; i < 1000; i++) {
      c1 = new CelsiusTemperature(r.nextDouble() * 100);
      c2 = new CelsiusTemperature(r.nextDouble() * 200 + 100);
      c3 = new CelsiusTemperature(-1 * r.nextDouble() * 100);
      f1 = new FahrenheitTemperature(r.nextDouble() * 212);
      f2 = new FahrenheitTemperature(r.nextDouble() * 424 + 212);
      f3 = new FahrenheitTemperature(-1 * r.nextDouble() * 212);
      // compare only in celsius or fahrenheit
      assertEquals(-1, c1.compareTo(c2));
      assertEquals(1, c2.compareTo(c1));
      assertEquals(1, c1.compareTo(c3));
      assertEquals(-1, c3.compareTo(c1));
      assertEquals(1, c2.compareTo(c3));
      assertEquals(-1, c3.compareTo(c2));
      assertEquals(0, cTemp.compareTo(cTemp1));
      assertEquals(0, cTemp1.compareTo(cTemp));

      assertEquals(-1, f1.compareTo(f2));
      assertEquals(1, f2.compareTo(f1));
      assertEquals(1, f1.compareTo(f3));
      assertEquals(-1, f3.compareTo(f1));
      assertEquals(1, f2.compareTo(f3));
      assertEquals(-1, f3.compareTo(f2));
      assertEquals(0, fTemp.compareTo(fTemp1));
      assertEquals(0, fTemp1.compareTo(fTemp));

      //Cross comparison
      assertEquals(-1, c1.compareTo(f2));
      assertEquals(1, f2.compareTo(c1));
      assertEquals(1, c1.compareTo(f3));
      assertEquals(-1, f3.compareTo(c1));
      assertEquals(1, c2.compareTo(f1));
      assertEquals(-1, f1.compareTo(c2));
      assertEquals(1, c2.compareTo(f3));
      assertEquals(-1, f3.compareTo(c2));
      assertEquals(-1, c3.compareTo(f2));
      assertEquals(1, f2.compareTo(c3));
    }
  }
}