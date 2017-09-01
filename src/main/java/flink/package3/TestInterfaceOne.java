package flink.package3;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/8/16
 * Time: 11:47
 * To change this template use File | Settings | File Templates.
 */

public interface TestInterfaceOne<IN, OUT> {

    OUT getOut(IN in);
}
