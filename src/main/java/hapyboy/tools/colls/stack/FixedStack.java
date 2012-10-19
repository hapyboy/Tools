package hapyboy.tools.colls.stack;

import hapyboy.tools.colls.GenericUtil;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**    
 * 长度固定的栈
 * 
 * @author 赵利波 <zhaolibo@cyanclone.com>    
 */
public class FixedStack<E> implements Stack<E>
{
	/** 容量*/
	protected int capacity;
	
	/** 游标同时起计数作用*/
	protected int cur;
	
	/** 栈容器*/
	protected E[] stacks;
	
	@SuppressWarnings("unchecked")
	protected FixedStack(int initcapacity){
		capacity = initcapacity;
		
		GenericUtil<E> gutil = new GenericUtil<E>();
		Class<E> clz = gutil.getGeneric(this);
		
		stacks = (E[]) Array.newInstance(clz, capacity);
	}
	
	@SuppressWarnings("unchecked")
	public FixedStack(Class<E> clz, int initcapacity){
		capacity = initcapacity;
		stacks = (E[]) Array.newInstance(clz, capacity);
	}
	
	/**
	 * 获取定长栈
	 * 
	 * @param clz 栈要容纳数据的类型
	 * @param capacity 栈大小
	 * @return 固定长度的栈实例
	 */
	public static <T> FixedStack<T> newInstance(Class<T> clz, int capacity)
	{
		return new FixedStack<T>(clz,capacity);
	}
	

	@Override
	public boolean push(E e)
	{
		if(cur >= capacity){
			return false;
		}
		
		stacks[cur++] = e;
		return true;
	}

	@Override
	public E pop()
	{
		if(cur < 1){
			return null;
		}
		E e = stacks[--cur];
		stacks[cur] = null;
		return e;
	}

	@Override
	public E peek()
	{
		if(cur<1){
			return null;
		}
		
		return stacks[cur-1];
	}

	@Override
	public E peek(int index)
	{
		if(index<0 || index >= cur)
		{
			return null;
		}
		return stacks[cur-index-1];
	}

	@Override
	public int search(E e)
	{
		if(cur <1){
			return -1;
		}
		for(int i = cur-1; i>=0; i--)
		{
			if(stacks[i].equals(e))
			{
				return cur-i-1;
			}
		}
		return -1;
	}

	@Override
	public boolean isEmpty()
	{
		return cur<1;
	}

	@Override
	public Iterator<E> iterator()
	{
		return new Iterator<E>(){
			private int c;
			@Override
			public boolean hasNext()
			{
				return c < cur;
			}

			@Override
			public E next()
			{
				if(!hasNext()){
					throw new NoSuchElementException("迭代到最后了，已经没有下一个！");
				}
				return stacks[cur-c-1];
			}

			@Override
			public void remove()
			{
				throw new UnsupportedOperationException("迭代器不支持删除操作！");
				
			}
			
		};
	}

	@Override
	public void clear()
	{
		if(cur>1){
			for(;cur>0;){
				stacks[--cur] = null;
			}
		}
		
	}
	
}
