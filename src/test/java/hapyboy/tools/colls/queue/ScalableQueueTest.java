package hapyboy.tools.colls.queue;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class ScalableQueueTest {
	IQueue<Integer> queue = ScalableQueue.newInstance(Integer.class);
	final int C = 500;

	@Test
	public void testRemoveAndAdd() {
		
		for (int i = 0; i < C; i++) {
			assertTrue(queue.enqueue(i));
		}
		assertEquals(C, queue.size());
		for (int i = 0; i < C; i++) {
			Integer j =i;
			assertEquals(j, queue.dequeue());
		}
		assertEquals(0, queue.size());
	}

	@Test
	public void testGet() {
		
		Integer obj = 3;
		queue.enqueue(obj);
		for (int i = 0; i < C; i++) {
			assertEquals(obj, queue.get());
		}
		assertEquals(1, queue.size());
		assertEquals(obj, queue.dequeue());
		assertEquals(0, queue.size());
	}

	@Test
	public void testGetInt() {
		
		for (int i = 0; i < C; i++) {
			queue.enqueue(i);
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
		
		queue.enqueue(5);
		queue.get(10);
	}
	

	@Test
	public void testIterator() {
		
		for (int i = 0; i < C; i++) {
			assertTrue(queue.enqueue(i));
		}

		Iterator<Integer> ite = queue.iterator();
		for (int i = 0; i < C; i++) {
			assertTrue(ite.hasNext());
			Integer I = i;
			assertEquals(I, ite.next());
		}
		assertFalse(ite.hasNext());
	}


	@Test
	public void testHasNext() {
		
		assertTrue(queue.isEmpty());
		queue.enqueue(2);
		assertFalse(queue.isEmpty());
	}

}
