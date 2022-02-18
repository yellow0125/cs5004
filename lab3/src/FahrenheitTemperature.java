/**
 * @ Program: lab3
 * @ Date: 2021/10/1
 * @ Description:  This class represents a temperature in Fahrenheit.
 * * It offers all the operations mandated by the AbstractShape interface.
 */
public class FahrenheitTemperature extends AbstractShape {

  /**
   * constructor that takes a single double representing temperature in Fahrenheit.
   *
   * @param temperatureInFahrenheit a temperature in Fahrenheit.
   */

  public FahrenheitTemperature(double temperatureInFahrenheit) {
    super(AbstractShape.convertFahrenheitToCelsius(temperatureInFahrenheit));
  }

  /**
   * constructor will take a double representing temperature in Celsius.
   * And a boolean whose value must be true.
   *
   * @param temperatureInCelsius a temperature inCelsius.
   * @param isCelsius            a boolean whose value must be true.
   * @throws IllegalArgumentException when the boolean's value is false throw it.
   */
  public FahrenheitTemperature(double temperatureInCelsius, boolean isCelsius)
          throws IllegalArgumentException {
    super(temperatureInCelsius);
    if (!isCelsius) {
      throw new IllegalArgumentException("Input boolean must be true");
    }
  }

  /**
   * Returns a string to present temperature in Fahrenheit.
   *
   * @return a string to present temperature in Fahrenheit
   */
  public String toString() {
    return String.format("%.1f° Fahrenheit", this.inFahrenheit());

  }

  @Override
  public Temperature add(Temperature other) {
    double result = (this.inFahrenheit() + other.inFahrenheit()) / 2;
    return new FahrenheitTemperature(result);
  }
}

