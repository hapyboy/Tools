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
 */

public class ScalableQueue<E> implements IQueue<E>{

	private E[] queue;

	private int head;
	private int tail;
	private int count;
	private int capacity;
	
	private final Class<E> clz;

	public ScalableQueue() {
		this(16);
	}
	
	@SuppressWarnings("unchecked")
	public ScalableQueue(int length) {
		if (length < 16) {
			length = 16;
		}
		capacity = length;
		GenericUtil<E> gutil = new GenericUtil<E>();
		clz = gutil.getGeneric(this);
		
		queue = (E[]) Array.newInstance(clz, length);
		
	}


	@Override
	public boolean enqueue(E e)
	{
		if (count >= capacity) {
			extands();
		}
		queue[tail++] = e;

		count++;
		if (tail == capacity) {
			tail = 0;
		}
		return true;
	}

	@Override
	public E dequeue()
	{
		if (count == 0) {
			throw new NoSuchElementException("队列已取空！");
		}

		E e = queue[head];
		queue[head++] = null;
		count--;
		if (head == capacity) {
			head = 0;
		}
		if( count>16 && capacity/count>2 ){
			shrink();
		}

		return e; // $codepro.audit.disable
	}
	
	@Override
	public E get() {
		if(count>0){
			return queue[head];
		}
		throw new NoSuchElementException("队列为空，不能get!");
	}

	@Override
	public int size() {

		return count;
	}

	@Override
	public E get(int index) {
		if(index>=count){
			throw new NoSuchElementException("索引超过界限");
		}
		return queue[(index + head) % capacity];
	}

	@Override
	public boolean isEmpty()
	{
		return count <= 0;
	}
	

	private final int calculateCapacity() {
		
		return count + (count>>>1);
	}
	
	@SuppressWarnings("unchecked")
	private void shrink() {
		capacity = calculateCapacity();
		E[] newqueue =   (E[]) Array.newInstance(clz, capacity);
		if(head<tail){
			System.arraycopy(queue, head, newqueue, 0, count);
		}else{
			int c = queue.length -head;
			System.arraycopy(queue, head, newqueue, 0, c);
			System.arraycopy(queue, 0, newqueue, c, tail);
		}
		
		
		
		head =0;
		tail = count;
		queue = newqueue;
	}


	@SuppressWarnings("unchecked")
	private void extands() {
		capacity = calculateCapacity();

		if (head == 0) {
			queue = Arrays.copyOf(queue, capacity);

		} else {
			E[] newqueue = (E[]) Array.newInstance(clz, capacity);
			int c = count-head;
			System.arraycopy(queue, head, newqueue, 0, c);

			System.arraycopy(queue, 0, newqueue, c, tail);

			queue = newqueue;

		}
		head = 0;
		tail = count;

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

				return cur < count;
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
