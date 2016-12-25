package hdfstest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;


/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/7
 * Time: 16:20
 * To change this template use File | Settings | File Templates.
 */

public class TestOne {

    private static final Logger logger = LoggerFactory.getLogger(TestOne.class);
    private static final boolean debug = false;

    public static void main(String [] args){
        try{
            Configuration configuration = new Configuration();
            //configuration.set("hbase.zookeeper.quorum", "inspur251.photo.163.org,inspur252.photo.163.org,inspur253.photo.163.org,inspur254.photo.163.org,inspur255.photo.163.org");
            configuration.addResource("hadoop-size-hz-cluster1.xml");
            FileSystem hdfs = FileSystem.get(new URI("hdfs://inspur249.photo.163.org:8020"),configuration);

            Path src = new Path("E:\\JavaWorkSpace1\\AvorDemo\\file.txt");

            if (debug){
                System.out.println(new Throwable().getStackTrace()[2].getLineNumber());
            }

            Path dest = new Path("/");

            hdfs.mkdirs(dest);

            if (debug){
                System.out.println(new Throwable().getStackTrace()[2].getLineNumber());
            }
            hdfs.copyFromLocalFile(src, dest);

            if (debug){
                System.out.println(new Throwable().getStackTrace()[2].getLineNumber());
            }
            //System.out.println("Upload to " + configuration.get("fs.default.name"));

            FileStatus[] statuses = hdfs.listStatus(dest);
            for(int i= 0 ; i < statuses.length ; i++){
                System.out.println(statuses[i].getPath().toString());
            }

            FSDataOutputStream out = hdfs.create(new Path("/test.txt"));
            InputStream in = new BufferedInputStream(new FileInputStream(new File("E:\\JavaWorkSpace1\\AvorDemo\\file.txt")));

            byte [] b = new byte[1024];
            int n;
            while((n = in.read(b)) > 0){
                out.write(b,0,n);
            }

            in.close();
            out.close();

            statuses = hdfs.listStatus(dest);
            for(int i= 0 ; i < statuses.length ; i++){
                System.out.println(statuses[i].getPath().toString());
            }
            hdfs.close();

        }catch (Exception e){
            logger.error("get error: {}",e);
        }

    }
}
