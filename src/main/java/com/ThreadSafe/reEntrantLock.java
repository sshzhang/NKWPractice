package com.ThreadSafe;

/**
 * 可重入锁
 * 如果锁不可重入， 那么下面代码会产生死锁
 * 最终锁住的对象都是子节点对象
 */
public class reEntrantLock {

    public synchronized void doSomething() {
        System.out.println(this);
    }

    public static void main(String... args) {

        new reSonLock().doSomething();

    }

}

class reSonLock extends reEntrantLock {

    public  synchronized  void doSomething() {
        System.out.println(this);
        super.doSomething();
    }

}

