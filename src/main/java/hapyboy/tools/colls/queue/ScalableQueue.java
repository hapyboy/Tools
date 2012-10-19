package hapyboy.tools.colls.queue;

import hapyboy.tools.colls.GenericUtil;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 可扩展队列，
 * Note: 不做边界检查，容量超过int能表达的正数的2/3时可能出问题
 * 因为主要用在手机上，int的边界很大了，一般不会出问题
 * 
 * @author qingfeng
 */

public class ScalableQueue<E> implements IQueue<E>{

	protected E[] queue;

	protected int head;
	protected int tail;
	protected int size;
	protected int capacity;
	
	/** 默认初始容量*/
	protected static final int LEN = 16;
	
	protected final Class<E> clz;

	protected ScalableQueue() {
		this(LEN);
	}
	
	@SuppressWarnings("unchecked")
	protected ScalableQueue(int length) {
		if (length < LEN) {
			length = LEN;
		}
		capacity = length;
		GenericUtil<E> gutil = new GenericUtil<E>();
		clz = gutil.getGeneric(this);
		
		queue = (E[]) Array.newInstance(clz, length);
		
	}
	
	@SuppressWarnings("unchecked")
	public ScalableQueue(Class<E> clz, int length)
	{
		this.clz = clz;
		if (length < LEN) {
			length = LEN;
		}
		capacity = length;
		queue = (E[]) Array.newInstance(clz, length);
		
	}
	
	/**
	 * 获取可扩展队列,采用默认初始容量
	 * 
	 * @param claz 队列内要容纳数据的类型
	 * @return 可扩展队列实例
	 */
	public static <T> ScalableQueue<T> newInstance(Class<T> claz)
	{
		return newInstance(claz, LEN);
	}
	/**
	 * 获取可扩展队列
	 * 
	 * @param claz 队列内要容纳数据的类型
	 * @param capacity 初始化容量
	 * @return 可扩展队列实例
	 */
	public static <T> ScalableQueue<T> newInstance(final Class<T> claz, int capacity)
	{
//		
//		class SimpleQueue extends ScalableQueue<T>
//		{
//
//			public SimpleQueue(int capacity)
//			{
//				super(claz,capacity);
//			}
//			
//		}
//		
//		return new SimpleQueue(capacity);
		
		return new ScalableQueue<T>(claz,capacity);
	}


	@Override
	public boolean enqueue(E e)
	{
		if (size >= capacity) {
			extands();
		}
		queue[tail++] = e;

		size++;
		if (tail == capacity) {
			tail = 0;
		}
		return true;
	}

	@Override
	public E dequeue()
	{
		if (size == 0) {
			throw new NoSuchElementException("队列已取空！");
		}

		E e = queue[head];
		queue[head++] = null;
		size--;
		if (head == capacity) {
			head = 0;
		}
		if( size>16 && capacity/size>2 ){
			shrink();
		}

		return e; // $codepro.audit.disable
	}
	
	@Override
	public E get() {
		if(size>0){
			return queue[head];
		}
		throw new NoSuchElementException("队列为空，不能get!");
	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public E get(int index) {
		if(index>=size){
			throw new NoSuchElementException("索引超过界限");
		}
		return queue[(index + head) % capacity];
	}

	@Override
	public boolean isEmpty()
	{
		return size <= 0;
	}
	

	@Override
	public int search(E e)
	{
		// TODO Auto-generated method stub
		return -1;
	}
	
	@Override
	public void clear()
	{
		if(size < 1){
			return;
		}
		for(;head != tail;){
			queue[head++] = null;
			checkHead();
		}
	}
	
	

	private final int calculateCapacity() {
		
		return size + (size>>>1);
	}
	
	@SuppressWarnings("unchecked")
	private void shrink() {
		capacity = calculateCapacity();
		E[] newqueue =   (E[]) Array.newInstance(clz, capacity);
		if(head<tail){
			System.arraycopy(queue, head, newqueue, 0, size);
		}else{
			int c = queue.length -head;
			System.arraycopy(queue, head, newqueue, 0, c);
			System.arraycopy(queue, 0, newqueue, c, tail);
		}
		
		
		
		head =0;
		tail = size;
		queue = newqueue;
	}


	@SuppressWarnings("unchecked")
	private void extands() {
		capacity = calculateCapacity();

		if (head == 0) {
			queue = Arrays.copyOf(queue, capacity);

		} else {
			E[] newqueue = (E[]) Array.newInstance(clz, capacity);
			int c = size-head;
			System.arraycopy(queue, head, newqueue, 0, c);

			System.arraycopy(queue, 0, newqueue, c, tail);

			queue = newqueue;

		}
		head = 0;
		tail = size;

	}
	
	/** 检查头是否越界*/
	private final void checkHead()
	{
		if(head >= capacity)
		{
			head = 0;
		}		
	}
	/** 检查尾是否越界*/
	private final void checkTail()
	{
		if(tail >= capacity)
		{
			tail = 0;
		}
		
	}


//-----------------------------------迭代器部分-----------------------------------
	


	@Override
	public Iterator<E> iterator()
	{
		return new Iterator<E>()
		{
			private int cur;

			public boolean hasNext()
			{

				return cur < size;
			}

			@Override
			public E next()
			{
				if (hasNext())
				{
					return queue[head + cur++];
				}
				return null;
			}

			public void remove()
			{

			}
		};
	}


}
