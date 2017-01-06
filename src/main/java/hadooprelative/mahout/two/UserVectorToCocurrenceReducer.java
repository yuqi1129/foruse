package hadooprelative.mahout.two;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.VectorWritable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/1/5
 * Time: 22:04
 * To change this template use File | Settings | File Templates.
 */

public class UserVectorToCocurrenceReducer extends Reducer<IntWritable,IntWritable,IntWritable,VectorWritable> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserVectorToCocurrenceMapper.class);

    public void reduce(IntWritable key, Iterable<IntWritable> values, Reducer.Context context){
        try {
            Vector vector = new RandomAccessSparseVector(Integer.MAX_VALUE, 100);

            for (IntWritable value : values) {
                int itemIndex = value.get();
                vector.set(itemIndex, vector.get(itemIndex) + 1.0);
            }

            context.write(key, new VectorWritable(vector));
        }catch (Exception e){
            LOGGER.error("get error {} in reducer",e);
        }
    }
}
