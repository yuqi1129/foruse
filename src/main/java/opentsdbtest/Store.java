package opentsdbtest;

import java.util.Map;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/18
 * Time: 20:37
 * To change this template use File | Settings | File Templates.
 */

public class Store {
    private String metric ;
    private Long timestamp;
    private Long value;
    private Map<String,String> tags;

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Store{" +
                "metric='" + metric + '\'' +
                ", timestamp=" + timestamp +
                ", value=" + value +
                ", tags=" + tags +
                '}';
    }
}
