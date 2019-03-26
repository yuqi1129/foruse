package com.aliababa.keplerhelp.util;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2019/1/28 下午7:44
 */

import org.apache.flink.shaded.guava18.com.google.common.collect.Maps;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Types;
import java.util.Map;

public class ConnectToMySql {

	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url="jdbc:mysql://";
	private static final String userName = "";
	private static final String passwd = "passwd";

	public static void main(String[] args) {
		try {
			Class.forName(driver);

			Connection connection = DriverManager.getConnection(url, userName, passwd);

			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select `minute`,\n" +
					"  imp_bidfloor_0,\n" +
					"  rate,\n" +
					"  bid_request_id,\n" +
					"  imp_tagid_0,\n" +
					"  bid_succ_price,\n" +
					"  creative_id,\n" +
					"  creative_appid,\n" +
					"  creative_type,\n" +
					"  creative_gid,\n" +
					"  imp_id_0,\n" +
					"  creative_pid,\n" +
					"  dev_ua,\n" +
					"  creative_soid,\n" +
					"  dev_os,\n" +
					"  creative_oid,\n" +
					"  dev_ip,\n" +
					"  creative_supplierid,\n" +
					"  dev_idfa_md5,\n" +
					"  creative_isdownload,\n" +
					"  dev_imei_md5,\n" +
					"  creative_price,\n" +
					"  searchkey,\n" +
					"  log_type,\n" +
					"  business_type,\n" +
					"  dev_client_ip,\n" +
					"  ts,\n" +
					"  app_id,\n" +
					"  bid_channel from klbs_dsp_track_log");

			Field[]  fields = Types.class.getDeclaredFields();


			ResultSetMetaData data= rs.getMetaData();
			while(rs.next()) {
				for (int i = 1; i <= data.getColumnCount(); i++) {
//获得所有列的数目及实际列数
					int columnCount = data.getColumnCount();
//获得指定列的列名
					String columnName = data.getColumnName(i);
//获得指定列的列值		i

					String columnValue = rs.getString(i);
					if (i == 6) {
						System.out.println(columnValue);
					}
//获得指定列的数据类型
					int columnType = data.getColumnType(i);
//获得指定列的数据类型名
					String columnTypeName = data.getColumnTypeName(i);
//所在的Catalog名字
					String catalogName = data.getCatalogName(i);
//对应数据类型的类
					String columnClassName = data.getColumnClassName(i);
//在数据库中类型的最大字符个数
//默认的列的标题
					String columnLabel = data.getColumnLabel(i);
//获得列的模式
					String schemaName = data.getSchemaName(i);
//小数点后的位数
					int scale = data.getScale(i);
//获取某列对应的表名
					String tableName = data.getTableName(i);
// 是否自动递增
					boolean isAutoInctement = data.isAutoIncrement(i);
//在数据库中是否为货币型
					boolean isCurrency = data.isCurrency(i);
//是否为空
					int isNullable = data.isNullable(i);
//是否为只读
					boolean isReadOnly = data.isReadOnly(i);
//能否出现在where中
					boolean isSearchable = data.isSearchable(i);
					System.out.println(columnCount);
					System.out.println("获得列" + i + "的字段名称:" + columnName);
					System.out.println("获得列" + i + "的字段值:" + columnValue);
					//System.out.println("获得列" + i + "的类型,返回SqlType中的编号:" + columnType);
					System.out.println("获得列" + i + "的数据类型名:" + columnTypeName);
					//System.out.println("获得列" + i + "所在的Catalog名字:" + catalogName);
					//System.out.println("获得列" + i + "对应数据类型的类:" + columnClassName);
					//System.out.println("获得列" + i + "的默认的列的标题:" + columnLabel);
					//System.out.println("获得列" + i + "的模式:" + schemaName);
					//System.out.println("获得列" + i + "小数点后的位数:" + scale);
					//System.out.println("获得列" + i + "对应的表名:" + tableName);
					//System.out.println("获得列" + i + "是否自动递增:" + isAutoInctement);
					//System.out.println("获得列" + i + "在数据库中是否为货币型:" + isCurrency);
					//System.out.println("获得列" + i + "是否为空:" + isNullable);
					//System.out.println("获得列" + i + "是否为只读:" + isReadOnly);
					//System.out.println("获得列" + i + "能否出现在where中:" + isSearchable);
				}
			}


			Map<String, String> map = Maps.newHashMap();


		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
