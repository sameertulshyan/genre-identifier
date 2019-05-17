import java.util.HashMap;
import java.util.Objects;
/**
 * Class models a book genre (within the context of the exercise).
 */
public class Genre {
  private String genreName;
  private HashMap<String, Integer> keywordsToPoints;

  public Genre(String newGenreName) {
    genreName = newGenreName;
    keywordsToPoints = new HashMap<String, Integer>();
  }

  public String getName() {
    return this.genreName;
  }

  public HashMap<String, Integer> getKeywords() {
    return this.keywordsToPoints;
  }

  /**
   * We define equality as having the same genreName and same keywordsToPoints
   */
  @Override
  public boolean equals(Object o) {
    if (o != null && o instanceof Genre) {
      Genre secondGenre = (Genre) o;
      boolean sameGenreName = this.genreName.equals(secondGenre.getName());
      boolean sameKeywordsToPoints = this.keywordsToPoints == secondGenre.getKeywords();
      return sameGenreName && sameKeywordsToPoints;
    } else {
    	return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.genreName, this.keywordsToPoints);
  }
}