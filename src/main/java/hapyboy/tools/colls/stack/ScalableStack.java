package hapyboy.tools.colls.stack;

import hapyboy.tools.colls.GenericUtil;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 可扩展栈,栈容量会随着加入元素的增加而自动增加，会随着元素的减少自动减少。
 * 本实现是非线程安全的，为了更简单更快速
 */

public abstract class ScalableStack<E> implements Stack<E>{
	/*
	 * 可扩展专用堆栈
	 */
	/** 容量*/
	private int capacity;
	/** 游标*/
	private int limit;
	/** 栈容器*/
	private E[] stacks;
	
	/** 默认初始容量*/
	private static final int LEN = 16;
	
	public ScalableStack(){
		this(LEN);
	}
	
	@SuppressWarnings("unchecked")
	ScalableStack(int initcapacity){
		if(initcapacity < LEN){
			initcapacity = LEN;
		}
		capacity = initcapacity;
		
		GenericUtil<E> gutil = new GenericUtil<E>();
		Class<E> clz = gutil.getGeneric(this);
		
		stacks = (E[]) Array.newInstance(clz, capacity);
	}
	
	@SuppressWarnings("unchecked")
	ScalableStack(Class<E> clz, int initcapacity){
		if(initcapacity < LEN){
			initcapacity = LEN;
		}
		capacity = initcapacity;
		stacks = (E[]) Array.newInstance(clz, capacity);
	}
	
	/**
	 * 通过给定泛型，得到栈实例，初始容量采用默认值
	 * 
	 * @param clz 栈内要容纳数据的类型
	 * @return 栈实例
	 */
	public static <T> ScalableStack<T> newInstance(Class<T> clz)
	{
		return newInstance(clz, LEN);
	}
	
	/**
	 * 通过给定泛型，和初始化容量得到栈实例
	 * 
	 * @param clz 栈内要容纳数据的类型
	 * @param initCapacity 初始化容量
	 * @return 栈实例
	 */
	public static <T> ScalableStack<T> newInstance(final Class<T> clz, int initCapacity)
	{
		class SimpleStack extends ScalableStack<T>
		{
			SimpleStack(int capacity)
			{
				super(clz,capacity);
			}
		}
		
		return new SimpleStack(initCapacity);
	}
	
	public boolean push(E e){
		if(e == null){//不保存空值，如果obj为空不会抛出异常，仅仅返回false
			return false;
		}
		if(limit == capacity){//堆栈满后调用方法来扩展
			expands();
		}
		stacks[limit++] = e;
		return true;
	}
	
	private void expands() {
		// 容量扩展方法
		capacity += capacity>>>1;//这里就不检查capacity超出int最大正数了，一般的应用基本不会超的
		Arrays.copyOf(stacks, capacity);
		
	}
	public E pop(){
		checkEmpty();
		E e = stacks[--limit];
		stacks[limit] = null;
		if(capacity>16 && capacity/limit >2){
			lower();
		}
		return e;
	}
	
	private void lower() {
		capacity = limit + (limit>>>1);
		Arrays.copyOf(stacks, capacity);
		
	}
	
	public E peek(){
		checkEmpty();
		return stacks[limit-1];
	}
	
	private final void checkEmpty() {
		if(limit == 0){
			throw new EmptyStackException();
		}
	}
	
	public E peek(int index){
		//从顶部开始记数，数字从0开始
		//如最上面的对象（也就是调用pop会弹出的那个对象）的索引为0，它下面的为1，以此类推
		if(index >= limit){
			throw new NoSuchElementException();
		}
		return stacks[limit-1-index];
	}
	public int search(E obj){
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

	@Override
	public Iterator<E> iterator(){
		return new Iterator<E>(){
			private int t = limit-1;
			public boolean hasNext() {
				
				return t>=0;
			}

			public E next() {
				if(!hasNext()){
					throw new NoSuchElementException();
				}
				return stacks[t--];
			}

			public void remove() {
				throw new RuntimeException("本迭代器不支持remove方法！");
				
			}
			
		}; 
	}
}
