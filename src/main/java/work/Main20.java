package work;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Queues;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * Author yuqi
 * Time 1/2/18 23:44
 **/
public class Main20 {

//    public static void main(String[] args) {

    ///// 20
//        int[][] ss = new int[][] {
//                {1,2,3,4},
//                {5,6,7,8},
//                {9,10,11,12},
//                {13,14,15,16}
//        };
//
//        printArray(ss, 4, 4);


    /////22
//        int[] a = new int[] {1, 2, 3, 4, 5};
//        int[] b = new int[] {4, 5, 3, 2, 1};
//        int[] c = new int[] {4, 3, 5, 1, 2};
//
//        System.out.println(isStackSequence(a, b));
//        System.out.println(isStackSequence(a, c));


    ////24

//        int[] a = new int[] {
//                2, 9, 5, 16, 17, 15, 19, 18, 12
//        };
//
//        int[] b = new int[] {
//                2, 9, 5, 16, 11, 15, 19, 18, 12
//        };
//
//        System.out.println(isPost(a, 0, a.length -1));
//        System.out.println(isPost(b, 0, b.length -1));

    //for 28
//        List<Character> characters = Lists.newArrayList('a', 'c', 'a', 'f');
//        printAllPaiLei(characters, new StringBuilder(), characters.size());


    //for 29

//        int[] a = new int[] {
//                3,4,2,3,3,3,5,3
//        };
//        System.out.println(findHalfValue(a));


    // for 31
//        int[] a = new int[] {
//                -6,-3,-2,-7,-15,-1,-2,-2
//        };
//        System.out.println(getMaxSumInArray2(a));
//    }


    // for 20
    ///////////////////////////////////////////


    public static void printArray(int[][] a, int row, int column) {
        int total = row * column;
        int i = 0;
        int j = 0;
        while (total > 0) {

            while (j < column && a[i][j] != -1) {
                System.out.println(a[i][j]);
                a[i][j] = -1;
                total--;
                j++;
            }
            j--;
            i++;

            while (i < row && a[i][j] != -1) {
                System.out.println(a[i][j]);
                a[i][j] = -1;
                i++;
                total--;
            }
            i--;
            j--;

            while (j >= 0 && a[i][j] != -1) {
                System.out.println(a[i][j]);
                a[i][j] = -1;
                j--;
                total--;
            }
            j++;
            i--;

            while (i >= 0 && a[i][j] != -1) {
                System.out.println(a[i][j]);
                a[i][j] = -1;
                i--;
                total--;
            }
            i++;
            j++;
        }
    }


    /// for 22;

    public static boolean isStackSequence(int[] a, int b[]) {
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i = 0; i < a.length; i++) {
            stack.push(a[i]);
            if (stack.peek() == b[j]) {
                stack.pop();
                j++;
            }
        }

        for (int k = j; k < b.length; k++) {
            if (stack.pop() != b[k]) {
                return false;
            }
        }

