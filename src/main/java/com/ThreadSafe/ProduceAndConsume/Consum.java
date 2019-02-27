package com.ThreadSafe.ProduceAndConsume;
//消费者
public class Consum implements Runnable {
    Repertory repertory = null;

    public Consum(Repertory repertory) {
        this.repertory = repertory;
    }

    @Override
    public void run() {
        while (true) {
            repertory.pop(Thread.currentThread().getName());
        }
    }
}
