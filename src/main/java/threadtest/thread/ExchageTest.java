package threadtest.thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/12/6
 * Time: 20:30
 * To change this template use File | Settings | File Templates.
 */

public class ExchageTest {

    private static Exchanger<String> exchanger = new Exchanger<String>();

    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String [] args){

        executorService.execute(new Runnable() {
            public void run() {
                try{
                    String  A = "100";
                    String B = exchanger.exchange(A);
                    System.out.println(Thread.currentThread().getName() + A + " buy " + B);
                }catch (Exception e){
                    //
                    e.printStackTrace();
                }
            }
        });

        executorService.execute(new Runnable() {
            public void run() {
                try{
                    String B = "drugs";
                    String A = exchanger.exchange(B);
                    System.out.println(Thread.currentThread().getName() + B + " sell " + A);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        executorService.shutdown();
    }
}
