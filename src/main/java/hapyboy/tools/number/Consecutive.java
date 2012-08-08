package hapyboy.tools.number;

public class Consecutive {
	public static final int[] get(int start,int end){
		/*
		 * 得到从start 到 end之间的连续数字
		 * 注意：包括start，但不包括end
		 */
		if(start<=end){
			return null;
		}
		int[] result = new int[end-start];
		int index = 0;
		for(int i=start; i<end; i++){
			result[index++] = i;
		}
		return result;
	}
	public static final int[] get(int start,int step,int count){
		/*
		 * 得到从start 开始，每次累加step的数组; 
		 * 
		 */
		int[] result = new int[count];
		int temp = start;
		for(int i=0; i<count; i++){
			
			result[i] = temp;
			temp  +=step;
		}
		return result;
	}
}
