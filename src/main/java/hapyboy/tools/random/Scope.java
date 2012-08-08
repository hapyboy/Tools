package hapyboy.tools.random;

import java.util.Random;
/*
 * 用于得到自定义范围内的随机数;
 * 返回的数不会重复
 * 
 * 如 Scope r = new Scoper(1,500);
 * 每次调用 r.next() 都会得到1到500以内的一个数字，最多可以调用499次，每次调用返回值不会重复
 */

public class Scope {
	private int[] container;
	private int index;
	Random random;
	public Scope(int size){
		this(0,size);
	}

	public Scope(int start, int end) {
		
		container = new int[end-start];
		for(int i=start; i<end; i++){
			container[index++] = i;
		}
		random = new Random();
	}
	public int next() throws EmptyException{
		if(index == 0){
			throw new  EmptyException("已经掏空了，还要！真贪婪！！");
		}
		int i = random.nextInt(index--);
		int result = container[i];
		System.arraycopy(container, i+1, container, i, index-i);
		return result;
	}
	public static void main(String[] args) throws EmptyException {
		Scope scope = new Scope(50);
		for(int i=0;i<51;i++){
			System.out.println(scope.next());
		}
	}
}
