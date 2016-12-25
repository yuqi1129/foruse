package hdfstest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.Compressor;
import org.apache.hadoop.util.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/12/18
 * Time: 19:43
 * To change this template use File | Settings | File Templates.
 */

public class TestTwo {

    public static final Logger logger = LoggerFactory.getLogger(TestTwo.class);

    public static void main(String [] args) {
        String hadoopConfigFile = "hadoop-size-cluster1.xml" ;
        Configuration configuration = new Configuration();

        Path path = new Path(hadoopConfigFile);
        configuration.addResource(path);

        System.out.println("success openconfig");

        CompressionCodec compressionCodec = null;
        try {
            //compressionCodec = (CompressionCodec) ReflectionUtils.newInstance(Class.forName("com.hadoop.comperssion.lzo.LzoCodec"),configuration);
            //compressionCodec = (CompressionCodec) ReflectionUtils.newInstance(Class.forName("org.anarres.lzo.LzoCompressor1x_1"),configuration);
        }catch (Exception e){
            logger.error("get error {}",e);
        }

        FileSystem fileSystem = null ;
        Path filePath = null;
        OutputStream outputStream = null ;
        System.out.println("1");
        try{
            filePath = new Path("hello.txt");
            fileSystem = filePath.getFileSystem(configuration);
            System.out.println("2");
            //if (!fileSystem.isFile(filePath))
            //    return ;
            outputStream = fileSystem.create(filePath);

            //Compressor compressor = compressionCodec.createCompressor();
            //outputStream = compressionCodec.createOutputStream(outputStream,compressor);
            outputStream.write("this is my first test".getBytes());
            System.out.println("3");
            outputStream.flush();
            outputStream.close();
            System.out.println("write success");

            InputStream in = fileSystem.open(filePath);
            IOUtils.copyBytes(in,System.out,1,false);

        }catch (Exception e){
            logger.error("get error {}",e);
        }

    }
}
