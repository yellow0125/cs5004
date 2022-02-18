/**
 * @ Program: lab02
 * @ Date: 2021/9/17
 * @ Description: This a class represents a single hourly employee.
 * The employee has a name, the number of hours he/she worked  in a given
 * week, and his/her pay rate.
 */

public class Employee {
  private String name;
  private double workHour;
  private double payRate;
  //private PayCheck weeklyCheck;

  /**
   * An employee constructor that takes a name and pay rate.
   *
   * @param name    The name of the Employee
   * @param payRate The pay rate of the Employee
   */
  public Employee(String name, double payRate) {
    this.name = name;
    this.payRate = payRate;
    this.workHour = 0.00;
  }

  /**
   * Return the name of the employee.
   *
   * @return the name of the employee
   */
  public String getName() {
    return this.name;
  }

  /**
   * Return the pay rate of the employee.
   *
   * @return the pay rate of the employee
   */
  public double getPayRate() {
    return this.payRate;
  }

  /**
   * Adds the input hours to the total hours worked this week.
   *
   * @param newHour The new hours to add  to the total
   * @return the employee's new work hours
   *
   */
  public double addHoursWorked(double newHour) {
    this.workHour += newHour;
    return this.workHour;
  }

  /**
   * Return the work hours of the object.
   *
   * @Return: the work hours of the object
   */
  public double getWorkHour() {
    return this.workHour;
  }

  /**
   * Resets the employee's hours worked for the week to zero.
   *
   * @return the employee's new work hour.
   */
  public double resetHoursWorked() {
    this.workHour = 0;
    return this.workHour;

  }

  /**
   * Get the py check.
   *
   * @return: a new PayCheck object
   */

  public PayCheck getWeeklyCheck() {
    return new PayCheck(this.getName(), this.getPayRate(), this.getWorkHour());

  }

  public String toString() {
    return this.name;

  }
}


