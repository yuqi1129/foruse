package logtest;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/9
 * Time: 10:09
 * To change this template use File | Settings | File Templates.
 */

public class TestNone {
    private static final Logger errorLog = LoggerFactory.getLogger("myerror");
    private static final Logger infoLog = LoggerFactory.getLogger("myinfo");
    private static final Logger LOGGER = LoggerFactory.getLogger(TestNone.class);

    public static void main(String [] args) {
        //
//        errorLog.error("we get error");
//        infoLog.info("we get a info,haha ");
//        LOGGER.info("get info");
//        LOGGER.error("get error");
//        LOGGER.info("{}", "haha" + System.currentTimeMillis());


        //ExecutorService executorService = Executors.newFixedThreadPool(10);
//
//        ThreadPoolExecutor executorService = new ThreadPoolExecutor(10, 10, 100, TimeUnit.SECONDS, new LinkedBlockingQueue<>(100), new ThreadFactoryBuilder().setNameFormat("%d").build(), new ThreadPoolExecutor.CallerRunsPolicy());
//
//        for (int i = 0; i < 10; i++) {
//            final int j = i;
//            executorService.submit(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        synchronized (TestNone.class) {
//                            File oldFile = new File("info.log");
//                            String before = FileUtils.readFileToString(oldFile);
//
//                            infoLog.error("{}", "haha " + j);
//
//                            String newFile = FileUtils.readFileToString(new File("info.log"));
//
//                            System.out.println(Thread.currentThread().getName() + " write \"" + newFile.substring(before.length()) + "\" to log");
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        }
//
//        executorService.shutdown();


        String s = "2017-09-28 19:39:34,458 11735753 [173.362a41a6-3463-4bf1-bf97-6029eef76a28] DEBUG org.apache.flink.streaming.api.graph.StreamGraph(line-217) - Vertex: 129";

        System.out.println(isDebugLog(s));
    }


    private static boolean isDebugLog(String singleLine) {
        String logPatternString = "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9:,]{12} [0-9]{8}([\\s\\S]*)";
        Pattern pattern = Pattern.compile(logPatternString);
        Matcher matcher = pattern.matcher(singleLine);

        if (matcher.find()) {
            return true;
        }

        return false;
    }
}
