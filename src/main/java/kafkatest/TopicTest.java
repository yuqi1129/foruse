package kafkatest;

import com.google.common.collect.Lists;
import kafka.admin.TopicCommand;
import kafka.admin.TopicCommand$;
import kafka.consumer.KafkaStream;

import java.util.List;
import java.util.Properties;

public class TopicTest {
    public static void main(String[] args) {
        List<String> arg = Lists.newArrayList("--zookeeper",
                "datastream0.lt.163.org:2881,datastream1.lt.163.org:2181,datastream2.lt.163.org:2181/kafka_lt_online", "--list");


        List<String> arg1 = Lists.newArrayList("--zookeeper",
                "datastream0.lt.163.org:2881,datastream1.lt.163.org:2181,datastream2.lt.163.org:2181/kafka_lt_online", "--delete", "--topic", "zztest");
        TopicCommand.main(arg1.toArray(new String[arg1.size()]));
    }

}
