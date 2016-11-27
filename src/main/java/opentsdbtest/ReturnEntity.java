package opentsdbtest;

import java.util.Map;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/24
 * Time: 20:27
 * To change this template use File | Settings | File Templates.
 */

public class ReturnEntity {

    private String name ;
    private Integer count ;
    private Map<String,Integer> value ;


    public ReturnEntity(String name, Integer count, Map<String, Integer> value) {
        this.name = name;
        this.count = count;
        this.value = value;
    }

    public ReturnEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Map<String, Integer> getValue() {
        return value;
    }

    public void setValue(Map<String, Integer> value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ReturnEntity{" +
                "name='" + name + '\'' +
                ", count=" + count +
                ", value=" + value +
                '}';
    }
}
