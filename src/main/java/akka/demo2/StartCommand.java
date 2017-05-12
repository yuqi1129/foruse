package akka.demo2;

import java.io.Serializable;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/3/30
 * Time: 10:38
 * To change this template use File | Settings | File Templates.
 */

public class StartCommand implements Serializable {

    private int actorCount = 0;

    public int getActorCount() {
        return actorCount;
    }

    public void setActorCount(int actorCount) {
        this.actorCount = actorCount;
    }

    public StartCommand(int actorCount) {
        this.actorCount = actorCount;
    }

    public StartCommand() {
    }
}
