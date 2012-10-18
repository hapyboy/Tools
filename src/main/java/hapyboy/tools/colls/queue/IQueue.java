package hapyboy.tools.colls.queue;

/**    
 * 非线程安全队列，队列实现的迭代器不支持删除操作，但不抛出异常只是简单的忽略
 * 
 * @author 赵利波 <zhaolibo@cyanclone.com>    
 */

public interface IQueue<E> extends Iterable<E>
{
	/**
	 * 添加元素到队列尾部
	 * 
	 * @param e 要添加的元素
	 * @return 添加成功返回True， 如果队列因为容量已经用完或要添加的元素不符合要求返回Null，如有些实现不接受为Null的元素
	 */
	boolean enqueue(E e);
	
	/**
	 * 移除队列最前面的元素
	 * 
	 * @return 排在最前面的元素，如果队列为空时返回Null
	 */
	E dequeue();
	
	/**
	 * 获取队列中已经容纳元素的个数
	 * 
	 * @return 元素的个数
	 */
	int size();
	
	/**
	 * 获取队列最前面的元素，但是不移除
	 * 
	 * @return 队列中最前面的元素，如果队列为空时返回Null
	 */
	E get();
	
	/**
	 * 获取队列中第index位元素，但是不移除
	 * 计数从0开始，第0位表示队列中最上面那个元素
	 * 
	 * @param index 在获取的元素在队列中的位数
	 * @return 指定位置的元素，如果index指定的范围无效返回null,比如队列中有10个元素时，如果指定大于等于10的元素，返回为Null
	 */
	E get(int index);
	
	/**
	 * 查看队列是否为空
	 * 
	 * @return 队列是否为空返回false
	 */
	boolean isEmpty();
}
