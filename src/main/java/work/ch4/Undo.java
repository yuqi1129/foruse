package work.ch4;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/1/21 上午11:48
 */

import com.google.common.collect.Maps;

import java.util.Map;

public class Undo {

	/*
		median-of-two-sorted-arrays
	 */


	//-----------------------------------------


	/**
	 *
	 */


	public static int lengthOfLongestSubstring(String s) {
		Map<Integer, Integer> map = Maps.newHashMap();


		int start = 0;
		int end = 0;
		int max = 0;
		for (int i = 0; i < s.length(); i++) {
			int tmp = s.charAt(i);

			if (map.get(tmp) == null) {
				map.put(tmp, i);

			} else {
				//clear -->

			}
		}

		return max;
	}

	public static void main(String[] args) {
		String s = "this is just a test";
		System.out.println(lengthOfLongestSubstring(s));

		char[] chars = new char[10];
		brance(chars, 0, 0, 0);
	}

	//
	public static void number(String a) {

	}


	public static void brance(char[] a, int n, int left, int right) {
		if (n >= a.length ) {
			if(left == a.length / 2 && right == a.length / 2) {
				printArray(a);
				return;
			} else {
				return;
			}
		}

		if (right > left) {
			return;
		}

		a[n] = '(';
		brance(a, n + 1, left + 1, right);
		a[n] = ')';
		brance(a, n + 1, left, right+1);
	}




	public static void printArray(char[] a) {
		for(int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println("");

	}

}