        return true;
    }


    // for 23
    public static class TreeNode {
        int data;
        TreeNode left;
        TreeNode right;
    }

    public static void printNodeList(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        addAndPrint(root, queue);


        while (queue.size() != 0) {
            TreeNode node = queue.poll();
            addAndPrint(node, queue);
        }
    }

    public static void addAndPrint(TreeNode root, Queue<TreeNode> queue) {
        System.out.println(root.data);
        if (root.left != null) {
            queue.add(root.left);
        }
        if (root.right != null) {
            queue.add(root.right);
        }
    }

    //for 24

    public static boolean isPost(int[] a, int start, int end) {

        if (start >= end) {
            return true;
        }

        int root = a[end];
        int i = start;
        while (a[i] < root) {
            i++;
        }

        for (int j = i; j < end; j++) {
            if (a[j] < root) {
                return false;
            }
        }

        return isPost(a, start, i - 1) && isPost(a, i, end - 1);
    }

    // for 25
    public static void printPathofSum(TreeNode root, int number) {
        LinkedList<Integer> list = Lists.newLinkedList();
        int current = 0;
        findSum(root, number, current, list);

    }

    private static void findSum(TreeNode root, int number, int current, LinkedList<Integer> list) {
        if (number == current) {
            //outList;
            return;
        }

        if (number > current) {
            return;
        }

        list.addLast(root.data);
        current += root.data;

        //tmp, 一个副本
        findSum(root.left, number, current, Lists.newLinkedList(list));
        findSum(root, number, current, Lists.newLinkedList(list));
    }


    // for 27

    //todo

    /**
     * public static Node changeTree2TwoDirectionList(Node root) {
     * <p>
     * if (root == null) {
     * return null;
     * }
     * <p>
     * Node leftReutrn = changeTree2TwoDirectionList(root.left);
     * root.left = leftReutrn;
     * <p>
     * Node rightReturn changeTree2TwoDirectionList(root.right);
     * <p>
     * Node left = root.left;
     * Node right = root.right;
     * if (left== null) {
     * return;
     * } else {
     * left.right = root;
     * }
     * <p>
     * if (right == null) {
     * return;
     * } else {
     * right.left = root;
     * }
     * }
     */


    // for 28
    public static void printAllPaiLei(List<Character> s, StringBuilder b, int n) {
        if (b.toString().length() == n) {
            System.out.println(b.toString());
            return;
        }

        for (int i = 0; i < s.size(); i++) {
            b.append(s.get(i));
            Character tmp = s.get(i);
            s.remove(i);
            printAllPaiLei(s, b, n);

            b.deleteCharAt(b.length() - 1);
            s.add(i, tmp);
        }
    }


    // for 29

    public static int findHalfValue(int[] a) {
        if (a == null || a.length == 0) {
            throw new RuntimeException("e");
        }

        if (a.length == 1) {
            return a[0];
        }

        int start = -1;
        int count = 0;
        for (int i = 1; i < a.length; i++) {

            if (count == 0) {
                start = a[i];
                count++;
                continue;
            }

            if (a[i] == start) {
                count++;
            } else {
                count--;
            }
        }

        return start;
    }

    //todo 30


    // for 31

    public static int getMaxSumInArray(int[] a) {
        int max = Integer.MIN_VALUE;

        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                sum += a[j];
                if (sum > max) {
                    max = sum;
                }
            }

            sum = 0;
        }
        return max;
    }

    public static int getMaxSumInArray2(int[] a) {
        int max = Integer.MIN_VALUE;

        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
            if (sum > max) {
                max = sum;
            }

            if (sum < 0) {
                sum = 0;
            }
        }

        return max;
    }

    //for 32
    // skip

    //for 33
//    private static void Metho30_1(Integer[] a) {
//        Arrays.sort(a, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1.toString().compareTo(o2.toString());
//            }
//        });
//
//
//        Arrays.sort(a, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return StringUtils.reverse(o1.toString()).compareTo(StringUtils.reverse(o2.toString()));
//            }
//        });
//
//        for (int i = 0; i < a.length; i++) {
//            System.out.print(a[i] + " ");
//        }
//    }
//
//    public static void main(String[] args) {
//        Metho30_1(new Integer[] {
//                3, 32, 321, 3241, 3142, 3421
//        });
//    }


    //for 34
//    private static int theNthUglyNumber(int n) {
//        List<Integer> ugly = Lists.newArrayList();
//        ugly.add(1);
//        int index = 0;
//        int index2 = 0;
//        int index3 = 0;
//        int index5 = 0;
//
//
//        while(ugly.get(index) < n) {
//            int min = Math.min(Math.min(ugly.get(index2) * 2, ugly.get(index3) * 3), ugly.get(index5) * 5);
//
//            if (min == ugly.get(index2) * 2) {
//                index2++;
//            }
//
//            if (min == ugly.get(index3) * 3) {
//                index3++;
//            }
//
//            if (min == ugly.get(index5) * 5) {
//                index5++;
//            }
//
//            ugly.add(++index, min);
//        }
//
//        return ugly.get(index-1);
//    }
//
//    public static void main(String[] args) {
//        System.out.println(theNthUglyNumber(2000));
//    }


    // for 35
