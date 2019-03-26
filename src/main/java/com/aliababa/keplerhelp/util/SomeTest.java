package com.aliababa.keplerhelp.util;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2018/12/23 下午5:22
 */

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;

public class SomeTest {
	public static void main(String[] args) {
		List<Comparator<Test>> list = Lists.newArrayList();
		list.add((o1, o2) -> {
			return 1;
		});


		System.out.println(list.get(0));

		for (int i = 2; --i >= 0;) {
			System.out.println(i);
		}

	}

	public static class Test implements Comparator<Test> {
		private String name;
		private String age;

		@Override
		public int compare(Test o1, Test o2) {
			int res = o1.name.compareTo(o2.name);
			return res == 0 ? o1.age.compareTo(o2.age) : res;
		}
	}
}
