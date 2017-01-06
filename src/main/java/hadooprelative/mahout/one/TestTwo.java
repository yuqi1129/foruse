package hadooprelative.mahout.one;


import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.common.Weighting;
import org.apache.mahout.cf.taste.eval.RecommenderBuilder;
import org.apache.mahout.cf.taste.eval.RecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.eval.AverageAbsoluteDifferenceRecommenderEvaluator;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.neighborhood.ThresholdUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.impl.similarity.SpearmanCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.apache.mahout.common.RandomUtils;

import java.io.File;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/1/2
 * Time: 15:27
 * To change this template use File | Settings | File Templates.
 */

public class TestTwo {

    public static void main(String [] args){

        RandomUtils.useTestSeed();

        try {

            DataModel dataModel = new FileDataModel(new File("src/main/java/hadooprelative/mahout/data/data.txt"));

            RecommenderEvaluator recommenderEvaluator = new AverageAbsoluteDifferenceRecommenderEvaluator();
            RecommenderBuilder builder = new RecommenderBuilder() {
                public Recommender buildRecommender(DataModel dataModel) throws TasteException {
                    //UserSimilarity userSimilarity = new PearsonCorrelationSimilarity(dataModel, Weighting.WEIGHTED);
                    //UserSimilarity userSimilarity = new EuclideanDistanceSimilarity(dataModel);
                    UserSimilarity userSimilarity = new SpearmanCorrelationSimilarity(dataModel);


                    UserNeighborhood neighborhood = new NearestNUserNeighborhood(100, userSimilarity, dataModel);
                    //UserNeighborhood neighborhood = new ThresholdUserNeighborhood(0.5,userSimilarity,dataModel);
                    return new GenericUserBasedRecommender(dataModel, neighborhood, userSimilarity);
                }
            };

            double socre = recommenderEvaluator.evaluate(builder, null, dataModel, 0.95, 0.5);
            System.out.println(socre);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
