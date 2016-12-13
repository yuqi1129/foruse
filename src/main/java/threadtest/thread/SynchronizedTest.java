package threadtest.thread;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/12/13
 * Time: 10:30
 * To change this template use File | Settings | File Templates.
 */

public class SynchronizedTest {

    public synchronized static void print1(){
        for (int i = 0 ; i < 5 ;i ++){
            System.out.println(Thread.currentThread().getName() + " ," + String.valueOf(i));
        }
    }

    public void print2(){

        synchronized (SynchronizedTest.class){
            for (int i = 0 ; i< 5 ; i++){
                System.out.println(Thread.currentThread().getName() + " ," + String.valueOf(i));
            }
        }
    }

    public static void main(String [] args){
        final SynchronizedTest synchronizedTest = new SynchronizedTest();
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                synchronizedTest.print2();
            }
        },"t1");

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                SynchronizedTest.print1();
            }
        },"t2");

        t1.start();
        t2.start();


    }



}
