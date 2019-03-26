package git.example.sync.thread;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/1/19 上午10:57
 */

import java.util.function.Supplier;

public class TestThreadAndRunnable {
	public static Object object = new Object();

	public static void main(String[] args) {
//		Seller seller1 = new Seller();
//		seller1.setName("seller1");
//
//		Seller seller2 = new Seller();
//		seller1.setName("seller2");
//
//		Seller seller3 = new Seller();
//		seller1.setName("seller3");
//
//
//		seller1.start();
//		seller2.start();
//		seller3.start();

		MySeller my = new MySeller();
		Thread mySeller1 = new Thread(my);
		mySeller1.setName("myseller1");

		Thread mySeller2 = new Thread(my);
		mySeller2.setName("myseller2");

		Thread mySeller3 = new Thread(my);
		mySeller3.setName("myseller3");


		mySeller1.start();
		mySeller2.start();
		mySeller3.start();

	}
}


class Seller extends Thread {
	//private int count = 10;

	private ThreadLocal<Integer> count = ThreadLocal.withInitial(new Supplier<Integer>() {
		@Override
		public Integer get() {
			return 10;
		}
	});


	@Override
	public void run() {
		for (int i = 0; i < count.get(); i++) {
			System.out.println(Thread.currentThread().getName() + " sell " + i);
		}
	}
}


class MySeller implements Runnable {
	//private int count = 10;

	private ThreadLocal<Integer> count = ThreadLocal.withInitial(new Supplier<Integer>() {
		@Override
		public Integer get() {
			return 10;
		}
	});


	@Override
	public void run() {
		for (int i = 0; i < count.get(); i++) {
			System.out.println(Thread.currentThread().getName() + " sell " + i);
		}
	}
}