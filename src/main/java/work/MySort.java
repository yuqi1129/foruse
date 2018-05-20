package work;

import java.util.Arrays;


/**
 * Author yuqi
 * Time 3/2/18 19:45
 **/
public class MySort {

    public static void main(String[] args) {
        int[] a = new int[] {
                3,5,4,9,10,1,7,6,2,8
        };

        int[] b = new int[] {
            57,68,59,52,72,28,96,33,34,19
        };

        quickSort(a);
        printArray(a);
    }

    private static void printArray(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ", ");
        }
        System.out.println("");
    }

    //quick sort
    private static void quickSort(int[] a) {
        int start = 0;
        int end = a.length - 1;
        myQuickSortModify(a, 0, end);


    }

    private static void myQuickSort(int[] a, int start, int end) {

        if (start >= end) {
            return;
        }

        int flag = a[end];
        int i = start;
        int j = end;
        while (i < j) {
            while (a[i] <= flag && i < j) {
                i++;
            }

            while (a[j] >= flag && j > i) {
                j--;
            }
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }

        if (a[j] > end) {
            a[end] = a[j];
            a[j] = flag;
        }

        myQuickSort(a, start, j - 1);
        myQuickSort(a, j + 1, end);
    }

    private static void myQuickSortModify(int[] a, int start, int end) {
        if (start >= end) {
            return;
        }

        int flag = a[end];
        int i = start;
        int j = end;

        while (i < j) {
            while (a[i] < flag && i < j) {
                i++;
            }

            while (a[j] > flag && j > i) {
                j--;
            }

            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
        }

        myQuickSortModify(a, start, j - 1);
        myQuickSortModify(a, j + 1, end);
    }

}
