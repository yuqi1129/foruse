package akka.demo1;

import akka.actor.UntypedActor;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/3/29
 * Time: 21:13
 * To change this template use File | Settings | File Templates.
 */

public class Greeter extends UntypedActor {

    String greeting = "";

    @Override
    public void onReceive(Object message) throws Throwable {
        if (message instanceof WhoToGreet) {
            greeting = "hello " + ((WhoToGreet)message).who;
        } else if (message instanceof Greet) {
            getSender().tell(new Greeting(greeting), getSelf());
        } else {
            unhandled(message);
        }
    }
}
