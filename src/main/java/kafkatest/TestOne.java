package kafkatest;

import com.google.common.base.Preconditions;

import org.apache.commons.lang.time.StopWatch;
import org.apache.hadoop.thirdparty.guava.common.collect.Lists;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.Future;
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

        String broker = "kafka0.xs.163.org:9092,kafka1.xs.163.org:9092,kafka2.xs.163.org:9092,kafka3.xs.163.org:9092";
        String broker_lt = "datastream0.lt.163.org:9092,datastream1.lt.163.org:9092,datastream2.lt.163.org:9092,datastream3.lt.163.org:9092,datastream4.lt.163.org:9092,datastream5.lt.163.org:9092" ;
        String broker_db180 = "db-180.photo.163.org:9092,db-180.photo.163.org:9093,db-180.photo.163.org:9094" ;
        Properties properties = new Properties();
        properties.put("metadata.broker.list",broker_db180);

        properties.put("serializer.class","kafka.serializer.StringEncoder");
        properties.put("key.serializer.class" , "kakfa.serializer.StringEncoder");

        //properties.put("partitioner.class","kafka.kafkatest.TestOne");

        properties.put("request.required.acks",1);

        properties.put("bootstrap.servers",broker_db180);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");



        Producer<String,String> producer = new KafkaProducer<String, String>(properties);



        /*

        long start = System.currentTimeMillis();

        for (long i = 0 ; i < 2000 ; i++){
            long runtime = new Date().getTime();
            final String ip = "192.168.2." + random.nextInt(255);

            final   String msg = runtime + ",www.exampl.com," + ip;
            //logger.info("send key = {},value = {} to kafka",ip,msg);

            //ProducerRecord record = new ProducerRecord<String,String>("test",0,ip,msg);
            ProducerRecord record = new ProducerRecord<String,String>("my-topic",0,ip,msg);
            StopWatch stopWatch = new StopWatch();
            //stopWatch.start();
            try {
                Future<RecordMetadata> future= producer.send(record, new Callback() {
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        logger.info("send completed,key={},value={}",ip,msg);
                    }
                });

                //producer.flush();
                System.out.print(future.get());

            }catch (Exception e){
                logger.error("caught error {}" ,e.getCause() + " , " + e.getMessage());
            }
        }

        */

        List<ProducerRecord> producerRecordList = Lists.newArrayList();

        for (int i = 0 ; i < 10000 ; i++){
            ProducerRecord producerRecord = new ProducerRecord("topic111",0,"192.168.3." + new Random().nextInt(255),"www.ifeng.com==" + new Integer(i).toString());
            producerRecordList.add(producerRecord);

        }

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

    }


}
