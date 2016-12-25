package threadtest.thread;


import com.google.common.collect.Queues;

import org.apache.commons.lang.time.StopWatch;
import org.omg.SendingContext.RunTime;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/12/13
 * Time: 17:42
 * To change this template use File | Settings | File Templates.
 */

public class CompleteServiceTest {


    private static class AddSum implements Callable<Long>{

        public Long call(){
            Long total = 0L;
            for (int i = 0 ; i< 10000000 ;i++){
                total += i;
            }
            return total;
        }
    }


    public static void TestExecuterService(){
        BlockingQueue<Future<Long>> blockingQueue = Queues.newLinkedBlockingQueue();
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0 ; i< 10 ;i++){
            Future<Long> future = executorService.submit(new CompleteServiceTest.AddSum());
            blockingQueue.add(future);
        }

        Long total = 0L ;
        try {
            for (Future<Long> future : blockingQueue) {
                total += future.get();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("1 Total " + total.toString() + " use " + String.valueOf(stopWatch.getTime()));
        executorService.shutdown();

    }


    public static void TestCompletitionService(){
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        CompletionService<Long> completionService = new ExecutorCompletionService<Long>(executorService) ;

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i =0 ; i< 10 ;i++){
            completionService.submit(new CompleteServiceTest.AddSum());
        }
        Long total = 0L;
        try {
            for (int i = 0; i < 10; i++) {
                total += completionService.take().get();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("2 Total " + total.toString() + " use " + String.valueOf(stopWatch.getTime()));

        executorService.shutdown();

    }

    public static void main(String [] args){
        CompleteServiceTest.TestCompletitionService();

        /*
        try{
            Thread.currentThread().sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }*/
        CompleteServiceTest.TestExecuterService();

        String one = "test";
        String two = new String("test");
        String three = new String("test");
        System.out.println("test" == one.intern());
        System.out.println(two.intern() == three.intern());


    }


}
