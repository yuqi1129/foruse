package main;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;


import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2017/8/29
 * Time: 11:22
 * To change this template use File | Settings | File Templates.
 */

public class SetTest {
    private static final Set<Integer> INTEGER_SET = Sets.newHashSet(1,2,3,4,5,6,7,8);
    private static final Set<Integer> shareSet = Sets.newConcurrentHashSet(INTEGER_SET);
    private static final List<Integer> TEST_LIST = Lists.newArrayList(1,2,3,4,5,6,7,8);

    public static void main(String[] args) {

        Thread thread1 = new MyThread();
        //Thread thread2 = new MyThread();
        thread1.start();
        //thread2.start();

    }

    private static class MyThread extends Thread {
        @Override
        public void run() {
//            for (Integer integer : shareSet) {
//                System.out.println(Thread.currentThread().getName() + " remove: " + integer);
//                shareSet.remove(integer);
//            }


//            Iterator<Integer> integerIterator = TEST_LIST.iterator();
//            while(integerIterator.hasNext()) {
//                Integer tmp = integerIterator.next();
//                if (tmp != 10){
//                    integerIterator.remove();
//                    System.out.println("remove: " + tmp );
//                    integerIterator = TEST_LIST.iterator();
//                }
//            }

//            for (Integer integer : TEST_LIST) {
//                if (integer < 10) {
//                    TEST_LIST.remove(integer);
//                }
//            }

//            int size = TEST_LIST.size();
//            for (int i = 0; i < size; i++) {
//                TEST_LIST.remove(TEST_LIST.get(i));
//            }
        }
    }
}
