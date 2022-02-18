/**
 * @ Program: lab5
 * @ Date: 2021/11/5
 * @ Description: This class represents a punctuation element of a sentence
 * in the sentence element ADT implementation.
 */
public class PunctuationElement implements SentenceElement {
  private String punctuation;

  /**
   * Construct a punctuation element object with the given string punctuation.
   *
   * @param punctuation a string represent a punctuation element of a sentence.
   */
  public PunctuationElement(String punctuation) {
    this.punctuation = punctuation;
  }

  @Override
  public boolean isWord() {
    return false;
  }

  @Override
  public int numZ() {
    return 0;
  }

  @Override
  public boolean containZ() {
    return false;
  }

  @Override
  public int getLength() {
    return 0;
  }

  @Override
  public SentenceElement translateToPigLatin() {
    return this;
  }

  @Override
  public SentenceElement duplicate() {
    return new PunctuationElement(this.punctuation);
  }

  @Override
  public String toString() {
    return this.punctuation;
  }
}
//  public SentenceElement getBigger(SentenceElement s) {
//    return s;
//  }