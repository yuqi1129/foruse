package threadtest.threadlocal;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/27
 * Time: 12:45
 * To change this template use File | Settings | File Templates.
 */

public class MainTest {
    //

    //ThreadPoolExecutor threadPoolExecutor = Executors.

    //ExecutorService service1 = Executors.newSingleThreadExecutor();

    //ThreadPoolExecutor threadPoolExecutor1 = new ThreadPoolExecutor(1,1,1000L, TimeUnit.SECONDS,new LinkedBlockingDeque<Runnable>());

    public static class Myrun implements Runnable{

        private ThreadLocal<Integer> threadLocal = new ThreadLocal();

        //private Integer i = 0;

        public void run() {
            for (Integer i = 0 ;i<10 ;i++) {
                threadLocal.set(i);
                // i++;
                System.out.println(Thread.currentThread().getName() + ",i = " + threadLocal.get()) ;
                //System.out.println(Thread.currentThread().getName() + ",i = " + i) ;
                try {
                    Thread.sleep(new Random().nextInt(100));
                }catch (InterruptedException e){
                    System.out.println("we get InterruptedExectipin:" +e );
                }
            }
        }
    }

    /*
    public static void main(String [] agrs){

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new Myrun());
        executorService.execute(new Myrun());
    }*/



}
