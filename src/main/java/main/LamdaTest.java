package main;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LamdaTest {
    public static void main(String[] args) {

        Map<Integer, Integer> map = Maps.newHashMap();
        List<Integer> list = Lists.newArrayList(1,2,3,4);
        map.put(1, 3);
        map.put(2, 5);

        System.out.println(map.values().stream().reduce((a, b) -> {
            a = a + b;
            return a;
        }).get());
        List<String> stringList = Lists.newArrayList("a", "b");
        List<String> another  = stringList.stream().map(a -> {
            return a.toUpperCase();
        }).collect(Collectors.toList());

        System.out.println(another);


    }
}
