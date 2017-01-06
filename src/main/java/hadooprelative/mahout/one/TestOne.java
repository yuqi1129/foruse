package hadooprelative.mahout.one;

import org.apache.mahout.cf.taste.common.Weighting;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericBooleanPrefItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.util.List;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/1/2
 * Time: 14:48
 * To change this template use File | Settings | File Templates.
 */

public class TestOne {

    public static void main(String [] args){
        try {
            DataModel model = new FileDataModel(new File("src/main/java/hadooprelative/mahout/data/data.txt"));

            UserSimilarity similarity = new PearsonCorrelationSimilarity(model,Weighting.WEIGHTED);
            UserNeighborhood neighborhood = new NearestNUserNeighborhood(10,similarity,model);

            Recommender recommender = new GenericUserBasedRecommender(model,neighborhood,similarity);

            List<RecommendedItem> recommendedItemList = recommender.recommend(3,1);

            for(RecommendedItem recommendedItem : recommendedItemList){
                System.out.println(recommendedItem);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
