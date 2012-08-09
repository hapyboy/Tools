package hapyboy.tools.number;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;



//求素数（质数）
public class Primes implements Iterable<Integer>{
	private static final int L = 1000;
	private static int[] primes;
	private int limit;
	private int c;
	private int max;

	public Primes() {
		primes = new int[L];
		c = L;
		add(2);
		add(3);
		add(5);
		try {
			get();
		} catch (Exception e) {
		
			e.printStackTrace();
		}
	}

	private final void add(int num) {
		if (limit == c) {
			expands();
		}
		primes[limit++] = num;
		max = num;

	}

	private void expands() {
		c += c >>> 2;
		primes = Arrays.copyOf(primes, c);

	}

	private final boolean isPrime(int s) throws Exception {
		if(s<0){
			throw new Exception("求质数过程中质数超过int可表达范围");
		}
		for (int i = 0; i < limit; i++) {
			
			if (s % primes[i] == 0)
				return false;
		}
		return true;

	}

	final int[] get() throws Exception {
		return get(L);

	}

	public int[] get(int count) throws Exception {

		if (count < 1) {
			throw new NoSuchElementException("请输入有效数字（大于1）！");
		}
		if (count < limit) {

			return Arrays.copyOf(primes, count);
		}

		int target = max + 1;
		while (limit < count) {
			if (isPrime(target)) {
				add(target);
			}
			target++;

		}
		return Arrays.copyOf(primes, count);
	}

	public boolean ckeck(int num) {
		if (num < 2) {
			return false;
		}
		if (num < max) {
			return (Arrays.binarySearch(primes, num) >= 0 ? true : false);
		}

		
			to(num);
		
		if (max == num) {
			return true;
		}

		return false;

	}

	private final void to(int num) {
		
		int target = max + 1;
		while (target <= num) {
			try {
				if (isPrime(target)) {
					add(target);
				}
			} catch (Exception e) {
				// 不可达区
				e.printStackTrace();
			}
			target++;
		}
		
	}

//	private final boolean addOne(){
//		int target = max+2;
//		try {
//			while(!isPrime(target)){
//				target++;
//			}
//		} catch (Exception e) {
//			return false;
//		}
//		add(target);
//		return true;
//	}

	public int next(int r) throws Exception{// r:Reference 参考数字
		/*
		 * 返回比R大的最小素数，如果r为9，那返回是11;
		 */

		if (r < 0) {
			return -1;// 返回-1，警告出错。最常见的错误是超过int可表达范围
		}
		if (r > max) {
			to(r);
			return primes[limit-1];
		}
		
		int s = 0;//start 起点
		int e = limit-1;//end 止点
		int t;//target 目标
		
		int g = e-s;//gap 差距
		while(g>1){
			t = s +(g>>1);
			if(r>primes[t]){
				s = t;
			}else if(r<primes[t]){
				e = t;
			}else{
				return primes[++t];
			}
			g = e-s;
		}
		return primes[e];
		

	}
	public int previous(int r){
		/*
		 * 返回比R小的最大素数，如果r为9，那返回是7;
		 */

		if (r < 3) {
			return -1;// 返回-1，警告出错。最常见的错误是超过int可表达范围
		}
		if (r > max) {
			try {
				to(r);
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
			return primes[limit-1];
		}
		
		int s =0;//start 起点
		int e = limit-1;//end 止点
		int t;//target 目标
		
		int g = e-s;//gap 差距
		while(g>1){
			t = s +(g>>1);
			if(r>primes[t]){
				s = t;
			}else if(r<primes[t]){
				e = t;
			}else{
				return primes[--t];
			}
			g = e-s;
		}
		return primes[s];
		
	}

	
	public Iterator<Integer> iterator() {
		
		return new Iterator<Integer>(){
			private int index;
			public boolean hasNext() {
				
				return index<limit;
			}

			public Integer next() {
				if(!hasNext()){
					throw new NoSuchElementException();
				}
				return primes[index++];
			}

			public void remove() {
				throw new UnsupportedOperationException();
				
			}
			
		};
	}

	public int size() {
		
		return limit;
	}

}
