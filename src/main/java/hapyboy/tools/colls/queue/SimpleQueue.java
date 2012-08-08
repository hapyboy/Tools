package hapyboy.tools.colls.queue;

import java.util.Iterator;

public class SimpleQueue<E> implements IQueue<E> {
	BQueue queue;

	public SimpleQueue(){
		this(16);
	}
	public SimpleQueue(int i) {
		queue = new BaseQueue(i);
	}
	public boolean add(E obj) {
		
		return queue.add(obj);
	}

	@SuppressWarnings("unchecked")
	public E remove() {
		
		return (E)queue.remove();
	}

	@SuppressWarnings("unchecked")
	public E get() {
		
		return (E) queue.get();
	}

	@SuppressWarnings("unchecked")
	public E get(int index) {
		
		return (E) queue.get(index);
	}

	public Iterator<E> iterator() {
		
		return new Iterator<E>(){
			@SuppressWarnings("unchecked")
			Iterator<E> ite = (Iterator<E>) queue.iterator();
			public boolean hasNext() {
				
				return ite.hasNext();
			}

			public E next() {
			
				return (E)ite.next();
			}

			public void remove() {
				//Do Nothing
				
			}
			
		};
	}

	public int size() {
		
		return queue.size();
	}

	public boolean hasNext() {
		
		return queue.hasNext();
	}

}
