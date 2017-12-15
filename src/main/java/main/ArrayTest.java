package main;

import main.EffectiveTest.ClassTest;
import org.apache.flink.hadoop.shaded.com.google.common.collect.Maps;

import java.util.Map;
import java.util.Random;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/5/22
 * Time: 10:34
 * To change this template use File | Settings | File Templates.
 */

public class ArrayTest  extends TestOne {

    private final Integer a;

    public ArrayTest(Integer a) {
        this.a = a;
    }

    public static void main(String[] args) {
        /**
        String arrayString = "1,2,3,4,";
        String[] strings = arrayString.split(",");
        //if (strings.length == 5)
        System.out.println(strings.length);
        ClassTest.Help help = new ClassTest.Help(1);
         */

        Map<Hello, Integer> map = Maps.newHashMap();
        map.put(new Hello("zhang", 1), 1);
        System.out.println(map.get(new Hello("zhang", 1)));

        Map<String, Integer> map1 = Maps.newHashMap();
        map1.put(new String("nice"), 1);
        System.out.println(map1.get(new String("nice")));
        //// TODO: 2017/11/27
        Map<Integer, Integer> integerMaps = Maps.newHashMap();
        integerMaps.put(1, 1);
        integerMaps.put(1, 2);

        for (Map.Entry<Integer, Integer> entry : integerMaps.entrySet()) {
            integerMaps.remove(entry.getKey());
        }

        int n = 2 * (Integer.MAX_VALUE / 3);
        int low = 0;
        Random r = new Random();

        for (int i = 0; i < 100000; i++) {
            if (r.nextInt(n) < n / 2) {
                low++;
            }
        }
        System.out.println(low);

    }

    private static class Hello {
        private final String name;
        private final Integer id;

        public Hello(String name, Integer id) {
            this.name = name;
            this.id = id;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Hello hello = (Hello) o;

            if (name != null ? !name.equals(hello.name) : hello.name != null) return false;
            return id != null ? id.equals(hello.id) : hello.id == null;

        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (id != null ? id.hashCode() : 0);
            return result;
        }
    }

}
