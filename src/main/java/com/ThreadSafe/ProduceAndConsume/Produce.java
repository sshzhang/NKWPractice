package com.ThreadSafe.ProduceAndConsume;

//生产者
public class Produce implements Runnable {

    public static int count = 0;
    Repertory repertory = null;

    public Produce(Repertory repertory) {
        this.repertory = repertory;
    }
    @Override
    public void run() {

        while (true) {
            //类锁 实现count++的原子性
            synchronized (Produce.class) {
                count++;
                Product product = new Product(count);
                repertory.push(product, Thread.currentThread().getName());
            }
        }
    }
}
