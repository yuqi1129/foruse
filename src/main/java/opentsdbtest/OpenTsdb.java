package opentsdbtest;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.regexp.internal.RE;

import common.Tailfile;

import org.apache.avro.data.Json;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.Date;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/18
 * Time: 20:14
 * To change this template use File | Settings | File Templates.
 */

public class OpenTsdb implements Tsdb{

    private static final Logger logger = LoggerFactory.getLogger(OpenTsdb.class);

    private static HttpClient httpClient = HttpClients.createDefault();

    private static HttpPost httpPost = new HttpPost("http://app-68.photo.163.org:20000/api/put");

    private static HttpPost httpPost1 = new HttpPost("http://app-68.photo.163.org:20000/api/query");

    public List<ResultClass>  getDataByGet(String metric,Integer type,Long startTime,Long endTime){
        return null;
    }

    public  List<ResultClass> getDataByPost(String metric,Integer type,Long startTime,Long endTime){

        String string = null ;

        QueryClass queryClass = new QueryClass() ;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
        Date date = new Date(startTime);

        queryClass.setStart(simpleDateFormat.format(date));
        if (endTime == null){
            queryClass.setEnd(null );
        }else{
            queryClass.setEnd(simpleDateFormat.format(new Date(endTime)));
        }

        List<Query> queryList = Lists.newArrayList();
        Query query = new Query();
        query.setMetric(metric);
        query.setAggregator("sum");

        if (queryClass.getEnd() == null){
            query.setDownsample("1d-sum");
        }else{
            Long intervalHours = (endTime - startTime ) / 3600000;
            if (intervalHours > 24){
                Long interval =  intervalHours / 24 ;
            }else{
                Integer i = 10 ;
                while(i * 24  < intervalHours * 60){
                    i += 10 ;
                }
                query.setDownsample(i.toString() + "m-sum");
            }
        }



        Map<String,String> tags = Maps.newHashMap();
        tags.put("type",type.toString());
        query.setTags(tags);
        queryList.add(query);

        queryClass.setQueries(queryList);

        string = JSONObject.toJSONString(queryClass);

        System.out.println("Query String:" + string);

        List<ResultClass> fi = null ;

        try{
            StringEntity s = new StringEntity(string,"UTF-8");
            s.setContentEncoding("UTF-8");
            httpPost1.setEntity(s);
            HttpResponse res = httpClient.execute(httpPost1);
            if (res.getStatusLine().getStatusCode()/100  != 2){
                logger.error("code:" + res.getStatusLine().getStatusCode());
            }

            InputStream in = res.getEntity().getContent();
            int len ;
            //byte [] b = new byte[(int)res.getEntity().getContentLength()] ; /// response len 怎么会是-1呢
            byte [] b = new byte[1024 * 128];
            StringBuffer stringBuffer = new StringBuffer() ;

            while((len = in.read(b)) != -1){
                stringBuffer.append(new String(b,"UTF-8"));
                b = new byte[1024 * 128];
            }
            String result = new String(stringBuffer);
            /*
            FileOutputStream f = new FileOutputStream(new File("re.txt"));
            f.write(result.trim().getBytes());
            f.close();
            System.out.println(result.trim());*/
            fi = JSONObject.parseArray(result.trim(), ResultClass.class);
        }catch (Exception e){
            logger.error("get error:{}",e);
        }


        return fi ;
    }

    public void putDataByPost(List<Tailfile> tailfile,Integer flag){

        //
        String result = null;

        List<Store> storeList = Lists.newArrayList();
        for(Tailfile tailfile1 : tailfile){

            Store store = new Store();
            Store store1 = new Store();
            Map<String, String> map = Maps.newHashMap();

            if (flag == 2 || flag == 3) {
                store.setMetric(tailfile1.getTagName());

                store.setTimestamp(tailfile1.getTimeStamp());
                store.setValue(tailfile1.getCount());
                map.put("hostname",tailfile1.getHostname());
                map.put("type",new Integer(tailfile1.getType()).toString());
                map.put("nodetype",tailfile1.getNodeType().toString());

            }else if (flag == 1) {
                // 这是tailfile
                //store tagname as metric,以tagname作为metric
                store.setMetric(tailfile1.getTagName());
                store.setTimestamp(tailfile1.getTimeStamp());
                store.setValue(tailfile1.getCount());

                map.put("hostname",tailfile1.getHostname());// 应改要用到反射功能
                map.put("filename",tailfile1.getFileName()); //存filename
                map.put("inode",tailfile1.getInode().toString());
                map.put("type",tailfile1.getType().toString());
                map.put("nodetype",tailfile1.getNodeType().toString());

            }
            store.setTags(map);

            store1.setMetric(tailfile1.getHostname());
            Map<String, String> map1 = Maps.newHashMap();
            map1.put("type", new Integer(tailfile1.getType()).toString());
            map1.put("tagname",tailfile1.getTagName());
            map1.put("nodetype",tailfile1.getNodeType().toString());
            store1.setTags(map1);
            store1.setValue(tailfile1.getCount());
            store1.setTimestamp(tailfile1.getTimeStamp());

            storeList.add(store);
            storeList.add(store1);

        }

        //System.out.println("storeList:" + storeList);

        String string = JSONObject.toJSONString(storeList);


        try{
            StringEntity s = new StringEntity(string,"UTF-8");
            s.setContentEncoding("UTF-8");
            //s.setChunked(true);
            httpPost.setEntity(s);
            HttpResponse res = httpClient.execute(httpPost);
            logger.info("success in put: " + s);
            if (res.getStatusLine().getStatusCode()/100  != 2){
                logger.error("code:" + res.getStatusLine().getStatusCode());
            }



        }catch (Exception e){
            logger.error("get error:{}",e);
        }

    }

    public List<ResultClass> getDataByPost(QueryClass queryClass){

        String postData = JSONObject.toJSONString(queryClass);
        System.out.println("queryString:" + postData);
        List<ResultClass> resultClasses = null ;

        try{
            StringEntity s = new StringEntity(postData,"UTF-8");
            s.setContentEncoding("UTF-8");
            httpPost1.setEntity(s);
            HttpResponse httpResponse = httpClient.execute(httpPost1);

            if (httpResponse.getStatusLine().getStatusCode() / 100 != 2){
                logger.error("get error: {}",httpResponse.getStatusLine().getStatusCode());
            }

            InputStream in = httpResponse.getEntity().getContent();
            int len ;
            //byte [] b = new byte[(int)res.getEntity().getContentLength()] ; /// response len 怎么会是-1呢
            byte [] b = new byte[1024 * 128];
            StringBuffer stringBuffer = new StringBuffer() ;

            while((len = in.read(b)) != -1){
                stringBuffer.append(new String(b,"UTF-8"));
                b = new byte[1024 * 128];
            }
            String result = new String(stringBuffer);

            resultClasses = JSONObject.parseArray(result.trim(), ResultClass.class);
        }catch (Exception e){
            logger.error("get error: {}",e);
        }

        return resultClasses;

    }



}
