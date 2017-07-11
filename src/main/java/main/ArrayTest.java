package main;

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
        String arrayString = "1,2,3,4,";
        String[] strings = arrayString.split(",");
        //if (strings.length == 5)
        System.out.println(strings.length);
    }


}
