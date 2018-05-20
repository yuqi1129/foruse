package kafkatest;

import com.google.common.base.Preconditions;

import com.google.common.collect.Lists;
import kafka.admin.TopicCommand;
import kafka.admin.TopicCommand$;
import org.apache.commons.lang.time.StopWatch;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.SystemTime;
import org.jboss.netty.channel.ChannelFutureNotifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Date;
import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import kafka.producer.KeyedMessage;

/**
 * Created with foruse. * User: hzyuqi1
 * Date: 2016/11/2
 * Time: 14:43
 * To change this template use File | Settings | File Templates.
 */

public class TestOne {
    //consumer
    private static Logger logger = LoggerFactory.getLogger(TestOne.class);
    private static Random random = new Random();

    public static void main(String [] args){

        LinkedBlockingQueue<ProducerRecord> linkedBlockingQueue = new LinkedBlockingQueue<>();

        String broker = "kafka0.xs.163.org:9092,kafka1.xs.163.org:9092,kafka2.xs.163.org:9092,kafka3.xs.163.org:9092";
        String broker_lt = "datastream0.lt.163.org:9092,datastream1.lt.163.org:9092,datastream2.lt.163.org:9092,datastream3.lt.163.org:9092,datastream4.lt.163.org:9092,datastream5.lt.163.org:9092" ;
        String broker_db180 = "db-180.photo.163.org:9092,db-180.photo.163.org:9093,db-180.photo.163.org:9094" ;
        String broker_db179 = "db-179.photo.163.org:9092" ;
        String broker_test = "db-180.photo.163.org:9092";
        String broker_test1 = "hadoop717.lt.163.org:9092";
        String broker_test2 = "datastream0.lt.163.org:9092";
        String broker_test3 = "sloth10.hz.163.org:9092,sloth11.hz.163.org:9092,sloth12.hz.163.org:9092";

        Properties properties = new Properties();
        properties.put("metadata.broker.list",broker_test3);

        properties.put("serializer.class","kafka.serializer.StringEncoder");
        properties.put("key.serializer.class" , "kakfa.serializer.StringEncoder");

        properties.put("request.required.acks",1);

        properties.put("bootstrap.servers",  broker_test3);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

//        String sql = "insert into test6 values(?, ?);";
//
//        Connection connection = null;
//        try{
//            Class.forName("com.mysql.jdbc.Driver").newInstance();
//            connection = DriverManager.getConnection("jdbc:mysql://10.172.121.100/ds","ds_dev","s]k51_(>R");
//        }catch (Exception e){
//            System.out.println("Mysql error: " + e.getMessage());
//        }
//        final Connection con = connection;

        Producer<String,String> producer = new KafkaProducer<String, String>(properties);

        String[] strings = new String[] {"nice", "good", "great", "excellent"};

        Thread thread1 = new Thread(new Runnable() {
            final Random random = new Random();
            @Override
            public void run() {
                try {
                    for (int i = 0; i < 1000000; i++) {
                        String name = "";
                        //String id = Integer.valueOf(random.nextInt(100)).toString();
                        String price = Integer.valueOf(random.nextInt(100)).toString();
                        String id = Integer.valueOf(random.nextInt(1000)).toString();

                        int k = 0;
                        while (k < 3) {
                            int start = 97 + random.nextInt(10);
                            name = name + (char) start;
                            k++;
                        }
                        //System.out.println(name);
                        int radint = random.nextInt(4);
//                        final PreparedStatement ps = con.prepareStatement(sql);
//                        ps.setString(1, strings[radint]);
//                        ps.setInt(2, Integer.valueOf(price));
//                        ps.execute();



                        linkedBlockingQueue.add(new ProducerRecord("kafak_in", strings[radint], strings[radint] + "," + price));
                        try {
                            Thread.currentThread().sleep(10);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    ProducerRecord producerRecord = linkedBlockingQueue.poll();
                    try {
                        if (producerRecord != null) {
                            Future<RecordMetadata> future = producer.send(producerRecord);
                            producer.flush();
                            RecordMetadata recordMetadata = future.get();
                            System.out.println(recordMetadata.topic() + ", " + recordMetadata.offset() + " ," + recordMetadata.partition());
                        }
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread2.start();

        /**
        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        List<Future<RecordMetadata>> list = Lists.newArrayList();

        for (ProducerRecord record : producerRecordList){
            try{
                System.out.println("start");
                Future<RecordMetadata> future = producer.send(record);
                list.add(future);
                RecordMetadata recordMetadata = future.get();
                System.out.println(recordMetadata.topic() + " ," + recordMetadata.partition() + " ," + recordMetadata.offset());
                System.out.println("end");
                producer.flush();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println(stopWatch.getTime());
        for (Future<RecordMetadata> future : list){
            try {
                RecordMetadata recordMetadata = future.get();
                System.out.println(recordMetadata.topic() + ", " + recordMetadata.offset() + " ," + recordMetadata.partition());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        */
    }


}
