package cn.chen.day01;

import org.junit.Test;

import java.util.concurrent.*;

public class JulMain {
    /**
     * runnable 未返回值
     * callable 有返回值
     * 小打小闹
     * 1.5版本就有的方法
     */
    @Test
    public void runnableAndCallable() throws ExecutionException, InterruptedException {
        /*
         * 继承关系
         */
        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName());
        }, "t1");
        t1.start();

        FutureTask<String> futureTask = new FutureTask<>(() -> {
            Thread.sleep(5000);
            return "我是返回值";
        });
        Thread t2 = new Thread(futureTask, "t2");
        t2.start();

        //阻塞式方式获取
        //System.out.println(futureTask.get());

        //循环方式获取
        while (true) {
            if (futureTask.isDone()) {
                //已经结束
                System.out.println(futureTask.get());
                break;
            } else {
                Thread.sleep(1000);
                System.out.println("快点给我小伙子....");
            }
        }

        //有一定的局限性
    }

    /**
     * jdk 1.8以后才有的多线程工具
     * 这个工具默认的线程池 使用的是守护线程 不是用户线程 用户线程结束 他的方法就默认不执行了
     */
    @Test
    public void completableFuture() throws ExecutionException, InterruptedException {
        /*---- future的使用方法 start ----**/
        //runnable
        CompletableFuture<Void> completableFutureRunnable = CompletableFuture.runAsync(() -> {
            System.out.println("runnable实现 使用默认线程池:" + Thread.currentThread().getName());
        });
        System.out.println(completableFutureRunnable.get());

        //有返回值
        CompletableFuture<String> completableFutureCallable = CompletableFuture.supplyAsync(() -> {
            return "我是返回值";
        });
        System.out.println(completableFutureCallable.get());

        //可配置 自己的线程池对象
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CompletableFuture<String> completableFutureCallable1 = CompletableFuture.supplyAsync(() -> {
            return "我是返回值" + Thread.currentThread().getName();
        }, executorService);
        System.out.println(completableFutureCallable1.get());

        /*---- future的使用方法 end ----**/

        /*---- 节省时间小例子 start ----**/
        /*
         * 一共三个任务 其中两个任务执行3s 其中一个执行5s
         * 如果只用主线程执行这几个方法 一共需要 大约11s以上
         */

        //主线程方式 耗时11s
        long start = System.currentTimeMillis();
        //method3s();
        //method3s();
        //method5s();
        long end = System.currentTimeMillis();
        System.out.println("总耗时:" + (end - start) / 1000 + "s");

        //传统的多线程方式
        start = System.currentTimeMillis();
        executorService.execute(this::method3s);
        executorService.execute(this::method3s);
        executorService.execute(this::method5s);
        end = System.currentTimeMillis();
        System.out.println("多线程执行总耗时:" + (end - start) / 1000 + "s");


        System.out.println("多线程执行");

        start = System.currentTimeMillis();
        CompletableFuture.runAsync(this::method3s, executorService);
        CompletableFuture.runAsync(this::method3s, executorService);
        CompletableFuture.runAsync(this::method5s, executorService);
        end = System.currentTimeMillis();
        System.out.println("多线程执行总耗时:" + (end - start) / 1000 + "s");



        /*---- 节省时间小例子 end ----**/
        //Thread.sleep(10000);
        executorService.shutdown();

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new JulMain().completableFuture();
    }

    public void method3s() {
        try {
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + "3秒执行完毕");
            System.out.println(Thread.currentThread().isDaemon() + "3秒执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void method5s() {
        try {
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + "5秒执行完毕");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
