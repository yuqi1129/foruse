package main.EffectiveTest;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/11/10
 * Time: 20:53
 * To change this template use File | Settings | File Templates.
 */

public class AnotherClassrTest {
    public static void main(String[] args) {
        MyQueue<Integer> myQueue = new MyQueue<>();
        myQueue.put(1);
        System.out.println(myQueue.get(0));
    }
}
