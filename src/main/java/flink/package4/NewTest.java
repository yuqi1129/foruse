package flink.package4;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.functions.KeySelector;
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
 * Time 26/3/18 10:47
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
                }).setParallelism(2).slotSharingGroup("test1").filter(new FilterFunction<Integer>() {
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
                }).keyBy(new KeySelector<Integer, Integer>() {
                    @Override
                    public Integer getKey(Integer integer) throws Exception {
                        return integer % 15;
                    }
                }).window(TumblingEventTimeWindows.of(Time.seconds(5))).trigger(EventTimeTrigger.create()).apply(new WindowFunction<Integer, Integer, Integer, TimeWindow>() {
                    @Override
                    public void apply(Integer integer, TimeWindow timeWindow, Iterable<Integer> iterable, Collector<Integer> collector) throws Exception {
                       iterable.forEach(v -> collector.collect(v));
                    }
                }).returns(TypeInformation.of(Integer.class)).slotSharingGroup("test1");


        dataStream.print();

        try {
            streamExecutionEnvironment.execute("a test job 2");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
