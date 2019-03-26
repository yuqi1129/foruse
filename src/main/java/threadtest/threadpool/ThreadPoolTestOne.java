package threadtest.threadpool;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/1/29 下午7:47
 */

import org.apache.flink.shaded.guava18.com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolTestOne {
	public static void main(String[] args) {

		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
				2,
				4,
				60,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue<>(30),
				new ThreadFactoryBuilder().setNameFormat("%d").build(),
				new ThreadPoolExecutor.DiscardPolicy()
		);


		threadPoolExecutor.execute(() -> {
			System.out.println("hello");
		});




	}

}
