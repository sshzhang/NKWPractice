package com.ThreadSafe;

/**
 * 线程中的不可见性
 */
public class NoVisiableThread {

    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        @Override
        public void run() {
            while (!ready) Thread.yield();
            System.out.println(number);
        }
    }

    public static void main(String... args) throws InterruptedException {
        new ReaderThread().start();
        Thread.sleep(500);
        number = 24;
        ready = true;
    }


}
