package hapyboy.tools.colls.heap;

import hapyboy.tools.colls.GenericUtil;

import java.lang.reflect.Array;
import java.util.Comparator;


public class ScalableHeap <E>
{
	/** 堆容器*/
	protected E[] heap;
	/** 游标同时也是计数器*/
	protected int cur;
	/** 比较器*/
	protected Comparator<E> cc;
	
	/** 默认初始容量*/
	protected static final int LEN = 16;
	
	protected final Class<E> clz;
	
	protected ScalableHeap(Comparator<E> comparator) {
		this(comparator,LEN);
	}

	@SuppressWarnings("unchecked")
	protected ScalableHeap(Comparator<E> comparator,int capacity) {
		this.cc = comparator;
		
		GenericUtil<E> gutil = new GenericUtil<E>();
		clz = gutil.getGeneric(this);
		
		heap =(E[]) Array.newInstance(clz, capacity);
	}
	
	@SuppressWarnings("unchecked")
	public ScalableHeap(Class<E> clz, Comparator<E> comparator,int capacity) {
		this.cc = comparator;
		this.clz = clz;
		
		heap =(E[]) Array.newInstance(clz, capacity);
	}
	
	/**
	 * 获取堆实例,初始容量为默认值
	 * 
	 * @param clz 堆里要存放数据的类型
	 * @param comparator 数据比较器
	 * @return 初始容量为默认大小的堆实例
	 */
	public static <T> ScalableHeap<T> newInstance(Class<T> clz, Comparator<T> comparator)
	{
		return newInstance(clz,comparator,LEN);
	}
	
	/**
	 * 获取指定初始大小的堆实例
	 * 
	 * @param clz 堆里要存放数据的类型
	 * @param comparator 数据比较器
	 * @param initCapacity 初始化容量
	 * @return 堆实例
	 */
	public static <T> ScalableHeap<T> newInstance(final Class<T> clz, Comparator<T> comparator, int initCapacity)
	{
//		class SimpleHeap extends ScalableHeap<T>
//		{
//
//			public SimpleHeap(Comparator<T> comparator, int capacity)
//			{
//				super(clz, comparator, capacity);
//			}
//		
//		}
//		
//		return new SimpleHeap(comparator,initCapacity);
		return new ScalableHeap<T>(clz,comparator,initCapacity);
	}
	
	public boolean add(E p) {
		if (p == null) {
			return false;
		}
		if(cur >=heap.length){
			expands();
		}
		int ori = cur++; // Origin
		int t; // Target
		while (ori != 0) {
			t = (ori - 1) >>> 1;// 上一级坐标
			if (cc.compare(p, (E)heap[t]) > 0) {//只有比上一级大时才继续向上
				heap[ori] = heap[t];
				ori = t;
				continue;
			}
			break;
		}
		heap[ori] = p;
		return true;
	}

	@SuppressWarnings("unchecked")
	private void expands() {
		int length = heap.length;
		length += length>>1;
		E[] arr = (E[]) Array.newInstance(clz, length);
		System.arraycopy(heap, 0, arr, 0, cur);
		heap = arr;
		arr = null;
		
	}

	public E remove() {
		if (cur == 0) {
			return null;
		}
		E result = heap[0];
		E end = heap[--cur];
		heap[cur] = null;
		int ori = 0; // Origin
		int t = (ori << 1) + 1; // Target
		
		while (t < cur) {
			if (t + 1 < cur) {// 防止越界
				if(cc.compare(heap[t], heap[t+1]) < 0 )t++;// 从下级中找一个大的
			}
			if (cc.compare(end, heap[t]) < 0) {// 比下级中大的那个小，就向下走
				heap[ori] = heap[t];
				ori = t;
				t = (ori << 1) + 1;
				continue;
			}
			break;
		}
		heap[ori] = end;

		return result;
	}

	public int size() {
		return cur;
	}

}
