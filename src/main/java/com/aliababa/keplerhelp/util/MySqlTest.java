package com.aliababa.keplerhelp.util;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2018/11/23 上午10:18
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MySqlTest {
	private static final String userName = "";
	private static final String passwd = "store_sales";
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String table_name = "t";
	private static final int insert_size = 50;

	private static final String url="jdbc:mysql://";

	public static void main(String[] args) {

		try {
			Connection connection = getConnection();

			StringBuilder builder = new StringBuilder();

			builder.append("insert into ").append(table_name).append(" values ");
			for (int i = 0; i < insert_size; i++) {
				builder.append("(1" + i + ")");
				if (i != insert_size - 1) {
					builder.append(",");
				}
			}


			PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());

			preparedStatement.executeUpdate();
			connection.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}





	private static final Connection getConnection() throws Exception {
		Class.forName(driver);
		return DriverManager.getConnection(url, userName, passwd); //
	}
}
