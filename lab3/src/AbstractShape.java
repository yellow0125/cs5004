/**
 * @ Program: lab3
 * @ Date: 2021/10/1
 * @ Description: AbstractShape is an abstract class for all different types of temperatures.
 * We represent temperature in Celsius, and when methods request other units.
 * We do the conversion in those methods.
 */
public abstract class AbstractShape implements Temperature {

  protected double temperatureInCelsius;

  /**
   * Initialize the fields for the AbstractShape. The input is given in Celsius.
   *
   * @param temperatureInCelsius A double representing the temperature in Celsius.
   * @throws IllegalArgumentException when input temperature in Celsius is
   *                                  smaller than  absolute zero.
   */
  public AbstractShape(double temperatureInCelsius) throws IllegalArgumentException {
    if (temperatureInCelsius <= Temperature.ABS_ZERO_C) {
      throw new IllegalArgumentException("Temperature must be greater than absolute 0.");
    }
    this.temperatureInCelsius = temperatureInCelsius;
  }

  //**
  //   * This method converts input double Fahrenheit to Celsius.
  //   * Initialize the fields of AbstractShape. The input is given in Fahrenheit but
  //   * converted to Celsius.
  //   *
  //   * @param temperatureInFahrenheit A double representing the temperature in Fahrenheit
  //   * @param isFahrenheit A boolean that is true when the input temperature in Fahrenheit
  //   *                                And it should always be true.
  //   * @throws IllegalArgumentException when the boolean whose value is not true.
  //   */
  //  public AbstractShape(double temperatureInFahrenheit, boolean isFahrenheit)
  //          throws IllegalArgumentException {
  //    this(AbstractShape.convertFahrenheitToCelsius(temperatureInFahrenheit));
  //    if (!isFahrenheit) {
  //      throw new IllegalArgumentException("Input boolean must be true");
  //    }
  //  }

  /**
   * This method converts input double Fahrenheit to Celsius.
   *
   * @param temperatureInFahrenheit temperature in fahrenheit.
   * @return temperature in celsius.
   */
  public static double convertFahrenheitToCelsius(double temperatureInFahrenheit) {
    return (temperatureInFahrenheit - 32.0) * (5.0 / 9.0);

  }

  @Override
  public int compareTo(Temperature o) {
    if (this.inCelsius() < o.inCelsius()) {
      return -1;
    } else if (this.inCelsius() > o.inCelsius()) {
      return 1;
    } else if (this.inCelsius() == o.inCelsius()) {
      return 0;
    }
    return 0;
  }

  @Override
  public double inCelsius() {
    return this.temperatureInCelsius;
  }

  @Override
  public double inFahrenheit() {
    return this.temperatureInCelsius * (9.0 / 5.0) + 32.0;
  }

  @Override
  public double inKelvin() {
    return this.temperatureInCelsius - Temperature.ABS_ZERO_C;
  }

  @Override
  public Temperature add(Temperature other) {
    return null;
  }

}
