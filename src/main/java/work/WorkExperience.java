package work;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;

/**
 * Author yuqi
 * Time 9/2/18 19:21
 **/
public class WorkExperience {

    /**
     * 最长无重复子串长度
     * @param s
     * @return
     */

    public static final int N = 2;

    public static int longDeductionString(String s) {
        Map<Character, Integer> map = Maps.newHashMap();
        int Max = Integer.MIN_VALUE;
        int tmp = 1;
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            Integer before = map.get(c);
            if (before == null) {
                map.put(c, i);
            } else {
                int len = i - tmp + 1;

                if (len > Max) {
                    Max = len;
                }

                map.put(c, i);
                tmp = before + 1;
            }
        }

        return Max;
    }
//
//    public static void main(String[] args) {
//        String s = "abcdefbcag";
//        System.out.println(longDeductionString(s));
//    }


    public static Map<String, Object> parser(String str) {
        Map<String, Object> map = new HashMap<>();
        if (str == null || str.length() == 0) {
            return map;
        }

        Stack<String> stringStack = new Stack<>();
        Stack<Map<String, Object>> mapStack = new Stack<>();


        StringBuilder builder = new StringBuilder();
        int i = 0;

        String key;
        String value;
        while (i < str.length()) {
            char c = str.charAt(i);
            if (c == '[') {
                mapStack.push(map);
                builder.setLength(0);
                map = Maps.newHashMap();
            } else if (c == ']') {
                key = stringStack.pop();
                value = builder.toString();
                if (!"".equals(value)) {
                    map.put(key, value);
                    Map<String, Object> tmp = mapStack.pop();
                    String key1 = stringStack.pop();
                    tmp.put(key1, map);
                    map = tmp;
                } else {
                    Map<String, Object> tmp = mapStack.pop();
                    tmp.put(key, map);
                    map = tmp;
                }
                builder.setLength(0);

            } else if (c == ',') {
                if (builder.length() != 0) {
                    key = stringStack.pop();
                    value = builder.toString();
                    map.put(key, value);
                    builder.setLength(0);
                }
            } else if (c == '=') {
                key = builder.toString();
                stringStack.push(key);
                builder.setLength(0);
            } else {
                builder.append(c);
            }
            i++;
        }

        return map;
    }

    //递归

    public static Map<String, Object> parser1(String str, int start, int[] position) {

        Map<String, Object> map = new HashMap<>();

        StringBuilder builder = new StringBuilder();
        int i = start;
        String key = null;
        String value = null;

        while (i < str.length()) {
            char c = str.charAt(i);
            if (c == '[') {
                if (key != null) {
                    map.put(key, parser1(str, i + 1, position));
                }
            } else if (c == ',' || c == ']') {
                if (builder.length() != 0) {
                    map.put(key, builder.toString());
                    builder.setLength(0);
                }

                if (c == ']') {
                    position[0] = i + 1;
                    return map;
                }
            } else if (c == '=') {
                key = builder.toString();
                builder.setLength(0);
            } else {
                builder.append(c);
            }

            if (position[0] != 0) {
                i = position[0];
                position[0] = 0;
            }
            i++;
        }

        return map;
    }

    public static void main(String[] args) {
        String s = "key1=value1,key2=[key10=value10],key9=[key3=[key4=value4,key5=value5,key6=[key7=value7]]],key8=[key11=[key12=value12]]";

        Map<String, Object> o = parser(s);
        System.out.println(o);
    }

//    public static Integer[] mergeTwoArray(int[] A, int[] B) {
//        Preconditions.checkArgument(A != null && B != null);
//
//        int i = 0;
//        int j = 0;
//
//
//        int len1 = A.length;
//        int len2 = B.length;
//        List<Integer> integerList = Lists.newArrayList();
//        int index = 0;
//        while (i < len1 && j < len2) {
//            if (A[i] < B[j]) {
//                i++;
//            } else if (A[i] > B[j]) {
//                j++;
//            } else if (A[i] == B[j]) {
//               i++;
//               j++;
//               integerList.add(A[i]);
//               integerList.add(B[j]);
//            }
//        }
//
//        return integerList.toArray(new Integer[integerList.size()]);
//    }


    public static List<Integer> mergeTwoArray(List<Integer> A, List<Integer> B) {
        List<Integer> result = new ArrayList<>();
        if (A == null || B == null) {
            return result;
        }

        int i = 0;
        int j = 0;
        while (i < A.size() && j < B.size()) {
            if (A.get(i) < B.get(j)) {
                i++;
            } else if (A.get(i) > B.get(j)) {
                j++;
            } else {
                result.add(A.get(i));
                i++;
                j++;
            }
        }

        // 也可以把A或B放入一个Map中，然后遍历查另一个查找，但这样做没有多大必要

        return result;
    }

    public static List<Integer> merge(List<List<Integer>> A) {
        Preconditions.checkArgument(A.size() > 1 && A.size() < 1024);
        List<Integer> integers = new ArrayList<>();

        List<List<Integer>> help = new ArrayList<>();

        //两两归并返回两个List<>中有重复的元素
        //复杂度lgN * 1000000
        while (help.size() + A.size() > 1) {
            int size = A.size();
            int i = 0;
            if ((size & 0x01) == 1) {
                while (i < size - 1) {
                    List<Integer> tmp = mergeTwoArray(A.get(i++), A.get(i++));
                    if (tmp.size() == 0) {
                        return integers;
                    } else {
                        help.add(tmp);
                    }
                }
                help.add(A.get(size - 1));
            } else {
                while (i < size) {
                    List<Integer> tmp = mergeTwoArray(A.get(i++), A.get(i++));
                    if (tmp.size() == 0) {
                        return integers;
                    } else {
                        help.add(tmp);
                    }
                }
            }

            A = help;
            help = new ArrayList<>();
        }

        return A.get(0);
    }

    /**
     * 如果
     * @param args
     */


//    public static void main(String[] args) {
//        List<Integer> A = Lists.newArrayList(1, 5, 8, 10, 17, 22, 40, 100);
//        List<Integer> B = Lists.newArrayList(4, 10, 27, 48, 70, 100, 200, 100);
//        List<Integer> C = Lists.newArrayList(5, 8, 10, 200, 1000);
//        List<Integer> D = Lists.newArrayList(10, 1000, 2000, 3000, 4000);
//
//        List<List<Integer>> array = Lists.newArrayList(A, B, C, D);
//
//
//        List<Integer> E = merge(array);
//        System.out.println(E);
//    }
}
