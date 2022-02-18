/**
 * @ Program: midterm
 * @ Date: 2021/10/26
 * @ Description: A class Represents one kitchen item.
 * includes double price, the number of stock, and double weight
 */
public class KitchenItem extends AbstractItem {

  /**
   * Initialize the fields for the kitchen item.
   * The input is given the price, size and stock of a kitchen item.
   *
   * @param initialPrice  A double representing the initial price of one kitchenItem
   * @param initialWeight A double representing the initial weight(size) of one kitchenItem
   * @param initialStock  An integer representing the initial stock of one kitchenItem
   */
  public KitchenItem(double initialPrice, double initialWeight, int initialStock) {
    super(initialPrice, initialWeight, initialStock);
  }

}
