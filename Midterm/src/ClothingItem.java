/**
 * @ Program: midterm
 * @ Date: 2021/10/26
 * @ Description: A class Represents one clothing item,
 * includes double price, double size, number of stock, and its collection.
 */
public class ClothingItem extends AbstractItem {

  /**
   * Initialize the fields for the clothing item.
   * The input is given the price, size, stock and collection of a clothing item.
   *
   * @param initialPrice A double representing the initial price of one ClothingItem
   * @param initialSize  A double representing the initial size of one ClothingItem
   * @param initialStock An integer representing the initial stock of one ClothingItem
   * @param collection   a collection representing the initial type of one ClothingItem
   */

  public ClothingItem(double initialPrice, double initialSize,
                      int initialStock, Collection collection) {
    super(initialPrice, initialSize, initialStock, collection);
  }

}
