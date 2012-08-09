package hapyboy.tools.colls.queue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * 可扩展队列，
 * Note: 不做边界检查，因为主要用在手机上，int的边界很大了，一般不会出问题
 */

public class BaseQueue implements BQueue {

	private Object[] queue;

	private int head;
	private int end;
	private int count;
	private int capacity;

	public BaseQueue() {
		this(16);
	}

	public BaseQueue(int length) {
		if (length < 16) {
			length = 16;
		}
		capacity = length;
		queue = new Object[capacity];
	}

	public boolean add(Object obj) {

		if (count >= capacity) {
			extands();
		}
		queue[end++] = obj;

		count++;
		if (end == capacity) {
			end = 0;
		}
		return true;

	}

	public Object remove() {
		if (count == 0) {
			throw new NoSuchElementException("队列已取空！");
		}

		Object obj = queue[head];
		queue[head++] = null;
		count--;
		if (head == capacity) {
			head = 0;
		}
		if( count>16 && capacity/count>2 ){
			shrink();
		}

		return obj; // $codepro.audit.disable

	}

	private void shrink() {
		capacity = calculateCapacity();
		Object[] newqueue = new Object[capacity];
		if(head<end){
			System.arraycopy(queue, head, newqueue, 0, count);
		}else{
			int c = queue.length -head;
			System.arraycopy(queue, head, newqueue, 0, c);
			System.arraycopy(queue, 0, newqueue, c, end);
		}
		
		
		
		head =0;
		end = count;
		queue = newqueue;
	}

	private final int calculateCapacity() {
		
		return count + (count>>>1);
	}

	private void extands() {
		capacity = calculateCapacity();

		if (head == 0) {
			queue = Arrays.copyOf(queue, capacity);

		} else {
			Object[] newqueue = new Object[capacity];
			int c = count-head;
			System.arraycopy(queue, head, newqueue, 0, c);

			System.arraycopy(queue, 0, newqueue, c, end);

			queue = newqueue;

		}
		head = 0;
		end = count;

	}

	public Object get() {
		if(count>0){
			return queue[head];
		}
		throw new NoSuchElementException("队列为空，不能get!");
	}

	public int size() {

		return count;
	}

	public Object get(int index) {
		if(index>=count){
			throw new NoSuchElementException("索引超过界限");
		}
		return queue[(index + head) % capacity];
	}

	public Iterator<? extends Object> iterator() {
		
		return new  Iterator<Object>(){
			private int index;
			

			public boolean hasNext() {
				
				return index < count;
			}

			public Object next() {
				/*
				 * 如果
				 */
				if(hasNext()){
					return get((head+index++)%capacity);
				}
				throw new NoSuchElementException();
			}

			public void remove() {
				//队列禁止从中间非头部删除，如果真要那么做，你应该考虑的是应不应该用队列
				throw new UnsupportedOperationException("本迭代器不支持remove方法！");
				
			}
			
			
		};
	}

	public boolean hasNext() {

		return count > 0;
	}
}
