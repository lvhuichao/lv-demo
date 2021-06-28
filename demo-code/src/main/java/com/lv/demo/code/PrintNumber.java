package com.lv.demo.code;

/**
 * @desc:
 * @author: huichaolv@tencent.com
 * @create: 2021/6/28
 */
public class PrintNumber {

    private static volatile int i = 1;

    public static void main(String[] args) throws InterruptedException {
        PrintRunnable printRunnable = new PrintRunnable();
        new Thread(printRunnable).start();
        new Thread(printRunnable).start();
        Thread.sleep(10000);
    }

    static class PrintRunnable implements Runnable {

        @Override
        public void run() {
            synchronized (this) {
                while (i <= 100) {
                    System.out.println(i++);
                    notify();
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }
    }
}
