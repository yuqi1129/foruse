package opentsdbtest;

import com.google.common.collect.Lists;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import common.Tailfile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import scala.Int;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/29
 * Time: 16:57
 * To change this template use File | Settings | File Templates.
 */

public class StoreThread implements Runnable {

    private ThreadLocal<Long> start = new ThreadLocal<Long>() ;
    private Long startCopy ;
    private ThreadLocal<Long> end = new ThreadLocal<Long>() ;
    private Long endCopy ;
    private ThreadLocal<Integer> flag = new ThreadLocal<Integer>();
    private Integer flagCopy ;
    private OpenTsdb openTsdb ;

    // 1 mean tailfile,2 mean dataroute,and 3 means datahandler

    private static final Logger logger = LoggerFactory.getLogger(StoreThread.class);

    public StoreThread(Long start, Long end, OpenTsdb openTsdb, Integer flag) {
        this.startCopy = start;
        this.endCopy = end ;
        this.openTsdb = openTsdb;
        this.flagCopy = flag;
    }

    public Long getStart() {
        return start.get();
    }

    public void setStart(Long start) {
        this.start.set(start);
    }

    public Long getEnd() {
        return this.end.get();
    }

    public void setEnd(Long end) {
        this.end.set(end);
    }

    public Integer getFlag() {
        return this.flag.get();
    }

    public void setFlag(Integer flag) {
        this.flag.set(flag);
    }

    public OpenTsdb getOpenstsd() {
        return openTsdb;
    }

    public void setOpenstsd(OpenTsdb openTsdb) {
        this.openTsdb = openTsdb;
    }

    public void run(){

        if (this.flag.get() == null){
            this.flag.set(flagCopy);
        }

        if (this.start.get() == null){
            this.start.set(startCopy);
        }

        if (this.end.get() == null){
            this.end.set(endCopy);
        }
        Connection connection ;
        List<Tailfile> list = Lists.newArrayList();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sql = null;
        try {

            connection = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/datastream?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&&autoReconnect=true&failOverReadOnly=false&connectTimeout=600000&socketTimeout=600000","ds","8w?yXNgs;");

            //connection = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3308/datastream?useUnicode=true&characterEncoding=utf8&allowMultiQueries=true&&autoReconnect=true&failOverReadOnly=false&connectTimeout=600000&socketTimeout=600000","ds_dev","s]k51_(>R");
            Statement statement = (Statement) connection.createStatement();
            Long offset = start.get();
            System.out.println("flag.get() = " + flag.get());
            if (this.flag.get() == 1){
                sql = "select file_name,host_name,count,tag_name,type,start_time,file_inode,node_type from tb_monitor_tag_tailfile  limit 10000 offset " + start.get().toString() ;
            }else if (this.flag.get() == 2 || flag.get() == 3){
                sql =  "select host_name,count,tag_name,start_time,type,node_type from tb_monitor_tag_route  limit 10000 offset " + start.get().toString();
            }else if (this.flag.get() == 3){
                sql =  "select host_name,count,tag_name,start_time,type,node_type from tb_monitor_tag_flow  limit 10000 offset " + start.get().toString();
            }else{
                logger.error("your flag goes wrong");
                return ;
            }

            ResultSet res = statement.executeQuery(sql);
            res.last();
            System.out.println("start to handler data:");
            while (res.getRow() != 0) {

                res.first();
                System.out.println("query complete,offset: " + offset);
                do{
                    //

                    Tailfile tailfile = new Tailfile();

                    if (flag.get() == 1) {

                        tailfile.setCount(res.getLong(3));
                        tailfile.setHostname(res.getString(2));
                        if (tailfile.getHostname() == null || tailfile.getHostname().trim().equals("")) {
                            tailfile.setHostname("unknow");
                        }

                        tailfile.setFileName(res.getString(1).toString());
                        tailfile.setInode(res.getLong(7));

                        tailfile.setType(res.getInt(5));
                        tailfile.setTagName(res.getString(4));
                        tailfile.setTimeStamp(format.parse(res.getString(6)).getTime());
                        tailfile.setNodeType(res.getInt(8));

                    }else {

                        // this is for dataroute
                        tailfile.setHostname(res.getString(1));
                        tailfile.setCount(res.getLong(2));
                        tailfile.setTimeStamp(format.parse(res.getString(4)).getTime());
                        tailfile.setTagName(res.getString(3));
                        tailfile.setType(res.getInt(5));
                        tailfile.setNodeType(res.getInt(6));
                    }

                    list.add(tailfile);

                    if (list.size() == 500) {
                        //System.out.println("insert into opentsdb");
                        openTsdb.putDataByPost(list,flag.get());  //attention,this 1 mean tailfile,2 means dataroute
                        logger.info("we put " + list.size() + " data in it ");
                        list.clear();
                    }

                    if (res.getRow() % 1000 == 0){
                        System.out.println("complete 1000 rows");
                    }
                }while(res.next());


                if (list.size() != 0) {
                    //System.out.println("insert into opentsdb");
                    openTsdb.putDataByPost(list,flag.get());
                    logger.info("we put " + list.size() + " data in it ");
                    list.clear();
                }

                // this if for dev
                if (offset.equals(end.get()))
                    break;

                offset += 10000;
                System.out.println("offset:" + offset);

                if (flag.get() == 1) {
                    sql = "select file_name,host_name,count,tag_name,type,modify_time,file_inode,node_type from tb_monitor_tag_tailfile  limit 10000 offset " + offset.toString();
                }else if (flag.get() == 2) {
                    sql =  "select host_name,count,tag_name,start_time,type,node_type from tb_monitor_tag_route  limit 10000 offset " + offset.toString();
                }else if (flag.get() == 3){
                    sql = "select host_name,count,tag_name,start_time,type,node_type from tb_monitor_tag_flow  limit 10000 offset " + offset.toString();
                }else{
                    logger.error("your flag goes wrong");
                    return ;
                }

                res = statement.executeQuery(sql);
                res.last();

            }
        }catch (Exception e){
            System.out.println(e);
            logger.error("get error: {}",e);
        }

    }

}
