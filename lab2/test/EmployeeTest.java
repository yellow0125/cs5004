import static org.junit.Assert.assertEquals;

/**
 * A testing class for employee class.
 */
public class EmployeeTest {
  private Employee lily;

  @org.junit.Before
  public void setUp() {
    this.lily = new Employee("Lily", 3.56);

  }

  @org.junit.Test
  public void getName() {
    assertEquals("Lily", lily.getName());

  }

  @org.junit.Test
  public void getPayRate() {
    assertEquals(3.56, lily.getPayRate(), 0.01);
  }

  @org.junit.Test
  public void addHoursWorked() {
    double hour = 44.5;
    assertEquals(44.5, lily.addHoursWorked(hour), 0.01);

  }

  @org.junit.Test
  public void getWorkHour() {
    assertEquals(lily.addHoursWorked(44.5), lily.getWorkHour(), 0.01);
  }

  @org.junit.Test
  public void resetHoursWorked() {
    assertEquals(0, lily.resetHoursWorked(), 0.01);

  }

  @org.junit.Test
  public void getWeeklyCheck() {
    double newHour = 66.2;
    lily.addHoursWorked(newHour);
    lily.getWeeklyCheck();
    //PayCheck payLily (this.lily.getName(), this.lily.getPayRate(), this.lily.getWorkHour());
    assertEquals("Lily", this.lily.getWeeklyCheck().getName());
    assertEquals(3.56, this.lily.getWeeklyCheck().getPayRate(), 0.01);
    assertEquals(66.2, this.lily.getWeeklyCheck().getWorkHour(), 0.01);

  }

  @org.junit.Test
  public void testToString() {
    assertEquals("Lily", this.lily.getName());
  }
}