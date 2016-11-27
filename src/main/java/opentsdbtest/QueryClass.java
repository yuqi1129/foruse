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

public class QueryClass {

    private String start ;
    private String end;
    private List<Query> queries;

    public QueryClass(String start, String end, List<Query> queries) {
        this.start = start;
        this.end = end;
        this.queries = queries;
    }

    public QueryClass() {
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public List<Query> getQueries() {
        return queries;
    }

    public void setQueries(List<Query> queries) {
        this.queries = queries;
    }


    @Override
    public String toString() {
        return "QueryClass{" +
                "start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", queries=" + queries +
                '}';
    }
}

class Query {
    private String metric ;
    private String aggregator ;
    private String downsample ;
    private boolean rate ;
    private Map<String,String> tags;
    private List<Filter> filters ;


    public Query(String metric, String aggregator, String downsample, boolean rate, Map<String, String> tags, List<Filter> filters) {
        this.metric = metric;
        this.aggregator = aggregator;
        this.downsample = downsample;
        this.rate = rate;
        this.tags = tags;
        this.filters = filters;
    }

    public Query() {
    }

    public String getMetric() {
        return metric;
    }

    public void setMetric(String metric) {
        this.metric = metric;
    }

    public String getAggregator() {
        return aggregator;
    }

    public void setAggregator(String aggregator) {
        this.aggregator = aggregator;
    }

    public String getDownsample() {
        return downsample;
    }

    public void setDownsample(String downsample) {
        this.downsample = downsample;
    }

    public boolean isRate() {
        return rate;
    }

    public void setRate(boolean rate) {
        this.rate = rate;
    }

    public Map<String, String> getTags() {
        return tags;
    }

    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }


    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    @Override
    public String toString() {
        return "Query{" +
                "metric='" + metric + '\'' +
                ", aggregator='" + aggregator + '\'' +
                ", downsample='" + downsample + '\'' +
                ", rate=" + rate +
                ", tags=" + tags +
                ", filters=" + filters +
                '}';
    }
}

class Filter{
    private String type ;
    private String tagk ;
    private String filter ;
    private boolean groupBy ;


    public Filter(String type, String tagk, String filter, boolean groupBy) {
        this.type = type;
        this.tagk = tagk;
        this.filter = filter;
        this.groupBy = groupBy;
    }

    public Filter() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTagk() {
        return tagk;
    }

    public void setTagk(String tagk) {
        this.tagk = tagk;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public boolean isGroupBy() {
        return groupBy;
    }

    public void setGroupBy(boolean groupBy) {
        this.groupBy = groupBy;
    }

    @Override
    public String toString() {
        return "Filter{" +
                "type='" + type + '\'' +
                ", tagk='" + tagk + '\'' +
                ", filter='" + filter + '\'' +
                ", groupBy=" + groupBy +
                '}';
    }
}