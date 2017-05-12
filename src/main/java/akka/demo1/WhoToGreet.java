package akka.demo1;

import java.io.Serializable;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/3/29
 * Time: 20:48
 * To change this template use File | Settings | File Templates.
 */

public class WhoToGreet implements Serializable {

    public final String who;

    public WhoToGreet(String who) {
        this.who = who;
    }
}



