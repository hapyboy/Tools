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

public class ScalableStack<E> implements Stack<E>{
	
	/** 容量*/
	protected int capacity;
	/** 游标同时也是计数器*/
	protected int cur;
	/** 栈容器*/
	protected E[] stacks;
	
	/** 默认初始容量*/
	protected static final int LEN = 16;
	
	protected ScalableStack(){
		this(LEN);
	}
	
	@SuppressWarnings("unchecked")
	protected ScalableStack(int initcapacity){
		if(initcapacity < LEN){
			initcapacity = LEN;
		}
		capacity = initcapacity;
		
		GenericUtil<E> gutil = new GenericUtil<E>();
		Class<E> clz = gutil.getGeneric(this);
		
		stacks = (E[]) Array.newInstance(clz, capacity);
	}
	
	public ScalableStack(Class<E> clz)
	{
		this(clz,LEN);
	}
	
	@SuppressWarnings("unchecked")
	public ScalableStack(Class<E> clz, int initcapacity){
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
	public static <T> ScalableStack<T> newInstance(final Class<T> claz, int initCapacity)
	{
//		class SimpleStack extends ScalableStack<T>
//		{
//			SimpleStack(int capacity)
//			{
//				super(clz,capacity);
//			}
//		}
//		
//		return new SimpleStack(initCapacity);
		
		return new ScalableStack<T>(claz,initCapacity);
	}
	
	public boolean push(E e){
		if(e == null){//不保存空值，如果obj为空不会抛出异常，仅仅返回false
			return false;
		}
		if(cur == capacity){//堆栈满后调用方法来扩展
			expands();
		}
		stacks[cur++] = e;
		return true;
	}
	
	private void expands() {
		// 容量扩展方法
		capacity += capacity>>>1;//这里就不检查capacity超出int最大正数了，一般的应用基本不会超的
		stacks = Arrays.copyOf(stacks, capacity);
		
	}
	public E pop(){
		checkEmpty();
		E e = stacks[--cur];
		stacks[cur] = null;
		if(capacity>16 && capacity/cur >2){
			lower();
		}
		return e;
	}
	
	private void lower() {
		capacity = cur + (cur>>>1);
		Arrays.copyOf(stacks, capacity);
		
	}
	
	public E peek(){
		checkEmpty();
		return stacks[cur-1];
	}
	
	private final void checkEmpty() {
		if(cur == 0){
			throw new EmptyStackException();
		}
	}
	
	public E peek(int index){
		//从顶部开始记数，数字从0开始
		//如最上面的对象（也就是调用pop会弹出的那个对象）的索引为0，它下面的为1，以此类推
		if(index >= cur){
			throw new NoSuchElementException();
		}
		return stacks[cur-1-index];
	}
	public int search(E e){
		//从顶部开始查找，找到后立即返回索引，如果没找到返回-1
		int t = cur-1;
		for (int i = 0; i < cur; i++) {
			if(stacks[t].equals(e)){
				return i;
			}
			t--;
		}
		return -1;
	}
	
	public boolean isEmpty(){
		return cur == 0;
	}

	@Override
	public Iterator<E> iterator(){
		return new Iterator<E>(){
			private int t = cur-1;
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

	@Override
	public void clear()
	{
		if(cur<1){
			return;
		}
		for(;cur>0;)
		{
			stacks[--cur] = null;
		}
		
	}
}
