package regx;

import java.util.regex.Pattern;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/1
 * Time: 21:17
 * To change this template use File | Settings | File Templates.
 */

public class TestOne {


    public static void main(String [] args) {
        Pattern namePattern = null;
        try {
            namePattern = Pattern.compile("txt");
        } catch (Exception e) {
            //
        }

        if (namePattern.matcher("test.txt").matches()) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }
}
