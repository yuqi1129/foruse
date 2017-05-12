package akka.demo1;

import akka.actor.UntypedActor;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/3/29
 * Time: 21:18
 * To change this template use File | Settings | File Templates.
 */

public class GreetPrinter extends UntypedActor {

    public void onReceive(Object message) throws Throwable {
        if (message instanceof Greeting) {
            System.out.println(((Greeting) message).message);
        }
    }
}
