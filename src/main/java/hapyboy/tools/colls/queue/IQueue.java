package hapyboy.tools.colls.queue;

import java.util.Iterator;

public interface IQueue <E>{
boolean add(E obj);
	
	E remove();
	E get();
	E get(int index);
	Iterator<E> iterator();//本迭代器不能做删除操作，允许实现抛出异常，或什么也不做
	
	int size();
	boolean hasNext();
}
