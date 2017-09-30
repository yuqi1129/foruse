package main;

import com.google.common.collect.Lists;

import java.util.Iterator;
import java.util.List;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/9/8
 * Time: 22:08
 * To change this template use File | Settings | File Templates.
 */

public class TestTwoThread {

    private static final List<Integer> INTEGER_LIST = Lists.newArrayList(1,2,3,4);

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Iterator<Integer> it;
                synchronized (INTEGER_LIST) {
                    it = INTEGER_LIST.iterator();
                }

                try {
                    Thread.currentThread().sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                while(it.hasNext()) {
                    it.next();
                    try {
                        Thread.currentThread().sleep(1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    synchronized (INTEGER_LIST) {
                        it.remove();
                    }
                    break;
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 5;
                while (true) {
                    synchronized (INTEGER_LIST) {
                        INTEGER_LIST.add(i++);
                    }

                    try {
                        Thread.currentThread().sleep(100);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();


    }
}
