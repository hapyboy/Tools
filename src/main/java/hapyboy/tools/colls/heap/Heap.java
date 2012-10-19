package hapyboy.tools.colls.heap;
/**    
 * 堆容器接口
 * 
 * @author 赵利波 <zhaolibo@cyanclone.com>    
 */
public interface Heap<E> extends Iterable<E>
{
	boolean add(E e);
	E remove();
	E get();
	boolean contains(E e);
	int size();
	boolean isEmpty();
}
