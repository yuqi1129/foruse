package flink.package2;

import org.apache.flink.api.common.functions.RuntimeContext;
import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.api.common.typeutils.TypeSerializer;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.windowing.RichWindowFunction;
import org.apache.flink.streaming.api.windowing.assigners.EventTimeSessionWindows;
import org.apache.flink.streaming.api.windowing.windows.Window;
import org.apache.flink.util.Collector;

import java.util.Iterator;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/8/16
 * Time: 14:38
 * To change this template use File | Settings | File Templates.
 */

class MyWindowFunction<IN, OUT, K, W extends Window> extends RichWindowFunction<IN, OUT, K, W>  {

    private TypeSerializer<OUT> typeSerializer;
    public ListState<OUT> value = null;


    public MyWindowFunction(TypeSerializer<OUT> typeSerializer) {
        this.typeSerializer = typeSerializer;
    }

    public void apply(K integer, W window, Iterable<IN> input, Collector<OUT> out) throws Exception {
        Iterator<OUT> old = value.get().iterator();
        while(old.hasNext()) {
            out.collect(old.next());
        }

        value.clear();

        Iterator<OUT> ne = value.get().iterator();
        while(ne.hasNext()) {
            OUT v = ne.next();
            out.collect(v);
            value.add(v);
        }
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        value = getRuntimeContext().getListState(new ListStateDescriptor<OUT>("state", typeSerializer));
    }
}
