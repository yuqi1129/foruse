package opentsdbtest;

import common.Tailfile;

import java.util.List;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/18
 * Time: 20:14
 * To change this template use File | Settings | File Templates.
 */

public interface Tsdb {

    List<ResultClass> getDataByGet(String metric,Integer type,Long startTime,Long endTime);
    List<ResultClass> getDataByPost(String metric,Integer type,Long startTime,Long endTime);


    List<ResultClass> getDataByPost(QueryClass queryClass) ;

    void putDataByPost(List<Tailfile> tailfile);
}
