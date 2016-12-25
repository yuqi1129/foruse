package threadtest.thread;

import org.apache.commons.lang.time.StopWatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/12/13
 * Time: 20:10
 * To change this template use File | Settings | File Templates.
 */

public class AtomicTest {

    private static final AtomicLong  atomicLong = new AtomicLong(0L);
    private static final Long Times = 1000000000L;

    private static Object object = new Object();
    private static final LongAdder lacount = new LongAdder();
    private static Long start = 0L ;

    private static CountDownLatch countDownLatch1 = new CountDownLatch(Runtime.getRuntime().availableProcessors());
    private static CountDownLatch countDownLatch2 = new CountDownLatch(Runtime.getRuntime().availableProcessors());
    private static CountDownLatch countDownLatch3 = new CountDownLatch(Runtime.getRuntime().availableProcessors());

    public static void testLock() {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        for(int i = 0 ; i < Runtime.getRuntime().availableProcessors() ; i++) {
            executorService.submit(new Runnable() {
                public void run() {
                    while(start < Times) {

                        synchronized (object) {
                                start++;
                        }
                    }
                    countDownLatch1.countDown();
                }
            });
        }
        try{
        countDownLatch1.await();
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("testlock use:" + String.valueOf(stopWatch.getTime()) + " millseconds");
        executorService.shutdown();
    }


    public static  void testAtomic(){
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        for(int i = 0 ; i < Runtime.getRuntime().availableProcessors() ; i++) {
            executorService.submit(new Runnable() {
                public void run() {
                    while(atomicLong.get() < Times) {
                        atomicLong.getAndIncrement();
                    }
                    countDownLatch2.countDown();
                }
            });
        }

        try{
            countDownLatch2.await();
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("atomic use:" + String.valueOf(stopWatch.getTime()) + " millseconds");
        executorService.shutdown();

    }


    public static void testAddr(){
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        for(int i = 0 ; i < Runtime.getRuntime().availableProcessors() ; i++) {
            executorService.submit(new Runnable() {
                public void run() {
                    Long v = lacount.sum();
                    while(v < Times) {
                        lacount.increment();
                        v = lacount.sum();
                    }
                    countDownLatch3.countDown();
                }
            });
        }

        try{
            countDownLatch3.await();
        }catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("Longaddr use:" + String.valueOf(stopWatch.getTime()) + " millseconds");
        executorService.shutdown();
    }

    public static void main(String [] args){
        AtomicTest.testLock();
        AtomicTest.start = 0L ;
        AtomicTest.testAddr();
        AtomicTest.start = 0L ;
        AtomicTest.testAtomic();
    }


}
