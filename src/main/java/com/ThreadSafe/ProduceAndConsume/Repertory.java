package com.ThreadSafe.ProduceAndConsume;

import java.util.LinkedList;

//创库
public class Repertory {

    public LinkedList<Product> store = new LinkedList<>();

    public static final int MAX_SIZE = 10;

    public LinkedList<Product> getStore() {
        return store;
    }
    public void setStore(LinkedList<Product> store) {
        this.store = store;
    }

    public synchronized void push(Product p, String ThreadName) {

        while (store.size() == MAX_SIZE) {//唤醒之后，再一次检查条件是否满足
            try {
                System.out.println(ThreadName + "报告:创库已满--->进入等待状态---->呼叫消费者");
                //仓库容量满，无法继续生产
                this.wait();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        store.add(p);
        //唤醒所有等待的消费者
        this.notifyAll();

        System.out.println(ThreadName + "生产了:" + p.id + "号产品 当前库存:" + store.size());

        try {
            Thread.sleep(100);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public synchronized void pop(String threadName) {
        while (store.size() == 0) {
            try {
                System.out.println(threadName + "下命令:仓库已空--->进入等待状态--->唤醒生产者");
                this.wait();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        Product product = store.removeFirst();

        this.notifyAll();
        System.out.println(threadName + "消费了:" + product.id + "号产品" + "当前库存来:" + store.size());
        try {
            Thread.sleep(100);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
