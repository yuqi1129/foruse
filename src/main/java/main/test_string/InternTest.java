package main.test_string;

/**
 * Author yuqi
 * Time 7/1/18 18:13
 **/
public class InternTest {
    public static void main(String[] args) {

        String a = new String("a");
        String b = "a";


        System.out.println(a == b);

        String c = new String("a") + new String("a");
        c.intern();
        String d = "aa";

        System.out.println(c == d);
    }
}
