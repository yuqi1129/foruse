package com.aliababa.keplerhelp.util;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2018/12/28 下午3:37
 */

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import org.apache.commons.io.IOUtils;
import org.mockito.internal.util.collections.Sets;

import java.io.FileWriter;
import java.io.InputStream;
import java.io.StringReader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CsvInsert {

	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url="jdbc:mysql://";
	private static final String url_mac ="jdbc:mysql://";
	private static final String nanxian_url = "jdbc:mysql://";

	private static final String userName = "";
	private static final String passwd = "store_sales";
	private static final String userNameMac = "root";

	//Batters,Calcs
	private static final String tableName = "Staples";//"Calcs", "Staples";
	private static final Set<String> needCommaSet = Sets.newSet(
			"varchar",
			"multivalue",
			"date",
			"time",
			"timestamp"
	);

	private static final int BATCH_SIZE = 100;

	public static void main(String[] args) {

		String currentLines = null;
		int currentLineNumber = 0;
		Writer writer1 = null;
		Writer writer2 = null;
		try {

			Map<String, String> columnTypeMap = getTableType(tableName);
			//String t1 = "asset_dump_shop_sku.txt";
			String in = tableName + ".csv";
			//String t2 = "asset_dump_shop_sku_out.txt";
			String out = tableName + "_out.txt";
			InputStream inputStream = CsvInsert.class.getClassLoader().getResourceAsStream("file/" + in);
			writer1 = new FileWriter("/Users/yuqi/project/foruse/src/main/resources/error/lines.txt", true);
			writer2 = new FileWriter("/Users/yuqi/project/foruse/src/main/resources/error/number.txt", true);

			List<String> lines = IOUtils.readLines(inputStream);
			Map<Integer, Boolean> shouldUseComma = Maps.newHashMap();

			String insertPrefix = "insert into " + tableName + " values ";
			List<String> insertLists = Lists.newArrayList();
			Connection connection = getConnection();


			TestCSVParser testCSVParser = new TestCSVParser(
				",".charAt(0),
				"\"".charAt(0),
				"\\".charAt(0),
				false,
					true,
					false,
					CSVReaderNullFieldIndicator.EMPTY_SEPARATORS
			);



			String[] array = getColumnFromFile();
			int totalLines = 0;


			for (String line : lines) {
				//first line is meta

				currentLineNumber = lines.indexOf(line);

				CSVReader csvParser = new CSVReaderBuilder(new StringReader(line)).withCSVParser(testCSVParser).build();
				String[] data = csvParser.readNext();


				if (lines.indexOf(line) == 0) {
					for (int i = 0; i < array.length; i++) {

						String columnName = columnTypeMap.get(array[i].toUpperCase());
						if (columnName.contains("(")) {
							columnName = columnName.substring(0, columnName.indexOf("("));
						}

						if (needCommaSet.contains(columnName)) {
							shouldUseComma.put(i, true);
						} else {
							shouldUseComma.put(i, false);
						}
					}
				}

				if (array.length != shouldUseComma.size()) {
					continue;
				}

				//String[] data = line.split(",");
				StringBuilder builder = new StringBuilder("(");

				for (int i = 0; i < data.length; i++) {

					if (data[i] == null) {
						builder.append("NULL");
					} else if (shouldUseComma.get(i)) {
						builder.append("\"");

						if (data[i].contains("\"")) {
							data[i] = data[i].replace("\"", "");
						}

						if (columnTypeMap.get(array[i].toUpperCase()).contains("timestamp")) {
							if (data[i].compareTo("1970-01-01 00:00:00") < 0) {
								data[i] = "2001-01-01 00:00:00";
							}
						}
						builder.append(data[i]);
						builder.append("\"");
					} else {
						builder.append(data[i]);
					}

					if (i != data.length - 1) {
						builder.append(",");
					}
				}




				builder.append(")");

				insertLists.add(builder.toString());
				if (insertLists.size() % BATCH_SIZE == 0 || lines.indexOf(line) == lines.size() - 1) {
					if (insertLists.size() == 0) {
						continue;
					}

					StringBuilder sb = new StringBuilder(insertPrefix);
					for (int i = 0; i < insertLists.size(); i++) {
						sb.append(insertLists.get(i));
						if (i != insertLists.size() - 1) {
							sb.append(",");
						}
					}

					sb.append(";");
					currentLines = sb.toString();

					try {
						PreparedStatement p = connection.prepareStatement(sb.toString());
						p.executeUpdate();
						//connection.commit();
						totalLines += insertLists.size();
						System.out.println("flush " + insertLists.size() + " success!");
						insertLists.clear();
					} catch (Exception e) {
						System.out.println(currentLines);
						System.out.println(currentLineNumber);

//						try {
//							if (writer1 != null) {
//								IOUtils.write(currentLines + "\n", writer1);
//							}
//
//							if (writer2 != null) {
//								IOUtils.write(currentLineNumber + ",", writer2);
//							}
//
//						} catch (Exception e1) {
//							e.printStackTrace();
//						}
						e.printStackTrace();
						System.exit(-1);
					}
				}

			}

			System.out.println("total line = " + totalLines);
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static Map<String, String> getTableType (String tableName) {

		Map<String, String> map = Maps.newHashMap();
		Connection connection = null;
		try {
			connection = getConnection();
			PreparedStatement s = connection.prepareStatement("desc " + tableName + ";");

			ResultSet resultSet = s.executeQuery();
			while(resultSet.next()) {
				String columnName = resultSet.getString(1);
				String type = resultSet.getString(2);

				map.put(columnName.toUpperCase(), type);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return map;
	}

	public static final Connection getConnection() throws Exception {
		Class.forName(driver);
		//return DriverManager.getConnection(url_mac, userNameMac, null); //
		return DriverManager.getConnection(url, userName, passwd); //
	}

	private static String[] getColumnFromFile() {
		List<String> list = Lists.newArrayList();

		try {
			InputStream inputStream = CsvInsert.class.getClassLoader().getResourceAsStream("sql/" + tableName + ".sql");
			List<String> content = IOUtils.readLines(inputStream);


			content.forEach(a -> {
				String[] s = a.trim().split("`");
				if (s.length >= 2 && !a.trim().contains("distribute")) {
					list.add(s[1].trim());
				}
			});

			return list.toArray(new String[list.size()]);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}




}
