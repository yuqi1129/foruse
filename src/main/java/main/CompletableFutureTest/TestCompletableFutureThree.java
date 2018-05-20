package main.CompletableFutureTest;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Author yuqi
 * Time 28/4/18 20:57
 **/
public class TestCompletableFutureThree {
    public static void main(String[] args) {
        CompletableFuture<String> completableFuture = new CompletableFuture();

        completableFuture.supplyAsync(() -> {
            System.out.println("1 --->" + Thread.currentThread().getName());
            return "1--->";
        }).thenApply(s -> {
            throw new RuntimeException("xxx");
            //System.out.println("2---->" + Thread.currentThread().getName());
            //return "1--->" + s;
            //throw new RuntimeException("xxx");
        }).whenComplete((Object o, Throwable t) -> {
            if (t != null) {
                System.out.println("xxxxx");
            }
        });



        CompletableFuture<Integer> IntegerFuture;
        IntegerFuture = CompletableFuture.supplyAsync(() -> "1").thenCompose((String s) -> CompletableFuture.supplyAsync(() -> Integer.valueOf(s + "0")));

        IntegerFuture.whenComplete((Integer value, Throwable t) -> {
            if (t != null) {
                System.out.println("error");
            }

            System.out.println(value);
        });

         CompletableFuture.supplyAsync(() -> {
            //System.out.println("here 1");
            try {
                Thread.currentThread().sleep(1000);
                System.out.println("here 1");
                return "1";
            } catch (Exception e) {
                throw new RuntimeException(e.toString());
            }
        }).completeExceptionally(new Exception("111"));

    }
}
