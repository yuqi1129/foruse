package datastructtest.skiplisttest;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/9/24
 * Time: 17:16
 * To change this template use File | Settings | File Templates.
 */

public class SkipTestOne {
    private static class Node {
        Integer data;
        Node next;
        Node below;

        public Node(Integer data) {
            this.data = data;
        }

        public Node(Integer data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(Integer data, Node next, Node below) {
            this.data = data;
            this.next = next;
            this.below = below;
        }

        public Integer getData() {
            return data;
        }

        public void setData(Integer data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getBelow() {
            return below;
        }

        public void setBelow(Node below) {
            this.below = below;
        }
    }

    public static Node createSkipTest(List<Integer> data, Node belowNode) {
        if (data == null) {
            return null;
        }

        if (data.isEmpty()) {
            Node node1 = new Node(Integer.MIN_VALUE);
            node1.next = new Node(Integer.MAX_VALUE);
            node1.below = belowNode;

            return node1;
        }

        Node head = new Node(Integer.MIN_VALUE);
        head.below = belowNode;
        head.next = null;

        Node tmp = head;
        for (Integer integer : data) {
            Node node = new Node(integer);
            tmp.next = node;
            tmp = node;

            if (belowNode != null) {
                Node first = belowNode;
                while (first.next != null && !first.next.data.equals(integer)) {
                    first = first.next;
                }

                node.below = first.next;
            }
        }

        tmp.next = new Node(Integer.MAX_VALUE);

        List<Integer> belowLevelData = Lists.newArrayList();
        Random random = new Random();

//        int size = data.size();
//        int i = 0;
//        while (i < size / 2) {
//            int number = random.nextInt(size);
//            if (!belowLevelData.contains(data.get(number))) {
//                belowLevelData.add(data.get(number));
//                i++;
//            }
//        }

        List<List<Integer>> lists = getHalf(data);
        List<Integer> integers = lists.get(random.nextInt(lists.size()));
        for (Integer integer : integers) {
            belowLevelData.add(data.get(integer));
        }


        return createSkipTest(belowLevelData, head);
    }

    public static void printSkipList(Node node) {
        if (node.below != null) {
            printSkipLevelList(node);
            printSkipList(node.below);
        } else {
            printSkipLevelList(node);
        }

    }

    public static void printSkipLevelList(Node node) {
        Node n = node;
        while (n != null) {
            System.out.print(n.data + " ");
            n = n.next;
        }
        System.out.println("\n");
    }


    public static List<List<Integer>> getHalf(List<Integer> list) {
        List<List<Integer>> result = Lists.newArrayList();
        if (list == null || list.isEmpty()) {
            return result;
        }

        List<Integer> emptyList = Lists.newArrayList();
        getHalfIndex(list, 0, emptyList, result);
        return result;
    }


    public static void getHalfIndex(List<Integer> origin, int number, List<Integer> list, List<List<Integer>> res) {

        int size = origin.size();

        if (number == size / 2) {
            res.add(Lists.newArrayList(list));
            return;
        }

        int start = list.size() == 0 ? 0 : list.get(list.size() - 1) + 1;
        for (int i = start; i <= size - (size / 2 - number); i++) {
            list.add(i);
            getHalfIndex(origin, number + 1, list, res);
            list.remove(list.get(list.size() - 1));
        }
    }

    public static void main(String[] args) {
        List<Integer> integers = Lists.newArrayList();
        for (int i = 0; i < 20; i++) {
            integers.add(i);
        }
        //Node node = createSkipTest(integers, null);
        //printSkipList(node);


        int numbers = 0;
        for (int i = 0; i < 1000; i++) {
            Node node = createSkipTest(integers, null);
            for (int j = 1; j <= 20; j++) {
                numbers += searchNumberInNode(node, j);
            }
        }

        System.out.println(numbers / 10000.0);

    }

    public static int searchNumberInNode(Node node, int data) {
        if (node == null) {
            return 0;
        }

        Node node1;
        Node node2;

        Node tmp = node;
        int i = 0;

        while (tmp.next != null && tmp.next.data > data) {
            tmp = tmp.below;
            i++;
        }

        if (tmp.next == null) {
            return 0;
        }

        node1 = tmp;
        node2 = tmp.next;
        // start search

        if (node2.data == data) {
            return i;
        }

        while (node1.below != null) {
            node1 = node1.below;
        }

        while(node2.below != null) {
            node2 = node2.below;
        }

        return search(node1, node2, data);
    }


    public static int search(Node start, Node end, int data) {
        Node tmp = start;
        int i = 0;
        while (tmp.data != data && tmp != end) {
            tmp = tmp.next;
            i++;
        }
        return i;
    }
}


