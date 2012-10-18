package hapyboy.tools.number;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Random;

import org.junit.Test;

public class PrimesTest {
	Primes primes = new Primes();
	private final int L = 1500;
	@Test
	public void testGetInt() throws Exception {
		Random random = new Random();
		
		
		for (int i = 0; i < 1000; i++) {
			int c = random.nextInt(L)+1;
			int[] sarray = primes.get(c);
			assertTrue("数组长度："+sarray.length +"C:"+c,sarray.length == c);
		}
		
		
		
		
		int[] array = primes.get(L);
		assertTrue(array.length == L);
		assertTrue(array[L-1]>L);
	}

	@Test
	public void testCkeck() throws Exception {
		assertTrue(primes.ckeck(2));
		assertTrue(primes.ckeck(11));
		assertTrue(primes.ckeck(9533));
		assertTrue(primes.ckeck( 4787 ));
		assertTrue(primes.ckeck( 6379 ));
		assertTrue(primes.ckeck( 6659 ));
		assertTrue(primes.ckeck( 7177 ));
		assertTrue(primes.ckeck( 3167 ));
		assertTrue(primes.ckeck( 191 ));
		assertTrue(primes.ckeck(1907 ));
		assertTrue(primes.ckeck( 5573 ));
		assertTrue(primes.ckeck(8117 ));
		assertTrue(primes.ckeck( 4507));
		
		assertFalse(primes.ckeck(3351));
		assertFalse(primes.ckeck(21));
		
		
	}

	@Test
	public void testNext() throws Exception {
		assertEquals(11, primes.next(9));
		assertEquals(11, primes.next(10));
		assertEquals(3, primes.next(2));
		assertEquals(4507, primes.next(4506));
	}

	@Test
	public void testPrevious() {
		assertEquals(11, primes.previous(13));
		assertEquals(11, primes.previous(12));
		assertEquals(2, primes.previous(3));
		assertEquals(1117, primes.previous(1118));
		assertEquals(8117, primes.previous(8118));
	}

	@Test
	public void testIterator() {
		Iterator<Integer> ite = primes.iterator();
		assertTrue(ite.hasNext());
		int t=1;
		for(int i:primes){
			assertTrue(i>t);
			t = i;
		}
		System.out.println(ite.hasNext());
	}

}
