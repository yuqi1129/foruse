package com.aliababa.keplerhelp.util;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/1/31 下午4:04
 */

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.Writer;
import java.util.List;

public class HandlerKeplerList {
	public static void main(String[] args) {

		try {
			InputStream inputStream = HandleMachineList.class.getClassLoader().getResourceAsStream("kepler/machine.list");

			List<String> stringList = IOUtils.readLines(inputStream);


			Writer writer = new FileWriter("/Users/yuqi/project/foruse/src/main/resources/kepler/result.txt", true);


			String ip = null;
			String hostInstance;
			for (int i = 0; i < stringList.size(); i++) {
				if (i % 3 == 0) {
					ip = stringList.get(i).split(":")[0];
				} else if (i % 3 == 2) {
					String tmp = stringList.get(i).split(",")[0];

					int tabTimes = StringUtils.countMatches(tmp, "\t");

					if (tabTimes == 15) {
						hostInstance = tmp.split("\t")[1];
						IOUtils.write(ip + " " + hostInstance + "\n", writer);
						continue;
					}


					int k = tmp.length() - 1;
					while (k >= 0 && tmp.charAt(k) != ' ') {
						k--;
					}

					int m = k - 1;

					while (m >= 0 && tmp.charAt(m) != ' ') {
						m--;
					}

					hostInstance = tmp.substring(m + 1, k);

					IOUtils.write(ip + " " + hostInstance + "\n", writer);
				}
			}
			writer.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}
	}
}
