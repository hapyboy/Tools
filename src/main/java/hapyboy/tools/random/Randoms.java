package hapyboy.tools.random;

import java.util.Random;

/*
 * 方便得到各种各样的随机数 数组
 */
public class Randoms {

	private static final Random random = new Random();

	public static final boolean[] booleans(int size) {
		boolean[] arr = new boolean[size];
		int index = 0;

		int data = 0;
		for (int i = index; i < size; i++) {
			if ((index % 32 == 0)) {
				data = random.nextInt();
			}
			
			arr[index++] = (data&1) ==1 ? true:false;
			
			data >>>= 1;
		}

		return arr;
	}
	public static final byte[] bytes(int size){
		byte[] result = new byte[size];
		int tmp=0;
		for(int i=0; i<size; i++){
			if(i%4 ==0){
				tmp = random.nextInt();
			}
			result[i] = (byte) tmp;
			tmp >>>=8;
		}
		return result;
	}

	public static final short[] shorts(int size) {
		short[] result = new short[size];
		
		int data =0;
		for (int i = 0; i < size; i++) {
			if(i%2==0){
				data = random.nextInt();
			}
			result[i] = (short) data;
			data >>>= 16;
		}
		return result;
	}

	public static final int[] ints(int size) {
		int[] result = new int[size];
		
		for (int i = 0; i < size; i++) {

			result[i] = random.nextInt();
		}
		return result;
	}

	public static final long[] longs(int size) {
		long[] result = new long[size];
		
		for (int i = 0; i < size; i++) {

			result[i] = random.nextLong();
		}
		return result;
	}
	public static final float[] floats(int size) {
		float[] floats = new float[size];
		for (int i = 0; i < size; i++) {
			floats[i] = random.nextFloat();
		}
		return floats;
	}

	public static final double[] doubles(int size) {
		double[] doubles = new double[size];
		for (int i = 0; i < size; i++) {
			doubles[i] = random.nextDouble();
		}
		return doubles;
	}

	public static void main(String[] args) {
		final int count = 50;
		boolean[] arr = booleans(count);
		for (int i = 0; i < count; i++) {
			System.out.println(arr[i]);
		}

	}
	//
	
	
}
