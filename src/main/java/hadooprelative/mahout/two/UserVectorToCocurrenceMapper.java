package hadooprelative.mahout.two;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.mahout.math.VarLongWritable;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.VectorWritable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/1/5
 * Time: 21:37
 * To change this template use File | Settings | File Templates.
 */

public class UserVectorToCocurrenceMapper extends Mapper<LongWritable,VectorWritable,IntWritable,IntWritable> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserVectorToCocurrenceMapper.class);

    public void map(LongWritable key, VectorWritable value, Context context){
        Iterator<Vector.Element> iterator1 = value.get().nonZeroes().iterator();

        try {
            while (iterator1.hasNext()) {

                int index1 = iterator1.next().index();

                Iterator<Vector.Element> iterator2 = value.get().nonZeroes().iterator();

                while (iterator2.hasNext()) {
                    int index2 = iterator2.next().index();
                    context.write(new IntWritable(index1), new IntWritable(index2));
                }
            }
        }catch (Exception e){
            LOGGER.error("get error {} in mapper",e);
        }
    }
}
