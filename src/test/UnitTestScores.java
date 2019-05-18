import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Class to perform unit testing on Score objects
 */
public class UnitTestScores {
  @Test
  public void testResultEquals() {
    Score test1 = new Score("G1", 30);
    Score test2 = new Score("G2", 10);
    Score test3 = new Score("g1", 30);
    Score test4 = new Score("G1", 30);
    Score test5 = new Score("G1", 10);


    assertEquals(true, test1.equals(test1));
    assertEquals(false, test1.equals(test2));
    assertEquals(false, test1.equals(test3));
    assertEquals(true, test1.equals(test4));
    assertEquals(false, test1.equals(test5));
  }

  @Test
  public void testResultCompareTo() {
    Score test1 = new Score("G1", 30);
    Score test2 = new Score("G2", 30);
    Score test3 = new Score("G1", 29);
    Score test4 = new Score("G4", 31);

    assertEquals(0, test1.compareTo(test1));
    assertEquals(0, test1.compareTo(test2));
    assertEquals(1, test1.compareTo(test3));
    assertEquals(-1, test1.compareTo(test4));
  }
}
