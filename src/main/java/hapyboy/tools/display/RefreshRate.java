package hapyboy.tools.display;

public class RefreshRate {
	/*
	 * 本类用于得到屏幕的刷新率
	 * 
	 * 每次刷新时必须调用一次refresh方法，也只能调用一次（不然“刷新率可是要翻番的哦！）
	 * 
	 */
	private int count;
	private long time;
	private int fps = 30;
	public RefreshRate(){
		this.count = fps-1;
		this.time = System.currentTimeMillis();
	}
	public  final int refresh(){
		/*
		 * 用来统计刷新率,每次刷新时都要调用，且只能调用一次（不然“刷新率可是要翻番的哦！）
		 * 
		 */
		count++;
		if(count >= 30){
			long newtime = System.currentTimeMillis();
			int t  = (int) (newtime - time)/count;//每次刷新用了多少毫秒
			time = newtime;
			fps = 1000/t;
			count =0;
		}
		return fps;
	}
	public final int get(){
		/*
		 * 得到刷新率
		 */
		return fps;
	}
}
