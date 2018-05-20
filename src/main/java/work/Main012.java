package work;

/**
 * Author yuqi
 * Time 31/1/18 21:12
 **/
public class Main012 {
    public static void main(String[] args) {
        int n = 6;
        printOne2N(n, "");

    }


    public static void printOne2N(int n, String before) {
        if (n == 0) {
            if (!before.startsWith("0")) {
                System.out.println(before);
            }
            return;
        }
        for (int i = 0; i <= 9; i++) {
            printOne2N(n-1, before + ((char) ('0' + i)));
        }
    }
}
