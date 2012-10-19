package hapyboy.tools.colls.queue;

import java.util.Iterator;

/**    
 * 固定长度的队列
 * 
 * @author 赵利波 <zhaolibo@cyanclone.com>    
 */
public class FixedQueue<E> implements IQueue<E>
{
	/** 队列的头*/
	private int head;
	/** 队列尾*/
	private int tail;
	/** 队列当前容量*/
	private int size;
	/** 队列容量限制*/
	private int capacity;
	
	/** 队列容器，用来存放所要容纳的元素*/
	private E[] queue;
	
	@Override
	public boolean enqueue(E e)
	{
		if(size >= capacity){
			return false;
		}
		queue[tail++] = e;
		checkTail();
		size++;
		return true;
	}

	@Override
	public E dequeue()
	{
		if(size<1){
			return null;
		}
		E e = queue[head];
		queue[head++] = null;
		checkHead();
		size++;
		return e;
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
	@Override
	public int size()
	{
		return size;
	}

	@Override
	public E get()
	{
		if(size<1){
			return null;
		}
		return queue[head];
	}

	@Override
	public E get(int index)
	{
		if(index<0 || index >= size){
			return null;
		}
		
		return queue[(head+index)%capacity];
	}

	@Override
	public boolean isEmpty()
	{
		return size<1;
	}
	
	@Override
	public Iterator<E> iterator()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int search(E e)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear()
	{
		// TODO Auto-generated method stub
		
	}

}
