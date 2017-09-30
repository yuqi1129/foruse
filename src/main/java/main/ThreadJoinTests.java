package main;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/9/21
 * Time: 10:37
 * To change this template use File | Settings | File Templates.
 */

public class ThreadJoinTests {

    private static class ThreadOne implements Runnable {
        @Override
        public void run() {
            try {
                Thread.currentThread().sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("hello");
        }
    }

    private static final Thread thread = new Thread(new ThreadOne());

    private static volatile Integer integer = new Integer(0);


//    public static void main(String[] args) {
//        thread.start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                synchronized (thread) {
//                    //thread.notify();
//                }
//            }
//        }).start();
//
//        try {
//            synchronized (thread) {
//                thread.wait();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("finish");
//    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (integer) {
                    try {
                        while (integer < 10) {
                            integer.wait();
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("number = " + integer);

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    synchronized (integer) {
                        integer = integer + 1;
                        if (integer > 10) {
                            integer.notify();
                            break;
                        }
                    }
                    try {
                        Thread.currentThread().sleep(50);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
