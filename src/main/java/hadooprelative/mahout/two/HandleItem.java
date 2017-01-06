package hadooprelative.mahout.two;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.InputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/1/5
 * Time: 21:36
 * To change this template use File | Settings | File Templates.
 */

public class HandleItem {

    private static final Logger LOGGER = LoggerFactory.getLogger(HandleItem.class);

    public static void main(String [] args){

        try {
            Configuration configuration = new Configuration();
            Job job = Job.getInstance(configuration, "to cocurrence vector");

            job.setJarByClass(HandleItem.class);
            job.setMapperClass(UserVectorToCocurrenceMapper.class);
            job.setReducerClass(UserVectorToCocurrenceReducer.class);

            job.setMapOutputKeyClass(IntWritable.class);
            job.setMapOutputValueClass(IntWritable.class);

            job.setOutputKeyClass(IntWritable.class);
            job.setOutputValueClass(IntWritable.class);

            job.setInputFormatClass(KeyValueTextInputFormat.class);


            FileInputFormat.setInputPaths(job, new Path(args[1]));

            FileOutputFormat.setOutputPath(job, new Path(args[2]));

            System.exit(job.waitForCompletion(true) ? 0 : 1);
        }catch (Exception e){
            LOGGER.error("get error {} in main ",e);
        }
    }
}
