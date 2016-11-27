package opentsdbtest;

import java.util.List;
import java.util.Map;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/23
 * Time: 20:14
 * To change this template use File | Settings | File Templates.
 */

public class ResultClass {

    private String metric ;
    private Map<String,String> tags ;

    private List<String> aggregateTags ;

    private Map<String,Integer> dps;


    public ResultClass(String metric, Map<String, String> tags, List<String> aggregateTags, Map<String, Integer> dps) {
        this.metric = metric;
        this.tags = tags;
        this.aggregateTags = aggregateTags;
        this.dps = dps;
    }

    public ResultClass() {
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }

    public List<String> getAggregateTags() {
        return aggregateTags;
    }

    public void setAggregateTags(List<String> aggregateTags) {
        this.aggregateTags = aggregateTags;
    }

    public Map<String, Integer> getDps() {
        return dps;
    }

    public void setDps(Map<String, Integer> dps) {
        this.dps = dps;
    }

    @Override
    public String toString() {
        return "ResultClass{" +
                "metric='" + metric + '\'' +
                ", tags=" + tags +
                ", aggregateTags=" + aggregateTags +
                ", dps=" + dps +
                '}';
    }
}
