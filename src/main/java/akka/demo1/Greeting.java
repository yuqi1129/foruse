package akka.demo1;

import akka.actor.UntypedActor;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/3/29
 * Time: 20:48
 * To change this template use File | Settings | File Templates.
 */

public class Greeting  {
    public final String message;

    public Greeting(String message) {
        this.message = message;
    }
}
