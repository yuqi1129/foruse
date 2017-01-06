package googlecommon.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

import java.io.RandomAccessFile;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/1/6
 * Time: 20:02
 * To change this template use File | Settings | File Templates.
 */

public class GoogleCache {

    private static Cache<Integer,String> cache = CacheBuilder.newBuilder().expireAfterWrite(30, TimeUnit.SECONDS).expireAfterAccess(1,TimeUnit.MINUTES).removalListener(new RemovalListener<Integer,String>() {


        public void onRemoval(RemovalNotification<Integer, String> var1){
            System.out.println("remove key: " + var1.getKey().toString());
        }
    }).build();

    final static Random random = new Random();

    public static void  main(String [] args){

        new TestThread().run();
        new TestThread().run();


    }

    private static class TestThread implements  Runnable{
        public void run() {
            //

            while (true){
                try {
                    final Integer key = random.nextInt(1000);
                    System.out.println(cache.get(key, new Callable<String>() {
                        public String call() throws Exception {
                            Integer value = key * key;
                            return value.toString();
                        }
                    }) + " size of cache = " + cache.size());

                    Thread.currentThread().sleep(1000);
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }catch (InterruptedException e1){
                    e1.printStackTrace();
                }
            }
        }
    }


}
