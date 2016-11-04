package ZookeeperTest;



import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/10/26
 * Time: 10:26
 * To change this template use File | Settings | File Templates.
 */

public class MainFunctiojn {

    public static void main(String [] args){

        ZooKeeper zooKeeper = null;
        try {
            zooKeeper = new ZooKeeper("app-68.photo.163.org", 500000, new Watcher() {
                public void process(WatchedEvent watchedEvent) {

                    try {
                        switch (watchedEvent.getType()) {
                            case NodeChildrenChanged:
                                System.out.println(watchedEvent.getPath() + " children Changed");
                                break;
                            case NodeCreated:
                                System.out.println(watchedEvent.getPath() + " node created ");
                                break;
                            case NodeDataChanged:
                                System.out.println(watchedEvent.getPath() + " node data created ");
                                break;
                            case NodeDeleted:
                                System.out.println(watchedEvent.getPath() + " node deleted");
                                break;
                            default:
                                System.out.println("Event " + watchedEvent.getType() + " happended");
                                break;
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }


                }
            });
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        try {
            zooKeeper.create("/yuqi", "mytest".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            zooKeeper.create("/yuqi/test1", "test1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            zooKeeper.create("/yuqi/test2", "test2".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("get /yuqi ------------------------------------------------>");
            System.out.println(zooKeeper.getChildren("/yuqi",true));
            System.out.println("get /yuqi/test1 ------------------------------------------------>");
            System.out.println(new String(zooKeeper.getData("/yuqi/test1", true, null),"UTF-8"));


            zooKeeper.setData("/yuqi/test1", "change data".getBytes(), -1);

            zooKeeper.delete("/yuqi/test1", -1);
            zooKeeper.delete("/yuqi/test2", -1);
            zooKeeper.delete("/yuqi",-1);
            zooKeeper.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


    }

}
