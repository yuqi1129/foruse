package threadtest.sync;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/2/2 下午1:44
 */

import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {

	private static final List<Integer> products = Lists.newArrayList(20);
	private static final int MAX_SIZE = 20;

	private static Lock putLock = new ReentrantLock();
	private static Lock getLock = new ReentrantLock();
	private static Condition notFull = putLock.newCondition();
	private static Condition notEmpty = getLock.newCondition();

	public static void main(String[] args) {
		new Produce().start();
		new Consumer().start();

		try {
			Thread.currentThread().sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	static class Produce extends Thread {
		@Override
		public void run() {
			//super.run();
			int i = 0;
			int s = -1;
			while (i < 40) {
				try {
					putLock.lock();
					while (products.size() == 20) {
						notFull.await();
					}
					System.out.println("Add project " + i);
					products.add(i++);
					if ((s = products.size()) <= 20) {
						notFull.signal();
					}
				} catch (Exception e) {
					//dothing
				} finally {
					putLock.unlock();
				}

				if (s > 0) {
					signalNotEmpty();
				}
			}
		}
	}

	private static void signalNotEmpty() {

		try {
			getLock.lock();
			notEmpty.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			getLock.unlock();
		}

	}

	static class Consumer extends Thread {
		@Override
		public void run() {
			//super.run();
			int i = 0;
			while (i < 40) {
				try {
					getLock.lock();
					while (products.size() == 0) {
						notEmpty.await();
					}
					System.out.println("I Consume " + products.remove(products.size() - 1));

					if (products.size() < MAX_SIZE) {
						signalNotFull();
					}
				} catch (Exception e) {
					//dothing
				} finally {
					getLock.unlock();
				}
			}
		}
	}

	static void signalNotFull() {
		try {
			putLock.lock();
			notFull.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			putLock.unlock();
		}
	}

}
