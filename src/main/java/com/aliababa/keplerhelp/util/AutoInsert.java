package com.aliababa.keplerhelp.util;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/1/15 下午8:19
 */

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.mockito.internal.util.collections.Sets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AutoInsert {
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url="jdbc:mysql://";
	private static final String nanxian_url = "jdbc:mysql://";

	private static final String userName = "";
	private static final String passwd = "store_sales";

	private static final String tableName = "T";
	private static final Set<String> needCommaSet = Sets.newSet(
			"varchar",
			"multivalue",
			"date",
			"time",
			"timestamp"
	);

	private static final int BATCH_SIZE = 500;

	private static final int INSERT_SIZE = 30000;

	public static void main(String[] args) {
		try {

			Map<String, String> columnTypeMap = getTableType(tableName);
			Map<Integer, Boolean> shouldUseComma = Maps.newHashMap();

			String insertPrefix = "insert into " + tableName + " values ";
			List<String> insertLists = Lists.newArrayList();
			Connection connection = getConnection();

			int total = 0;
			for (int i = INSERT_SIZE; i < INSERT_SIZE * 10; i++) {

				StringBuilder builder = new StringBuilder();
				builder.append("(" + i + ", \'" + i * i + "\'" + ", " + i + 1 + ")");
				insertLists.add(builder.toString());

				if (insertLists.size() % BATCH_SIZE == 0 || i == INSERT_SIZE * 10 - 1) {
					if (insertLists.size() == 0) {
						continue;
					}

					StringBuilder sb = new StringBuilder(insertPrefix);
					for (int j = 0; j < insertLists.size(); j++) {
						sb.append(insertLists.get(j));
						if (j != insertLists.size() - 1) {
							sb.append(",");
						}
					}

					sb.append(";");

					PreparedStatement p = connection.prepareStatement(sb.toString());
					p.executeUpdate();
					//connection.commit();
					System.out.println("flush " +  insertLists.size() + " success!");
					total += insertLists.size();
					System.out.println("total insert = " + total);
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
