package flink.package6;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.functions.source.ParallelSourceFunction;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.util.Collector;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Author yuqi
 * Time 26/3/18 21:06
 **/
public class NewTest {

    public static void main(String[] args) {
        StreamExecutionEnvironment streamExecutionEnvironment =
                StreamExecutionEnvironment.getExecutionEnvironment();

        DataStream<Integer> dataStream =
                streamExecutionEnvironment.addSource(new ParallelSourceFunction<Integer>() {
                    private boolean isRunning = true;

                    @Override
                    public void run(SourceContext<Integer> ctx) throws Exception {
                        Random r = new Random();
                        while (isRunning) {
                            ctx.collect(Integer.valueOf(r.nextInt(100)));
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void cancel() {
                        isRunning = false;
                    }
                }).setParallelism(1).filter(new FilterFunction<Integer>() {
                    @Override
                    public boolean filter(Integer integer) throws Exception {
                        return integer != null;
                    }
                }).assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarks<Integer>() {

                                                     private long maxTimeStamp = 0L;

                                                     @Nullable
                                                     @Override
                                                     public Watermark getCurrentWatermark() {
                                                         final long now = Math.max(maxTimeStamp, System.currentTimeMillis());
                                                         maxTimeStamp = now;
                                                         return new Watermark(maxTimeStamp - 1);
                                                     }

                                                     @Override
                                                     public long extractTimestamp(Integer element, long previousElementTimestamp) {
                                                         if (maxTimeStamp == 0L) {
                                                             maxTimeStamp = System.currentTimeMillis();
                                                         }
                                                         return maxTimeStamp;
                                                     }
                                                 }
                ).map(new MapFunction<Integer, Integer>() {
                    @Override
                    public Integer map(Integer integer) throws Exception {
                        return integer * integer;
                    }
                }).flatMap(new FlatMapFunction<Integer, Integer>() {
                    @Override
                    public void flatMap(Integer integer, Collector<Integer> collector) throws Exception {
                        collector.collect(integer * 10 + 1);
                    }
                }).setParallelism(1);
        dataStream.print();

        try {
            streamExecutionEnvironment.execute("a test job 1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
