import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @ Description:Tests for Abstract items, include two specific items, clothing and kitchen.
 */
public class AbstractItemTest {

  AbstractItem shorts = new ClothingItem(51.2, 35.0, 20, Collection.SUMMER);
  AbstractItem fifthPants = new ClothingItem(66.8, 55.0, 1, Collection.SPRING);
  AbstractItem trousers = new ClothingItem(129.0, 110.0, 5, Collection.WINTER);
  AbstractItem flatware = new KitchenItem(9.9, 2.6, 100);
  AbstractItem oven = new KitchenItem(339.8, 99.8, 8);
  AbstractItem cookware = new KitchenItem(144.5, 56.7, 50);

  /**
   * Test for get the price of the abstract item.
   */

  @Test
  public void testGetPrice() {
    assertEquals(51.2, shorts.getPrice(), 0.01);
    assertEquals(66.8, fifthPants.getPrice(), 0.01);
    assertEquals(129.0, trousers.getPrice(), 0.01);
    assertEquals(9.9, flatware.getPrice(), 0.01);
    assertEquals(339.8, oven.getPrice(), 0.01);
    assertEquals(144.5, cookware.getPrice(), 0.01);
    for (int i = 0; i < 100000; i++) {
      double x1 = Math.random() * 1000 + 1;
      double y1 = Math.random() * 1000 + 1;
      int z1 = (int) (Math.random() * 1000 + 1);
      double x2 = Math.random() * 1000 + 1;
      double y2 = Math.random() * 1000 + 1;
      int z2 = (int) (Math.random() * 1000 + 1);
      AbstractItem clothingItem = new ClothingItem(x1, y1, z1, Collection.FALL);
      AbstractItem kitchenItem = new KitchenItem(x2, y2, z2);
      assertEquals(clothingItem.price, clothingItem.getPrice(), 0.01);
      assertEquals(kitchenItem.price, kitchenItem.getPrice(), 0.01);
    }
  }

  /**
   * Test for get the size( weight) of the abstract item.
   */
  @Test
  public void testGetSize() {
    assertEquals(35.0, shorts.getSize(), 0.01);
    assertEquals(55.0, fifthPants.getSize(), 0.01);
    assertEquals(110.0, trousers.getSize(), 0.01);
    assertEquals(2.6, flatware.getSize(), 0.01);
    assertEquals(99.8, oven.getSize(), 0.01);
    assertEquals(56.7, cookware.getSize(), 0.01);
    for (int i = 0; i < 100000; i++) {
      double x1 = Math.random() * 1000 + 1;
      double y1 = Math.random() * 1000 + 1;
      int z1 = (int) (Math.random() * 1000 + 1);
      double x2 = Math.random() * 1000 + 1;
      double y2 = Math.random() * 1000 + 1;
      int z2 = (int) (Math.random() * 1000 + 1);
      AbstractItem clothingItem = new ClothingItem(x1, y1, z1, Collection.WINTER);
      AbstractItem kitchenItem = new KitchenItem(x2, y2, z2);
      assertEquals(clothingItem.size, clothingItem.getSize(), 0.01);
      assertEquals(kitchenItem.size, kitchenItem.getSize(), 0.01);
    }
  }

  /**
   * Test for determine whether the item is in stock or not.
   */

  @Test
  public void testInStock() {
    assertTrue(shorts.inStock());
    assertTrue(fifthPants.inStock());
    assertTrue(trousers.inStock());
    assertTrue(flatware.inStock());
    assertTrue(oven.inStock());
    assertTrue(cookware.inStock());
    for (int i = 0; i < 100000; i++) {
      double x1 = Math.random() * 1000 + 1;
      double y1 = Math.random() * 1000 + 1;
      int z1 = (int) (Math.random() * (-1000 + 1));
      double x2 = Math.random() * 1000 + 1;
      double y2 = Math.random() * 1000 + 1;
      int z2 = (int) (Math.random() * (-1000 + 1));
      AbstractItem clothingItem = new ClothingItem(x1, y1, z1, Collection.WINTER);
      AbstractItem kitchenItem = new KitchenItem(x2, y2, z2);
      assertFalse(clothingItem.inStock());
      assertFalse(kitchenItem.inStock());
    }
  }

  /**
   * Test for get the collection of the abstract item.
   */

  @Test
  public void getCollection() {
    assertEquals(shorts.collection, shorts.getCollection());
    assertEquals(fifthPants.collection, fifthPants.getCollection());
    assertEquals(trousers.collection, trousers.getCollection());
  }

  /**
   * Test for illegal argument for selling item. We cannot sell more than are in stock.
   */
  @Test
  public void illegalSellItem() {
    for (int i = 0; i < 100000; i++) {
      double x1 = Math.random() * 1000 + 1;
      double y1 = Math.random() * 1000 + 1;
      int z1 = (int) (Math.random() * 100 + 1);
      double x2 = Math.random() * 1000 + 1;
      double y2 = Math.random() * 1000 + 1;
      int z2 = (int) (Math.random() * 100 + 1);
      AbstractItem clothingItem = new ClothingItem(x1, y1, z1, Collection.WINTER);
      AbstractItem kitchenItem = new KitchenItem(x2, y2, z2);

      int nSold1 = (int) (Math.random() * 1000 + 101);
      int nSold2 = (int) (Math.random() * 1000 + 101);
      try {
        clothingItem.sellItem(nSold1);
        fail("We cannot sell more than are in stock.");
      } catch (IllegalArgumentException ignore) {
        //catch illegal argument exception
      }
      try {
        kitchenItem.sellItem(nSold2);
        fail("We cannot sell more than are in stock.");
      } catch (IllegalArgumentException ignore) {
        //catch illegal argument exception
      }
    }
  }

  /**
   * Test for selling abstract item with the input number sold.
   */

  @Test
  public void sellItem() {
    for (int i = 0; i < 100000; i++) {
      double x1 = Math.random() * 1000 + 1;
      double y1 = Math.random() * 1000 + 1;
      int z1 = (int) (Math.random() * 1000 + 101);
      double x2 = Math.random() * 1000 + 1;
      double y2 = Math.random() * 1000 + 101;
      int z2 = (int) (Math.random() * 1000 + 101);
      AbstractItem clothingItem = new ClothingItem(x1, y1, z1, Collection.WINTER);
      AbstractItem kitchenItem = new KitchenItem(x2, y2, z2);

      int nSold1 = (int) (Math.random() * 100);
      int nSold2 = (int) (Math.random() * 100);
      clothingItem.sellItem(nSold1);
      kitchenItem.sellItem(nSold2);
      assertEquals(z1 - nSold1, clothingItem.stock);
      assertEquals(z2 - nSold2, kitchenItem.stock);

    }
  }

  /**
   * Test for computing the value of stock of the abstract item.
   */
  @Test
  public void valueOfStock() {
    for (int i = 0; i < 100000; i++) {
      double x1 = Math.random() * 1000 + 1;
      double y1 = Math.random() * 1000 + 1;
      int z1 = (int) (Math.random() * 1000 + 101);
      double x2 = Math.random() * 1000 + 1;
      double y2 = Math.random() * 1000 + 101;
      int z2 = (int) (Math.random() * 1000 + 101);
      AbstractItem clothingItem = new ClothingItem(x1, y1, z1, Collection.WINTER);
      AbstractItem kitchenItem = new KitchenItem(x2, y2, z2);
      assertEquals(x1 * z1, clothingItem.valueOfStock(), 0.01);
      assertEquals(x2 * z2, kitchenItem.valueOfStock(), 0.01);

    }
  }
}