/**
 * @ Program: lab04
 * @ Date: 2021/10/21
 * @ Description: This class represents the end of a sentence.
 */
public class EmptyNode implements Sentence {
  NodeType type;

  /**
   * Constructor that takes a null representing empty node at the end of a sentence.
   */
  public EmptyNode() {
    this.type = NodeType.Empty;

  }

  @Override
  public int getNumberOfWords() {
    return 0;
  }

  @Override
  public String longestWord() {
    return "";
  }

  @Override
  public Sentence clone() {
    return new EmptyNode();
  }

  @Override
  public Sentence merge(Sentence other) {
    return other.clone();
  }

  @Override
  public String toString() {
    return "";
  }
}
