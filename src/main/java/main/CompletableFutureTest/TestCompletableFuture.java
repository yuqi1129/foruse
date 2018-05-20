package main.CompletableFutureTest;

import org.apache.commons.lang.time.StopWatch;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Author yuqi
 * Time 22/3/18 21:31
 **/
public class TestCompletableFuture {
    public static void main(String[] args) {
        //System.out.println(CompletableFuture.supplyAsync(() -> "test").thenApplyAsync(s -> s + " hello").join());


        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("------------1----------");
            return "Hello";
        }).thenApplyAsync(s -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("------------2----------");
            return s + " World";
        }).join();

        System.out.println("First time, we take: " + stopWatch.getTime());
        stopWatch = new StopWatch();
        stopWatch.start();

        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("------------3----------");
            return "Hello";
        }).thenApply(s -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            System.out.println("------------4----------");
            return s + " World";
        }).join();

        System.out.println("Second time, we take: " + stopWatch.getTime());

        stopWatch = new StopWatch();
        stopWatch.start();
        CompletableFuture future = CompletableFuture.runAsync(() -> {
            System.out.println("nice");
        }).thenRun(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }).thenRunAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        try {
            future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("take time: " + stopWatch.getTime());


        CompletableFuture newFuture = CompletableFuture.supplyAsync(TestCompletableFuture::getInt);
        Future<Integer> integerFuture = newFuture.whenComplete((v1, v2) -> {
            System.out.println(v1);
            System.out.println(v2);
        });

        try {
            System.out.println(integerFuture.get());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    static int getInt() {
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 1;
    }

}
