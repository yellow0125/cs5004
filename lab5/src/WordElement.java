/**
 * @ Program: lab5
 * @ Date: 2021/11/5
 * @ Description: This class represents a word element of a sentence in the
 * sentence element ADT implementation.
 */
public class WordElement implements SentenceElement {
  private String word;

  /**
   * Construct a word element object with the given string word.
   *
   * @param word a string represent a word element of a sentence.
   */
  public WordElement(String word) {
    this.word = word;
  }

  @Override
  public boolean isWord() {
    return true;
  }

  @Override
  public int numZ() {
    int num = 0;
    String tempWord = word;
    while (tempWord.length() > 0) {
      int locationZ = tempWord.indexOf("z");
      tempWord = tempWord.substring(locationZ + 1);
      if (locationZ < 0) {
        break;
      }
      num++;
    }
    return num;
  }

  @Override
  public boolean containZ() {
    return this.word.toLowerCase().contains("z");
  }

  @Override
  public int getLength() {
    return word.length();
  }

  @Override
  public SentenceElement translateToPigLatin() {
    if ("AEIOUaeiou".contains(this.word.substring(0, 1))) {
      return new WordElement(this.word + "way");
    } else {
      return new WordElement(this.word.substring(1) + this.word.charAt(0) + "ay");
    }
  }

  @Override
  public SentenceElement duplicate() {
    return new WordElement(this.word);
  }

  @Override
  public String toString() {
    if (this.getLength() == 1) {
      return this.word;
    }
    return " " + this.word;
  }
  //  public SentenceElement getBigger(SentenceElement s) {
  //    if (this.getLength() > s.getLength()) {
  //      return this;
  //    }
  //    return s;
  //  }
}
