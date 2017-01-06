package hadooprelative.mahout.three;

import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.mahout.math.VarLongWritable;


import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/1/2
 * Time: 21:41
 * To change this template use File | Settings | File Templates.
 */

/**
 *  老的接口用org.apache.hadoop.mapre.Mapper
 *  新的接口直接用org.apache.hadoop.mapreduce.Mapper
 */

public class MovieToItemPerfMapper extends Mapper<Object,Text,VarLongWritable,VarLongWritable> {

    private static final Pattern pattern = Pattern.compile("(\\d+)");

    public void map(Object longWritable, Text text,Mapper<Object,Text,VarLongWritable,VarLongWritable>.Context context) throws IOException,InterruptedException{

        String line = text.toString();
        Matcher matcher = pattern.matcher(line);
        matcher.find();

        VarLongWritable userId = new VarLongWritable(Long.parseLong(matcher.group()));
        VarLongWritable itemId = new VarLongWritable();

        while(matcher.find()){
            itemId.set(Long.parseLong(matcher.group()));
            System.out.println("key=" + userId.get() + " value=" + itemId.get());
            context.write(userId,itemId);
        }

    }
}
