package hapyboy.tools.colls.queue;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class SimpleQueueTest {
	SimpleQueue<Integer> queue = new SimpleQueue<Integer>();
	final int C = 500;

	@Test
	public void testRemoveAndAdd() {
		
		for (int i = 0; i < C; i++) {
			assertTrue(queue.add(i));
		}
		assertEquals(C, queue.size());
		for (int i = 0; i < C; i++) {
			Integer j =i;
			assertEquals(j, queue.remove());
		}
		assertEquals(0, queue.size());
	}

	@Test
	public void testGet() {
		
		Integer obj = 3;
		queue.add(obj);
		for (int i = 0; i < C; i++) {
			assertEquals(obj, queue.get());
		}
		assertEquals(1, queue.size());
		assertEquals(obj, queue.remove());
		assertEquals(0, queue.size());
	}

	@Test
	public void testGetInt() {
		
		for (int i = 0; i < C; i++) {
			queue.add(i);
		}
		for (int i = 0; i < C; i++) {
			Integer j = i;
			assertEquals(j, queue.get(i));
		}
	}
	@Test(expected = NoSuchElementException.class)
	public void testGetException() {
		
		queue.get();
	}
	@Test(expected = NoSuchElementException.class)
	public void testGetException2() {
		
		queue.add(5);
		queue.get(10);
	}
	

	@Test
	public void testIterator() {
		
		for (int i = 0; i < C; i++) {
			assertTrue(queue.add(i));
		}

		Iterator<Integer> ite =  (Iterator<Integer>) queue.iterator();
		for (int i = 0; i < C; i++) {
			assertTrue(ite.hasNext());
			Integer I = i;
			assertEquals(I, ite.next());
		}
		assertFalse(ite.hasNext());
	}


	@Test
	public void testHasNext() {
		
		assertFalse(queue.hasNext());
		queue.add(2);
		assertTrue(queue.hasNext());
	}

}
