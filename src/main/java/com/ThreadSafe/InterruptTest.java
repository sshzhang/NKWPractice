package com.ThreadSafe;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
/**
 * interrupt()方法测试
 */
public class InterruptTest implements Runnable{
    volatile static boolean on = false;
    @Override
    public void run() {
        /*while (true) { // 测试当前线程是否中断?
            if (Thread.currentThread().isInterrupted()) {
                System.out.println(Thread.currentThread().getName() + "中断");
                return;
            }else{
                System.out.println(Thread.currentThread().getName()+"线程运行中");
            }
        }*/

        while (!on) {
            try {
                Thread.sleep(100000000);
            } catch (InterruptedException ex) {
                System.out.println("中断");
            }
        }
    }


    public static void main(String... args) throws InterruptedException {
        InterruptTest interruptTest = new InterruptTest();
  /*      Thread thread = new Thread(interruptTest);
        thread.start();
        System.out.println(thread.isInterrupted());
        System.out.println("准备interrupt()");
        Thread.sleep(100);
        //开关
        on = true;
        //两个作用
        thread.interrupt();
        //线程是否中断
        System.out.println(thread.isInterrupted());
        System.out.println("main end");*/

        BlockingQueue<Integer> integers = new ArrayBlockingQueue<>(3);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                interruptTest.getNextInteger(integers);
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(100);
        thread.interrupt();
        if (Thread.interrupted()) {
            System.out.println("已经被中断");
        }

        thread.interrupt();
    }


    public Integer getNextInteger(BlockingQueue<Integer> queues) {

        boolean interrupted = false;

        try{
            while (!interrupted) {
                try {
                    System.out.println(Thread.currentThread().getName());
                    return queues.take();

                } catch (InterruptedException ex) {
                    interrupted = true;
                    System.out.println("被中断");
                }
            }
        }finally {
                Thread.currentThread().interrupt();
                System.out.println("finally");
        }
        return null;
    }
}
