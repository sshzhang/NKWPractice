package com.ThreadSafe;

//测试CountDownLatch 效果闭锁， 调用此方法的await会一直阻塞，直到最终的值为0 开启线程

import java.util.concurrent.CountDownLatch;

public class TestCountDownLatch implements Runnable {
    public static final CountDownLatch latch = new CountDownLatch(2);
    @Override
    public void run() {
        try {
            System.out.println("阻塞" + Thread.currentThread().getName());
            latch.await();
            System.out.println(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public static void main(String... args) throws InterruptedException {
        TestCountDownLatch testCountDownLatch = new TestCountDownLatch();
        Thread t1 = new Thread(testCountDownLatch, "张");
        Thread t2 = new Thread(testCountDownLatch, "王");
        t1.start();
        t2.start();
        latch.countDown();
        Thread.sleep(10000);
        System.out.println("释放");
        latch.countDown();
    }
}
