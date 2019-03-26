package com.aliababa.keplerhelp.util;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2018/11/22 下午9:29
 */

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class TestUnsafe {
	public static void main(String[] args) {
		//
		try {
			Field f = Unsafe.class.getDeclaredField("theUnsafe");

			f.setAccessible(true);

			Unsafe unsafe = (Unsafe) f.get(null);

			System.out.println(unsafe.getInt(1));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
