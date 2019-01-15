package rocksdb;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2018/7/20 下午5:10
 */

import com.google.common.collect.Maps;
import org.apache.commons.lang.time.StopWatch;
import org.rocksdb.Checkpoint;
import org.rocksdb.RocksDB;

import java.io.File;
import java.util.Map;
import java.util.Random;

public class TestTwo {
	public static final String rocksDBPath = "/tmp/rocksdbdir/rocksdb";
	public static final String rocksDBPathCheckpointBase = "/tmp/rocksdbdir/";
	private static RocksDB rocksDB;
	static {
		try {
			rocksDB = RocksDB.open(rocksDBPath);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		Random r = new Random();

		StopWatch watch = new StopWatch();
		watch.start();

		Map<Integer, String> map = Maps.newHashMap();

		try {
			for (int i = 0; i < 1000; i++) {
				String key = r.nextInt() + "-" + r.nextInt();
				map.put(i, key);
				rocksDB.put(key.getBytes(), new byte[100000]);
			}

			System.out.println("1000 insert take " + watch.getTime() + " ms");


			File file = new File(rocksDBPath);
			for (File f : file.listFiles()) {
				System.out.println(f.getName());
			}

			Checkpoint checkpoint1 = Checkpoint.create(rocksDB);
			checkpoint1.createCheckpoint(rocksDBPathCheckpointBase + "/1");


			System.out.println("------------------1---------------------");
			File file1 = new File(rocksDBPathCheckpointBase + "/1");
			for (File f : file1.listFiles()) {
				System.out.println(f.getName());
			}


			for (int i = 0; i < 1000; i++) {
				String key = r.nextInt() + "-" + r.nextInt();
				map.put(i, key);
				rocksDB.put(key.getBytes(), new byte[100000]);
			}


			Checkpoint checkpoint2 = Checkpoint.create(rocksDB);
			checkpoint2.createCheckpoint(rocksDBPathCheckpointBase + "/2");


			System.out.println("------------------2---------------------");
			File file2 = new File(rocksDBPathCheckpointBase + "/2");
			for (File f : file2.listFiles()) {
				System.out.println(f.getName());
			}



//			watch.reset();
//			watch.start();
//
//			for (int i = 0; i < 1000; i++) {
//				rocksDB.delete(map.get(i).getBytes());
//			}
//			System.out.println("1000 delete take " + watch.getTime() + " ms");

		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}
