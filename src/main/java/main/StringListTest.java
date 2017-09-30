package main;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/9/19
 * Time: 14:04
 * To change this template use File | Settings | File Templates.
 */

public class StringListTest {
    public static void main(String[] args) {
        List<String> list = Lists.newArrayList();

        String a = new String("test");
        String b = new String("test");

        list.add(a);
        list.remove(b);
        System.out.print(list.size());
    }
}
