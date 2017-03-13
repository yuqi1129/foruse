package main;

import java.math.BigDecimal;
import java.math.MathContext;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/3/9
 * Time: 17:28
 * To change this template use File | Settings | File Templates.
 */

public class BigDecimalTest {
    public static void main(String [] args) {
        BigDecimal one = new BigDecimal("0.06");
        BigDecimal two = new BigDecimal("0.01");

        BigDecimal three = one.add(two);
        System.out.println(three.toString());

        System.out.println(0.01d + 0.06d);
    }
}
