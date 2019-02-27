package com.ThreadSafe;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CycliBarrier类和CountDownLatch之间的区别是  此类能够循环利用。它是通过Lock和Condition来实现的，也就是说它是独占锁类型，
 *  而CountDownLatch共享锁 其实通过AQS实现
 */
public class testCycliBarrierU {
    int N;
    int matrix[][];
    CyclicBarrier cyclicBarrier;
    private void MergesureRows() {
        for (int i=0;i<N;i++)
        System.out.println(matrix[i][0]);
    }

    public testCycliBarrierU(int matrix[][]) throws InterruptedException {
        this.matrix = matrix;
        N = matrix.length;
        Runnable BarriAction = new Runnable() {
            @Override
            public void run() {
                System.out.println("所有线程到达等待节点");
                MergesureRows();
            }
        };
        cyclicBarrier = new CyclicBarrier(N, BarriAction);
        for (int i = 0; i < N; i++) {
            Thread thread = new Thread(new Worker(i));
            thread.start();
//            Thread.sleep(1000);
//            if(i==0) cyclicBarrier.reset();
        }
    }

    class Worker implements Runnable {
        int myRow;
        Worker(int myRow) {
            this.myRow = myRow;
        }
        @Override
        public void run() {
            for (int i = 1; i < matrix[myRow].length; i++) {
                matrix[myRow][0] += matrix[myRow][i];
            }
            try {
//                Thread.sleep(1000);
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String... args) throws InterruptedException {
        int[][] matrix = new int[4][4];
        for (int i = 0, k=1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = k++;
            }
        }
        testCycliBarrierU cycliBarrierU = new testCycliBarrierU(matrix);
    }
}
