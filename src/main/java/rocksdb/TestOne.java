package rocksdb;

import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/9/1
 * Time: 10:06
 * To change this template use File | Settings | File Templates.
 */

public class TestOne {
    private static RocksDB rocksDB;
    static {
        try {
            rocksDB = RocksDB.open("E:/test/");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {

            ExecutorService executorService = Executors.newFixedThreadPool(5);
            for (int i = 0; i < 5; i++) {
                int finalI = i;
                executorService.submit(new Runnable() {

                    @Override
                    public void run() {
                        try {
                            rocksDB.put("test".getBytes(), ("value" + finalI).getBytes());
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            System.out.println(new String(rocksDB.get("test".getBytes())));

            executorService.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
