package com.ThreadSafe;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
public class testCycliBarrier {
    static Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            System.out.println(3);
        }
    });
    static CyclicBarrier c = new CyclicBarrier(2, t);
    static Thread thread = null;
    static {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    c.await();
                    System.out.println(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public static void main(String... args) {
          thread.start();
        try {
            c.await();
            System.out.println(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
/**
 *    结果一定是先输出3  , 后续1 ,2 随机。
 */
    }
}


