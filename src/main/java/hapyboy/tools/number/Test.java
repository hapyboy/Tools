package hapyboy.tools.number;

import java.util.ArrayList;
import java.util.List;

/**
 * //TODO
 * 
 * @author 赵利波 <zhaolibo@cyanclone.com>
 */
public class Test {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		String[] array = { "1", "2", "3", "4", "10", "11", "23", "24", "25",
				"27", "26" };

		boolean m = false;
		String start = array[0];
		int rule = Integer.parseInt(start);
		int hand = 0;
		String s = null;
		int length = array.length;
		for (int i = 1; i < length - 1; i++) {
			s = array[i];
			hand = Integer.parseInt(s);

			if (hand == rule + 1) {
				m = true;
				rule = hand;
				continue;
			}
			if (m) {
				list.add(start + "-" + rule);

			} else {
				list.add(start);
			}
			rule = hand;
			m = false;
			start = s;

		}
		s = array[length - 1];
		hand = Integer.parseInt(s);

		if (hand == rule + 1) {
			list.add(start + "-" + s);
		} else {
			if (m) {
				list.add(start + "-" + rule);
				list.add(s);
			} else {

				list.add(start);
				list.add(s);
			}

		}

		for (String content : list) {
			System.out.println(content);
		}

	}

}
