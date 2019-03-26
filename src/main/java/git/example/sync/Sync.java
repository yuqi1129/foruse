package git.example.sync;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/1/19 上午12:47
 */


import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Sync {

	//
	public static final Object object = new Object();

	private static LinkedBlockingQueue<Integer> queue = new LinkedBlockingQueue<>();
	public static void main(String[] args) {

		new ThreadOne().start();
		new ThreadTwo().start();


		//java 基础， 需要好好看一下
		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
		scheduledExecutorService.scheduleAtFixedRate(new Consumer(),0, 5000, TimeUnit.MILLISECONDS);
		scheduledExecutorService.scheduleAtFixedRate(new Consumer(),0, 5000, TimeUnit.MILLISECONDS);

		//java 基础
		//线程 同步
		//jvm
		//否则

	}


	public static class ThreadOne extends Thread {
		@Override
		public void run() {

			synchronized (object) {
				try {
					object.wait();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}


	public static class ThreadTwo extends Thread {
		@Override
		public void run() {

			try {
				Thread.currentThread().sleep(10000);
			} catch (Exception e) {
				e.printStackTrace();
			}


			synchronized (object) {
				queue.add(1);
				object.notifyAll();
			}
		}
	}


	public static class Consumer extends Thread {
		@Override
		public void run() {
			synchronized (queue) {
				while (queue.isEmpty()) {
					try {
						queue.wait(1000);
						System.out.println("I wait 1000ms, and not element");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				try {
					System.out.println(queue.take());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}


	public static class Producer extends Thread  {
		@Override
		public void run() {
			synchronized (queue) {
				try {
					queue.put(1);
					queue.notifyAll();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

}
