package ZookeeperTest;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.server.quorum.StateSummary;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/11/1
 * Time: 11:46
 * To change this template use File | Settings | File Templates.
 */

public class MainFunctionTwo {

    public static void main(String [] args){
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("app-68.photo.163.org").sessionTimeoutMs(5000).retryPolicy(new RetryNTimes(5,1000)).build();
        client.start();

        NodeCache cache = new NodeCache(client,"/ds_config",false);
        try {
            cache.start();

            cache.getListenable().addListener(new NodeCacheListener() {
                public void nodeChanged() throws Exception {
                    System.out.println("node just changed");
                    //your <code>
                    System.out.println("end of code");
                }
            });
        }catch (Exception e){
            //todo
        }

    }

}
