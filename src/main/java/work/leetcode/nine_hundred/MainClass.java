package work.leetcode.nine_hundred;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/3/5 下午5:02
 */

public class MainClass {


	//840
	public static void main(String[] args) {
		int a[] = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
		allSort(a, 0);
	}


	static boolean isValid(int[] a) {
		for (int i = 0; i < 3; i++) {
			if(a[i * 3] + a[i * 3 + 1] + a[i * 3 + 2] != 15) {
				return false;
			}

			if (a[i] + a[i + 3] + a[i + 6] != 15) {
				return false;
			}
		}

		if (a[0] + a[4] + a[8] != 15) {
			return false;
		}

		if (a[2] + a[4] + a[6] != 15) {
			return false;
		}

		return false;
	}

	public static void allSort(int[] a, int start) {

//		if (a[4] != 5) {
//			return;
//		}

		if (start + 1 == a.length) {
			if(isValid(a)) {
				printArray(a);
			}
		}
		for (int i = start + 1; i < a.length; i++) {
			int tmp = a[start];
			a[start] = a[i];
			a[i] = tmp;
			allSort(a, start + 1);
			tmp = a[start];
			a[start] = a[i];
			a[i] = tmp;
		}
	}

	public static void printArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			if (i % 3 == 0) {
				System.out.print("\n");
			}

			System.out.print(a[i] + " ");
		}

		System.out.println("\n-------------------------------------------");
	}


}
