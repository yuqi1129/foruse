package com.aliababa.keplerhelp.util;
/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2018/8/27 上午9:39
 */

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.List;

public class SqlCreate {
	public static void main(String[] args) {
		String oriSqlFile = "/Users/yuqi/test.sql";
		StringBuilder builder = new StringBuilder();

		try {
			List<String> lines = FileUtils.readLines(new File(oriSqlFile));

			for (String line : lines) {
				builder.append(line.trim());
			}
			FileUtils.write(new File("/Users/yuqi/result.sql"), builder.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
