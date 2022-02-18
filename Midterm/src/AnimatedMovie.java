/**
 * Animated movie is a specific type of movie (it implements the Movie interface).
 * You need to make a class diagram.
 * The class diagram should include the methods the interface requires,
 * as well as any class variables that you think the class would require
 * given the methods and the descriptions in the interface.
 *
 * <p>Once you have your class diagram, you need to give the concrete implementations
 * for getPublicationYear, jumpToTime, and resetProgress
 *
 * <p>You can assume that getTitle and saveProgress are implemented correctly.
 * You can also assume that there is a constructor that properly initializes
 * All the class variables that you include in your class diagram.
 *
 * <p>If you think you need any other methods to properly implement your 3 methods,
 * you may add them as private methods, and then update your class diagram to
 * include them.
 */
public class AnimatedMovie implements Movie {
  final String title;
  final int year;
  final int totalMin;
  final int totalSec;
  private int playMinute;
  private int playSecond;

  /* Put your class diagram here:
  Movie(interface){
    methods{
      int getPublicationYear()
      String getTitle()
      void jumpToTime(int, int)
      void resetProgress()
      void saveProgress()
   }
  }
  // A dotted line from AnimatedMovie to Movie
  AnimatedMovie(class){
    fields{
    String title
    int year
    int totalMin
    int totalSec
    int playMinute
    int playSecond
    }
    methods{
      AnimatedMovie(String, int, int, int, int, int)
      int getPublicationYear()
      String getTitle()
      void jumpToTime(int, int)
      void resetProgress()
      void saveProgress()

    }
   */

  /**
   * Initialize the fields for the AnimatedMovie.
   * The input is given the title, publisher year, total time and play time of the movie.
   * Time is consisted of minute and second. Second need to be smaller than 60.
   *
   * @param title      A String representing the title of the animated movie.
   * @param year       An integer representing the published year of the animated movie.
   * @param totalMin   An integer representing the total minutes of the animated movie.
   * @param totalSec   An integer representing the total seconds of the animated movie.
   * @param playMinute An integer representing the play minute 0 of the animated movie.
   * @param playSecond An integer representing the play second 0 of the animated movie.
   * @throws IllegalArgumentException when input total second is larger than 60.
   */
  public AnimatedMovie(String title, int year, int totalMin, int totalSec,
                       int playMinute, int playSecond) throws IllegalArgumentException {
    if (totalSec > 60) {
      throw new IllegalArgumentException("The seconds can't not be over sixty");
    }
    this.title = title;
    this.year = year;
    this.totalMin = totalMin;
    this.totalSec = totalSec;
    this.playMinute = playMinute;
    this.playSecond = playSecond;
  }

  @Override
  public int getPublicationYear() {
    return this.year;
  }

  @Override
  public void jumpToTime(int minute, int seconds) throws IllegalArgumentException {
    if (seconds > 60) {
      throw new IllegalArgumentException("The seconds can not be larger than 60");
    } else {
      this.playMinute = minute;
      this.playSecond = seconds;
    }

  }

  @Override
  public void resetProgress() {
    if (this.playMinute != 0 || this.playSecond != 0) {
      this.playMinute = 0;
      this.playSecond = 0;
    }
  }
  //ALL THE METHODS BELOW HERE ARE ASSUMED TO BE PROPERLY IMPLEMENTED

  @Override
  public String getTitle() {

    return this.title;
  }

  @Override
  public void saveProgress() {
    //assumed to be properly implemented
  }

}
