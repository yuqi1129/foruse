package akka.demo2;

import com.typesafe.config.ConfigFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/3/30
 * Time: 10:51
 * To change this template use File | Settings | File Templates.
 */

public class AkkaDemo2 {

    public static void main(String[] args) throws Exception {
        final ActorSystem actorSystem = ActorSystem.create("demo2", ConfigFactory.load("demo2").getConfig("demo2"));

        final ActorRef actorRef = actorSystem.actorOf(Props.create(ControlActor.class), "control");

        actorRef.tell(new StartCommand(100), ActorRef.noSender());
    }
}
