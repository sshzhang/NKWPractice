package com.ThreadSafe;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class testRetry {


    /**
     * retry标志表示从此位置继续执行，如果是continue retry的话，类似于直接continue操作，
     * 如果是break retry 类似 break, 不过是重试或者终止的位置变化啦。
     */
    @Test
    public  void textRetryDemon() {

        System.out.println(Integer.toBinaryString(~0));

        retry://待执行的位置
        for (;;) {
//            retry:
            for (int j = 1; j <=5; j++) {
                System.out.println(j);
                if(j==4) continue retry;
            }
        }
    }

    @Test
    public void testThreadPoolExecutor() {

        ExecutorService executorService =
                Executors.newFixedThreadPool(5);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("添加的测试任务----执行者线程为" + Thread.currentThread().getName());
            }
        };
        executorService.submit(runnable);
        executorService.shutdown();
    }

}
