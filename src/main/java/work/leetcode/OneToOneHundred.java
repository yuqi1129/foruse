package work.leetcode;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/1/21 下午3:44
 */

import com.google.common.collect.Maps;
import org.apache.flink.shaded.guava18.com.google.common.collect.Queues;
import work.common.Node;
import work.common.Tree;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

public class OneToOneHundred {
//	public static void main(String[] args) {
//		int[] a = new int[] {0, 4, 4,6,6, 7, 10, 10, 10, 11, 23, 40};
//		reduceDuplicate(a);
//
//		printIntArray(a);
//
//
//		int[] b = new int[] {7, 8, 9, 10, 1, 2,  4, 5};
//		System.out.println(findMinValue(b));
//	}


	public static void printIntArray(int[] a) {
		printIntArray(a, a.length);
	}

	public static void printIntArray(int[] a, int n) {
		for (int i = 0; i < n; i++) {
			System.out.print(a[i] + " ");
		}

		System.out.println("");
	}


	public static int findMinValue(int[] a) {
		int i = 0;
		int j = a.length -1;

		while (i < j) {
			int mid = (i + j) >> 1;

			int left = 0;
			if (mid - 1 < 0) {
				left = Integer.MIN_VALUE;
			} else {
				left = a[mid -1];
			}


			int right = 0;
			if (mid + 1 >= a.length) {
				right = Integer.MAX_VALUE;
			} else {
				right = a[mid + 1];
			}


			//

			if (a[mid] > left && a[mid] < right) {
				if (a[mid] > a[i]) {
					i = mid + 1;
				} else {
					j = mid - 1;
				}
			} else {
				if (a[mid] > left && a[mid] > right) {
					return a[mid+1];
				}

				if (a[mid] < left && a[mid] < right) {
					return a[mid];
				}
			}
		}

		return -1;
	}

//	public static int maxValidate(String s) {
//		int leftNumber = 0;
//		int leftIndex = 0;
//		int rightNumber = 0;
//		int rightIndex = 0;
//
//		int maxValidate = 0;
//		for (int i = 0; i < s.length(); i++) {
//			char c = s.charAt(i);
//			if (c == '(') {
//				leftNumber++;
//				leftIndex = i;
//			} else {
//				rightNumber++;
//				maxValidate += 2;
//				rightIndex = i;
//			}
//
//
//
//
//		}
//	}


	//26
	public static void reduceDuplicate(int[] a) {
		int i = 0;
		int j = 1;

		while (j < a.length) {
			if (a[j] != a[i]) {
				a[++i] = a[j];
			}

			j++;
		}
	}

	public static void number(int[] a, int[] result, int n, int N, int curr, int start) {
		if (curr > N) {
			return;
		}

		if (curr == N) {
			printIntArray(result, n);
			return;
		}


		for (int i = start; i < a.length; i++) {
			result[n] = a[i];
			number(a, result, n+1, N, curr + a[i], i + 1);
		}
	}

//	public static void main(String[] args) {
//		int[] a = new int[] {2,3,5,7};
//		int[] b = new int[a.length];
//
//		number(a, b, 0, 7, 0, 0);
//	}


	public static char[] multiString(String a, String b) {
		int lenA = a.length();
		int lenB = b.length();

		char[] result = new char[lenA + lenB];

		for (int i = 0; i < result.length; i++) {
			result[i] = '\0';
		}

		int k = lenA + lenB;

		for (int i = a.length() -1; i >= 0; i--) {
			int tmp = a.charAt(i) - '0';

			k--;
			int m = k;
			int aux = 0;
			for (int j = b.length() - 1; j >=0; j--) {
				int number = tmp * (b.charAt(j) - '0') + aux + result[k];

				int res = number % 10;
				aux = res / 10;
				result[m--] = (char) ('0' + res);
			}

			if (aux > 0) {
				result[m--] = (char) aux;
			}
		}

		return result;
	}
//

//	public static void main(String[] args) {
//		String a = "123451";
//		String b = "23";
//
//		char[] res = multiString(a, b);
//
//		int i = 0;
//		while (res[i++] == '\n' && i < res.length);
//		while (i < res.length) {
//			System.out.print(res[i]);
//			i++;
//		}
//	}

//	public static void main(String[] args) {
//		int[] a = new int[] {1, 2, 3, 4};
//
//		permutation(a, 0);
//	}

