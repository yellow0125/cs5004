/**
 * This is an interface for words in sentences. It will be  implemented by sentence.
 */
public interface Sentence {

  /**
   * A method computes and returns the number of words in a sentence.
   * This does not count punctuation.
   *
   * @return the number of words in a sentence.
   */

  public int getNumberOfWords();

  /**
   * A method determines and returns the longest word in a sentence.
   * The word returned is just the word, and should not begin or end with punctuation.
   * If the sentence contains no words, longestWord should return an empty string.
   *
   * @return the longest word in a sentence.
   */

  public String longestWord();

  /**
   * A method converts the sentence into one string.
   * There must be a space between every two words.
   * A punctuation mark should have no space between it and whatever precedes it.
   * There is no space between the last word and the end of this sentence.
   * If there is no punctuation mark at the end of the sentence,
   * this string should end with a period (it shouldn’t add the period to the original sentence).
   *
   * @return the string of a sentence
   */

  public String toString();

  /**
   * A method returns a duplicate of a given sentence.
   * A duplicate is a list that has the same words and punctuation in the same sequence,
   * but is independent of the original list.
   *
   * @return a duplicate of a given sentence.
   */

  public Sentence clone();

  /**
   * A method merges two sentences into a single sentence.
   * The merged list should preserve all the punctuation.
   * The merged list should be returned by this method, and the original lists should be unchanged.
   *
   * @return a new sentence which is combined by two sentences.
   */

  public Sentence merge(Sentence other);

}
