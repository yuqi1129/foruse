package hbasetest.testone;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;

import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutorService;


/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/4
 * Time: 10:15
 * To change this template use File | Settings | File Templates.
 */

public class TestOne {

    private static final Logger logger = LoggerFactory.getLogger(TestOne.class);
    private static HBaseAdmin hBaseAdmin;
    private static Configuration configuration;

    static {
        try{
            configuration = HBaseConfiguration.create();
            configuration.set("hbase.zookeeper.quorum","127.0.0.1");
            configuration.set("hbase.zookeeper.property.clientPort", "2182");

            System.out.println("before");
            hBaseAdmin = new HBaseAdmin(configuration);
            System.out.println("hBaseAdmin:" + hBaseAdmin.getConfiguration().get("hbase.zookeeper.quorum"));
        }catch (Exception e){
            System.out.println(e);
            //logger.error("get error {}",e);
        }
    }

    private static String rowKey = "rowKey";

    public static void main(String [] args){

        /*
        try {
            Configuration configuration = HBaseConfiguration.create();
            //configuration.addResource("conf.properties"); //只能add xml 文件
            configuration.set("hbase.zookeeper.quorum", "inspur251.photo.163.org,inspur252.photo.163.org,inspur253.photo.163.org,inspur254.photo.163.org,inspur255.photo.163.org");

            HBaseAdmin admin = new HBaseAdmin(configuration);

            HTableDescriptor tableDescriptor = new HTableDescriptor("test1");

            tableDescriptor.addFamily(new HColumnDescriptor("personal"));
            tableDescriptor.addFamily(new HColumnDescriptor("professional"));
            admin.createTable(tableDescriptor);
            System.out.println("create tabe success!!");
        }catch (MasterNotRunningException ee){
            System.out.print("get error: " + ee);
            logger.error("get error: {}",ee);
        }catch (Exception e){
            logger.error("get error: {}",e);
        }*/

        /*
        String [] columns = new String[]{"age","name"};
        try {
            createTable("nice", columns);
        }catch (Exception e){
            System.out.println(e);
            logger.error("get error {}",e);
        }*/

        System.out.println("Hello,world!");
        try {
            HTable hTable = new HTable(configuration, "people");
            System.out.println("1");

            System.out.println(hTable.getTableDescriptor());
            HColumnDescriptor [] hColumnDescriptors = hTable.getTableDescriptor().getColumnFamilies();
            System.out.println("1");
            for (int i= 51 ; i< 60 ;i++){
                Put put = new Put(String.valueOf(i).getBytes());
                for (HColumnDescriptor hColumnDescriptor: hColumnDescriptors){
                    put.addColumn(hColumnDescriptor.getName(),"test".getBytes(),String.valueOf(i).getBytes());
                }

                hTable.put(put);
                System.out.println("put " + i  + " succeed");
            }
            System.out.println("3");
            Get get = new Get("1".getBytes());
            get.addColumn("name".getBytes(),"test".getBytes());
            Result result = hTable.get(get);
            System.out.println(result.getMap());


            System.out.println("row [0] = " + String.valueOf(result.getRow().toString()));

            for (Cell cell :result.rawCells()){
                byte [] value = cell.getValueArray();
                byte [] family = cell.getFamilyArray();
                byte [] quailfer = cell.getQualifierArray();

                for (byte b : value){
                    System.out.println("value:" + String.valueOf(b));
                }

                for (byte b : family){
                    System.out.println("family:" + String.valueOf(b));
                }

                for (byte b : quailfer){
                    System.out.println("quailfer:" + String.valueOf(b));
                }



            }




        }catch (Exception e){
            System.out.println(e);
        }




    }


    private static void createTable(String tableName,String [] columns) throws IOException{
        System.out.println("iii");
        dropTable(tableName);
        System.out.println("222");
        HTableDescriptor hTableDescriptor = new HTableDescriptor(tableName);

        for (String column : columns){
            HColumnDescriptor columnDescriptor = new HColumnDescriptor(column);
            hTableDescriptor.addFamily(columnDescriptor);
        }

        System.out.println("3");

        hBaseAdmin.createTable(hTableDescriptor);
        System.out.println("4");
        System.out.println("create table success!");

    }


    private static void dropTable(String tableName){
        try {
            if (hBaseAdmin.tableExists(tableName)) {
                System.out.println("table exist");
                hBaseAdmin.disableTable(tableName);
                hBaseAdmin.deleteTable(tableName);
            }
        }catch (Exception e){
            logger.error("get error {}",e);
        }
    }


    public static HTable  getTable(String tableName) throws IOException{
        return new HTable(configuration,tableName);
    }



    public static void insert(String tableName, Map<String, String >map) throws Exception{
        HTable hTable = getTable(tableName);
        byte [] row1 = Bytes.toBytes(rowKey);

        Put put = new Put(row1);
        for (String name : map.keySet()){
            byte [] value = Bytes.toBytes(map.get(name));

            String [] col = name.split(":");

            if (col.length == 2){
                byte [] family = col[0].getBytes();
                byte []  qualifier = col[1].getBytes();
                put.addColumn(family,qualifier,value);
            }
        }

        hTable.put(put);

        Get get = new Get(rowKey.getBytes());
        Result result = hTable.get(get);

        System.out.println(get);
    }

    private static void createtable(String name){
        try {
           ///
        }catch (Exception e){
            e.printStackTrace();
        }
        // void 
    }


}
