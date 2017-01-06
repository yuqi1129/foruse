package hadooprelative.mahout.three;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.mahout.math.VarLongWritable;
import org.apache.mahout.math.VectorWritable;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/1/2
 * Time: 21:40
 * To change this template use File | Settings | File Templates.
 */

public class HandleUser {

    public static void main(String [] args){
        //

        try {
            Configuration configuration = new Configuration();
            Job job = Job.getInstance(configuration, "handler user vector");


            job.setJarByClass(HandleUser.class);
            job.setMapperClass(MovieToItemPerfMapper.class);
            job.setReducerClass(MovieToUserVectorReducer.class);

            /*
            job.setInputFormatClass(KeyValueTextInputFormat.class);
            job.setOutputFormatClass(TextOutputFormat.class);
            */


            //job.setCombinerClass(MovieToUserVectorReducer.class);

            job.setMapOutputKeyClass(VarLongWritable.class);
            job.setMapOutputValueClass(VarLongWritable.class);

            job.setOutputKeyClass(VarLongWritable.class);
            job.setOutputValueClass(VectorWritable.class);


            FileOutputFormat.setOutputPath(job,new Path(args[2]));
            FileInputFormat.setInputPaths(job,new Path(args[1]));
            System.exit(job.waitForCompletion(true) ? 0 : 1);

        }catch (Exception e){
            e.printStackTrace();
        }


    }

}
