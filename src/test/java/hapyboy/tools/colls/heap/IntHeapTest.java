package hapyboy.tools.colls.heap;

import static org.junit.Assert.*;
import hapyboy.tools.exception.EmptyException;
import hapyboy.tools.random.Scope;

import org.junit.Test;

public class IntHeapTest {
	IntHeap heap = new IntHeap();
	@Test
	public void testAdd() {
		assertTrue(heap.add(1));
	}

	@Test
	public void testRemove() throws EmptyException {
		final int C = 500;
		Scope scope = new Scope(C);
		for (int i = 0; i < C; i++) {
			heap.add(scope.next());
			
		}
		for (int i = C-1; i >0; i--) {
			assertEquals(i, heap.remove());
			
		}
	}

	@Test
	public void testSize() {
		for(int i=1; i<500; i++){
			heap.add(i);
			assertEquals(i, heap.size());
		}
	}

}
