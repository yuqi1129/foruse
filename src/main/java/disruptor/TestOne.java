package disruptor;

import com.google.common.collect.Queues;

import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.Sequence;
import com.lmax.disruptor.SequenceBarrier;
import com.lmax.disruptor.Sequencer;
import com.lmax.disruptor.YieldingWaitStrategy;

import common.Person;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.Queue;
import java.util.concurrent.Exchanger;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created with foruse.
 * User: hzyuqi1
 * Date: 2016/10/26
 * Time: 19:57
 * To change this template use File | Settings | File Templates.
 */

public class TestOne {

    final  long objCount = 10000000 ;
    final long bufferSize ;
    {
        bufferSize = getRingBufferSize(objCount);
    }

    static long getRingBufferSize(long sum){
        long s = 2;
        while(s < sum){
            s <<= 1;
        }
        return s;
    }

    public void testBlockingQueue() throws Exception{

        final LinkedBlockingQueue<Person> queue = new LinkedBlockingQueue<Person>();
        //final Queue<Person> queue = Queues.newLinkedBlockingQueue();
        Thread producer = new Thread(new Runnable() {
            public void run() {
                try{
                    for (long i = 1;i <= objCount ;i++){
                        queue.put(new Person("ii",1));
                    }
                }catch (InterruptedException e){
                    //todo
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            public void run() {
                try{
                    Person p = null;
                    for (long i = 1 ; i <= objCount ; i++) {
                        p = queue.take();
                    }

                }catch (InterruptedException e){
                    //todo
                }
            }
        });


        long timeNow = System.currentTimeMillis();
        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        long timeEnd = System.currentTimeMillis();

        System.out.println("LinkedBlockQueue takes:" + (timeEnd - timeNow));

    }


    public void testRingBuffer() throws Exception{
        final RingBuffer<Person> ringBuffer = RingBuffer.createSingleProducer(new EventFactory<Person>() {
            public Person newInstance() {
                return new Person("ii",1);
            }
        },(int)bufferSize,new YieldingWaitStrategy());

        final SequenceBarrier barrier = ringBuffer.newBarrier();

        Thread producer = new Thread(new Runnable() {
            public void run() {
                for(long i = 1 ; i <= objCount ; i++){
                    long index = ringBuffer.next();
                    ringBuffer.get(index).setName("ii");
                    ringBuffer.get(index).setId(1);
                    ringBuffer.publish(index);
                }
            }
        });


        Thread consumer = new Thread(new Runnable() {
            public void run() {
                Person  p = null ;

                long readCount = 0 ;
                long readIndex = Sequencer.INITIAL_CURSOR_VALUE;

                while(readCount < objCount){
                    try{
                        long nextIndex = readIndex + 1;
                        long availableIndex = barrier.waitFor(nextIndex);

                        while (nextIndex <= availableIndex){
                            p = ringBuffer.get(nextIndex);
                            readCount++ ;
                            nextIndex++ ;

                        }
                        readIndex = availableIndex;
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });

        long timeNow = System.currentTimeMillis();

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        long timeEnd = System.currentTimeMillis();

        System.out.println("disruptor takes:" + (timeEnd - timeNow));

    }

    public static void main(String []  args){
        TestOne testOne = new TestOne();
        try {
            testOne.testBlockingQueue();
            testOne.testRingBuffer();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}