//    private static char theOnlyOnceInString(String s) {
//        int[] array = new int[128];
//        for (int i = 0; i < 128; i++) {
//            array[i] = 0;
//        }
//
//        for (int i = 0; i < s.length(); i++) {
//            char character = s.charAt(i);
//            int c = (int) character;
//            array[c]++;
//        }
//
//        for (int i = 0; i < 128; i++) {
//            if (array[i] == 1) {
//                return (char) i;
//            }
//        }
//
//        return '0';
//    }
//
//    public static void main(String[] args) {
//        String s = "HelloloeHioaa";
//        System.out.println(theOnlyOnceInString(s));
//    }

    // 36, 37 skip

    //38

//    private static int getTimesOfNInSortArray(int[] a, int number) {
//        int i = 0;
//        int j = a.length - 1;
//
//        while (i < j) {
//            int mid = (i + j) >> 1;
//            if (a[mid] < number) {
//                i = mid + 1;
//            } else if (a[mid] > number) {
//                j = mid - 1;
//            } else {
//                j = mid;
//            }
//        }
//
//        int start = (a[i] == number) ? i : j;
//
//        i = 0;
//        j = a.length - 1;
//
//        while(i < j) {
//            int mid = (i + j ) >> 1;
//            if (a[mid] < number) {
//                i = mid + 1;
//            } else if (a[mid] > number) {
//                j = mid - 1;
//            } else if (a[mid] == number) {
//                i = mid;
//            }
//        }
//
//        int end = a[j] == number ? j : i;
//
//        return end - start + 1;
//    }
//
//    public static void main(String[] args) {
//        int[] array = new int[] {
//                1, 4, 5, 6, 7, 8, 8, 9, 9, 9, 11, 23, 40
//        };
//
//        System.out.println(getTimesOfNInSortArray(array, 9));
//    }


    private static void Main40_1(int[] a) {
        int res = 0;
        for (int i = 0; i < a.length; i++) {
            res = res ^ a[i];
        }

        int number = 1;
        while (true) {
            if ((res & 0x01) == 1) {
                break;
            }
            number = number << 1;
            res = res >> 1;
        }

        int res1 = 0;
        int res2 = 0;
        for (int i = 0; i < a.length; i++) {
            if ((a[i] & number) == number) {
                res1 = res1 ^ a[i];
            } else {
                res2 = res2 ^ a[i];
            }
        }

        System.out.println(res1);
        System.out.println(res2);
    }

//    public static void main(String[] args) {
//        int[] array = new int[] {
//                2,4,3,6,3,2,5,5
//        };
//
//        Main40_1(array);
//    }

    public static void Main41_1(int number) {
        //wo should know that start should no greater than number;
        for (int i = 1; i <= number; i++) {
            for (int j = 1; j <= number; j++) {
                int tmp = Main41_2(i, j);
                if (tmp == number) {
                    Main41_3(i, j);
                    break;
                } else if (tmp > number) {
                    break;
                }
            }
        }

    }

    private static int Main41_2(int start, int n) {
        return ((start + (n - 1) + start) * n) >> 1;
    }

    private static void Main41_3(int start, int n) {
        for (int i = start; i < start + n; i++) {
            System.out.print(i + ", ");
        }
        System.out.println("");
    }

