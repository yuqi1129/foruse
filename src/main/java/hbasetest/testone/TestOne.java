package hbasetest.testone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/4
 * Time: 10:15
 * To change this template use File | Settings | File Templates.
 */

public class TestOne {

    private static final Logger logger = LoggerFactory.getLogger(TestOne.class);

    public static void main(String [] args){

        /*
        try {
            Configuration configuration = HBaseConfiguration.create();
            //configuration.addResource("conf.properties"); //只能add xml 文件
            configuration.set("hbase.zookeeper.quorum", "inspur251.photo.163.org,inspur252.photo.163.org,inspur253.photo.163.org,inspur254.photo.163.org,inspur255.photo.163.org");

            HBaseAdmin admin = new HBaseAdmin(configuration);

            HTableDescriptor tableDescriptor = new HTableDescriptor("test1");

            tableDescriptor.addFamily(new HColumnDescriptor("personal"));
            tableDescriptor.addFamily(new HColumnDescriptor("professional"));
            admin.createTable(tableDescriptor);
            System.out.println("create tabe success!!");
        }catch (MasterNotRunningException ee){
            System.out.print("get error: " + ee);
            logger.error("get error: {}",ee);
        }catch (Exception e){
            logger.error("get error: {}",e);
        }*/



    }


}
