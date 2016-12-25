package threadtest.thread;

import org.apache.commons.lang.time.StopWatch;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/12/14
 * Time: 17:45
 * To change this template use File | Settings | File Templates.
 */

public class LinkedBlockingQueueTest {

    private static BlockingQueue<Integer> integerBlockingQueue = new LinkedBlockingQueue<Integer>();


    public static void main(String [] args){

        Thread thread1 = new Thread(new Runnable() {
            Random random = new Random();
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName() + " put " + i);
                        integerBlockingQueue.add(Integer.valueOf(i));
                        Thread.currentThread().sleep(random.nextInt(100));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        },"thread-1");

        Thread thread2 = new Thread(new Runnable() {
            StopWatch stopWatch = new StopWatch();
            public void run() {

                while (true) {
                    for (int i = 0; i < 10; i++) {
                        stopWatch.start();
                        System.out.println(System.currentTimeMillis() + " " + Thread.currentThread().getName() + " get " + integerBlockingQueue.poll());

                        System.out.println(stopWatch.getTime());
                        stopWatch.reset();
                    }
                }
            }
        },"thread-2");
        thread1.start();
        thread2.start();

    }
}
