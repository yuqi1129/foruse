package akka.demo2;

import akka.actor.UntypedActor;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/3/30
 * Time: 10:39
 * To change this template use File | Settings | File Templates.
 */

public class WriteActor extends UntypedActor {

    public void onReceive(Object message) throws Throwable {
        System.out.println(Thread.currentThread().getName());
    }
}
