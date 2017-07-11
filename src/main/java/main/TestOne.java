package main;

import com.google.common.collect.Sets;

import java.util.Map;
import java.util.Set;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/8
 * Time: 10:05
 * To change this template use File | Settings | File Templates.
 */

public class TestOne {
    private int a = 10;

    public static void main(String [] args){

        Set<Integer> set1 = Sets.newTreeSet();

        set1.add(1);
        set1.add(3);

        Set<Integer> set2 = Sets.newHashSet();
        set2.add(3);
        set2.add(4);

        set2.retainAll(set1);

        System.out.println(set2);

        System.out.println(System.getProperty("line.separator"));

    }
}
