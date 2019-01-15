package guava;

import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;

import java.util.Map;

public class MapTest1 {
    public static void main(String[] args) {
        Map<String, Integer> map1 = Maps.newHashMap();

        map1.put("a", 1);
        map1.put("b", 2);

        Map<String, Integer> map2 = Maps.newHashMap();
        map2.put("a", 1);

        MapDifference<String, Integer> diff = Maps.difference(map1, map2);

        System.out.println(diff.areEqual());
        System.out.println(diff.entriesInCommon());
        System.out.println(diff.entriesOnlyOnLeft());
        System.out.println(diff.entriesOnlyOnRight());
    }
}
