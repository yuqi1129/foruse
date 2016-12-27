package opentsdbtest;


import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import com.sun.scenario.effect.Offset;

import common.Tailfile;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpPost;

import org.apache.http.impl.client.CloseableHttpClient;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.text.SimpleDateFormat;

import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/17
 * Time: 22:33
 * To change this template use File | Settings | File Templates.
 */

public class TestOne {

    private static final Logger logger = LoggerFactory.getLogger(TestOne.class);
    private static final Integer TAILFILE = 1;
    private static final Integer DATAROUTE = 2;
    private static final Integer DATAHANDLER = 3;

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



        testOne.storeData(openTsdb,TAILFILE);
        testOne.storeData(openTsdb,DATAROUTE);
        testOne.storeData(openTsdb,DATAHANDLER);
        //testOne.getQueryResult()


    }


    public List<ResultClass> getQueryResult(OpenTsdb openTsdb,String metric,Integer type,Long startTime,Long endTime){
        return openTsdb.getDataByPost(metric,type,startTime,endTime);
    }

    public void storeData(OpenTsdb openTsdb,Integer flag){

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4,4,1000, TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(1000), new ThreadFactoryBuilder().setNameFormat("send-thread").build()
                ,new ThreadPoolExecutor.AbortPolicy());

        try{

            //threadPoolExecutor.submit(new StoreThread(0L,10000000L,openTsdb,flag));
            //threadPoolExecutor.submit(new StoreThread(17140000L,20000000L,openTsdb,flag));
            //threadPoolExecutor.submit(new StoreThread(25280000L,30000000L,openTsdb,flag));

            threadPoolExecutor.submit(new StoreThread(0L,50000L,openTsdb,flag));
            threadPoolExecutor.submit(new StoreThread(60000L,100000L,openTsdb,flag));
            threadPoolExecutor.submit(new StoreThread(110000L,150000L,openTsdb,flag));

            //threadPoolExecutor.submit(new StoreThread(0L,20000L,openTsdb,flag));
            //threadPoolExecutor.submit(new StoreThread(30000L,50000L,openTsdb,flag));
            //threadPoolExecutor.submit(new StoreThread(60000L,80000L,openTsdb,flag));

            System.out.println(Thread.currentThread());
        }catch (Exception e){
            logger.error("get error: {}",e);
        }

        threadPoolExecutor.shutdown();

    }

}
