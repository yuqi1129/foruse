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

        Double a = 0.01;
        Double b = 0.06;
        BigDecimal bigDecimalStringA = new BigDecimal(a.toString());
        BigDecimal bigDecimalStringB = new BigDecimal(b.toString());
        System.out.println("first = " + bigDecimalStringA.add(bigDecimalStringB).toString());

        BigDecimal bigDecimalStringC = BigDecimal.valueOf(a);
        BigDecimal bigDecimalStringD = BigDecimal.valueOf(b);
        System.out.println("second = " + bigDecimalStringC.add(bigDecimalStringD).toString());

        Float Test1 = Float.MAX_VALUE;
        Float Test2 = Float.MIN_VALUE;

        Float result1 = Float.MAX_VALUE - Float.MIN_VALUE;
        System.out.println(result1);
        System.out.println(Float.MAX_VALUE);

        Double result2 = Double.MAX_VALUE - Double.MIN_VALUE;
        System.out.println(result2);
        System.out.println(Double.MAX_VALUE);
    }
}
