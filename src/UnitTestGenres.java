import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Class to perform unit testing on Genre objects.
 */
public class UnitTestGenres {
  @Test
  public void testGenreEquals(){
    Genre test1 = new Genre("A");
    Genre test2 = new Genre("A");
    Genre test3 = new Genre("a");

    assertEquals(true, test1.equals(test1));
    assertEquals(false, test1.equals(test2));
    assertEquals(false, test1.equals(test3));
  }
}