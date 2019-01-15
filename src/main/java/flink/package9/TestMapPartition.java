package flink.package9;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2018/6/13 下午6:07
 */

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapPartitionFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class TestMapPartition {

	public static void main(String[] args) throws Exception {
		final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

		DataSet<String> text = env.fromElements(
				"Who's there?",
				"I think I hear them. Stand, ho! Who's there?");

		DataSet<Tuple2<String, Integer>> wordCounts = text
				.flatMap(new LineSplitter())
				.mapPartition(new MapPartitionFunction<Tuple2<String,Integer>, Tuple2<String, Integer>>() {
					@Override
					public void mapPartition(Iterable<Tuple2<String, Integer>> iterable, Collector<Tuple2<String, Integer>> collector) throws Exception {

						for (Tuple2<String, Integer> i : iterable) {
							collector.collect(i);
						}
					}
				})
				.groupBy(0)
				.sum(1);

		wordCounts.print();
	}
}
