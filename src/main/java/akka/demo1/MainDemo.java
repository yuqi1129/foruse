package akka.demo1;

import java.util.concurrent.TimeUnit;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import akka.actor.dsl.Creators;
import scala.concurrent.duration.Duration;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/3/29
 * Time: 21:19
 * To change this template use File | Settings | File Templates.
 */

public class MainDemo {
    public static void main(String[] args) {
        try {
            final ActorSystem actorSystem = ActorSystem.create("hello");

            //到Greeter的管道
            final ActorRef greeter = actorSystem.actorOf(Props.create(Greeter.class), "greeter");

            final Inbox inbox = Inbox.create(actorSystem);

            greeter.tell(new WhoToGreet("akka"), ActorRef.noSender());

            inbox.send(greeter, new Greet());

            Greeting greeting1 = (Greeting) inbox.receive(Duration.create(5, TimeUnit.SECONDS));
            System.out.println(greeting1.message);

            greeter.tell(new WhoToGreet("typesafe"), ActorRef.noSender());
            inbox.send(greeter, new Greet());

            Greeting greeting2 = (Greeting) inbox.receive(Duration.create(5, TimeUnit.SECONDS));
            System.out.println(greeting2.message);

            ActorRef greetPrinter = actorSystem.actorOf(Props.create(GreetPrinter.class));

            actorSystem.scheduler().schedule(Duration.Zero(), Duration.create(1, TimeUnit.SECONDS), greeter, new Greet(), actorSystem.dispatcher(), greetPrinter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
