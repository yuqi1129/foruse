package threadtest;

import com.google.common.collect.Queues;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/12/13
 * Time: 17:30
 * To change this template use File | Settings | File Templates.
 */

public class BlockQueueTest {

    private static final BlockingQueue<String> blockingQueue = new LinkedBlockingQueue();


    public static void main(String [] args){
        Thread thread1 = new Thread(new Runnable() {

            public void run () {
                try {
                    for (int i = 0; i < 10; i++) {
                        blockingQueue.put(String.valueOf(i));
                        System.out.println(Thread.currentThread().getName() + " put " + String.valueOf(i));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }

        },"thread-1");

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                try {
                    for (int i = 0; i < 10; i++) {
                        System.out.println(Thread.currentThread().getName() + " take " + blockingQueue.take());
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },"thread-2");

        thread1.start();
        thread2.start();



    }
}
