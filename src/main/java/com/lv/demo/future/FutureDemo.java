package com.lv.demo.future;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @desc:
 * @author: huichaolv@tencent.com
 * @create: 2021/3/25
 */
public class FutureDemo {

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    @Test
    public void testNormal() throws ExecutionException, InterruptedException {
        Future<?> future = executorService.submit(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });//FutureTask的源码值得看
        System.out.println(System.currentTimeMillis());
        System.out.println(future.get());//阻塞
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void testError() throws ExecutionException, InterruptedException {
        Future<?> future = executorService.submit(new Runnable() {
            public void run() {
                throw new RuntimeException();//会被包装成ExecutionException
            }
        });
        future.get();//抛出ExecutionException
    }
}
