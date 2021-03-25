package com.lv.demo.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * @desc:
 * @author: huichaolv@tencent.com
 * @create: 2021/3/25
 */
public class CompletableFutureDemo {

    @Test
    public void testSupplyAsync() {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            printThreadName("1");
            sleep(1000);
            return "0";
        });
        completableFuture.thenAccept(s -> {
            System.out.println(s);
            printThreadName("2");
        });
        completableFuture.thenAcceptAsync(s -> {
            System.out.println(s);
            printThreadName("3");
        });
        completableFuture.thenRun(() -> printThreadName("4"));
        sleep(2000);
    }

    @Test
    public void testApplyToEither() {
        CompletableFuture.supplyAsync(() -> 0)
                .applyToEither(CompletableFuture.supplyAsync(() -> 1),
                        (Function<Integer, Void>) integer -> {
                            System.out.println(integer);
                            return null;
                        });//两个任意完成一个就拿它的结果去执行function
    }

    @Test
    public void testCompose() {
        Integer join = CompletableFuture.supplyAsync(() -> 0)
                .thenCompose(integer -> CompletableFuture.supplyAsync(() -> integer + 1))
                .join();//根据前者的结果来生成一个CompletableFuture
        System.out.println(join);
    }

    @Test
    public void testJoin() {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return 0;
                }
        );
        System.out.println(System.currentTimeMillis());
        System.out.println(completableFuture.join());//与get一致
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void testJoinWithError() {
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    throw new RuntimeException();
                }
        );
        System.out.println(System.currentTimeMillis());
        System.out.println(completableFuture.join());//get抛出ExecutionException(checked),join抛出CompletionException
        // (unchecked),To better conform with the use of common functional forms
        System.out.println(System.currentTimeMillis());
    }

    private void printThreadName(String key) {
        System.out.println(key + ":" + Thread.currentThread().getName());
    }

    private void sleep(long sleep) {
        try {
            Thread.sleep(sleep);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