	public static void permutation(int[] a, int n) {
		if (n == a.length) {
			printIntArray(a);
			return;
		}

		for (int i = n; i < a.length; i++) {
			int tmp = a[n];
			a[n] = a[i];
			a[i] = tmp;

			permutation(a, n + 1);
			tmp = a[n];
			a[n] = a[i];
			a[i] = tmp;

		}
	}


	private static void nQueue(int[][] a, int n, int[] aux, int len) {
		if (n == len) {
			print2Darray(a, len);
			return;
		}

		for (int i = 0; i < len; i++) {
			if (isValid(aux, n, i)) {
				a[n][i] = 0;
				aux[n] = i;
				nQueue(a, n + 1, aux, len);
				a[n][i] = 1;
				aux[n] = 100000;
			}
		}
	}
	private static boolean isValid(int[] a, int len, int n) {
		for (int i = 0; i < len; i++) {
			if (n == i) {
				return false;
			}

			double d1 = (a[i] + 1) / (i + 1);
			double d2 = (n + 1) / (len + 1);

			if (d1 == d2) {
				return false;
			}
		}

		return true;
	}

	private static void print2Darray(int[][] a, int len) {
		for (int i = 0; i < len; i++) {
			for (int j = 0; j < len; j++) {
				System.out.print(a[i][j] + " ");
			}

			System.out.println("");
		}

	}

//	public static void main(String[] args) {
//		int[][] a = new int[][] {
//			{1, 1, 1, 1, 1, 1},
//			{1, 1, 1, 1, 1, 1},
//			{1, 1, 1, 1, 1, 1},
//			{1, 1, 1, 1, 1, 1},
//			{1, 1, 1, 1, 1, 1},
//			{1, 1, 1, 1, 1, 1}
//		};
//
//		int[] aux = new int[] {
//				100000,100000,100000,100000,100000,100000
//		};
//
//		nQueue(a, 0, aux, 6);
//	}


	private static void maxSum(int[] a , int n) {
		int max = Integer.MIN_VALUE;

		int sum = 0;

		for (int i = 0; i < a.length; i++) {
			int res = sum + a[i];

			int tmpmax = Math.max(res, a[i]);
			if (tmpmax > sum) {
				sum = tmpmax;
			}
		}
	}


	private static void combine(int[] a, int k, int number, int[] res) {

		if (number == 3) {
			printIntArray(res);
			return;
		}

		for (int i = k; i < a.length; i++) {
			//
			res[number] = a[i];
			combine(a, i+1, number + 1, res);
		}
	}

//	public static void main(String[] args) {
//		int[] a = new int[] {1,2,3,4,5,6};
//		int[] b = new int[3];
//
//		//combine(a, 0, 0, b);
//
//	}


	public static void allSet(int[] a, int n, boolean[] b) {
		if (n == a.length) {
			for (int i = 0; i < b.length; i++) {
				if (b[i]) {
					System.out.print(a[i] + " ");
				}
			}
			System.out.println("");
		}

		b[n] = true;
		allSet(a, n + 1, b);
		b[n] = false;
		allSet(a, n + 1, b);
	}



	public void partition(Tree tree, int n) {

	}

	private static void decode(String number, int i, String tmp) {
		if (i >= number.length()) {
			System.out.println(tmp);
			return;
		}

		String s = number.substring(i,i+1);

		String tmp1 = tmp + ((char)(s.charAt(0) - '0' + 96));
		decode(number, i + 1, tmp1);

		if (i + 2 < number.length()) {
			s = number.substring(i, i + 2);
			int a = Integer.valueOf(s);
			if (a <= 26) {
				tmp1 = "" + ((char)(a + 96));
				decode(number, i + 2, tmp1);
			}
		}
	}


//	public static void main(String[] args) {
//		String s = "91432194712";
//		decode(s, 0, "");
//	}


	private static void allIpAddr(String s, int count, int start, int[] aux) {

		if (count == 4 && start == s.length()) {
			printIntArray(aux);
			return;
		}

		if (count >= 4 || start >= s.length()) {
			return;
		}

		for (int i = start + 1; i <= start + 3; i++) {
			if (i >= s.length()) {
				return;
			}

			if (isValid(s.substring(start, i))) {
				aux[count] = Integer.valueOf(s.substring(start, i));
				allIpAddr(s, count + 1, i, aux);
			}
		}
	}

	private static boolean isValid(String s) {
		if (s.startsWith("0")) {
			return false;
		}

		return Integer.valueOf(s) <= 255;
	}

//	public static void main(String[] args) {
//		String s = "25525511135";
//		allIpAddr(s, 0, 0, new int[4]);
//	}


