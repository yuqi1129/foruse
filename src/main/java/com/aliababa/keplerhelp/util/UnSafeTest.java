package com.aliababa.keplerhelp.util;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2018/12/23 下午1:38
 */

import org.apache.commons.lang.time.StopWatch;
import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;

public class UnSafeTest {
	public static void main(String[] args) {

		try {
			Constructor<Unsafe> constructor = Unsafe.class.getDeclaredConstructor();

			constructor.setAccessible(true);
			User user = new User();

			Unsafe unsafe = constructor.newInstance(null);

			StopWatch stopWatch = new StopWatch();
			stopWatch.start();


			for (int i = 0; i < 100_000_000; i++) {
				user.setAge(i);
			}
			System.out.println("use set take: " + stopWatch.getTime() + " ms");

			stopWatch.reset();
			stopWatch.start();
			Field field = User.class.getDeclaredField("age");

			long offset = unsafe.objectFieldOffset(field);

			for (int i = 0; i < 100_000_000; i++) {
				unsafe.putInt(user, offset, i);
			}

			System.out.println("use unsafe take: " + stopWatch.getTime() + "ms");

			String[] a = new String[] {"a", "b", "c"};
			System.out.println(Arrays.toString(a));


			System.out.println(8196 << 11);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}



	private static class User {
		private int age;
		private String name;


		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}
	}
}
