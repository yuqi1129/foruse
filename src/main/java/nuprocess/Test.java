package nuprocess;

import com.google.common.collect.Lists;

import com.zaxxer.nuprocess.NuProcessBuilder;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/6/21
 * Time: 14:10
 * To change this template use File | Settings | File Templates.
 */

public class Test {

    public static void main(String[] args) {

        char[] chars = new char[32];
        Random random = new Random();
        for (int i = 0; i < chars.length; i++) {
            int value = 0;
            while (!((value >= 48 && value <= 57) || (value >= 97 && value <= 122))) {
                value = random.nextInt() % 128;
            }

            chars[i] = (char) value;
        }

        for (int i = 0; i < chars.length; i++) {
            System.out.print(chars[i]);
        }


    }


}