//    public static void main(String[] args) {
//        Main41_1(100);
//    }


    public static class Node {
        int data;
        Node next;
    }


    private static void Main57_1(Node node) {
        Node tmp = node;
        if (tmp == null) {
            return;
        }

        int data = tmp.data;

        while (tmp.next != null) {
            Node t = tmp;
            while (tmp.next.data != data) {
                tmp = tmp.next;
            }

            t.next = tmp;
            data = tmp.data;
        }
    }

    //for 58

    public static class SpecialTreeNode {
        int data;
        SpecialTreeNode left;
        SpecialTreeNode right;
        SpecialTreeNode parent;
    }

    public static SpecialTreeNode findNext(SpecialTreeNode root, SpecialTreeNode node) {
        // assume normal case
        if (node.right != null) {
            SpecialTreeNode specialTreeNode = node.right;
            while (specialTreeNode.left != null) {
                specialTreeNode = specialTreeNode.left;
            }

            return specialTreeNode;
        } else {
            SpecialTreeNode specialTreeNode = node.parent;
            SpecialTreeNode current = node;
            while (specialTreeNode != null && specialTreeNode.right == current) {
                current = specialTreeNode;
                specialTreeNode = specialTreeNode.parent;
            }
            return specialTreeNode;
        }
    }

    //for 60

    public static void CenCi(TreeNode node) {
        if (node == null) {
            return;
        }
        Queue<Pair<TreeNode, Integer>> queue = Queues.newArrayDeque();
        queue.add(Pair.of(node, 1));
        List<Pair<TreeNode, Integer>> treeNodes = Lists.newArrayList();

        while (!queue.isEmpty()) {
            Pair<TreeNode, Integer> pair = queue.poll();
            treeNodes.add(pair);

            TreeNode treeNode = pair.getKey();
            if (treeNode.left != null) {
                queue.add(Pair.of(treeNode.left, pair.getValue() + 1));
            }

            if (treeNode.right != null) {
                queue.add(Pair.of(treeNode.right, pair.getValue() + 1));
            }
        }

        System.out.print(treeNodes.get(0).getKey().data);
        int cengCi = 1;

        for (int i = 1; i < treeNodes.size(); i++) {
            int newCen = treeNodes.get(i).getValue();
            if (newCen != cengCi) {
                System.out.println("");
                cengCi = newCen;
            }
            System.out.print(treeNodes.get(i).getKey().data);
        }
    }

    //61 is the same as 60

    //skip 62

    //63

    private static void Main63_1(TreeNode node, int k) {
        int[] a = new int[]{0};
        Main63_2(node, a, k);
    }

    private static void Main63_2(TreeNode node, int[] a, int k) {
        if (node == null) {
            return;
        }
        if (a[0] == k) {
            System.out.println(node.data);
            return;
        }
        if (node.left != null) {
            Main63_2(node.left, a, k);
        }
        a[0]++;
        if (a[0] == k) {
            System.out.println(node.data);
            return;
        }

        if (node.right != null) {
            Main63_2(node.right, a, k);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode();
        root.data = 5;

        TreeNode one = new TreeNode();
        one.data = 3;
        root.left = one;

        TreeNode tow = new TreeNode();
        tow.data = 4;
        one.right = tow;

        TreeNode three = new TreeNode();
        three.data = 1;
        one.left = three;

        TreeNode four = new TreeNode();
        four.data = 8;
        root.right = four;

        //Main63_1(root, 3);
        //testTree(root);

        //midOrderForTree(root);
        postOrderForTree(root);
    }

    //just for test
    // this is no use
    private static void testTree(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> treeNodes = new Stack<>();
        treeNodes.add(root);

        while (!treeNodes.isEmpty()) {
            TreeNode element = treeNodes.pop();
            System.out.println(element.data + ", ");
            if (element.left != null) {
                treeNodes.push(element.left);
            }

            if (element.right != null) {
                treeNodes.push(element.right);
            }
        }
    }


    private static void midOrderForTree(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();

        while (treeNode != null || !stack.isEmpty()) {
            if (treeNode != null) {
                stack.push(treeNode);
                treeNode = treeNode.left;
            } else {
                treeNode = stack.pop();
                System.out.println(treeNode.data);
                treeNode = treeNode.right;
            }
        }
    }


    private static void postOrderForTree(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        Map<TreeNode, Integer> map = Maps.newHashMap();

        while (treeNode != null || !stack.isEmpty()) {
            if (treeNode != null) {
                stack.add(treeNode);
                treeNode = treeNode.left;
            } else {
                treeNode = stack.pop();
                if (treeNode.right != null && map.get(treeNode.right) == null) {
                    stack.push(treeNode);
                    treeNode = treeNode.right;
                } else {
                    System.out.println(treeNode.data);
                    map.put(treeNode, 1);
                    treeNode = null;
                }
            }
        }
    }
}
