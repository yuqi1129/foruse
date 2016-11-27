package opentsdbtest;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/24
 * Time: 17:41
 * To change this template use File | Settings | File Templates.
 */

public class OpenTSDBQuery {

    private OpenTsdb openTsdb ;


    public OpenTSDBQuery(OpenTsdb openTsdb) {
        this.openTsdb = openTsdb;
    }

    public List<ReturnEntity> getTagFlow(String tagName, Integer nodetype,Long startTime, Long endTime){

        Preconditions.checkArgument(startTime != null);

        // 组装query
        QueryClass queryClass = new QueryClass() ;

        SimpleDateFormat format = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");
        Date date = new Date(startTime);

        queryClass.setStart(format.format(date));
        if (endTime == null){
            queryClass.setEnd(null );
        }else{
            queryClass.setEnd(format.format(new Date(endTime)));
        }

        List<Query> queryList = Lists.newArrayList() ;
        Query query = new Query();
        query.setMetric(tagName);
        query.setRate(false);
        query.setAggregator("sum");
        query.setTags(null);
        Filter filter1 = new Filter("wildcard","hostname","*",true);
        Filter filter2 = new Filter("literal_or","type",nodetype.toString(),false);

        query.setFilters(Lists.newArrayList(filter1,filter2));

        //Map<String,String> tags = Maps.newHashMap();
        //tags.put("type",nodetype.toString());
        //query.setTags(tags);
        queryList.add(query);
        queryClass.setQueries(queryList);



        if (queryClass.getEnd() == null){
            query.setDownsample("1d-sum");
        }else{
            Long intervalHours = (endTime - startTime ) / 3600000;
            if (intervalHours > 24){
                Long interval =  intervalHours / 24 ;
            }else{
                Integer i = 10 ;
                while(i * 24  < intervalHours * 60){
                    i += 10 ;
                }
                query.setDownsample(i.toString() + "m-sum");
            }
        }

        // 发送http请求
        List<ResultClass>  result = openTsdb.getDataByPost(queryClass);

        if (result == null || result.size() == 0)
            return null;

        // 对反回的数据进行解析
        List<ReturnEntity> returnEntities = Lists.newArrayList() ;
        ReturnEntity maxTime = null ;
        for(ResultClass resultClass : result){
           ReturnEntity returnEntity = new ReturnEntity();
            returnEntity.setName(resultClass.getTags().get("hostname"));
            returnEntity.setValue(resultClass.getDps());
            Integer tmp = 0;

            for(Map.Entry<String,Integer> entry : returnEntity.getValue().entrySet()){
                tmp += entry.getValue();
            }
            returnEntity.setCount(tmp);
            returnEntities.add(returnEntity);

            if (maxTime == null){
                maxTime = returnEntity;
            }else{
                if (returnEntity.getValue().entrySet().size() > maxTime.getValue().entrySet().size()){
                    maxTime = returnEntity ;
                }
            }
        }
        System.out.println(maxTime);
        for(Map.Entry<String,Integer> entry : maxTime.getValue().entrySet()){
            for(ReturnEntity returnEntity:returnEntities){
                if (!returnEntity.getValue().containsKey(entry.getKey())){
                    returnEntity.getValue().put(entry.getKey(),0);
                }
            }
        }


        //get the sum result;
        ReturnEntity returnEntitySum = new ReturnEntity();
        returnEntitySum.setValue(Maps.<String, Integer>newHashMap());
        Integer totalcount = 0;
        for(Map.Entry<String,Integer> entry: maxTime.getValue().entrySet()){
            Integer tmpsize = 0;
            for(ReturnEntity returnEntity : returnEntities){
                tmpsize += returnEntity.getValue().get(entry.getKey());
            }
            returnEntitySum.getValue().put(entry.getKey(),tmpsize);
            totalcount += tmpsize ;
        }

        returnEntitySum.setName("total");
        returnEntitySum.setCount(totalcount);

        returnEntities.add(returnEntitySum);

        return returnEntities;
    }
}
