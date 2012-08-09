package hapyboy.tools.number;

import java.util.NoSuchElementException;

//求素数（质数）
public class Primes {
	private static final int L = 1000;
	private static int[] primes;
	private static int limit;

	private static final void add(int num) {

		primes[limit++] = num;

	}

	public static void main(String[] args) {

		long a = System.currentTimeMillis();
		int[] array = get();
		long b = System.currentTimeMillis();
		b -= a;
		int c = 0;
		for (int i = 0; i < limit; i++) {
			System.out.print(array[i] + "\t");
			c++;
			if (c > 10) {
				System.out.print('\n');
				c = 0;
			}
		}
		System.out.println();
		System.out.println("所用时间：" + b + "毫秒");
		System.out.println("找到：" + limit + "个素数");

	}

	private static final boolean isPrime(int s) {
		for (int i = 0; i < limit; i++) {
			if (s % primes[i] == 0)
				return false;
		}
		return true;

	}

	public static final int[] get() {
		return get(L);

	}

	private static int[] get(int count) {
		
		if(count<1){
			throw new NoSuchElementException("请输入有效数字（大于1）！");
		}
		if(count<4){
			primes = new int[3];
			add(2);
			add(3);
			add(5);
			return primes;
		}
		primes = new int[count];
		add(2);
		add(3);
		add(5);
		int start = 6;
		for (; limit < count;) {
			if (isPrime(start)) {
				add(start);
				

			}
			start++;
			
		}
		return primes;
	}
}
