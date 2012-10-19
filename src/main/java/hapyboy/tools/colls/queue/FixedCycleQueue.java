package hapyboy.tools.colls.queue;

import hapyboy.tools.colls.GenericUtil;

import java.lang.reflect.Array;
import java.util.Iterator;

/**    
 * 固定长度环绕队列
 * 当队列满的时候如果再加入元素会丢弃最前面的元素
 * 第二位的元素将成为最前面的元素
 * 
 * @author 赵利波 <zhaolibo@cyanclone.com>    
 */
public abstract class FixedCycleQueue<E> implements IQueue<E>
{
	/** 队列容器，用来存放所要容纳的元素*/
	private E[] queue;
	/** 队列的头*/
	private int head;
	/** 队列尾*/
	private int tail;
	/** 队列当前容量*/
	private int size;
	
	/** 队列最大容量限制*/
	private final int len;

	@SuppressWarnings("unchecked")
	public FixedCycleQueue(int length)
	{
		this.len = length;
		
		GenericUtil<E> gutil = new GenericUtil<E>();
		Class<E> clz = gutil.getGeneric(this);
		
		queue = (E[]) Array.newInstance(clz, length);
	}
	
	@SuppressWarnings("unchecked")
	public FixedCycleQueue(Class<E> clz, int length)
	{
		this.len = length;
		queue = (E[]) Array.newInstance(clz, length);
	}
	
	/**
	 * 获取类实例
	 * 
	 * @param claz 队列里要容纳数据的类型
	 * @param capacity 初始容量
	 * @return 固定长度环绕队列
	 */
	public static <T> FixedCycleQueue<T> newInstance(final Class<T> claz, int capacity)
	{
		class SimpleQueue extends FixedCycleQueue<T>
		{

			public SimpleQueue( int capacity)
			{
				super(claz,capacity);
			}
			
		}
		return new SimpleQueue(capacity);
	}

	@Override
	public boolean enqueue(E e)
	{
		if(size == len)
		{
			head++;
			checkHead();
		}else{
			size++;
		}
		queue[tail++] = e;
		checkTail();
		
		return true;
	}

	
	/** 检查头是否越界*/
	private final void checkHead()
	{
		if(head >= len)
		{
			head = 0;
		}		
	}
	/** 检查尾是否越界*/
	private final void checkTail()
	{
		if(tail >= len)
		{
			tail = 0;
		}
		
	}

	@Override
	public E dequeue()
	{
		if(size <= 0)
		{
			return null;
		}
		size--;
		E e = queue[head];
		queue[head++] = null;
		checkHead();
		return e;
	}

	@Override
	public int size()
	{
		return size;
	}
	
	@Override
	public boolean isEmpty()
	{
		
		return size <= 0;
	}

	public E get()
	{
		if(size <= 0){
			return null;
		}
		return queue[head];
	}
	
	@Override
	public E get(int index)
	{
		if(index<0 || index >= size)
		{
			return null;
		}
		return queue[head+index];
	}

	
	//----------------------------------迭代器部分----------------------------------
	
	

	@Override
	public Iterator<E> iterator()
	{
		// TODO Auto-generated method stub
		return new Iterator<E>(){
			private int cur;
			
			@Override
			public boolean hasNext()
			{
				return cur < size;
			}

			@Override
			public E next()
			{
				E e = queue[cur+head];
				cur++;
				return e;
			}

			@Override
			public void remove()
			{
				// TODO Auto-generated method stub
				
			}
		};
	}

	

}
