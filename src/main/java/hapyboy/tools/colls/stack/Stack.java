package hapyboy.tools.colls.stack;

/*
 * 可扩展专用堆栈,堆栈容量会随着加入元素的增加而自动增加，会随着元素的减少自动减少。
 */
public interface Stack<E> extends Iterable<E>{
	
	/**
	 * 把元素放入堆栈
	 * 堆栈不接受Null元素，放入Null元素时不会抛出异常，只是简单返回false
	 * 
	 * @param e 要放入的元素，不能为Null
	 * @return 放入成功返回True
	 */
	boolean push(E	e);
	
	/**
	 * 返回最后放入的元素
	 * 如果堆栈为空时调用Pop会抛出异常
	 * 
	 * @return 最后放入的元素
	 */
	E pop();
	
	/**
	 * 查看最上面的元素
	 * 如果堆栈为空时调用Peek会抛出异常
	 * 
	 * @return 最上面的元素
	 */
	E peek();
	

	/**
	 * 查看第index个元素
	 * 从顶部开始记数，最上面的对象（也就是调用pop会弹出的那个对象）的索引为0，它下面的为1，以此类推
	 * 如果索引index大于堆栈容量，会抛出异常
	 * 
	 * @param index
	 * @return 要查看的元素
	 */
	E peek(int index);
	
	/**
	 * 查找制定元素
	 * 
	 * @param o 要查找的元素
	 * @return 第一个找到元素的索引，如果没找到返回-1
	 */
	int search(E o);//从顶部开始查找，找到后立即返回索引，
	
	/**
	 * 查看堆是不是空的
	 * 
	 * @return 堆空与否
	 */
	boolean isEmpty();
	
}
