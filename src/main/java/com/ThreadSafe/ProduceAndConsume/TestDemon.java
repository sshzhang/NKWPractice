package com.ThreadSafe.ProduceAndConsume;

import java.io.InputStream;
import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class TestDemon {

    public static void main(String... args) throws InterruptedException {

   /*     Repertory repertory = new Repertory();
        Produce p1 = new Produce(repertory);
        Produce p2 = new Produce(repertory);
        Produce p3 = new Produce(repertory);

        Consum c1 = new Consum(repertory);
        Consum c2 = new Consum(repertory);
        Thread t1 = new Thread(p1, "张飞");
        Thread t2 = new Thread(p2, "关羽");
        Thread t3 = new Thread(p3, "赵云");

        Thread t4 = new Thread(c1, "刘备");
        Thread t5 = new Thread(c2, "曹操");

        t2.setPriority(10);
        t3.setPriority(10);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();*/


        /*BlockingQueue<UProduct> uProducts = new LinkedBlockingQueue<>();

        UProduce p1 = new UProduce(uProducts);
        UProduce p2 = new UProduce((uProducts));

        UConsumer c1 = new UConsumer(uProducts);
        UConsumer c2 = new UConsumer(uProducts);
        Thread t1 = new Thread(p1, "张飞");
        Thread t2 = new Thread(p2, "关羽");

        Thread t4 = new Thread(c1, "刘备");
        Thread t5 = new Thread(c2, "曹操");

        t1.setPriority(10);
        t2.setPriority(10);

        t1.start();
        t2.start();
        t4.start();
        t5.start();*/

        ArrayBlockingQueue<String> strings = new ArrayBlockingQueue<String>(3);
        strings.put("12");
        strings.put("34");
        strings.put("56");
        Iterator<String> iterator =
                strings.iterator();

        ArrayBlockingQueue<Integer> integers = new ArrayBlockingQueue<>(5);

        AtomicInteger atomicInteger = new AtomicInteger();
        System.out.println(atomicInteger.get());
        System.out.println(atomicInteger.getAndIncrement());
        System.out.println(atomicInteger.get());

    }

}
