package main;


/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/10/23
 * Time: 15:42
 * To change this template use File | Settings | File Templates.
 */

public class ThreadLocalTest {

    private int local =  1;

    public static class Myrun implements Runnable{

        /*
        private ThreadLocal<Integer> localdata = new ThreadLocal<Integer>(){
            @Override
            protected Integer initialValue(){
                return 1;
            }


        };*/

        private int localdata ;

        public Myrun(int data){
            localdata = data;
        }

        public void run(){

            try{
                Thread.sleep(1000);
                //localdata.set(localdata.get() + 1);
                //System.out.println("Thread: " + Thread.currentThread().getName() + " :" + (Integer)(localdata.get()));
                localdata += 1;
                System.out.println("Thread: " + Thread.currentThread().getName() + " :" + localdata);
            }catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }


    public static void main(String [] args){
        Myrun myrun = new Myrun(1);
        Thread thread1 = new Thread(myrun);
        Thread thread2 = new Thread(myrun);

        thread1.start();
        thread2.start();
    }



}
