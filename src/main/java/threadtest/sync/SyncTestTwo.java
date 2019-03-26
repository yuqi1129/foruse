package threadtest.sync;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/2/2 上午10:09
 */

import java.util.concurrent.locks.LockSupport;

public class SyncTestTwo {


	public static void main(String[] args) {

		Thread current = Thread.currentThread();
		ChildThread childThread = new ChildThread(current);

		childThread.start();
		System.out.println("Start to print in parent");
		LockSupport.park();
		System.out.println("end to print in parent");

	}


	static class ChildThread extends Thread {
		private Thread parent;

		public ChildThread(Thread t){
			super();
			this.parent = t;
		}

		public ChildThread() {
		}

		@Override
		public void run() {
			System.out.println("Start to print in child");
			for (int i = 0; i < 10; i++) {
				System.out.println(i);

				try {
					Thread.currentThread().sleep(500);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			System.out.println("End to print in child, unpark parent");
			LockSupport.unpark(parent);
		}
	}

}
