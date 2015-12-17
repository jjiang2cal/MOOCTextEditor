/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		
		// TODO: Add more tests here
		try {
			emptyList.remove(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			//empty body
		}
		
		int b = list1.remove(1);
		assertEquals("Remove: check b is correct ", 42, b);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 1, list1.size());
		
		try {
			list1.remove(3);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			//empty body
		}
		
		try {
			list1.remove(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			//empty body
		}
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		boolean a = list1.add(14);
		assertEquals("Add: check a is correct: ", true, a);
		assertEquals("Add: check element 3 is correct: ", (Integer)14, list1.get(3));
		assertEquals("Add: check size is correct: ", 4, list1.size());
		
		boolean b = emptyList.add(5);
		assertEquals("Add: check b is correct: ", true, b);
		assertEquals("Add: check element 0 is correct: ", (Integer)5, emptyList.get(0));
		assertEquals("Add: check size is correct: ", 1, emptyList.size());
		
		boolean c = shortList.add("C");
		assertEquals("Add: check c is correct: ", true, c);
		assertEquals("Add: check element 2 is correct ", "C", shortList.get(2));
		assertEquals("Add: check size is correct: ", 3, shortList.size());
		
		try {
			list1.add(null);
			fail("null pointer");
		}
		catch (NullPointerException e) {
			//empty body
		}
		assertEquals("Add: check element 3 is correct: ", (Integer)14, list1.get(3));
		assertEquals("Add: check size is correct: ", 4, list1.size());
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		assertEquals("Check size of emptyList ", 0, emptyList.size());
		assertEquals("Check size of shortList ", 2, shortList.size());
		assertEquals("Check size of longerList ", 10, longerList.size());
		assertEquals("Check size of list1 ", 3, list1.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		try {
			emptyList.add(-1, 11);
			fail("Index out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			//empty body
		}
		
		emptyList.add(0, 12);
		assertEquals("AddAtIndex: check element 0 is correct: ", (Integer)12, emptyList.get(0));
		assertEquals("AddAtIndex: check size is correct: ", 1, emptyList.size());
		
		try {
			shortList.add(10, "element");
			fail("Index out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			//empty body
		}
		
		shortList.add(1, "A1");
		assertEquals("AddAtIndex: check element 1 is correct: ", "A1", shortList.get(1));
		assertEquals("AddAtIndex: check element 2 is correct: ", "B", shortList.get(2));
		assertEquals("AddAtIndex: check size is correct: ", 3, shortList.size());
		
		try {
			list1.add(1, null);
			fail("null pointer");
		}
		catch (NullPointerException e) {
			//empty body
		}
		assertEquals("AddAtIndex: check element 1 is correct: ", (Integer)21, list1.get(1));
		assertEquals("AddAtIndex: check size is correct: ", 3, list1.size());
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
	    try {
	    	list1.set(-1, 7);
	    	fail("Index out of bounds");
	    }
	    catch (IndexOutOfBoundsException e) {
	    	//empty body
	    }
	    
	    try {
	    	list1.set(10, 7);
	    	fail("Index out of bounds");
	    }
	    catch (IndexOutOfBoundsException e) {
	    	//empty body
	    }
	    
	    int a = list1.set(1, 20);
	    assertEquals("Set: check a is correct: ", 21, a);
	    assertEquals("Set: check element 1 is correct: ", (Integer)20, list1.get(1));
	    assertEquals("Set: check size is corrct: ", 3, list1.size());
	    
	    try {
	    	emptyList.set(0, 0);
	    	fail("Index out of bounds");
	    }
	    catch (IndexOutOfBoundsException e) {
	    	//empty body
	    }
	    
	    try {
	    	shortList.set(1, null);
	    	fail("null pointer");
	    }
	    catch (NullPointerException e) {
	    	//empty body
	    }
	    assertEquals("Set: check element 1 is correct: ", "B", shortList.get(1));
	}
	
	
	// TODO: Optionally add more test methods.
	
}
