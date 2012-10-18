package hapyboy.tools.colls.queue;


/**    
 * 固定环绕队列测试
 * 
 * @author 赵利波 <zhaolibo@cyanclone.com>    
 */
public class TestQueue extends FixedCycleQueue<String>
{

	public TestQueue(int length)
	{
		
		super(length);
	}
	
	public static void main(String[] args)
	{
		IQueue<String> queue = new TestQueue(10);
		for(int i = 0; i<100; i++)
		{
			queue.enqueue(""+i);
		}
		
		int c = queue.size();
		System.out.println("Size:"+c);
		for(int i = 0; i<=10 ;i++)
		{
			System.out.println("---"+i+"---");
			System.out.println(queue.dequeue()+'\t'+"Size:"+queue.size());
			
			System.out.println();
		}

		
	}


}
