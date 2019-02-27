package com.ThreadSafe.ProduceAndConsume;

import java.util.concurrent.BlockingQueue;

public class UConsumer implements Runnable {


    BlockingQueue<UProduct> uProducts;

    public UConsumer(BlockingQueue<UProduct> uProducts) {
        this.uProducts = uProducts;
    }
    @Override
    public synchronized void run() {
        while (true) {
            try {
               UProduct uProduct = uProducts.take();
                System.out.println(Thread.currentThread().getName() + "线程消费了, 第几个" + uProduct.getCount() + "产品");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("异常");
                Thread.currentThread().interrupt();
            }
        }
    }
}
