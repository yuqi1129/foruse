package com.aliababa.keplerhelp.util;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2018/10/19 下午4:24
 */

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.io.IOUtils;
import org.mockito.internal.util.collections.Sets;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MakeSqlInsert {
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url="jdbc:mysql://";
	private static final String nanxian_url = "jdbc:mysql://";

	private static final String userName = "";
	private static final String passwd = "store_sales";

	private static final String tableName = "Batters";
	private static final Set<String> needCommaSet = Sets.newSet(
			"varchar",
			"multivalue",
			"date",
			"time",
			"timestamp"
	);

	private static final int BATCH_SIZE = 500;

	public static void main(String[] args) {
		try {

			Map<String, String> columnTypeMap = getTableType(tableName);
			//String t1 = "asset_dump_shop_sku.txt";
			String in = tableName + ".txt";
			//String t2 = "asset_dump_shop_sku_out.txt";
			String out = tableName + "_out.txt";
			InputStream inputStream = MakeSqlInsert.class.getClassLoader().getResourceAsStream("file/" + in);

			List<String> lines = IOUtils.readLines(inputStream);
			Map<Integer, Boolean> shouldUseComma = Maps.newHashMap();

			String insertPrefix = "insert into " + tableName + " values ";
			List<String> insertLists = Lists.newArrayList();
			Connection connection = getConnection();
			for (String line : lines) {
				//first line is meta

				//-----------------------------------------------
				// normal sql file use this
				String[] array = line.split("\t");
				//------------------------------------------------

				// csv use this
				//String[] array = line.split(",");


				if (lines.indexOf(line) == 0) {
					for (int i = 0; i < array.length; i++) {
						if (needCommaSet.contains(columnTypeMap.get(array[i].toUpperCase()))) {
							shouldUseComma.put(i, true);
						} else {
							shouldUseComma.put(i, false);
						}
					}

					continue;
				}

				if (array.length != shouldUseComma.size()) {
					continue;
				}

				StringBuilder builder = new StringBuilder("(");
				for (int i = 0; i < array.length; i++) {
					if (shouldUseComma.get(i) && !"NULL".equalsIgnoreCase(array[i])) {
						builder.append("\"");
						builder.append(array[i]);
						builder.append("\"");
					} else {
						builder.append(array[i]);
					}

					if (i != array.length - 1) {
						builder.append(",");
					}
				}
				builder.append(")");

				insertLists.add(builder.toString());
				if (insertLists.size() % 100 == 0 || lines.indexOf(line) == lines.size() - 1) {
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

					PreparedStatement p = connection.prepareStatement(sb.toString());
					p.executeUpdate();
					//connection.commit();
					System.out.println("flush " +  BATCH_SIZE + " success!");
					insertLists.clear();
				}

			}
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
		return DriverManager.getConnection(url, userName, passwd); //
	}
}
