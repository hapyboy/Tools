package hapyboy.tools.colls.stack;

import java.util.Iterator;

public  class SimpleStack<E> implements Stack<E> {
	private BaseStack stack;
	public SimpleStack(){
		this.stack = new BaseStack();
	}
	public SimpleStack(int initcapacity){
		this.stack = new BaseStack(initcapacity);
	}
	public boolean push(E e) {
		
		return stack.push(e);
	}

	@SuppressWarnings("unchecked")
	public E pop() {
		
		return (E) stack.pop();
	}

	@SuppressWarnings("unchecked")
	public E peek() {
		
		return (E) stack.peek();
	}

	@SuppressWarnings("unchecked")
	public E peek(int index) {
		
		return (E) stack.peek(index);
	}

	public int search(E o) {
		
		return stack.search(o);
	}

	public boolean isEmpty() {
		
		return stack.isEmpty();
	}

	@SuppressWarnings("unchecked")
	public Iterator<E> iterator() {
		
		return (Iterator<E>) stack.iterator();
	}

}
