package flink.package5;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.functions.source.ParallelSourceFunction;
import org.apache.flink.streaming.api.functions.windowing.WindowFunction;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.triggers.EventTimeTrigger;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
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

        DataStream<Tuple2<Integer, Integer>> dataStream =
                streamExecutionEnvironment.addSource(new ParallelSourceFunction<Tuple2<Integer, Integer>>() {
                    private boolean isRunning = true;

                    @Override
                    public void run(SourceContext<Tuple2<Integer, Integer>> ctx) throws Exception {
                        Random r = new Random();
                        while (isRunning) {
                            ctx.collect(new Tuple2<>(r.nextInt(1000), r.nextInt(100)));
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
                }).setParallelism(2).filter(new FilterFunction<Tuple2<Integer, Integer>>() {
                    @Override
                    public boolean filter(Tuple2<Integer, Integer> integer) throws Exception {
                        return integer.f0 > 10;
                    }
                }).assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarks<Tuple2<Integer, Integer>>() {

                                                     private long maxTimeStamp = 0L;

                                                     @Nullable
                                                     @Override
                                                     public Watermark getCurrentWatermark() {
                                                         final long now = Math.max(maxTimeStamp, System.currentTimeMillis());
                                                         maxTimeStamp = now;
                                                         return new Watermark(maxTimeStamp - 1);
                                                     }

                                                     @Override
                                                     public long extractTimestamp(Tuple2<Integer, Integer> element, long previousElementTimestamp) {
                                                         if (maxTimeStamp == 0L) {
                                                             maxTimeStamp = System.currentTimeMillis();
                                                         }
                                                         return maxTimeStamp;
                                                     }
                                                 }
                ).map(new MapFunction<Tuple2<Integer, Integer>, Tuple2<Integer, Integer>>() {
                    @Override
                    public Tuple2<Integer, Integer> map(Tuple2<Integer, Integer> integer) throws Exception {
                        return integer;
                    }
                }).flatMap(new FlatMapFunction<Tuple2<Integer, Integer>, Tuple2<Integer, Integer>>() {
                    @Override
                    public void flatMap(Tuple2<Integer, Integer> integer, Collector<Tuple2<Integer, Integer>> collector) throws Exception {
                        collector.collect(integer);
                    }
                }).keyBy(new KeySelector<Tuple2<Integer, Integer>, Integer>() {
                    @Override
                    public Integer getKey(Tuple2<Integer, Integer> integer) throws Exception {
                        return integer.f0;
                    }
                }).sum(1).setParallelism(2);
        dataStream.print();

        try {
            streamExecutionEnvironment.execute("a test job,haha");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
