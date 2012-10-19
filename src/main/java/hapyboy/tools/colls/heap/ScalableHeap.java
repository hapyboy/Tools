package hapyboy.tools.colls.heap;

import hapyboy.tools.colls.GenericUtil;

import java.lang.reflect.Array;
import java.util.Comparator;


public abstract class ScalableHeap <E>{
	private E[] heap;
	private int index;
	Comparator<E> cc;
	
	/** 默认初始容量*/
	private static final int LEN = 16;
	
	private final Class<E> clz;
	

	public ScalableHeap(Comparator<E> comparator) {
		this(comparator,LEN);
	}

	@SuppressWarnings("unchecked")
	public ScalableHeap(Comparator<E> comparator,int capacity) {
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
		class SimpleHeap extends ScalableHeap<T>
		{

			public SimpleHeap(Comparator<T> comparator, int capacity)
			{
				super(clz, comparator, capacity);
			}
		
		}
		
		return new SimpleHeap(comparator,initCapacity);
	}
	
	public boolean add(E p) {
		if (p == null) {
			return false;
		}
		if(index >=heap.length){
			expands();
		}
		int ori = index++; // Origin
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
		System.arraycopy(heap, 0, arr, 0, index);
		heap = arr;
		arr = null;
		
	}

	public E remove() {
		if (index == 0) {
			return null;
		}
		E result = heap[0];
		E end = heap[--index];
		heap[index] = null;
		int ori = 0; // Origin
		int t = (ori << 1) + 1; // Target
		
		while (t < index) {
			if (t + 1 < index) {// 防止越界
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
		return index;
	}

}
