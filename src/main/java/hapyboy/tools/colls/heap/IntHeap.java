package hapyboy.tools.colls.heap;

import java.util.Arrays;

import hapyboy.tools.exception.EmptyException;



public class IntHeap {
	private int[] heap;
	private int index;
	private int capacity;
	
	

	
public IntHeap() {
		
		this(16);
	}
	public IntHeap(int capacity) {
		this.capacity = capacity;
		heap = new int[capacity];
	}


	public boolean add(int p) {
		
		if(index >=capacity){
			expands();
		}
		int s = index++; // Source
		int t; // Target
		while (s != 0) {
			t = (s - 1) >>> 1;// 上一级坐标
			if (p>heap[t]) {
				heap[s] = heap[t];
				s = t;
				continue;
			}
			break;
		}
		heap[s] = p;
		return true;
	}

	private void expands() {
		
		capacity += capacity>>1;
		int[] arr = new int[capacity];
		System.arraycopy(heap, 0, arr, 0, index);
		heap = Arrays.copyOf(heap, capacity);
		
	}


	public int remove() throws EmptyException {
		if (index == 0) {
			throw new EmptyException("堆里已经空了！不能再取！！");
		}
		
		int result = heap[0];
		int end = heap[--index];
//		heap[index] = null;
		int s = 0;
		int t = (s << 1) + 1;
		while (t < index) {
			if (t + 1 < index) {// 防止越界
				if( heap[t]<heap[t+1] )t++;// 从下级中找一个大的
			}
			if (heap[t]>end) {// 比下级中大的那个小，就向下走
				heap[s] = heap[t];
				s = t;
				t = (s << 1) + 1;
				continue;
			}
			break;
		}
		heap[s] = end;

		return result;
	}

	public int size() {
		return index;
	}
	
}
