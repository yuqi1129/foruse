package com.aliababa.keplerhelp.util;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/1/2 下午9:37
 */

import com.google.common.collect.Maps;
import org.apache.commons.io.IOUtils;
import org.apache.flink.api.java.tuple.Tuple2;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class HandleExcel {
	public static void main(String[] args) {
		try {

			InputStream inputStream = HandleExcel.class.getClassLoader().getResourceAsStream("data/data.txt");

			List<String> list = IOUtils.readLines(inputStream);

			Map<String, Tuple2<Integer, Integer>> map = Maps.newHashMap();
			for (String s : list) {
				String[] data = s.trim().split("\t");
				String key = data[0].substring(0, data[0].indexOf(".") == -1 ? data[0].length() : data[0].indexOf("."));
				Tuple2<Integer, Integer> value = map.get(key);
				if (value == null) {
					Tuple2<Integer, Integer> tmp = new Tuple2<>();
					tmp.f0 = 1;
					tmp.f1 = Integer.valueOf(data[1]);

					map.put(key, tmp);
				} else {
					value.f0 = value.f0 + 1;
					value.f1 = value.f1 + Integer.valueOf(data[1]);
				}
			}

			int total = 0;
			int pass = 0;
			for (Map.Entry<String, Tuple2<Integer, Integer>> entry : map.entrySet()) {
				total += entry.getValue().f0;
				pass += entry.getValue().f1;
				System.out.println(entry.getKey() + " = " + 1.0 * entry.getValue().f1 / entry.getValue().f0);
			}

			System.out.println("total passed = " + 1.0 * pass / total);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
