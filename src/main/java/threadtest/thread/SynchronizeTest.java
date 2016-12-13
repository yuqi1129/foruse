package threadtest.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/12/6
 * Time: 19:26
 * To change this template use File | Settings | File Templates.
 */

public class SynchronizeTest {
    public static void main(String [] args){
        //
        Thread t1 = new Thread(new Test(),"test1");
        if (t1.isDaemon()){
            System.out.println("t1 is deamon");
        }
        t1.start();
        new Thread(new Test(),"test2").start();



    }

    private static class Test implements Runnable{


        public void run(){
            synchronized (Object.class){
                //
                try{
                    TimeUnit.SECONDS.sleep(2);
                    System.out.println(Thread.currentThread().getName() + " ended");
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }

            }
        }

    }
}
