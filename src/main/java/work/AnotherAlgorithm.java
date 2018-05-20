package work;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

/**
 * Author yuqi
 * Time 10/2/18 20:49
 **/
public class AnotherAlgorithm {

    // 逆序对
    public static int niXuDui(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }

        int result = 0;
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j+1]) {
                    swap(a, j, j+1);
                    result++;
                }
            }
        }

        return result;
    }

    private static void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

//    public static void main(String[] args) {
//        int[] a = new int[] {2, 4, 1, 3, 5};
//        System.out.println(niXuDui(a));
//
//        //2,1,4,3,5
//        //2,1,3,4,5
//        //1,2,3,4,5
//    }

    //合并区间
    public static List<Pair<Integer, Integer>> mergeQuJian(List<Pair<Integer, Integer>> pairList) {

        pairList.sort((a, b) -> {
            if (a.getKey() > b.getKey()) {
                return 1;
            } else if (a.getKey() < b.getKey()) {
                return -1;
            } else {
                return 0;
            }
        });

        int start = pairList.get(0).getKey();
        int end = pairList.get(0).getValue();

        List<Pair<Integer, Integer>> result = Lists.newArrayList();

        for (int i = 1; i < pairList.size(); i++) {
            if (end >= pairList.get(i).getKey()) {
                end = Math.max(pairList.get(i).getValue(), end);
            } else {
                result.add(Pair.of(start, end));
                start = pairList.get(i).getKey();
                end = pairList.get(i).getValue();
            }
        }
        result.add(Pair.of(start, end));

        return result;
    }

//    public static void main(String[] args) {
//        List<Pair<Integer, Integer>> pairs = Lists.newArrayList(
//                Pair.of(1, 2),
//                Pair.of(2, 4),
//                Pair.of(8, 11),
//                Pair.of(7, 12),
//                Pair.of(15, 18));
//
//        System.out.println(mergeQuJian(pairs));
//    }

    //搜索旋转排序数组
    //4 5 6 7 0 1 2
    public static int serachInBinaryArray(int[] array, int element) {
        if (array == null || array.length == 0) {
            return -1;
        }

        int i = 0;
        int j = array.length - 1;

        while (i <= j) {
            int mid = (i + j) >> 1;

            if (array[mid] == element) {
                return mid;
            }

            if (array[mid] > array[i]) {
                if (array[i] <= element && element < array[mid]) {
                    j = mid - 1;
                } else {
                    i = mid + 1;
                }
            } else {
                if (array[j] >= element && element > array[mid]) {
                    i = mid + 1;
                } else {
                    j = mid - 1;
                }
            }
        }

        return -1;
    }
//
//    public static void main(String[] args) {
//        int[] a = new int[] {4, 5, 6, 7, 0, 1, 2};
//        System.out.println(serachInBinaryArray(a, 1));
//    }

    //删除排序数组中的重复数字

    public static int deleteDeductionElement(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }

        int i = 0;
        int len = 0;

        while (i < a.length) {
           int j = i + 1;
           while (j < a.length && a[j] == a[i]) {
               j++;
           }

           len++;
           i = j;
        }
        return len;
    }

    public static int deleteDeductionElement1(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }

        int i = 0;
        int len = 0;

        while (i < a.length) {
            int j = i + 1;
            while (j < a.length && a[j] == a[i]) {
                j++;
            }

            if (j - i >= 3) {
                len += 2;
            } else {
                len += (j - i);
            }
            i = j;
        }
        return len;
    }

    public static void main(String[] args) {
        int[] a = new int[] {
                1,2,2,3,5,5,5,7,20
        };

        System.out.println(deleteDeductionElement1(a));
    }

    //两数和 - 输入的数据是有序的

    //颜色分类

}
