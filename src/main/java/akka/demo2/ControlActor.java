package akka.demo2;

import com.google.common.collect.Lists;

import java.util.List;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/3/30
 * Time: 10:39
 * To change this template use File | Settings | File Templates.
 */

public class ControlActor  extends UntypedActor{

    public void onReceive(Object message) throws Throwable {
        if (message instanceof StartCommand) {
            List<ActorRef> actorRefs = createActors(((StartCommand)message).getActorCount());

            actorRefs.stream().parallel().forEach(actorRef -> actorRef.tell("Insert", ActorRef.noSender()));
        }
    }

    private List<ActorRef> createActors(int actAccount) {
        Props props = Props.create(WriteActor.class).withDispatcher("write-dispatcher");
        List<ActorRef> actorRefs = Lists.newArrayList();

        for (int i = 0; i < actAccount; i++) {
            actorRefs.add(getContext().actorOf(props, "writer_" + i));
        }
        return actorRefs;
    }
}
