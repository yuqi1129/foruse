package threadtest.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/12/6
 * Time: 11:14
 * To change this template use File | Settings | File Templates.
 */

public class CountDown {
    private static final Logger logger = LoggerFactory.getLogger(CountDown.class);

    private static class Example implements Runnable{

        private CountDownLatch countDownLatch ;

        public Example(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }


        public void run(){
            try {
                Thread.sleep(3000L);
                System.out.println(Thread.currentThread().getName() + " ended");
                countDownLatch.countDown();

            }catch (InterruptedException e1){
                //
                logger.info("catch interruptedException");
            }catch (Exception e){
                //
                logger.info("catch interruptedException");
            }
        }

    }

    public static void main(String [] args){

        CountDownLatch countDownLatch = new CountDownLatch(3);

        Example example = null ;
        Thread thread = null ;
        for (int i = 0 ; i< 3 ;i++){
            example = new Example(countDownLatch);
            thread = new Thread(example);
            thread.start();
        }
        try {
            countDownLatch.await();
        }catch (InterruptedException e){
            logger.info("catch interruptedException in main");
        }
        System.out.println("main functin end");
    }


}


