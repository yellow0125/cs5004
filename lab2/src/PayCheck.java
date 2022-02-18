/**
 * @ program: lab02
 * @ Date: 2021/9/17
 * @ Description: This a class calculates employee pay for the week.
 * The pay check knows  an employee name, a rate and the hours worked.
 */
public class PayCheck {
  private String name;
  private double payRate;
  private double workHour;
  private double total;

  /**
   * This metho takes the employee name, rate, and hours worked as parameters
   * and calculates (and stores) the total pay for the week.
   *
   * @param name     the employee's name
   * @param payRate  the pay rate of each employee.
   * @param workHour the weekly work hour of each employee.
   */

  public PayCheck(String name, double payRate, double workHour) {
    this.name = name;
    this.payRate = payRate;
    this.workHour = workHour;
    if (workHour <= 40) {
      total = this.payRate * this.workHour;
    } else if (workHour > 40) {
      total = this.payRate * 40 + this.payRate * 1.5 * (this.workHour - 40);
    }

  }

  /**
   * Returns the total pay for the week.
   *
   * @return: returns the total pay for the week.
   */

  public double getTotalPay() {
    return total;
  }

  /**
   * PayCheck objects by the totalPay (in proper dollars/cents $xxx.yy format).
   *
   * @return: java.lang.String
   */

  public String toString() {
    String str;
    str = String.format("$%.2f", total);
    return str;
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
   * Return the work hours of the object.
   *
   * @Return: the work hours of the object
   */

  public double getWorkHour() {
    return this.workHour;
  }
}
