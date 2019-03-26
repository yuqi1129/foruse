package work.ch4;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/1/17 下午7:55
 */


import com.google.common.collect.Lists;
import org.apache.flink.shaded.curator.org.apache.curator.shaded.com.google.common.collect.Queues;
import work.common.Tree;

import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Chapter4 {
//	public static void main(String[] args) {
//		int[] a = new int[] {1, 2, 3, 4, 5};
//		int[] b = new int[] {4, 5, 3, 2, 1};
//		int[] c = new int[] {4, 3, 5, 2, 1};
//
//		System.out.println(isStackSequence(a, b));
//		System.out.println(isStackSequence(a, c));
//
//		translate(12258);
//
//
//		int[] ar = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
//
//		getUgly(ar, 10);
//
//		int[] d = ar;
//
//	}


	//
	public void swapTree(Tree tree) {
		if (tree == null) {
			return;
		}


		Tree left = tree.left;
		Tree right = tree.right;


		Tree tmp = tree.left;
		tree.left = tree.right;
		tree.right = tmp;

		swapTree(tree.left);
		swapTree(tree.right);
	}

	//Swap again

	public void swapTreeNoRecrusive(Tree tree) {
		Queue<Tree> queue = Queues.newLinkedBlockingQueue();
		while (tree != null) {

		}
	}


	//
	public static boolean isStackSequence(int[] a, int[] b) {
		if (a == null || b == null) {
			return false;
		}

		if (a.length != b.length) {
			return false;
		}

		Stack<Integer> stack = new Stack<>();
		int j = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] == b[j]) {
				//do nothing
				j++;
			} else {
				stack.push(a[i]);
			}
		}

		while (!stack.isEmpty()) {
			if (stack.pop() != b[j]) {
				return false;
			}
			j++;
		}

		return true;
	}


	public static void printPathOfN(Tree a, int N) {
		printPath(a, N, 0, Lists.newArrayList());
	}


	private static void printList(List<Tree> list) {
		if (list == null || list.isEmpty()) {
			//donothing
		} else {
			list.forEach(a -> System.out.println(a.data));
		}
	}

	public static void printPath(Tree a, int N, int curr, List<Tree> list) {
		if (a == null) {
			return;
		}

		curr += a.data;

		if (curr == N) {
			printList(list);
		}

		//copy list

		if (a.left != null) {
			list.add(a);
			printPath(a.left, N, curr, list);
			list.remove(list.size() - 1);
		}

		if (a.right != null) {
			list.add(a);
			printPath(a.right, N, curr, list);
			list.remove(list.size() - 1);
		}
	}



	//

	private void allSort(String a) {
		int len = a.length();


	}


	private void mySort(int i, String a) {
		if (i == a.length() - 1) {
			//chuchu
		}

		for (int k = i; k < a.length(); k++) {
			//swamp()
			mySort(k, a);
			//swamp
		}
	}

	private static void translate(int b) {
		translateString(String.valueOf(b));
		System.out.println(a);
	}

	public static int a = 0;
	private static void translateString(String s) {
		if (s.length() == 0) {
			return;
		}

		a++;
		translateString(s.substring(1));

		if (s.length() >= 2) {
			int tmp = Integer.valueOf(s.substring(0, 2));
			if (tmp < 26) {
				a++;
				translateString(s.substring(2));
			}
		}
	}


	private static void quickSort(int[] a, int start, int end) {


		//checkaugement
		int flag = a[end];

		int i = start;
		int j = end - 1;

		while (i < j) {
			while (i < j && a[i] < flag) {
				i++;
			}

			while(j > i && a[j] > flag) {
				j--;
			}

			if (i < j) {
				int tmp = a[i];
				a[i] = a[j];
				a[j] = tmp;
			}

			i++;
			j--;
		}

		if (i > j) {
			int tmp = a[i];
			a[i] = flag;
			a[end - 1] = tmp;

			//
			quickSort(a, start, i - 1);
			quickSort(a, i + 1, end);
		}
	}

	//psvm

	private static void getUgly(int[] a, int n) {

		int num2 = 1;
		int num3 = 1;
		int num5 = 1;


		a[0] = 1;
		for (int i = 1; i < n; i++) {
			int min = threeMin(num2 * 2, num3 * 3, num5 * 5);

			a[i] = min;

			while (num2 * 2 <= min) {
				num2++;
			}

			while (num3 * 3 <= min) {
				num3++;
			}

			while (num5 * 5 <= min) {
				num5++;
			}
		}
	}

	private static int threeMin(int a, int b, int c) {
		return Math.min(Math.min(a, b), c);
	}

	//do
	//[1, 2, 3, 3, 3, 3, 4, 5]

	private static int getTimesofN(int[] a, int n) {
		return highOfN(a, n);
	}


	private static int highOfN(int[] a, int n) {
		int i = 0;
		int j = a.length - 1;

		int high = 0;
		int low = 0;
		//get high
		while (i < j) {

			if (j == i + 1) {
				if (a[j] == n) {
					high = j;
					break;
				}

				if (a[i] == n) {
					high = i;
					break;
				}
			}

			int mid = (i + j) >> 1;

			if (a[mid] > n) {
				j = mid - 1;
			} else if (a[mid] == n) {
				i = mid;
			} else {
				i = mid + 1;
			}
		}

		//get low
		i = 0;
		j = a.length - 1;
		while (i < j) {
			if (j == i + 1) {
				if (a[i] == n) {
					low = i;
					break;
				}

				if (a[j] == n) {
					low = j;
					break;
				}
			}


			int mid = (i + j) >> 1;

			if (a[mid] > n) {
				j = mid - 1;
			} else if (a[mid] == n) {
				j = mid;
			} else {
				i = mid + 1;
			}
		}


		return high - low + 1;
	}

	public static void main(String[] args) {
		int[] a = new int[] {1, 2, 4, 4, 5, 6, 7, 9, 10};
		System.out.println(highOfN(a, 4));
	}
}
