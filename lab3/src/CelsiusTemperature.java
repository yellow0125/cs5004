/**
 * @ Program: lab3
 * @ Date: 2021/10/1
 * @ Description: This class represents a temperature in Celsius.
 * It offers all the operations mandated by the AbstractShape interface.
 */
public class CelsiusTemperature extends AbstractShape {

  /**
   * constructor that takes a single double representing temperature in Celsius;.
   *
   * @param temperatureInCelsius a temperature in Celsius
   */

  public CelsiusTemperature(double temperatureInCelsius) {
    super(temperatureInCelsius);
  }

  /**
   * constructor will take a double representing temperature in Celsius.
   * And a boolean whose value must be true.
   *
   * @param temperatureInFahrenheit a temperature inCelsius.
   * @param isFahrenheit            a boolean whose value must be true.
   * @throws IllegalArgumentException when the boolean's value is false throw it.
   */

  public CelsiusTemperature(double temperatureInFahrenheit, boolean isFahrenheit)
          throws IllegalArgumentException {
    this(AbstractShape.convertFahrenheitToCelsius((temperatureInFahrenheit)));
    if (!isFahrenheit) {
      throw new IllegalArgumentException("Input boolean must be true");
    }
  }

  /**
   * Returns a string to present temperature in Celsius.
   *
   * @return a string to present temperature in Celsius
   */

  public String toString() {
    return String.format("%.1f° Celsius", this.inCelsius());

  }

  @Override
  public Temperature add(Temperature other) {
    double result = (this.inCelsius() + other.inCelsius()) / 2;
    return new CelsiusTemperature(result);
  }
}
