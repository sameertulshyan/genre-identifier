import java.util.Objects;
/**
 * Class models the score for a given Genre. Used to encapsulate genre and score for easier processing within Book class.
 */
public class Score implements Comparable<Object> {

  private String genreName;
  private int scoreValue;

  Score(String newGenre, int newScore) {
    genreName = newGenre;
    scoreValue = newScore;
  }

  public int getScore() {
    return this.scoreValue;
  }

  public String getGenre() {
    return this.genreName;
  }

  public void setScore(int newScore) {
    this.scoreValue = newScore;
  }

  /**
   * Need to implement compareTo. We order scores by their scoreValue
   */
  @Override
  public int compareTo(Object o) {
    if (this == o) {
    	return 0;
    }

    if (o instanceof Score) {
      if (this.scoreValue < ((Score) o).getScore()) {
    	  return -1;
      }
      else if (this.scoreValue > ((Score) o).getScore()) {
    	  return 1;
      }
      else {
    	  return 0;
      }
    }

    throw new IllegalArgumentException("Invalid Object type provided.");
  }

  /**
   * We define equality based on genreName and scoreValue
   */
  @Override
  public boolean equals(Object o) {
    if (o != null && o instanceof Score) {
      Score secondScore = (Score) o;
      boolean sameGenreName = this.genreName.equals(secondScore.getGenre());
      boolean sameScoreValue = this.scoreValue == secondScore.getScore();
      return sameGenreName && sameScoreValue;
    } else {
    	return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.genreName, this.scoreValue);
  }
}
