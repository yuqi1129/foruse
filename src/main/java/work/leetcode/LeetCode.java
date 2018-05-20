package work.leetcode;

import work.Common;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Author yuqi
 * Time 17/2/18 22:41
 **/
public class LeetCode {


    private int calcuteSumOfTwoList(LinkedList<Integer> one, LinkedList<Integer> two) {

        Stack<Integer> stack = new Stack<>();

        Iterator<Integer> it1 = one.iterator();
        Iterator<Integer> it2  = two.iterator();

        int tmp = 0;
        while (it1.hasNext() || it1.hasNext()) {
            int tmp1 = 0;
            int tmp2 = 0;
            if (it1.hasNext()) {
               tmp1 = it1.next();
            }

            if (it2.hasNext()) {
                tmp2 = it2.next();
            }

            stack.push((tmp1 + tmp2 + tmp) % 10);
            tmp = (tmp1 + tmp2 + tmp) / 10;
        }

        StringBuilder builder = new StringBuilder();
        stack.stream().forEach(i -> builder.append(i));

        return new Integer(builder.toString());

    }
}
