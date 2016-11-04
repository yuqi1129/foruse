package main;


import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/10/21
 * Time: 16:59
 * To change this template use File | Settings | File Templates.
 */

public class MppTest {
    //

    private static final long LEN = 2014L;

    /*
    public static void main(String [] args) {
        try {
            RandomAccessFile raf = new RandomAccessFile("./test.txt", "rw");
            FileChannel fileChannel = raf.getChannel();

            MappedByteBuffer mbb = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, LEN);

            mbb.position(0);
            mbb.put("Hello World".getBytes());
            mbb.putInt(3);
            mbb.force();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }*/


    /**
     *  显示CountDownLatch 的使用方法
     * @param args
     */
    /*
    public static void main(String [] args){
        final CountDownLatch latch = new CountDownLatch(2);

        new Thread(){

            @Override
            public void run(){
                try{
                    System.out.println("child thread " + Thread.currentThread().getName() + " is running");
                    Thread.sleep(3000);
                    System.out.println("child thread " + Thread.currentThread().getName() + " execute completely");
                    latch.countDown();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }.start();

        new Thread(){

            @Override
            public void run(){
                try{
                    System.out.println("child thread " + Thread.currentThread().getName() + " is running");
                    Thread.sleep(3000);
                    System.out.println("child thread " + Thread.currentThread().getName() + " execute completely");
                    latch.countDown();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }.start();

        try{
            System.out.println("waiting for the 2 thread to complete ...");
            latch.await();

            System.out.println("two thread have execute completely");
            System.out.println("the main thread continue to execute");

        }catch (InterruptedException e){
            e.printStackTrace();
        }

    }*/

    /**
     *  验证cyclicBarrier用法
     * @param args
     */
    /*
    public static void main(String [] args){
        int N = 4;
        CyclicBarrier barrier = new CyclicBarrier(N);
        for(int i =0 ; i< N ; i++){
            new Writer(barrier).start();
        }


    }


    private static class Writer extends  Thread{
        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier){
            this.cyclicBarrier = cyclicBarrier;
        }

        private Writer(){
            super();
        }
        @Override
        public void run(){
            System.out.println("thread " + Thread.currentThread().getName() + " is writing data...");
            try{
                //Thread.sleep(3000);
                System.out.println("thread" + Thread.currentThread().getName() + "write data completely");
                Thread.sleep(new Random().nextInt(5) * 1000);
                cyclicBarrier.await();
            }catch (InterruptedException e){
                e.printStackTrace();
            }catch (BrokenBarrierException e){
                e.printStackTrace();
            }

            System.out.println("all thread have complete write data");
        }

    }*/


    private static final int THREAD_SIZE = 1;
    private static final int CAPACITY = 1;

    public static void main(String [] args){

        ThreadPoolExecutor pool = new ThreadPoolExecutor(THREAD_SIZE,CAPACITY,0, TimeUnit.SECONDS
        ,new ArrayBlockingQueue<Runnable>(CAPACITY));

        //pool.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        //pool.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardOldestPolicy());
        //pool.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());
        pool.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        for(int i = 0; i< 10 ;i ++){
            Runnable myrun = new Myrun("task-" + i);
            pool.execute(myrun);
        }

        pool.shutdown();

    }

    static class Myrun implements Runnable{
        private String name ;

        public Myrun(String name){
            this.name = name;
        }

        public void run(){
            try{
                System.out.println(this.name + " is running ...");
                Thread.sleep(100);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }



}
