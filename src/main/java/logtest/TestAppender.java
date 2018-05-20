package logtest;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * Author yuqi
 * Time 25/4/18 11:46
 **/
public class TestAppender extends AppenderSkeleton {

    LinkedBlockingQueue<Object> events = new LinkedBlockingQueue<>();

    public TestAppender() {
        new Thread(() -> {
            while (true) {
                Object loggingEvent = events.poll();
                if (loggingEvent != null) {
                    System.out.println("Get log: " + loggingEvent.toString());
                }

                try {
                    Thread.currentThread().sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    protected void append(LoggingEvent loggingEvent) {
        Object object = loggingEvent.getMDC("UID");
        if (object != null) {
            events.add(object);
        }
    }

    @Override
    public void close() {

    }

    @Override
    public boolean requiresLayout() {
        return false;
    }
}
