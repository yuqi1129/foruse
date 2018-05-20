package flink.package8;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.streaming.api.CheckpointingMode;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Author yuqi
 * Time 7/4/18 14:47
 **/
public class FailedJob
{
    static final Logger LOGGER = LoggerFactory.getLogger(FailedJob.class);
    public static void main( String[] args ) throws Exception
    {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.enableCheckpointing(5000, CheckpointingMode.EXACTLY_ONCE);

        DataStream<String> stream = env.fromCollection(Arrays.asList("test"));
        stream.map(new MapFunction<String, String>(){
            @Override
            public String map(String obj)

            {                 throw new NullPointerException("NPE");             }

        });
        env.execute("Failed job");
    }
}
