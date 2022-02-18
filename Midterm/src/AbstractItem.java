/**
 * This represents an abstract item that will be in a web store.
 * You are a developer for the web store and noticed that
 * the original developer did not use proper abstraction.
 * You need to correct this problem.
 * You need to look at the specific items:
 * KitchenItem
 * ClothingItem
 *
 * <p>and determine what should be moved into the abstract class.
 * Then properly add javadoc to all methods in the
 * abstract class and subclasses.
 * You will need to turn in this file, along with the properly
 * modified version of the other files so that they can make use
 * of all the fields and methods of this AbstractClass.
 */
public abstract class AbstractItem {
  protected double price;
  protected double size;
  protected int stock;
  protected Collection collection;

  /**
   * Initialize the fields for the AbstractItem.
   * The input is given the price, size, stock and collection of an item in web store.
   * This if for all items which has a property of collection.
   *
   * @param initialPrice A double representing the initial price of one ClothingItem
   * @param initialSize  A double representing the initial size of one ClothingItem
   * @param initialStock An integer representing the initial stock of one ClothingItem
   * @param collection   a collection representing the initial type of one ClothingItem
   */

  public AbstractItem(double initialPrice, double initialSize,
                      int initialStock, Collection collection) {
    this.price = initialPrice;
    this.size = initialSize;
    this.stock = initialStock;
    this.collection = collection;
  }

  /**
   * Initialize the fields for the AbstractItem.
   * The input is given the price, size, stock and collection of an item in web store.
   * This if for all items without a property of collection.
   *
   * @param initialPrice A double representing the initial price of one ClothingItem
   * @param initialSize  A double representing the initial size of one ClothingItem
   * @param initialStock An integer representing the initial stock of one ClothingItem
   */
  public AbstractItem(double initialPrice, double initialSize, int initialStock) {
    this.price = initialPrice;
    this.size = initialSize;
    this.stock = initialStock;
  }

  /**
   * Get the current price of the abstract item.
   *
   * @return the current price of the abstract item.
   */

  public double getPrice() {
    return this.price;
  }

  /**
   * Get the current  size( weight) of the abstract item.
   *
   * @return the size( weight) of the abstract item.
   */

  public double getSize() {
    return this.size;
  }

  /**
   * Get the current stock of the abstract item.
   *
   * @return the stock of the abstract item.
   */

  public boolean inStock() {
    return this.stock > 0;
  }

  /**
   * Get the collection of the abstract item.
   *
   * @return the collection of the abstract item.
   */

  public Collection getCollection() {
    return this.collection;
  }

  /**
   * The method represents to get the new stock when we have sold n of the abstract item.
   * Throws an illegal argument when n is bigger than the stock of item.
   *
   * @throws IllegalArgumentException when input nSold is bigger than the stock of item.
   */

  public void sellItem(int nSold) throws IllegalArgumentException {
    if (nSold > this.stock) {
      throw new IllegalArgumentException("We cannot sell more than are in stock.");
    }
    this.stock = this.stock - nSold;
  }

  /**
   * Get the value of the stock of item.
   *
   * @return the value of the stock of one item.
   */

  public double valueOfStock() {
    return this.price * this.stock;
  }
}
