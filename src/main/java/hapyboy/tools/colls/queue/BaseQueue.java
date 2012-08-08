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
			extend();
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
			lessen();
		}

		return obj; // $codepro.audit.disable

	}

	private void lessen() {
		capacity = calculateCapacity();
		Object[] newqueue = new Object[capacity];
		if(head<end){
			System.arraycopy(queue, head, newqueue, 0, count);
			head =0;
			end = count;
			return;
		}
		
		int c = queue.length -head;
		System.arraycopy(queue, head, newqueue, 0, c);
		System.arraycopy(queue, 0, newqueue, c, end);
		head =0;
		end = count;
	}

	private final int calculateCapacity() {
		
		return count + (count>>>1);
	}

	private void extend() {
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
			private int c;//count

			public boolean hasNext() {
				
				return c <= count;
			}

			public Object next() {
				/*
				 * 如果
				 */
				if(hasNext()){
					c++;
					return get((head+index++)%capacity);
				}
				throw new NoSuchElementException();
			}

			public void remove() {
				//什么也不做
				//队列禁止从中间非头部删除
				
			}
			
			
		};
	}

	public boolean hasNext() {

		return count > 0;
	}
}
