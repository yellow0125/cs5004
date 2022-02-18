/**
 * This is an interface for a list of element in sentences.
 */
public interface SentenceElement {
  /**
   * Returns true if this element is a word.
   *
   * @return true if element is a word, and false otherwise.
   */
  boolean isWord();

  /**
   * Returns the number of the letter "z" in a word.
   * Both "Z" and "z" are counted.
   *
   * @return the count of the occurrences of the letter "z" in a word.
   */
  int numZ();

  /**
   * Returns true if this sentence element contains a z or a Z.
   *
   * @return true is z is present
   */
  boolean containZ();

  /**
   * Gets the length of this sentence element as an integer.
   *
   * @return the length of this sentence element.
   */
  int getLength();

  /**
   * Returns the element convert to Pig Latin.
   *
   * @return a sentence element of the element converted to Pig Latin.
   */
  SentenceElement translateToPigLatin();

  /**
   * Creat a new version of this object and return it.
   *
   * @return an identical, but different object as this.
   */
  SentenceElement duplicate();
}
