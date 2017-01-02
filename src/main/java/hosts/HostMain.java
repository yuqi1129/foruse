package hosts;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.Runnables;

import org.apache.commons.lang.time.StopWatch;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.omg.SendingContext.RunTime;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.netty.util.concurrent.DefaultThreadFactory;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/1/1
 * Time: 21:44
 * To change this template use File | Settings | File Templates.
 */

public class HostMain {

    /**
     *
     */

    private static final TreeMap<String,Integer> map = Maps.newTreeMap();
    private static HttpClient httpClient = HttpClients.createDefault();
    private static BufferedWriter bufferedWriter;
    private static Object object = new Object();

    private static final int threadNumber = Runtime.getRuntime().availableProcessors() * 2 ;
    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(threadNumber,threadNumber,1,TimeUnit.MINUTES,new
            LinkedBlockingQueue<Runnable>(1000),new DefaultThreadFactory("test-google",false),new ThreadPoolExecutor.CallerRunsPolicy());
    static {
        try{
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("result.txt"))));
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void main(String [] args){


        try{

            //getTime("74.125.2.6");
            //isValid("64.233.189.199");



            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream( new File("tmp.txt"))));

            String line = null ;
            while ((line = bufferedReader.readLine()) != null){

                String [] array = line.split("/");
                if (array.length != 2){
                    return ;
                }

                String host = array[0];
                String flag = array[1];


                if ("16".equals(flag)){
                    String [] segment = host.split("\\.");
                    System.out.println(line);
                    for (int i = 1 ; i <= 255 ; i++ ){
                        for (int j = 1 ; j<= 255 ; j++ ){
                            if (i == 255 && j== 255 )
                                continue;
                            final StringBuilder builder = new StringBuilder("");
                            builder.append(segment[0]).append(".").append(segment[1]).
                                    append(".").append(String.valueOf(i)).append(".").append(String.valueOf(j));
                            //getTime(builder.toString());

                            if (threadPoolExecutor.getQueue().size() > 100){
                                try{
                                    Thread.currentThread().sleep(10000);
                                }catch (Exception e){
                                    e.printStackTrace();
                                }

                                if (threadPoolExecutor.getQueue().size() < 100){
                                    threadPoolExecutor.execute(new Runnable() {
                                        public void run() {
                                            isValid(builder.toString());
                                        }
                                    });
                                }
                            }else {
                                threadPoolExecutor.execute(new Runnable() {
                                    public void run() {
                                        isValid(builder.toString());
                                    }
                                });
                            }

                            try{
                                Thread.currentThread().sleep(1000);
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                        }
                    }
                }else if ("24".equals(flag)){
                    String [] segment= host.split("\\.");
                    for ( int i = 2 ; i< 254 ; i++){
                        final StringBuilder builder = new StringBuilder("");
                        builder.append(segment[0]).append(".").append(segment[1]).
                                append(".").append(segment[2]).append(".").append(String.valueOf(i));

                        //getTime(builder.toString());

                        if (threadPoolExecutor.getQueue().size() > 100){
                            try{
                                Thread.currentThread().sleep(10000);
                            }catch (Exception e){
                                e.printStackTrace();
                            }

                            if (threadPoolExecutor.getQueue().size() < 100){
                                threadPoolExecutor.execute(new Runnable() {
                                    public void run() {
                                        isValid(builder.toString());
                                    }
                                });
                            }
                        }else {
                            threadPoolExecutor.execute(new Runnable() {
                                public void run() {
                                    isValid(builder.toString());
                                }
                            });
                        }

                        try{
                            Thread.currentThread().sleep(1000);
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                }
            }


        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPoolExecutor.shutdown();
        }
    }

    public static void getTime(String hostname){

        OutputStream outputStream ;
        try {
            Process process = Runtime.getRuntime().exec("ping " + hostname);

            BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(process.getInputStream(),"gbk"));

            String tmp = null;
            while ((tmp = bufferedReader1.readLine()) != null) {
                if ("请求超时。".equals(tmp)) {
                    break;
                }
                if (tmp.contains("最短")) {
                    break;
                }
            }

            if (tmp != null && tmp.contains("最短")) {
                //
                String[] array1 = tmp.split("=");
                if (array1.length != 4)
                    return;
                String[] array2 = array1[array1.length - 1].split("ms");

                System.out.println(hostname + " " + Integer.valueOf(array2[0].trim()));

                outputStream = new FileOutputStream(new File("result.txt"));
                outputStream.write((hostname + " " + Integer.valueOf(array2[0].trim())).getBytes());
                outputStream.close();

                map.put(hostname, Integer.valueOf(array2[0].trim()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPoolExecutor.shutdown();
        }
    }


    public static void isValid(String hostname){
        System.out.println("we start to process:" + hostname);
        try{
            HttpGet httpGet = new HttpGet("http://" + hostname);
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();

            HttpResponse httpResponse = httpClient.execute(httpGet);
            long time = stopWatch.getTime();

            if (httpResponse.getStatusLine().getStatusCode() / 100 != 2){
                return;
            }

            System.out.println(hostname + " " + String.valueOf(time));
            synchronized (object){
                bufferedWriter.write((hostname + " " + String.valueOf(time) + "\n"));
                bufferedWriter.flush();
            }
            System.out.println("end to process:" + hostname);
        }catch (Exception e){

        }
    }


}
