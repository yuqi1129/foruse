package main;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/5/11
 * Time: 15:27
 * To change this template use File | Settings | File Templates.
 */

public class FloatDoubleTest {

    public static void main(String [] args) {
        Float f = Float.MAX_VALUE;
        Long l = Long.MAX_VALUE;

        System.out.println(f * l > Double.MAX_VALUE);
    }
}
