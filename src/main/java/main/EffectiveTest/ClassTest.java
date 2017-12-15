package main.EffectiveTest;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/11/10
 * Time: 20:50
 * To change this template use File | Settings | File Templates.
 */

public class ClassTest {
    private int i;

    public void main(String[] args) {
        Help help = new Help(1);
    }

    public static class Help {
        public int a;

        public Help(int a) {
            this.a = a;
        }
    }

    public class Good {
        private int b;

        public Good(int b) {
            this.b = b;
        }

        public int getB() {
            System.out.print(i);
            return b;
        }
    }

}