	private void reverseNode(Node node, int m, int n) {
		//check the number


		//     |          |
		// --> 4--> 5 --> 6 --> 7
		// --> 5--> 6 --> 4 --->7
		// --> 6 --> 5 --> 4 -->7
		int i = 1;

		Node parent = node;
		Node start = node;
		while (i < m) {
			parent = start;
			start = start.next;
			i++;
		}

		Node end = start;
		i = 1;
		while (i < n - m + 1) {
			end = end.next;
		}

		Node endNext = end.next;


		for (i = m; i < n; i++) {
			end.next = start;
			start = start.next;
		}

		parent.next = start;
		end.next = endNext;

	}


	private static void leftView(Tree tree) {
		if (tree == null) {
			return;
		}

		Queue<Tree> queue = Queues.newArrayDeque();
		((ArrayDeque<Tree>) queue).addLast(tree);

		while (!queue.isEmpty()) {
			Tree t = ((ArrayDeque<Tree>) queue).getLast();
			System.out.println(t.data);
			if (t.right != null) {
				((ArrayDeque<Tree>) queue).addLast(t.right);
			} else {
				((ArrayDeque<Tree>) queue).addLast(t.left);
			}
		}
	}

	public static void main(String[] args) {
		String s = "this is a test";

		System.out.println(longestUniqueString(s));

		noRecursive(new int[] {1, 2, 3, 4, 5});
	}

	public static int longestUniqueString(String s) {
		//do

		Map<Integer, Integer> map = Maps.newHashMap();

		int start = 0;
		int end;
		int res = 0;
		for (int i = 0; i < s.length(); i++) {
			int tmp = s.charAt(i);
			if (map.get(tmp) == null) {
				map.put(tmp, i);
			} else {
				int pos = map.get(tmp);
				if (pos >= start) {
					if (i - pos > res) {
						start = pos + 1;
						res = i - pos;
						end = i;
					}
				}
			}
		}

		return res;
	}

	//permution not use recursive

	private static void noRecursive(int[] a) {
		Arrays.sort(a);

		do {
			printIntArray(a);
		} while (!Objects.equals(myper(a), a));

	}

	private static int[] myper(int[] a) {
		int i = a.length -1;
		while (i > 0) {
			if (a[i] > a[i-1]) {
				break;
			}
			i--;
		}

		if (i > 0) {

			int tmp = a[i];
			a[i] = a[i - 1];
			a[i - 1] = tmp;

			reverse(a, i + 1, a.length - 1);
		}
		return a;
	}

	private static void reverse(int[] a, int start, int end) {
		int i = start;
		int j = end;

		while (i < j) {
			int tmp = a[i];
			a[i] = a[j];
			a[j] = tmp;
			i++;
			j--;
		}
	}


	private static int longCommonSeq(String a, String b) {

		return 0;
	}


	private static int maxValidateBrace(String s) {
		int start = 0;
		int res = 0;

		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '{') {
				stack.push(i);
			} else {
				if (stack.isEmpty()) {
					start = i + 1;
				} else {
					int tmp = stack.pop();
					if (stack.isEmpty()) {
						if (i - tmp + 1 > res) {
							res = i - start + 1;
						}
					} else {
						if (i - stack.firstElement() > res) {
							res = i - stack.firstElement();
						}
					}
				}
			}
		}


		return res;
	}


	private static void minWindowString(String s, String t) {
		int[] a = new int[128];

		int[] b = new int[128];

		for (int i = 0; i < t.length(); i++) {
			b[t.charAt(i)]++;
		}

		int count = 0;
		int left = 0;
		int minWindow = Integer.MAX_VALUE;

		for (int i = 0; i < s.length(); i++) {
			if (--b[s.charAt(i)] >= 0) { //1
				count++;
			}

			while (count == t.length()) {
				if (i - left + 1 < minWindow) {
					minWindow = i - left + 1;
				}

				//如果 s.charAt(left) < 0, 则表明在1处, s的left位置出现一个多余的字符， 相对于t
				//如s ='aab', t = 'ab'
				//                 -1,0
				if (++b[s.charAt(left)] > 0) {
					count--;
				}

				left++;
			}
		}
	}

	private static int sumOfTree(Tree tree, int start) {
		if (tree == null) {
			return 0;
		}

		int tmp = start + tree.data * 10;

		return sumOfTree(tree.left, tmp) + sumOfTree(tree.right, tmp);
	}

}
