/**
 * This class represents a dog with a name, an age, a type,
 * a weight(kg), a length(cm) and adopted or not.
 */

public class Dog {
  private String name;
  private int age;
  private String type;
  private double weight;
  private double length;
  private boolean adopted;


  /**
   * Constructs a Dog object and initializes it to the given name, age, type,
   * weight(kg), length(cm) and adopted or not.
   *
   * @param name the name of this dog
   * @param age the age of this dog
   * @param type the type of this dog
   * @param weight the weight(kg) of this dog
   * @param length the length(cm) of this dog
   */
  public Dog(String name, int age, String type, double weight,
             double length, boolean adopted){
    this.name = name;
    this.age = age;
    this.type = type;
    this.weight = weight;
    this.length = length;
    this.adopted = adopted;
  }
  /**
   * Get the name of this dog
   *
   * @return the name of this dog
   */
  public String getName(){
    return this.name;
  }

  /**
   * Get the age of this dog
   * @return the age of this dog
   */
  public int getAge(){
    return this.age;
  }

  /**
   * Get the type of this dog
   * @return the type of this dog
   */

  public String getType(){
    return this.type;
  }

  /**
   * Get the weight of this dog
   * @return the weight of this dog
   */
  public double getWeight(){
    return this.weight;
  }

  /**
   * Get the length of this dog
   * @return the length of this dog
   */
  public double getLength(){
    return this.length;
  }

  /**
   * Get a formatted string which contains name, age, type, weight(kg),
   * length(cm) and adopted or not.
   * @return the formatted string as above
   */
  public String toString(){
    String str = String.format("name: %s, age: %d, type: %s, weight: %.2f, "
                    + "length: %.2f, adopted: %b\n",this.name,this.age,this.type,this.weight,
            this.length,this.adopted);
    return str;
  }
}
