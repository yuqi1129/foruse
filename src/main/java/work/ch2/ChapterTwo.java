package work.ch2;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/1/16 下午10:55
 */

public class ChapterTwo {

	public static void main(String[] args) {

		int[] a = new int[] { 3, 4, 5, 1, 2};
		System.out.println(getMin(a));
	}


	//
	public static int getMin(int[] a) {

		int low = 0;
		int high = a.length - 1;
		int mid = (high + low) >> 1;

		while (low < high) {
			if (a[mid] < a[mid - 1] && a[mid] < a[mid + 1]) {
				return a[mid];
			} else if (a[mid] > a[mid-1] && a[mid] < a[mid - 1]) {
				low = mid + 1;
			} else {
				high = mid - 1;
			}

			mid = (high + low) >> 1;
		}

		return -1;
	}
}
