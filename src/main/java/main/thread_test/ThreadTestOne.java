package main.thread_test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Author yuqi
 * Time 20/1/18 21:29
 **/
public class ThreadTestOne {
    public static Lock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    private static class MyThreadA extends Thread {
        int i = 10;
        @Override
        public void run() {
            try {
                lock.lock();
                while (i > 0) {
                    System.out.println(Thread.currentThread().getName() + ", i --> " + i);
                    i--;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    private static class MyThreadB implements Runnable {
        volatile int i = 10;

        @Override
        public void run() {
            while (i > 0) {
                System.out.println(Thread.currentThread().getName() + ", i --> " + i);
                i--;
            }
        }
    }

    public static void main(String[] args) {
        MyThreadA a1 = new MyThreadA();
        MyThreadA a2 = new MyThreadA();
        MyThreadA a3 = new MyThreadA();

        a1.start();
        a2.start();
        a3.start();

        MyThreadB myThreadB = new MyThreadB();

        Thread b1 = new Thread(myThreadB);
        Thread b2 = new Thread(myThreadB);
        Thread b3 = new Thread(myThreadB);

        b1.start();
        b2.start();
        b3.start();

        Lock lock = new ReentrantLock();
    }

}
