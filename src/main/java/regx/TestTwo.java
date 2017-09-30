package regx;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/9/21
 * Time: 16:02
 * To change this template use File | Settings | File Templates.
 */

public class TestTwo {
    public static void main(String[] args) {
        String test1 = "{xxxx}$";

        System.out.print(test1.replace("{xxxx}$", "xxx"));
    }
}
