package kafkatest;

//import org.apache.kafka.clients.consumer.KafkaConsumer;
//import org.apache.kafka.streams.kstream.KStream;
//import org.apache.kafka.streams.kstream.internals.KStreamImpl;
//import org.apache.kafka.streams.processor.internals.InternalTopologyBuilder;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;
import java.util.Properties;

public class KafkaStreamTest {

    public static void main(String[] args) {
        //Properties p = new Properties();
        //KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<String, String>(p);
        //KStream<String, String> kStream = kafkaConsumer.
        //InternalTopologyBuilder internalTopologyBuilder = ;

        Map<String, String> map = Maps.newHashMap();
        map.put("id", "345");
        map.put("name", "zhang");
        System.out.println(new Gson().toJson(map));

        List<String> list = Lists.newArrayList();
        list.add("xxxx");
        list.add("xx,xxx");
        System.out.println(new Gson().toJson(list));



    }
}
