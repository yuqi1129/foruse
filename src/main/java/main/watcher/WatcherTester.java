package main.watcher;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.nio.file.*;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Author yuqi
 * Time 13/4/18 15:01
 **/
public class WatcherTester {

    public static void main(String[] args) {

        WatchService watchService = null;
        try {
            watchService = FileSystems.getDefault().newWatchService();
            Paths.get("/tmp/test/").register(
                    watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_DELETE,
                    StandardWatchEventKinds.ENTRY_MODIFY
            );

            LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

            new Thread(() -> {
                while (true) {
                    try {
                        String fileName = "/tmp/test/" + UUID.randomUUID().toString();
                        File file = new File(fileName);
                        FileUtils.write(file, "xxxxxxxx");
                        queue.add(fileName);
                        Thread.sleep(2000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }).start();
//
//            new Thread(() -> {
//                while(true) {
//                    try {
//                        String fileName = queue.poll(100, TimeUnit.SECONDS);
//                        if (fileName != null) {
//                            FileUtils.deleteQuietly(new File(fileName));
//                        } else {
//                            Thread.currentThread().sleep(1000);
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();

            while (true) {
                WatchKey key = watchService.take();
                for (WatchEvent<?> watchEvent : key.pollEvents()) {
                    System.out.println("file: " + watchEvent.context() + " -------> " + watchEvent.kind());
                }

                if (!key.reset()) {
                    System.out.println("Can't reset: " + key);
                    break;
                }

                //Thread.currentThread().sleep(1000);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
