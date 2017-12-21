package main;

import com.google.common.collect.Maps;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.math.BigDecimal.ROUND_HALF_UP;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/3/9
 * Time: 17:28
 * To change this template use File | Settings | File Templates.
 */

public class BigDecimalTest {
    /**
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
    }*/

    public static void main(String[] args) {

        /**
        BigInteger bigInteger = new BigInteger("23");
        BigDecimal bigDecimal = new BigDecimal(bigInteger);
        Long l = 4L;

        System.out.println(bigDecimal.divide(BigDecimal.valueOf(l), 14, ROUND_HALF_UP).doubleValue());

        Map<String, Integer> map = Maps.newHashMap();
        map.put(null, 1);
        System.out.println(map.get(null));
         */

        try {
            Class<?> aClass = TestOne.class;
            TestOne testOne = (TestOne) aClass.newInstance();
            Method method = aClass.getDeclaredMethod("main", String[].class);
            String[] objects = new String[]{"test"};
            method.invoke(testOne, (Object)objects);
            System.out.println(Arrays.toString(objects));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
