import java.util.Random;

import static org.junit.Assert.assertEquals;

/**
 * Test for one sentence implementation ADT.
 */
public class SentenceImplTest {

  /**
   * test for add word element or punctuation element into a sentence..
   */
  @org.junit.Test
  public void addTerm() {
    SentenceImpl test = new SentenceImpl();
    test.addTerm(new WordElement("Hello"));
    test.addTerm(new PunctuationElement(","));
    test.addTerm(new WordElement("World"));
    test.addTerm(new WordElement("Tomorrow"));
    test.addTerm(new PunctuationElement("!"));

    int expected = 3;
    int actual = test.getNumberOfWords();
    assertEquals(expected, actual);

    expected = 2;
    actual = test.getNumberOfPunctuation();
    assertEquals(expected, actual);
  }

  /**
   * test for toString method.
   */
  @org.junit.Test
  public void testToString() {
    SentenceImpl test = new SentenceImpl();
    test.addTerm(new WordElement("Hello"));
    test.addTerm(new PunctuationElement(","));
    test.addTerm(new WordElement("World"));
    test.addTerm(new WordElement("Tomorrow"));
    test.addTerm(new PunctuationElement("!"));

    String excepted = "Hello, World Tomorrow!";
    String actual = test.toString();
    assertEquals(excepted, actual);

    test = new SentenceImpl();
    test.addTerm(new WordElement("Yesterday"));
    test.addTerm(new WordElement("Once"));
    test.addTerm(new WordElement("More"));
    test.addTerm(new PunctuationElement("."));
    excepted = "Yesterday Once More.";
    actual = test.toString();
    assertEquals(excepted, actual);
  }

  /**
   * test for get the number of words in a sentence.
   */
  @org.junit.Test
  public void getNumberOfWords() {
    for (int testNum = 0; testNum < 1000; testNum++) {
      Sentence test = new SentenceImpl();
      Random random = new Random();
      int wordLimit = random.nextInt(25);
      for (int iWord = 0; iWord < wordLimit; iWord++) {
        test.addTerm(new WordElement("a"));
      }

      int expected = wordLimit;
      int actual = test.getNumberOfWords();
      assertEquals(expected, actual);
    }
  }

  /**
   * test for get the number of punctuations in a sentence.
   */
  @org.junit.Test
  public void getNumberOfPunctuation() {
    for (int testNum = 0; testNum < 1000; testNum++) {
      Sentence test = new SentenceImpl();
      Random random = new Random();
      int wordLimit = random.nextInt(25);
      for (int iPunctuation = 0; iPunctuation < wordLimit; iPunctuation++) {
        test.addTerm(new PunctuationElement("#"));
      }

      int expected = wordLimit;
      int actual = test.getNumberOfPunctuation();
      assertEquals(expected, actual);
    }
  }

  /**
   * test for get the number of letter "z" ot "Z" in a sentence.
   */
  @org.junit.Test
  public void getNumberOfZ() {
    SentenceImpl test = new SentenceImpl();
    test.addTerm(new WordElement("Hello"));
    test.addTerm(new PunctuationElement(","));
    test.addTerm(new WordElement("World"));
    test.addTerm(new PunctuationElement("!"));
    int expected = 0;
    int actual = test.getNumberOfZ();
    assertEquals(expected, actual);

    SentenceImpl other = new SentenceImpl();
    other.addTerm(new WordElement("There"));
    other.addTerm(new WordElement("Are"));
    other.addTerm(new WordElement("Many"));
    other.addTerm(new WordElement("Zebras"));
    other.addTerm(new WordElement("In"));
    other.addTerm(new WordElement("The"));
    other.addTerm(new WordElement("Zoo"));
    other.addTerm(new PunctuationElement(","));
    other.addTerm(new WordElement("All"));
    other.addTerm(new WordElement("Of"));
    other.addTerm(new WordElement("Them"));
    other.addTerm(new WordElement("Are"));
    other.addTerm(new WordElement("Zigzagging"));
    other.addTerm(new PunctuationElement("."));

    expected = 3;
    actual = other.getNumberOfZ();
    assertEquals(expected, actual);

    for (int testNum = 0; testNum < 1000; testNum++) {
      test = new SentenceImpl();
      other = new SentenceImpl();
      Random random = new Random();
      int wordLimit = random.nextInt(25);
      for (int iPunctuation = 0; iPunctuation < wordLimit; iPunctuation++) {
        test.addTerm(new PunctuationElement("z"));
        other.addTerm(new PunctuationElement("Z"));
      }

      expected = wordLimit;
      int actual1 = test.getNumberOfPunctuation();
      int actual2 = other.getNumberOfPunctuation();
      assertEquals(expected, actual1);
      assertEquals(expected, actual2);
    }
  }

