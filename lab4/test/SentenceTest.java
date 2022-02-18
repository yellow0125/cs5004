import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * @ Program: lab04
 * @ Date: 2021/10/21
 * @ Description: Testfor the words in sentence.
 */

public class SentenceTest {

  /**
   * Test for get the number of words in a sentence.
   */

  @org.junit.Test
  public void testGetNumberOfWords() {
    Sentence s = new EmptyNode();
    s = new PunctuationNode("!", s);
    int excepted = 0;
    int actual = s.getNumberOfWords();
    assertEquals(excepted, actual);

    s = new WordNode("World", s);
    s = new WordNode("Hello", s);
    excepted = 2;
    actual = s.getNumberOfWords();
    assertEquals(excepted, actual);

  }

  /**
   * Test for get the longest word in a sentence.
   */
  @org.junit.Test
  public void longestWord() {
    Sentence s = new EmptyNode();
    s = new PunctuationNode("!", s);
    String except = "";
    String actual = s.longestWord();
    assertEquals(except, actual);

    s = new WordNode("Sentence", s);
    s = new WordNode("The", s);
    s = new WordNode("Of", s);
    s = new WordNode("Word", s);
    s = new WordNode("Longest", s);
    s = new WordNode("The", s);
    s = new WordNode("Get", s);
    except = "Sentence";
    actual = s.longestWord();
    assertEquals(except, actual);
  }

  /**
   * Test for  toString method. get the sentence.
   */
  @org.junit.Test
  public void testToString() {
    Sentence listEnd = new EmptyNode();
    Sentence exclaim = new PunctuationNode("!", listEnd);

    Sentence lastWord = new WordNode("World", exclaim);
    String excepted = "World!";
    String actual = lastWord.toString();
    assertEquals(excepted, actual);

    Sentence firstWord = new WordNode("Hello", lastWord);
    excepted = "Hello World!";
    actual = firstWord.toString();
    assertEquals(excepted, actual);

    Sentence s = new EmptyNode();
    s = new WordNode("Period", s);
    s = new WordNode("A", s);
    s = new WordNode("Without", s);
    s = new WordNode("Ending", s);
    s = new PunctuationNode(",", s);
    s = new WordNode("Sentence", s);
    s = new WordNode("A", s);
    excepted = "A Sentence, Ending Without A Period.";
    actual = s.toString();
    assertEquals(excepted, actual);

  }

  /**
   * Test for clone a sentence, and the duplicate  is independent of the original list.
   */

  @org.junit.Test
  public void testClone() {
    Sentence s1 = new EmptyNode();
    s1 = new PunctuationNode(".", s1);
    s1 = new WordNode("Sentence", s1);
    s1 = new WordNode("First", s1);
    s1 = new WordNode("My", s1);

    Sentence s2 = s1.clone();
    assertNotEquals(s1, s2);
    String excepted = "My First Sentence.";
    String actual = s2.toString();
    assertEquals(excepted, actual);
    assertNotEquals(s1, s2);

  }

  /**
   * Test for merge two sentence together.
   */
  @org.junit.Test
  public void testMerge() {
    Sentence s1 = new EmptyNode();
    s1 = new PunctuationNode(".", s1);
    s1 = new WordNode("Sentence", s1);
    s1 = new WordNode("First", s1);
    s1 = new WordNode("My", s1);

    Sentence s2 = new EmptyNode();
    s2 = new PunctuationNode("!", s2);
    s2 = new WordNode("Sentence", s2);
    s2 = new WordNode("Second", s2);
    s2 = new WordNode("A", s2);
    s2 = new WordNode("Add", s2);
    s2 = new PunctuationNode(",", s2);
    s2 = new WordNode("Now", s2);

    Sentence newS = s1.merge(s2);
    String excepted = "My First Sentence. Now, Add A Second Sentence!";
    String actual = newS.toString();
    assertEquals(excepted, actual);

  }

}