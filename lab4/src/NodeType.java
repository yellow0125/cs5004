

/**
 * @ Program: lab04
 * @ Date: 2021/10/21
 * @ Description: This class represents the type of each node in a sentence.
 */
public enum NodeType {
  Word,
  PunctuationNode,
  Space,
  Empty;

  public String toString() {
    return "Node type: " + this.name();

  }
}
