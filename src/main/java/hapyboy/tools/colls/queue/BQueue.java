package hapyboy.tools.colls.queue;

import java.util.Iterator;

public interface BQueue {//Base Queue Interface
	boolean add(Object obj);
	
	Object remove();
	Object get();
	Object get(int index);
	Iterator<? extends Object> iterator();//本迭代器不能做删除操作，允许实现抛出异常，或什么也不做
	
	int size();
	boolean hasNext();
}
