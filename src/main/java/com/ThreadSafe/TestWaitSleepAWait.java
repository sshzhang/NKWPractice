package com.ThreadSafe;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TestWaitSleepAWait{

    public static final ReentrantLock TLOCK = new ReentrantLock();
    public static final Condition CState = TLOCK.newCondition();

   //会释放锁
    public  synchronized  void textWait() throws InterruptedException {

        while (true) {
            System.out.println(Thread.currentThread().getName());
            this.wait();
        }
    }

    //不会释放锁
    public synchronized   void textSleep() throws InterruptedException {
        while (true) {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(999999999);
        }
    }

    //会释放锁
    public void testLockAwait() throws InterruptedException {
        TLOCK.lock();
        try{
            while (true) {
                System.out.println(Thread.currentThread().getName());
                CState.await();
            }
        }finally {
            TLOCK.unlock();
        }
    }
    private static class MyThread implements Runnable {
        static TestWaitSleepAWait dest;
        public MyThread(TestWaitSleepAWait dest) {
            MyThread.dest = dest;
        }

        @Override
        public void run() {
            System.out.println("线程" + Thread.currentThread().getName() + "运行");
            try {
                dest.testLockAwait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String... args) {

        TestWaitSleepAWait testWaitSleepAWait = new TestWaitSleepAWait();

        Thread t2 = new Thread(new MyThread(testWaitSleepAWait), "张飞");
        Thread t1 = new Thread(new MyThread(testWaitSleepAWait), "李四");
        t1.start();
        t2.start();

    }


}

