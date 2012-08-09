package hapyboy.tools.colls.stack;

import java.util.Iterator;
/*
 * 可扩展专用堆栈,堆栈容量会随着加入元素的增加而自动增加，会随着元素的减少自动减少。
 */
public interface Stack<E> extends Iterable<E>{
	
	boolean push(E	e);//堆栈不接受Null元素，放入Null元素时不会抛出异常，只是简单返回false
	E pop();//如果堆栈为空时调用Pop会抛出异常
	
	E peek();//如果堆栈为空时调用Peek会抛出异常
	
	
	/*
	 * 从顶部开始记数，最上面的对象（也就是调用pop会弹出的那个对象）的索引为0，它下面的为1，以此类推
	 * 如果索引index大于堆栈容量，会抛出异常
	 */
	E peek(int index);
	int search(E o);//从顶部开始查找，找到后立即返回索引，如果没找到返回-1
	
	boolean isEmpty();
	
	/*
	 * 返回的迭代器不能做删除操作，调用remove方法时，本迭代器会抛出UnsupportedOperationException异常，
	 * 
	 * Note:堆栈只能从头部删除，如果你想从中间删除，你应该考虑别的数据结构
	 * 
	 */
	Iterator<E> iterator(); 
}
