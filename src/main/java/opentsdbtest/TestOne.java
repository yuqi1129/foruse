package opentsdbtest;


import com.google.common.collect.Lists;

import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.sun.scenario.effect.Offset;

import common.Tailfile;

import org.apache.hadoop.hbase.ipc.Delayable;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpPost;

import org.apache.http.impl.client.CloseableHttpClient;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.DriverManager;
import java.sql.ResultSet;

import java.text.SimpleDateFormat;

import java.util.List;
import java.util.Map;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/17
 * Time: 22:33
 * To change this template use File | Settings | File Templates.
 */

public class TestOne {

    private static final Logger logger = LoggerFactory.getLogger(TestOne.class);

    public static void main(String [] args){


        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (Exception e){
            logger.error("can get driver: {}" ,e);
        }


        Connection connection;
        List<Tailfile>  list = Lists.newArrayList();
        OpenTsdb openTsdb = new OpenTsdb();


        TestOne testOne = new TestOne();


        /*
        //for qurey
        List<ResultClass> result = testOne.getQueryResult(openTsdb,"yidun_track_log",2,1476331200000L,null);
        //List<ResultClass> result = testOne.getQueryResult(openTsdb,"yidun_track_log",2,1476331200000L,1476388800000L);
        //List<ResultClass> result = testOne.getQueryResult(openTsdb,"yuqi_test_5032",2,1476331200000L,null);
        Long total = 0L;
        if (result == null || result.get(0) == null || result.get(0).getDps() == null){
            System.exit(-1);
        }
        for(Map.Entry<String,Integer> value : result.get(0).getDps().entrySet()){
            total += Integer.valueOf(value.getValue());
        }
        System.out.println(total);
        */

        //for store data
        testOne.storeData(openTsdb);

        /*
        Long now = System.currentTimeMillis() ;
        List<ReturnEntity>  returnEntities = new OpenTSDBQuery(openTsdb).getTagFlow("epay_access_online",2,1475164800000L,null);
        JSONObject jsonObject = new JSONObject() ;
        jsonObject.put("code",200);
        jsonObject.put("value",returnEntities);
        System.out.printf("use %d millseconds\n" , (System.currentTimeMillis() - now));
        System.out.println(jsonObject) ;
        */

    }


    public List<ResultClass> getQueryResult(OpenTsdb openTsdb,String metric,Integer type,Long startTime,Long endTime){
        return openTsdb.getDataByPost(metric,type,startTime,endTime);
    }

    public void storeData(OpenTsdb openTsdb){
        Connection connection ;
        List<Tailfile>  list = Lists.newArrayList();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            // this is online
             connection = (Connection)DriverManager.getConnection("jdbc:mysql://10.120.151.128:3306/datastream?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&&autoReconnect=true&failOverReadOnly=false&connectTimeout=600000&socketTimeout=600000","ds","8w?yXNgs;");
            //this is develop
            //connection = (Connection) DriverManager.getConnection("jdbc:mysql://10.120.153.171:3306/ds?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&&autoReconnect=true&failOverReadOnly=false&connectTimeout=600000&socketTimeout=600000", "ds_dev", "s]k51_(>R");

            Statement statement = (Statement) connection.createStatement();

            Long offset = 200010000L;
            //on line
            //String sql = "select id,host_name,count,tag_name,type,modify_time from tb_monitor_tag_tailfile where tag_name = 'yidun_track_log' limit 20000 offset 0";
            String sql = "select file_name,host_name,count,tag_name,type,start_time,file_inode from tb_monitor_tag_tailfile  limit 10000 offset 200010000";
            //dev
            //String sql = "select id,host_name,count,tag_name,type,modify_time from tb_monitor_tag_tailfile limit 20000 offset 0";
            ResultSet res = statement.executeQuery(sql);

            res.last();
            while (res.getRow() != 0) {

                res.first();
                System.out.println("query complete,offset: " + offset);
                do{
                    //
                    Tailfile tailfile = new Tailfile();
                    tailfile.setCount(res.getLong(3));
                    tailfile.setHostname(res.getString(2));

                    if (tailfile.getHostname() == null || tailfile.getHostname().trim().equals("")){
                        tailfile.setHostname("unknow");
                    }

                    tailfile.setFile(res.getString(1).toString() + "__" + new Long(res.getLong(7)).toString());

                    tailfile.setType(res.getInt(5));

                    tailfile.setTagName(res.getString(4));

                    tailfile.setTimeStamp(format.parse(res.getString(6)).getTime());

                    list.add(tailfile);
                    if (list.size() == 20) {
                        //System.out.println("insert into opentsdb");

                        openTsdb.putDataByPost(list);
                        logger.info("we put " + list.size() + " data in it ");
                        list.clear();
                    }

                    if (res.getRow() % 1000 == 0){
                        System.out.println("complete 1000 rows");
                    }
                }while(res.next());


                if (list.size() != 0) {
                    //System.out.println("insert into opentsdb");
                    openTsdb.putDataByPost(list);
                    logger.info("we put " + list.size() + " data in it ");
                    list.clear();
                }



                // this if for dev


                offset += 10000;
                System.out.println("offset:" + offset);
                sql = "select file_name,host_name,count,tag_name,type,modify_time,file_inode from tb_monitor_tag_tailfile  limit 10000 offset " + offset.toString();
                res = statement.executeQuery(sql);
                res.last();

                //System.out.println("get = " + res.getRow());
            }
        }catch (Exception e){
            logger.error("get error: {}",e);
        }

    }

}
