package mqtest;

import com.google.common.collect.Maps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.reflect.annotation.ExceptionProxy;

import java.util.Map;

import mqtest.util.Producer;
import mqtest.util.TestConsumer;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/2
 * Time: 21:28
 * To change this template use File | Settings | File Templates.
 */

public class TestOne {
    private static Logger logger = LoggerFactory.getLogger(TestOne.class);

    public static void main(String [] args){





        System.out.println(1482315000000L - 1482315000000L % 3600000);

        System.out.println(((Number) 12345L).intValue());

        try {

            TestConsumer consumer = new TestConsumer("test-yuqi");
            Thread cousumeThread = new Thread(consumer);
            cousumeThread.start();


            Producer producer = new Producer("test-yuqi");

            for (int i = 0; i < 10000; i++) {
               // Map message = Maps.newHashMap();
               // message.put("message number", i);
                String message = "message-" + i ;
                producer.sendMessage(message.getBytes("UTF-8")); //?problem

            }
        }catch (Exception e){

        }
    }


}
