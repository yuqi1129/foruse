package flink.package3;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/8/16
 * Time: 11:50
 * To change this template use File | Settings | File Templates.
 */

public class ImplementOne<IN, OUT> implements ExtendOne<IN, OUT> {

    public OUT getOut(IN o) {
        return (OUT) o;
    }
}
