/**
 * @ Program: lab04
 * @ Date: 2021/10/21
 * @ Description: This class represents a punctuation per node in a sentence.
 */
public class PunctuationNode implements Sentence {
  String punctuation;
  Sentence rest;
  NodeType type;

  /**
   * Constructor that takes a string representing punctuation dnode in sentence.
   *
   * @param punctuation one punctuation node in a sentence
   * @param rest        the rest parts of the sentence
   */

  public PunctuationNode(String punctuation, Sentence rest) {
    this.punctuation = punctuation;
    this.rest = rest;
    this.type = NodeType.PunctuationNode;

  }

  @Override
  public int getNumberOfWords() {
    return 0 + this.rest.getNumberOfWords();
  }

  @Override
  public String longestWord() {
    return this.rest.longestWord();
  }

  @Override
  public Sentence clone() {
    return new PunctuationNode(this.punctuation, this.rest.clone());
  }

  @Override
  public Sentence merge(Sentence other) {
    return new PunctuationNode(this.punctuation, this.rest.merge(other));
  }

  @Override
  public String toString() {
    if (this.rest instanceof WordNode) {
      return this.punctuation + " " + this.rest.toString();
    }
    if (this.rest instanceof PunctuationNode) {
      return this.punctuation + "";
    }
    return "" + this.punctuation + this.rest.toString();

  }
}
