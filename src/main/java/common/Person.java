package common;

import com.google.common.collect.ComparisonChain;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/10/26
 * Time: 15:32
 * To change this template use File | Settings | File Templates.
 */

public class Person implements Comparable<Person> {
    private String name;
    private Integer id;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person(String name, Integer id) {
        this.name = name;
        this.id = id;
    }

    public Person() {
    }

    public int compareTo(Person p){
        return ComparisonChain.start().compare(this.id,p.getId())
                .compare(this.getName(),p.getName()).result();
    }
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
