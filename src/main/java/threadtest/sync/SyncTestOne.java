package threadtest.sync;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2018/11/6 上午9:40
 */

import java.util.Random;

public class SyncTestOne {

	public static void main(String[] args) {
		T t = new T();
		T t2 = new T();

		Thread thread1 = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				t.inc(1);
				System.out.println(t.getA());
			}
		});

		Thread thread2 = new Thread(() -> {
			for (int i = 0; i < 10; i++) {
				t2.inc(10000);
				System.out.println(t2.getA());
			}
		});

		thread1.start();
		thread2.start();

	}



	static class T {
		private int a = 0;
		private static final Random r = new Random();


		public int getA() {
			return a;
		}

		public synchronized void inc(int num) {
			try {
				Thread.sleep(r.nextInt(1000));
				a += num;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
