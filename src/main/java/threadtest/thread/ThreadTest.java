package threadtest.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/12/6
 * Time: 19:13
 * To change this template use File | Settings | File Templates.
 */

public class ThreadTest {

    public static void main(String [] args){
        //
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo [] threadInfos = threadMXBean.dumpAllThreads(false,false);
        for(ThreadInfo threadInfo : threadInfos){
            System.out.println(String.format("[%d] %s",threadInfo.getThreadId(),threadInfo.getThreadName()));
        }

    }
}
