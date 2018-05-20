package java8.lamda;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author yuqi
 * Time 29/12/17 10:12
 **/
public class StreamTestOne {

    public static void main(String[] args) {
        List<Person> peoples = Lists.newArrayList(
                new Person("4", "zhang"),
                new Person("2", "wang"),
                new Person("6", "li"),
                new Person("5", "huang"),
                new Person("2", "yu")
        );

        System.out.println(peoples.stream().filter(p -> !"2".equalsIgnoreCase(p.id)).sorted().limit(2).collect(Collectors.toList()));
    }


    private static class Person implements Comparable<Person> {
        public String id;
        public String name;

        public Person(String id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public int compareTo(Person o) {
            if (null == o) {
                return 1;
            }

            return id.compareTo(o.id);
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}