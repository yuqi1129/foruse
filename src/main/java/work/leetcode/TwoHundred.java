package work.leetcode;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/3/6 下午4:11
 */

public class TwoHundred {

	private static void printIntArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}

		System.out.println();
	}
	private static int[] minLongSumOfN(int[] a, int s) {
		int[] res = new int[2];
		int min = Integer.MAX_VALUE;

		int start = 0;
		int end = 0;

		int sum = 0;

		while (end < a.length) {
			while (sum < s && end < a.length) {
				sum += a[end++];
			}

			if (end == a.length) {
				break;
			}

			if (sum == s) {
				if (end - start + 1 < min) {
					res[0] = start;
					res[1] = end;
				}
			} else if (sum > s) {
				sum -= a[start];
				start++;
			}
		}

		return res;
	}

	public static void main(String[] args) {
		int[] data = new int[] {2,3,1,2,4,3};
		int[] res = minLongSumOfN(data, 7);
		printIntArray(res);
	}
}
