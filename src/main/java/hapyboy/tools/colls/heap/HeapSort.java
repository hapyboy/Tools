package hapyboy.tools.colls.heap;

import java.util.Comparator;


public class HeapSort <T>{
	private Object[] heap;
	private int index;
	Comparator<T> cc;
	

	public HeapSort(Comparator<T> comparator) {
		this(comparator,16);
	}

	public HeapSort(Comparator<T> comparator,int capacity) {
		this.cc = comparator;
		heap = new Object[capacity];
	}

	@SuppressWarnings("unchecked")
	public boolean add(T p) {
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
			if (cc.compare(p, (T)heap[t]) > 0) {//只有比上一级大时才继续向上
				heap[ori] = heap[t];
				ori = t;
				continue;
			}
			break;
		}
		heap[ori] = p;
		return true;
	}

	private void expands() {
		int length = heap.length;
		length += length>>1;
		Object[] arr = new Object[length];
		System.arraycopy(heap, 0, arr, 0, index);
		heap = arr;
		arr = null;
		
	}

	@SuppressWarnings("unchecked")
	public T remove() {
		if (index == 0) {
			return null;
		}
		Object result = heap[0];
		Object end = heap[--index];
		heap[index] = null;
		int ori = 0; // Origin
		int t = (ori << 1) + 1; // Target
		
		while (t < index) {
			if (t + 1 < index) {// 防止越界
				if(cc.compare((T)heap[t], (T)heap[t+1]) < 0 )t++;// 从下级中找一个大的
			}
			if (cc.compare((T)end, (T)heap[t]) < 0) {// 比下级中大的那个小，就向下走
				heap[ori] = heap[t];
				ori = t;
				t = (ori << 1) + 1;
				continue;
			}
			break;
		}
		heap[ori] = end;

		return (T)result;
	}

	public int size() {
		return index;
	}

}
