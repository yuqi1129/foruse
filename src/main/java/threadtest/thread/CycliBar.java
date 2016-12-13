package threadtest.thread;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/12/6
 * Time: 11:24
 * To change this template use File | Settings | File Templates.
 */

public class CycliBar {

    public static class Test implements Runnable{

        private CyclicBarrier cyclicBarrier;

        public Test(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        public void run(){
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " get four await");
        }


    }

    public static void main(String [] args){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(4, new Runnable() {
            public void run() {
                System.out.println("heha,four thread has done");
            }
        });

        Test t = null ;
        Thread thread = null ;

        for (int i = 0 ; i < 4 ; i++){
            t = new Test(cyclicBarrier);
            thread = new Thread(t);
            thread.start();

            try {
                Thread.sleep(new Random().nextInt(5) * 1000);
            }catch (Exception e){
                System.out.println("catch Exception");
            }
        }


    }
}
