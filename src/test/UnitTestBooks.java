import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Class to perform unit testing on Book objects
 */
public class UnitTestBooks {
	
  @Test
  public void testEquals(){
    Book test1 = new Book("title", "description.");
    Book test2 = new Book("title", "description.");
    Book test3 = new Book("Title", "Description");
    Book test4 = new Book("title", "Description");
    Book test5 = new Book("Title", "description");

    assertEquals(true, test1.equals(test1));
    assertEquals(true, test1.equals(test2));
    assertEquals(false, test1.equals(test3));
    assertEquals(false, test1.equals(test4));
    assertEquals(false, test1.equals(test5));
  }
}
