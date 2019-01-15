package flink.package9;/*
 * Author: park.yq@alibaba-inc.com
 * Date: 2018/6/13 下午6:10
 */

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class LineSplitter implements FlatMapFunction<String, Tuple2<String, Integer>> {
	@Override
	public void flatMap(String line, Collector<Tuple2<String, Integer>> out) {
		for (String word : line.split(" ")) {
			out.collect(new Tuple2<String, Integer>(word, 1));
		}
	}
}
