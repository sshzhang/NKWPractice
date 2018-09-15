package com.jvm.test;

/**
 * 线程死锁问题
 * 资源相互竞争
 *
 */
public class ThreadDepthProblem1 {

    private Object m = new Object();

    private Object n = new Object();

    private int visitetd;

    public void ReadValueB() {

            synchronized (this.m) {

                System.out.println(Thread.currentThread().getName() + " m");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (this.n) {
                    System.out.println(Thread.currentThread().getName() + " n");
                }
            }

    }

    public  void ReadValueA() {

        synchronized (this.n) {

            System.out.println(Thread.currentThread().getName() + " n");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (this.m) {
                System.out.println(Thread.currentThread().getName() + " m");
            }
        }
    }

    public static void main(String... args) {

        ThreadDepthProblem1 threadDepthProblem1 = new ThreadDepthProblem1();
        Thread thread = new Thread() {
            @Override
            public void run() {
                threadDepthProblem1.ReadValueA();
            }
        };
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                threadDepthProblem1.ReadValueB();
            }
        };
        thread.start();
        thread1.start();
    }


}