  /**
   * test for translate one sentence to pig latin.
   */
  @org.junit.Test
  public void translateToPigLatin() {
    SentenceImpl test = new SentenceImpl();
    test.addTerm(new WordElement("Hello"));
    test.addTerm(new PunctuationElement(","));
    test.addTerm(new WordElement("World"));
    test.addTerm(new PunctuationElement("!"));

    SentenceImpl other = new SentenceImpl();
    other.addTerm(new WordElement("How"));
    other.addTerm(new WordElement("Are"));
    other.addTerm(new WordElement("You"));
    other.addTerm(new PunctuationElement("?"));

    String expected = "elloHay, orldWay!";
    String actual = test.translateToPigLatin().toString();
    assertEquals(expected, actual);

    expected = "owHay Areway ouYay?";
    actual = other.translateToPigLatin().toString();
    assertEquals(expected, actual);

    other = new SentenceImpl();
    other.addTerm(new WordElement("There"));
    other.addTerm(new WordElement("Are"));
    other.addTerm(new WordElement("Many"));
    other.addTerm(new WordElement("Zebras"));
    other.addTerm(new WordElement("In"));
    other.addTerm(new WordElement("The"));
    other.addTerm(new WordElement("Zoo"));
    other.addTerm(new PunctuationElement(","));
    other.addTerm(new WordElement("All"));
    other.addTerm(new WordElement("Of"));
    other.addTerm(new WordElement("Them"));
    other.addTerm(new WordElement("Are"));
    other.addTerm(new WordElement("Zigzagging"));
    other.addTerm(new PunctuationElement("."));
    expected = "hereTay Areway anyMay ebrasZay Inway heTay ooZay, Allway " +
            "Ofway hemTay Areway igzaggingZay.";
    actual = other.translateToPigLatin().toString();
    assertEquals(expected, actual);

    other = new SentenceImpl();
    assertEquals("", actual = other.translateToPigLatin().toString());
    other.addTerm(new PunctuationElement("?"));
    assertEquals("?", actual = other.translateToPigLatin().toString());
  }

  /**
   * test for get the longest word in a sentence.
   */
  @org.junit.Test
  public void longestWord() {
    SentenceImpl test = new SentenceImpl();
    test.addTerm(new WordElement("Hello"));
    test.addTerm(new PunctuationElement(","));
    test.addTerm(new WordElement("World"));
    test.addTerm(new WordElement("Tomorrow"));
    test.addTerm(new PunctuationElement("!"));

    String expected = "Tomorrow";
    String actual = test.longestWord();
    assertEquals(expected, actual);

    test = new SentenceImpl();
    test.addTerm(new WordElement("Yesterday"));
    test.addTerm(new WordElement("Once"));
    test.addTerm(new WordElement("More"));
    test.addTerm(new PunctuationElement("."));
    expected = "Yesterday";
    actual = test.longestWord();
    assertEquals(expected, actual);

    test = new SentenceImpl();
    assertEquals("", test.longestWord());

    test.addTerm(new PunctuationElement("#"));
    assertEquals("", test.longestWord());
  }

  /**
   * test for clone one sentence.
   */
  @org.junit.Test
  public void testClone() {
    SentenceImpl test = new SentenceImpl();
    test.addTerm(new WordElement("Hello"));
    test.addTerm(new PunctuationElement(","));
    test.addTerm(new WordElement("World"));
    test.addTerm(new WordElement("Tomorrow"));
    test.addTerm(new PunctuationElement("!"));

    Sentence copyTest = test.clone();
    String expected = test.toString();
    String actual = copyTest.toString();
    assertEquals(expected, actual);

    test.addTerm(new WordElement("End"));
    actual = copyTest.toString();
    assertEquals(expected, actual);
    assertEquals(actual + " End", test.toString());

    test = new SentenceImpl();
    copyTest = test.clone();
    assertEquals("", copyTest.toString());
    test = new SentenceImpl();
    test.addTerm(new PunctuationElement("$"));
    copyTest = test.clone();
    assertEquals("$", copyTest.toString());

  }

  /**
   * test for merge other sentence to this sentence.
   * the merger sentence is the other sentence followed by this sentence.
   */
  @org.junit.Test
  public void merge() {
    SentenceImpl test = new SentenceImpl();
    test.addTerm(new WordElement("Hello"));
    test.addTerm(new PunctuationElement(","));
    test.addTerm(new WordElement("World"));
    test.addTerm(new PunctuationElement("!"));

    SentenceImpl other = new SentenceImpl();
    other.addTerm(new WordElement("How"));
    other.addTerm(new WordElement("Are"));
    other.addTerm(new WordElement("You"));
    other.addTerm(new PunctuationElement("?"));

    Sentence merged = other.merge(test);
    String expected = "Hello, World! How Are You?";
    String actual = merged.toString();
    assertEquals(expected, actual);

    merged = test.merge(other);
    expected = "How Are You? Hello, World!";
    actual = merged.toString();
    assertEquals(expected, actual);

    other = new SentenceImpl();
    merged = other.merge(test);
    expected = "Hello, World!";
    actual = merged.toString();
    assertEquals(expected, actual);
  }
}