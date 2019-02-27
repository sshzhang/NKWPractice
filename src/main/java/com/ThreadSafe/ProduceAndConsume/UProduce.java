package com.ThreadSafe.ProduceAndConsume;

import java.util.concurrent.BlockingQueue;

public class UProduce implements Runnable {

    private static  int count = 0;
    private BlockingQueue<UProduct> uProducts;
    public UProduce(BlockingQueue<UProduct> uProducts) {
        this.uProducts = uProducts;
    }

    @Override
    public void run() {

        while (true) {
            synchronized (UProduce.class) {
                try {
                    count++;
                    UProduct uProduct = new UProduct(count);
                    uProducts.put(uProduct);
                    System.out.println(Thread.currentThread().getName() + "线程生产了, 第几个" + count + "产品");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("中断异常");
                    Thread.currentThread().interrupt();
                }
            }
        }

    }
}

