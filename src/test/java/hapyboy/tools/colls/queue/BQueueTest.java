package hapyboy.tools.colls.queue;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class BQueueTest {
	BaseQueue queue;
	final int C = 500;
	
	
	@Test
	public void testRemoveAndAdd() {
		queue = new BaseQueue();
		for (int i = 0; i < C; i++) {
			assertTrue(queue.add(i));
		}
		assertEquals(C, queue.size());
		for (int i = 0; i < C; i++) {
			assertEquals(i, queue.remove());
		}
		assertEquals(0, queue.size());
	}

	@Test
	public void testGet() {
		queue = new BaseQueue();
		Object obj = new Object();
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
		queue = new BaseQueue();
		for (int i = 0; i < C; i++) {
			queue.add(i);
		}
		for (int i = 0; i < C; i++) {
			assertEquals(i, queue.get(i));
		}
	}
	@Test(expected = NoSuchElementException.class)
	public void testGetException() {
		queue = new BaseQueue();
		queue.get();
	}
	@Test(expected = NoSuchElementException.class)
	public void testGetException2() {
		queue = new BaseQueue();
		queue.add(new Object());
		queue.get(10);
	}
	

	@Test
	public void testIterator() {
		queue = new BaseQueue();
		for (int i = 0; i < C; i++) {
			assertTrue(queue.add(i));
		}
		@SuppressWarnings("unchecked")
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
		queue = new BaseQueue();
		assertFalse(queue.hasNext());
		queue.add(new Object());
		assertTrue(queue.hasNext());
	}

}
