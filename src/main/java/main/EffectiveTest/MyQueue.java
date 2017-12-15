package main.EffectiveTest;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/11/17
 * Time: 21:49
 * To change this template use File | Settings | File Templates.
 */

public class MyQueue<T> {
    List<T> list = Lists.newArrayList();

    public void put(T t) {
        list.add(t);
    }

    public <T> T[] toArray() {
        return null;
    }

    public T get(int i) {
        return list.get(i);
    }
}
