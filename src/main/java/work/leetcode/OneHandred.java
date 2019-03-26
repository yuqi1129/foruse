package work.leetcode;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/2/17 下午1:11
 */

import com.google.common.collect.Maps;
import work.common.Node;
import work.common.Tree;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class OneHandred {
	//do something else
//	public static void main(String[] args) {
//	}

	Object o = new Object();
	private static volatile boolean b = false;


	private static boolean isCircle(Node root) {
		Node slow = root;
		Node fast = root;

		while (fast != null && fast.next != null) {
			fast = fast.next.next;
			slow = slow.next;

			if (fast == slow) {
				return true;
			}
		}


		return false;
	}

	private static void makeList(Node root) {

	}


	private static void PreOrder(Tree tree) {
		if (tree == null) {
			return;
		}


		Stack<Tree> s = new Stack<>();

		s.push(tree);
		while (!s.isEmpty()) {
			Tree t = s.pop();
			System.out.println(t.data);

			if (t.left != null) {
				s.push(t.left);
			}

			if (t.right != null) {
				s.push(t.right);
			}
		}
	}


	private static void MidOrder(Tree tree) {
		if (tree == null) {
			return;
		}

		Stack<Tree> s = new Stack<>();

		s.push(tree);
		Tree p = null;
		while (!s.isEmpty() || p != null) {
			if (p == null) {
				p = s.pop();
			}

			while (p != null) {
				p = p.left;
				s.push(p);
			}

			Tree tmp = s.pop();
			System.out.println(tmp.data);
			p = tmp.right;
		}
	}


	private static void postOrder(Tree tree) {
		if (tree == null) {
			return;
		}

		Stack<Tree> s = new Stack<>();

		s.push(tree);
		Tree p = null;
		Tree parent = null;

		while (!s.isEmpty() || p != null) {
			if (p == null) {
				p = s.pop();
			}

			while (p != null) {
				parent = p;
				p = p.left;
				s.push(p);
			}

			//now p = null, then we try to handle parent right side;
			p = parent.right;
			if (p == null) {
				//print p
			} else {

			}
		}
	}


	//Binary Tree Upside Down
	private static Tree UpsideDownTree(Tree tree) {
		mirrorTree(tree);

		return null;
	}

	private static void makeDownUpside(Tree tree) {
		if (tree == null) {
			return;
		}

		if (tree.left == null || tree.right == null) {
			return;
		}

		Tree left = tree.left;
		Tree right = tree.right;

		if (right != null && right.left != null && right.right != null) {
			makeDownUpside(right);
		}

		//Tree tmp = tree;
	}

	private static void mirrorTree(Tree tree) {
		if (tree == null) {
			return;
		}

		Tree tmp = tree.left;
		tree.left = tree.right;
		tree.right = tmp;


		mirrorTree(tree.left);
		mirrorTree(tree.right);

	}


	private static int nonRepeatedString(String s) {
		Map<Character, Integer> map = Maps.newHashMap();

		int start = 0;
		int i;
		int max = Integer.MIN_VALUE;

		for (i = 0; i < s.length(); i++) {
			Character c = s.charAt(i);

			Integer tmp = map.get(c);
			if (tmp == null) {
				map.put(c, i);
			} else {
				map.put(c, i);
				max = Math.max(max, i - start);
				start = tmp + 1 > start ? tmp + 1 : start;
			}
		}

		return Math.max(max, i - start);
	}

//	public static void main(String[] args) {
//		String s = "this is a test";
//		System.out.println(nonRepeatedString(s));
//	}

	//undo

	public static void zMethod(String s, int n) {
		int size = s.length();

		char[][] chars = new char[n][size];

		int flag = 0;
		int j = 0;
		while (j < s.length()) {
			if (flag % 2 == 0) {
				int k = 0;
				while (k < n) {
					chars[k][flag] = s.charAt(j++);
					k++;
				}
				flag++;
			} else {
				int t = 1;
				while (n - t >= 0) {
					chars[flag + t][n - t] = s.charAt(j++);
					t++;
				}

				flag++;
			}
		}

	}

	public static long reverseInt(int i) {
		long l = 0;
		boolean b = i > 0 ? true : false;

		while (i != 0) {
			l = l * 10 + i % 10;
			i /= 10;
		}

		if (!b) {
			l = - l;
		}
		return l;
	}

	public static void getNumber(String s) {
		String[] dic = new String[] {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		getPhoneNumber(s, 0, new StringBuilder(), dic);
	}

	public static void getPhoneNumber(String s, int start, StringBuilder b, String[] dic) {
		if (start >= s.length()) {
			System.out.println(b.toString());
			return;
		}

		char ch = s.charAt(start);
		int n = ch - '0';

		String tmp = dic[n];

		if (tmp.length() == 0) {
			getPhoneNumber(s, start + 1, b, dic);
		} else {

			for (int i = 0; i < tmp.length(); i++) {
				int len = b.length();
				b.append(tmp.charAt(i));
				getPhoneNumber(s, start + 1, b, dic);
				b.delete(len, b.length());
			}
		}
	}

//	public static void main(String[] args) {
//		String s = "2341";
//		getNumber(s);
//	}

	public static void getAllBrace(int left, int right, int total, char[] res) {
		if (right > left) {
			return;
		}

		if (total >= res.length) {
			//print res
			return;
		}

		if (left < res.length / 2) {
			res[total + 1] = '(';
			getAllBrace(left + 1, right,total + 1, res);
		}

		if (right < res.length / 2) {
			res[total + 1] = ')';
			getAllBrace(left, right + 1, total + 1, res);
		}
	}

	public static Node mergeAllNode(List<Node> nodeList) {
		Node[] nodes = new Node[nodeList.size()];

		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = nodeList.get(i);
		}

		Node tail = null;
		while (!isOver(nodes)) {
			Node tmp = minOfAll(nodes);
			if (tail == null) {
				tail = tmp;
			} else {
				tail.next = tmp;
			}
		}

		return tail;
	}

	public static boolean isOver(Node[] nodes) {
		return Arrays.stream(nodes).allMatch(a -> a == null);
	}

	public static Node minOfAll(Node[] nodes) {
		//assume
		Node node = nodes[0];
		for (int i = 1; i < nodes.length; i++) {
			if (node == null) {
				node = nodes[i];
				continue;
			}

			if (nodes[i] != null && nodes[i].data < node.data) {
				node = nodes[i];
				nodes[i] = nodes[i].next;
			}
		}

		return node;
	}

	public void test() {
		synchronized (o) {
			int i = 0;
		}
	}

	public static void main(String[] args) {
		Object o = new Object();

		Thread t1 = new Thread(() -> {
			synchronized (o) {
				while (!b) {
					try {
						o.wait();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				//dosomething
			}
		});


		Thread t2 = new Thread(() -> {
			try {
				Thread.currentThread().sleep(5000);
			} catch (Exception e) {
				e.printStackTrace();
			}

			//先修改状态，然后notify
			synchronized (o) {
				b = true;
				o.notify();
			}
		});

		t1.start();
		t2.start();
	}
}
