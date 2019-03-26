package work.leetcode.one_hundred;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/3/5 下午5:39
 */

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import work.common.Node;
import work.common.Tree;

import java.util.List;
import java.util.Map;
import java.util.Stack;

public class MainTest {
//	public static void main(String[] args) {
//
//	}

	//

	private static boolean huiWen(String s) {
		int i = 0;
		int j = s.length() - 1;

		while (i < j) {
			if (s.charAt(i) != s.charAt(j)) {
				return false;
			}
		}

		return true;
	}



	private static int gasStation(int[] gas, int[] cost) {
		int start = 0;

		while (start < gas.length) {

			int total = 0;
			int i = 0;
			for (i = start; i < start + gas.length; i++) {
				total += gas[i % gas.length] - cost[i % gas.length];

				if (total < 0) {
					start++;
					break;
				}
			}

			if (total >= 0 && i == start + gas.length) {
				return start;
			}
		}

		return -1;
	}

	//139

	private static boolean isSub(String s, List<String> sub) {
		int size = sub.size();

		boolean[] b = new boolean[size + 1];
		b[0] = true;

		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < i; j++) {
				if(b[j] && sub.contains(s.substring(j, i - j))) {
					b[i] = true;
					break;
				}
			}
		}

		return b[size];
	}

	private static void printList(List<String> s) {
		for (String l : s) {
			System.out.print(l + " ");
		}
	}

	private static void printArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}

		System.out.println();
	}

	private static void IsSub2(String s, List<String> sub, int n, List<String> res) {
		if (n >= s.length()) {
			printList(res);
		}


		for (String l : sub) {
			if(s.startsWith(l)) {
				sub.remove(l);
				res.add(l);
				IsSub2(s.substring(l.length()), sub, n + l.length(), res);

				sub.add(l);
				res.remove(l);
			}
		}
	}


//	public static void main(String[] args) {
//		String test = "good";
//		List<String> list = Lists.newArrayList("g", "go", "o", "od", "d");
//
//		IsSub2(test, list, 0, Lists.newArrayList());
//	}

	private static void postOrder(Tree tree) {
		if (tree == null) {
			return;
		}

		List<Tree> trees = Lists.newArrayList();

		Stack<Tree> treeStack = new Stack<>();

		treeStack.push(tree);
		while (!treeStack.isEmpty()) {
			Tree t = treeStack.pop();
			trees.add(0, t);
			if (t.left != null) {
				treeStack.push(t);
			}

			if (t.right != null) {
				treeStack.push(t);
			}
		}
	}


	private static void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	//pop
	private static void sortPop(int[] a, int start, int end) {
		for (int i = 0; i < a.length; i++) {

			for (int j = 0; j < a.length - i; j++) {
				if (a[i] < a[j]) {
					swap(a, i, j);
				}
			}
		}
	}

	//switch
	private static void sortSwitch(int[] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = i; j < a.length - 1; i++) {
				if (a[j] < a[j+1]) {
					swap(a, j, j + 1);
				}
			}
		}
	}

	private static void sortQuick(int[] a, int start, int end) {
		if (start < 0 || end >= a.length || start >= end) {
			return;
		}
		int flag = a[end];

		int i = start;
		int j = end - 1;

		while (i < j && i < a.length && j >= 0) {
			while (a[i] < flag && i < j) {
				i++;
			}

			while (a[j] > flag && j > i) {
				j--;
			}

			if (i < j) {
				swap(a, i, j);
			}
		}

		if (j == i) {
			swap(a, i, end);
		}
		sortQuick(a, start, j - 1);
		sortQuick(a, j + 1, end);
	}


	private static void sortMerge(int[] a, int start, int end) {
		if (start == end) {
			return;
		}

		sortMerge(a, start, (start + end) >> 1);
		sortMerge(a, (start + end) >> 1 + 1, end);
		merge(a, start, (start + end) >> 1, (start + end) >> 1 + 1, end);
	}

	private static void merge(int[] a, int s1, int e1, int s2, int e2) {
		//assume s1 is smallest;

		int flag = s1;
		int i = s1;
		int j = s2;
		while (i <= e1 || j <= e2) {
			if (i > e1) {
				a[flag++] = a[j++];
			}

			if (j > e2) {
				a[flag++] = a[i++];
			}

			if (a[i] < a[j]) {
				a[flag++] = a[i++];
			} else {
				a[flag++] = a[j++];
			}
		}
	}


	public static void main(String[] args) {
		int a[] = new int[] {4, 1, 5, 2 ,7, 6, 3};
		sortQuick(a, 0, 6);

		printArray(a);
	}

	//148
	public static void sortList(Node node) {
		if (node == null) {
			return;
		}

		Node start = node.next;
		Node head = node;
		while (start != null) {
			Node p = head;
			Node f = p;
			while (p.data < start.data) {
				f = p;
				p = p.next;
			}

			if (p == f) {
				Node t = start;
				start = start.next;
				t.next = head;
				head = t;
			} else {
				Node t = start;
				start = start.next;

			}
		}
	}

	//

	public void reverseString(String s) {
		if (s == null || s.isEmpty()) {
			return;
		}
	}

	//156
	//todo

	public static void longSubStringOf2(String s) {
		Map<Character, Integer> aux = Maps.newHashMap();

		int left = 0;
		int max = Integer.MIN_VALUE;

		for (int i = 0; i < s.length(); i++) {

			Integer tmp = aux.get(s.charAt(i));
			if (tmp == null) {
				aux.put(s.charAt(i), 1);

				while (aux.size() > 2) {
					int t =  aux.get(s.charAt(left));
					if (t == 1) {
						aux.remove(s.charAt(left));
						left++;
					} else {
						aux.put(s.charAt(left), t - 1);
					}

				}

				max = Math.max(max, i - left + 1);
			} else {
				tmp++;
				aux.put(s.charAt(i), tmp);
				max = Math.max(max, i - left + 1);
			}
		}
	}
}
