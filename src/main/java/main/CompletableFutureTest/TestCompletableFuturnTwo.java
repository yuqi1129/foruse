package main.CompletableFutureTest;

import com.google.common.base.Throwables;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

/**
 * Author yuqi
 * Time 8/4/18 14:40
 **/
public class TestCompletableFuturnTwo {
    public static void main(String[] args) {
//        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
//            Random random = new Random();
//            if (random.nextInt(2) == 1) {
//                throw new RuntimeException("We get error");
//            }
//            return "TEST";
//        }).handle((String result, Throwable error) -> {
//            System.out.println(Throwables.getStackTraceAsString(error));
//            return "NICE";
//        });


        CompletableFuture<String> cf1 = CompletableFuture.supplyAsync(() -> {
            Random random = new Random();
            if (random.nextInt(2) == 1) {
                throw new RuntimeException("We get error");
            }
            return "hehe";
        }).exceptionally((Throwable throwable) -> {
            throw new RuntimeException("We get nice");
        }).whenComplete((String s, Throwable e) -> {
            //do nothing;
        });


//        System.out.println(completableFuture.complete("HELLO"));

        try {
            //cf1.get();
            //cf1.completeExceptionally(new RuntimeException("xxxx"));
            //System.out.println(cf1.completeExceptionally(new RuntimeException("xxx")));
            //cf1.get();
        } catch (Exception e) {
            e.printStackTrace();
        }


        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> {
            if ((System.currentTimeMillis() / 1000 & 0x01) != 0) {
                throw new RuntimeException("h");
            }
            return "ni";
        });

        completableFuture1.completeExceptionally(new RuntimeException("ex"));

        try {
            completableFuture1.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
