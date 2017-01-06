package hadooprelative.mahout.three;

import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.VarLongWritable;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.VectorWritable;

import java.io.IOException;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/1/3
 * Time: 11:02
 * To change this template use File | Settings | File Templates.
 */

public class MovieToUserVectorReducer extends Reducer<VarLongWritable,VarLongWritable,VarLongWritable,VectorWritable>{

    @Override
    public void reduce(VarLongWritable useId, Iterable<VarLongWritable> itemPerf, Reducer<VarLongWritable,VarLongWritable,VarLongWritable,VectorWritable>.Context context) throws IOException,InterruptedException{

        System.out.println("we comet to reduce");
        Vector userVector = new RandomAccessSparseVector(Integer.MAX_VALUE,100);

        for(VarLongWritable varLongWritable : itemPerf ){
            userVector.set((int)varLongWritable.get(),1.0f);
        }

        context.write(useId,new VectorWritable(userVector));
    }


}
