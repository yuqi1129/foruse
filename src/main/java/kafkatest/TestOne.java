package kafkatest;

import com.google.common.base.Preconditions;

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
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/2
 * Time: 14:43
 * To change this template use File | Settings | File Templates.
 */

public class TestOne {
    //consumer
    private static Logger logger = LoggerFactory.getLogger(TestOne.class);
    private static Random random = new Random();

    public static void main(String [] args){

        //String broker = "kafka0.xs.163.org:9092,kafka1.xs.163.org:9092,kafka2.xs.163.org:9092,kafka3.xs.163.org:9092";

        String broker = "db-180.photo.163.org:9092,db-180.photo.163.org:9093,db-180.photo.163.org:9094";

        Properties properties = new Properties();
        properties.put("metadata.broker.list",broker);

        properties.put("serializer.class","kafka.serializer.StringEncoder");
        properties.put("key.serializer.class" , "kakfa.serializer.StringEncoder");

        //properties.put("partitioner.class","kafka.kafkatest.TestOne");

        properties.put("request.required.acks",1);

        properties.put("bootstrap.servers",broker);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");


        //ProducerConfig config = new ProducerConfig(properties);

        Producer<String,String> producer = new KafkaProducer<String, String>(properties);


        long start = System.currentTimeMillis();

        for (long i = 0 ; i < 100 ; i++){
            long runtime = new Date().getTime();

           final String ip = "192.168.2." + random.nextInt(255);

          final   String msg = runtime + ",www.exampl.com," + ip;
            //logger.info("send key = {},value = {} to kafka",ip,msg);

            ProducerRecord record = new ProducerRecord<String,String>("test",0,ip,msg);
            try {
                Future<RecordMetadata> future= producer.send(record, new Callback() {
                    public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                        logger.info("send completed,key={},value={}",ip,msg);
                    }
                });
                producer.flush();
                System.out.print(future.get());
            }catch (Exception e){
                logger.error("caught error {}" ,e.getCause() + " , " + e.getMessage());
            }
        }


    }


}
