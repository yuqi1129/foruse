package threadtest.thread;

import org.apache.commons.lang.time.StopWatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/12/6
 * Time: 11:38
 * To change this template use File | Settings | File Templates.
 */

public class CallTest {

    private static class MyCall implements Callable<Long>{

        private Integer start;
        private Integer end ;

        public MyCall(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }

        public MyCall() {
        }

        public Long call(){
            Long init = 0L;
            for (int i = start ; i < end ; i++){
                init += i;
            }
            System.out.println(Thread.currentThread().getName() + " ended");
            return init;
        }
    }


    public static void main(String [] args){


        MyCall myCall = new MyCall();
        FutureTask futureTask = new FutureTask(myCall);
        futureTask.run();

        try {
            System.out.println(futureTask.get());
        }catch (Exception e){
            //
        }

        ExecutorService service = Executors.newFixedThreadPool(4);
        List<Future> list = new ArrayList<Future>();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for (int i = 0 ; i< 10 ; i++){
            Future future = service.submit(new MyCall(i*10000000 + 1,(i+1) * 10000000));
            list.add(future);

        }

        Long total = 0L;
        try {
            for (Future future : list) {
                total += (Long)future.get(); // 阻塞型的
            }
        }catch (Exception e){
            //
            System.out.println(e.getMessage());
        }
        System.out.println(stopWatch.getTime());
        System.out.println("total = " + total);
        service.shutdown();
    }

    /*
    public static void main(String [] args){
        Long init = 0L ;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        for(int i = 0 ; i< 100000000 ;i++){
            init += i ;
        }
        System.out.println(init);
        System.out.println(stopWatch.getTime());

    }*/
}
