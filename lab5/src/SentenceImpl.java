import java.util.LinkedList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @ Program: lab5
 * @ Date: 2021/10/26
 * @ Description: This class represents a sentence ADT implementation.
 */
public class SentenceImpl implements Sentence {
  public LinkedList<SentenceElement> sentence;

  /**
   * Construct a sentence with no parameters.
   */
  public SentenceImpl() {
    sentence = new LinkedList<SentenceElement>();
  }

  /**
   * Construct a sentence with no parameters.
   *
   * @param inlist the elements in linked list sentenceElement.
   */
  public SentenceImpl(LinkedList<SentenceElement> inlist) {
    this.sentence = inlist;
  }

  @Override
  public void addTerm(SentenceElement element) {
    sentence.add(element);
  }

  @Override
  public int getNumberOfWords() {
    //return (int) this.sentence.stream().filter(element -> element.isWord()).count();
    return getGenericCount(element -> element.isWord());
  }

  @Override
  public int getNumberOfPunctuation() {
    //return (int) this.sentence.stream().filter(element -> !element.isWord()).count();
    return getGenericCount(element -> !element.isWord());
  }

  @Override
  public int getNumberOfZ() {
    //return (int) this.sentence.stream().filter(element -> element.containZ()).count();
    return getGenericCount(element -> element.containZ());
  }

  @Override
  public Sentence translateToPigLatin() {
    return new SentenceImpl(this.sentence.stream().map(element -> element
            .translateToPigLatin()).collect(Collectors.toCollection(LinkedList::new)));
  }

  @Override
  public String longestWord() {
    if (sentence.size() > 0) {
      return this.sentence.stream().reduce(new WordElement(""), (a, b) ->
              a.getLength() < b.getLength() ? b : a).toString().trim();
    }
    return "";
  }

  @Override
  public Sentence clone() {
    return new SentenceImpl(
            this.sentence.stream().map(element ->
                    element.duplicate()).collect(Collectors.toCollection(LinkedList::new)));
  }

  @Override
  public Sentence merge(Sentence other) {
    Sentence mergeS = other.clone();
    this.sentence.stream().forEach(element -> mergeS.addTerm(element));
    return mergeS;
  }

  @Override
  public String toString() {
    return this.sentence.stream().map(element -> element.toString())
            .reduce("", (a, b) -> a + b).trim();
  }

  @Override
  public int getGenericCount(Predicate<SentenceElement> p) {
    return (int) this.sentence.stream().filter(p).count();
  }
}
