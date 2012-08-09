package hapyboy.tools.colls.stack;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;
/*
 * 可扩展专用堆栈,堆栈容量会随着加入元素的增加而自动增加，会随着元素的减少自动减少。
 * 本实现是最基本的，没有对泛型、多线程的支持，这样是为了最简单最快速
 */
public class BaseStack {
	/*
	 * 可扩展专用堆栈
	 */
	private int capacity;
	private int limit;
	private Object[] stacks;
	public BaseStack(){
		this(16);
	}
	BaseStack(int initcapacity){
		if(initcapacity<16){
			initcapacity = 16;
		}
		capacity = initcapacity;
		stacks = new Object[capacity];
	}
	
	public boolean push( Object obj){
		if(obj == null){//不保存空值，如果obj为空不会抛出异常，仅仅返回false
			return false;
		}
		if(limit == capacity){//堆栈满后调用方法来扩展
			expands();
		}
		stacks[limit++]= obj;
		return true;
	}
	private void expands() {
		// 容量扩展方法
		capacity += capacity>>>1;//这里就不检查capacity超出int最大正数了，一般的应用基本不会超的
		Arrays.copyOf(stacks, capacity);
		
	}
	public Object pop(){
		checkEmpty();
		Object obj = stacks[--limit];
		stacks[limit] = null;
		if(capacity>16 && capacity/limit >2){
			lower();
		}
		return obj;
	}
	
	private void lower() {
		capacity = limit + (limit>>>1);
		Arrays.copyOf(stacks, capacity);
		
	}
	public Object peek(){
		checkEmpty();
		return stacks[limit-1];
	}
	private final void checkEmpty() {
		if(limit == 0){
			throw new EmptyStackException();
		}
	}
	
	public Object peek(int index){
		//从顶部开始记数，数字从0开始
		//如最上面的对象（也就是调用pop会弹出的那个对象）的索引为0，它下面的为1，以此类推
		if(index >= limit){
			throw new NoSuchElementException();
		}
		return stacks[limit-1-index];
	}
	public int search(Object obj){
		//从顶部开始查找，找到后立即返回索引，如果没找到返回-1
		int t = limit-1;
		for (int i = 0; i < limit; i++) {
			if(stacks[t].equals(obj)){
				return i;
			}
			t--;
		}
		return -1;
	}
	
	public boolean isEmpty(){
		return limit == 0;
	}
	public Iterator<?> iterator(){
		return new Iterator<Object>(){
			private int t = limit-1;
			public boolean hasNext() {
				
				return t>=0;
			}

			public Object next() {
				if(!hasNext()){
					throw new NoSuchElementException();
				}
				return stacks[t--];
			}

			public void remove() {
				throw new UnsupportedOperationException("本迭代器不支持remove方法！");
				
			}
			
		}; 
	}
}
