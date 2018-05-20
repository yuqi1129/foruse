package work;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Author yuqi
 * Time 31/1/18 22:24
 **/
public class Main65 {
    public static void main(String[] args) {
        int[] a = new int[] {4,2,7,9,10,1,5,2,7,8,4,3,2,2,9,7};
        System.out.println(maxSequence(a, 4));
        maxStack(a, a.length);
        System.out.println(Arrays.toString(a));
    }

    public static int maxSequence(int[] a, int k) {
        LinkedList<Integer> list = Lists.newLinkedList();
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < a.length; i++) {
            if (list.size() == k) {
                if (a[i] > list.getLast()) {
                    int tmp = list.stream().reduce((sum, b) -> {
                        return sum + b;
                    }).get();
                    if (tmp > max) {
                        max = tmp;
                    }
                }

                list.removeFirst();
                list.addLast(a[i]);
            } else {
                list.addLast(a[i]);
                if (list.size() == k) {
                    max = list.stream().reduce((sum, b) -> {
                        return (sum + b);
                    }).get();
                }
            }
        }

        return max;
    }


    public static void maxStack(int[] a, int len) {
        // a is not null and n is not null;

        int index = len >> 1;
        for (int i = index; i >= 1 ; i--) {
            int left = 2 * i;
            int right = 2 * i + 1;
            if (right > len) {
                if (a[left - 1] > a[i - 1]) {
                    int tmp = a[i - 1];
                    a[i -1] = a[left -1];
                    a[left - 1] = tmp;

                    //should cleanup

                }

                continue;
            }

            int max = Math.max(Math.max(a[i-1], a[left-1]), a[right-1]);
            if (max == a[left -1]) {
                //change a[i -1] and a[left -1]
                int tmp = a[i - 1];
                a[i -1] = a[left -1];
                a[left - 1] = tmp;

            } else if (max == a[right -1]) {
                //change a[i-1] and a[right-1]
                int tmp = a[i - 1];
                a[i -1] = a[right -1];
                a[right- 1] = tmp;
            }
        }
    }

//    private static void cleanUp(int[] a, int start) {
//        while(start < a.length / 2) {
//            int left = start * 2;
//            int right = start * 2 + 1;
//
//
//        }
//    }


}
