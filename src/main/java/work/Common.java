package work;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Author yuqi
 * Time 2/2/18 23:51
 **/
public class Common {
    public ArrayList<Integer> maxInWindows(int [] num, int size)
    {
        ArrayList<Integer> result = new ArrayList<>();
        if (num == null || size > num.length || size < 1) {
            return result;
        }

        LinkedList<Integer> tmp = new LinkedList<>();
        for (int i = 0; i < num.length; i++) {
            tmp.add(num[i]);
            if (tmp.size() == size) {
                result.add(getMaxInList(tmp));
                tmp.removeLast();
            }
        }

        return result;
    }

    private int getMaxInList(LinkedList<Integer> list) {
        int max = Integer.MIN_VALUE;
        for (Integer value : list) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        Common common = new Common();
        int[] ints = new int[] {
                2,3,4,2,6,2,5,1
        };
        ArrayList<Integer> list = common.maxInWindows(ints, 3);
        for (Integer o : list) {
            System.out.println(o + ", ");
        }
    }

    public static class MyLinkedList {
        private int value;
        private MyLinkedList next;


    }
}
