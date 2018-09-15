package com.jvm.test;

/**
 * 死锁问题
 * 两个线程和共享的资源
 */
public class ThreadDepthProblem implements Runnable {


    private static Object o1 = new Object(), o2 = new Object();
    private int flage = 0;
    @Override
    public void run() {

        if(flage==0){

            synchronized (o1) {

                try {
                    System.out.println(Thread.currentThread().getName() + "o1");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println(Thread.currentThread().getName() + "o2");
                }

            }

        }else{

            synchronized (o2) {
                System.out.println(Thread.currentThread().getName() + "o2");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println(Thread.currentThread().getName() + "o1");

                }
            }
        }

    }


    public static void main(String... args) throws InterruptedException {

        ThreadDepthProblem main = new ThreadDepthProblem();
        ThreadDepthProblem main1 = new ThreadDepthProblem();
        main1.flage = 1;
        new Thread(main).start();
        new Thread(main1).start();

    }
}
