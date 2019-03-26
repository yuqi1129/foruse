package com.aliababa.keplerhelp.util;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2018/12/28 下午5:29
 */

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;

import java.io.StringReader;
import java.util.Arrays;

public class TestCSV {
	public static void main(String[] args) {
		TestCSVParser testCSVParser = new TestCSVParser(
				",".charAt(0),
				"\"".charAt(0),
				"\\".charAt(0),
				false,
				true,
				false,
				CSVReaderNullFieldIndicator.EMPTY_SEPARATORS
		);

		String line = "\"Eureka Recycled Copy Paper 8 1/2\"\" x 11\"\", Ream\",REGULAR SHIPPING";
		CSVReader csvParser = new CSVReaderBuilder(new StringReader(line)).withCSVParser(testCSVParser).build();

		String[] data = null;
		try {
			data = csvParser.readNext();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (data != null) {
			Arrays.stream(data).forEach(a -> {
				System.out.println(a);
			});
		}
	}
}
