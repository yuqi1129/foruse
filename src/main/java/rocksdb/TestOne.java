package rocksdb;

import org.rocksdb.Options;
import org.rocksdb.RocksDB;
import org.rocksdb.RocksDBException;

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
            Options options = new Options();

            //
            rocksDB = RocksDB.open("/tmp/rocksdb");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        try {
//
//            ExecutorService executorService = Executors.newFixedThreadPool(8);
//            Random random = new Random();
//
//            for (int i = 0; i < 10000000; i++) {
//                int finalI = i;
//
//                executorService.submit(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        try {
//                            byte[] key = new byte[random.nextInt(64)];
//                            byte[] value = new byte[random.nextInt(256)];
//
//                            for (int j = 0; j < key.length; j++) {
//                                key[j] = (byte) random.nextInt(256);
//                            }
//
//                            for (int j = 0; j < value.length; j++) {
//                                value[j] = (byte) random.nextInt(256);
//                            }
//
//                            rocksDB.put(key, value);
//                            if (finalI % 10000 == 0) {
//                                System.out.println("finish 10000 insert, now = " + finalI);
//                            }
//                        }catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//            }
//            System.out.println(new String(rocksDB.get("test".getBytes())));
//
//            executorService.shutdown();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) {

        try {
            rocksDB.put("test".getBytes(), "test".getBytes());
        } catch (RocksDBException e) {
            e.printStackTrace();
        } finally {
            rocksDB.close();
        }
    }
}
