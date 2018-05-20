package flink.package2;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;

import org.apache.flink.api.common.typeutils.TypeSerializer;
import org.apache.flink.api.common.typeutils.base.IntSerializer;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.functions.source.ParallelSourceFunction;
import org.apache.flink.streaming.api.functions.windowing.RichWindowFunction;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.assigners.EventTimeSessionWindows;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.triggers.EventTimeTrigger;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.api.windowing.windows.Window;
import org.apache.flink.types.IntValue;
import org.apache.flink.util.Collector;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import javax.annotation.Nullable;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/8/16
 * Time: 10:17
 * To change this template use File | Settings | File Templates.
 */

public class WindowTestOne {

    public static void main(String[] args) {

        StreamExecutionEnvironment streamExecutionEnvironment = StreamExecutionEnvironment.createLocalEnvironment();

        streamExecutionEnvironment.getConfig().setAutoWatermarkInterval(5000);
        TypeSerializer<Integer> serial = new IntSerializer();
        MyWindowFunction<Integer, Integer, Integer, TimeWindow> window = new MyWindowFunction<>(serial);

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
        }).filter(new FilterFunction<Integer>() {
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
        }).window(TumblingEventTimeWindows.of(Time.seconds(5))).trigger(EventTimeTrigger.create()).apply(window).returns(Integer.class);


        dataStream.print();

        try {
            streamExecutionEnvironment.execute("a test job");
        } catch (Exception e) {
            e.printStackTrace();
        }

//        ThreadPoolExecutor th = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
//        Future<Integer> f = th.submit(new Callable<Integer>() {
//            @Override
//            public Integer call() throws Exception {
//                return 1;
//            }
//        });
//
//        try {
//            System.out.print(f.get());
//            th.shutdown();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
    }
}
