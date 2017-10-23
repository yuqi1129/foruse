package ZookeeperTest;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
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

    public static final String PATH = "/sloth-flink";

    public static void main(String [] args){
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString("sloth2.hz.163.org:2181,sloth3.hz.163.org:2181,sloth4.hz.163.org:2181").sessionTimeoutMs(5000).retryPolicy(new RetryNTimes(5,1000)).build();
        client.start();

        NodeCache cache = new NodeCache(client,"/yuqi",false);
        PathChildrenCache pathChildrenCache = new PathChildrenCache(client, PATH, false);

        try {
            cache.start();

            cache.getListenable().addListener(new NodeCacheListener() {
                public void nodeChanged() throws Exception {
                    System.out.println("node just changed");
                    //your <code>
                    System.out.println("end of code");
                }
            });

            pathChildrenCache.start();
            pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
                @Override
                public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {

                    if (event.getType() == PathChildrenCacheEvent.Type.CHILD_ADDED) {
                        String path = event.getData().getPath();
                        System.out.println(event.getData().getPath() + " has beed added");
                        //System.out.println(String.format("node '%s' was added and data is '%s'", event.getData().getPath(), new String(event.getData().getData())));
                    } else if (event.getType() == PathChildrenCacheEvent.Type.CHILD_REMOVED) {
                        //System.out.println(String.format("node '%s' was remove and data is '%s'", event.getData().getPath(), new String(event.getData().getData())));
                        System.out.println(event.getData().getPath() + " has beed removed");
                    } else if (event.getType() == PathChildrenCacheEvent.Type.CHILD_UPDATED) {
                        //System.out.println(String.format("node '%s' have changed and now data is '%s'", event.getData().getPath(), new String(event.getData().getData())));
                        System.out.println(event.getData().getPath() + " has beed changed");
                    }
                }
            });


            while (true) {
                //check exist

            }
        }catch (Exception e){
            //todo
        }

    }

}
