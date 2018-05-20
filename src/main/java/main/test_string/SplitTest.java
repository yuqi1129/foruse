package main.test_string;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.Arrays;
import java.util.Objects;

/**
 * Author yuqi
 * Time 8/1/18 19:46
 **/
public class SplitTest {
    public static void main(String[] args) {
//        String[] arr1 = "a".split(",", 2);
//        String[] arr2 = "a,a".split(",", 2);
//        String[] arr3 = "a,a,a".split(",", 2);
//
//        System.out.println("arr1:" + Arrays.asList(arr1));
//        System.out.println("arr2:" + Arrays.asList(arr2));
//        System.out.println("arr3:" + Arrays.asList(arr3));


        StringBuffer javascript = null;
        ScriptEngine runtime = null;

        try {
            runtime = new ScriptEngineManager().getEngineByName("javascript");
            javascript = new StringBuffer();

            javascript.append("1 + 1");

            double result = Double.valueOf((Integer) runtime.eval(javascript.toString()));

            System.out.println("Result: " + result);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
