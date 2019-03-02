package com.ThreadSafe;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

public class testExecutor {
    public static void main(String... args) throws InterruptedException, ExecutionException {
//        textShutDownAndInvokeAll();

//        testExecutor testExecutor = new testExecutor();
//        System.out.println(testExecutor instanceof Object);
        ArrayList<Integer> integers = new ArrayList<>(2);
        Iterator<Integer> iterator =
                integers.iterator();
        System.out.println(iterator.next());
    }

    public static void textShutDownAndInvokeAll() throws ExecutionException, InterruptedException {
        ExecutorService executorService =
                Executors.newFixedThreadPool(10);
        List<MyWorker> myWorkers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            myWorkers.add(new MyWorker(i));
        }

        //立即关闭  尝试关闭正在运行的任务(中断)，  返回等待队列中 没有执行过的任务
//        List<Runnable> runnables = executorService.shutdownNow();
        //批量执行任务
        List<Future<String>> futures = executorService.invokeAll(myWorkers);

        for (int i = 0; i < futures.size(); i++) {
            Future<String> stringFuture = futures.get(i);
            String s = stringFuture.get();
            System.out.println(s);
        }
        //必须调用此方法 正常关闭。
        executorService.shutdown();

        try {
            //如果能正常关闭 不需要调用， 如果在阻塞的时间内任务执行完成返回true,  如果超出时间任务还没有执行完成返回false
            if (executorService.awaitTermination(2000, TimeUnit.SECONDS)) {
                System.out.println("任务执行完成!");
            } else {
                System.out.println("任务还没有执行完成！");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("successs");
    }
}

class MyWorker implements Callable<String> {
    int id = 0;
    public MyWorker(int id) {
        this.id = id;
    }
    @Override
    public String call() throws InterruptedException {
        //模拟下载操作
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            System.out.println("中断" + id);
            throw new InterruptedException();
        }
        return id + "success";
    }
}

