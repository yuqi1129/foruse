package main.thread_test;

import java.util.concurrent.CountDownLatch;

/**
 * Author yuqi
 * Time 24/4/18 19:53
 **/
public class ThreadTestTwo {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        try {
            for (int i = 0; i < 5; i++)
                new Thread(() -> {
                    countDownLatch.countDown();
                    System.out.println("countDown = " + countDownLatch.getCount());
                }).start();
            countDownLatch.await();

            System.out.println("main thead end");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
