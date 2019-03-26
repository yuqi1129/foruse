package com.aliababa.keplerhelp.util;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2018/11/2 下午3:53
 */

import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class HandleFile {

	public static void main(String[] args) {

		String fileName = "sql.txt";
		InputStream inputStream = null;
		try {
			inputStream = HandleFile.class.getClassLoader().getResourceAsStream("data/" + fileName);

			List<String> lines = IOUtils.readLines(inputStream);
			List<String> output = Lists.newArrayList();
//			for (String line : lines) {
//				if (line.trim().startsWith("##")) {
//					output.add(line);
//				}
//			}
			int i = 0;
			while (i < lines.size()) {
				if (lines.get(i).trim().startsWith("##")) {
					while(!lines.get(i).startsWith("#the baseline is")) {
						output.add(lines.get(i));
						i++;
					}
				}
				i++;
			}

			String path = HandleFile.class.getClassLoader().getResource("data/" + fileName).getPath();
			File f = new File(path);
			File p = f.getParentFile();

			//
			OutputStream outputStream = new FileOutputStream(new File(p.getAbsolutePath() + "/out.sql"));
			IOUtils.writeLines(output, "\n\n", outputStream);
			outputStream.close();

		} catch (Exception e) {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception e1) {
					//do ting
				}
			}
		}
	}
}
