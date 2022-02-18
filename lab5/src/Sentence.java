import java.util.function.Predicate;

/**
 * This is an interface for words in sentences. It will be  implemented by sentenceImpl.
 */
public interface Sentence {

  /**
   * Adds a new sentenceElement to this Sentence.
   *
   * @param element the sentence element to add to the end of the sentence.
   */
  void addTerm(SentenceElement element);

  /**
   * A method computes and returns the number of words in a sentence.
   * This does not count punctuation.
   *
   * @return the number of words in a sentence.
   */
  int getNumberOfWords();

  /**
   * A method computes and returns the number of punctuations in a sentence.
   *
   * @return the number of punctuations in a sentence.
   */
  int getNumberOfPunctuation();

  /**
   * A method computes and returns the number of words containing the letter "z"
   * in a sentence.
   *
   * @return the number of words containing the letter "z" in a sentence.
   */
  int getNumberOfZ();

  /**
   * A method translates the English sentence to a PigLatin sentence.
   *
   * @return a sentence, but not in PigLatin.
   */
  Sentence translateToPigLatin();

  /**
   * A method determines and returns the longest word in a sentence.
   * The word returned is just the word, and should not begin or end with punctuation.
   * If the sentence contains no words, longestWord should return an empty string.
   *
   * @return the longest word in a sentence.
   */
  String longestWord();

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
  String toString();

  /**
   * A method returns a duplicate of a given sentence.
   * A duplicate is a list that has the same words and punctuation in the same sequence,
   * but is independent of the original list.
   *
   * @return a duplicate of a given sentence.
   */
  Sentence clone();

  /**
   * Merges two sentence into one. The merger sentence is the other sentence
   * followed by this sentence.
   *
   * @param other given other sentence merged to this.
   * @return a new sentence which is combined by two sentences.
   */
  Sentence merge(Sentence other);

  /**
   * Merge some  counting operations into a common higher-order-function.
   *
   * @param p one predicate SentenceElement.
   * @return an integer of count.
   */

  int getGenericCount(Predicate<SentenceElement> p);

}
