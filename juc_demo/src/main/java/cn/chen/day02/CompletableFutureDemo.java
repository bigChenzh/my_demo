package cn.chen.day02;

import java.util.concurrent.*;

/**
 * @author chen
 * @date 2022/8/20 14:24
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3, 5, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10), new ThreadPoolExecutor.AbortPolicy());

        /*---- runnable 和 callable ----*/

        //runnable 基础使用
        CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "runnable");
        });

        //runnable 自定义线程池
        CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "runnable 自定义线程");
        }, poolExecutor);


        //callable 基础使用 可以获取返回值
        CompletableFuture<String> callableCf = CompletableFuture.supplyAsync(() -> "我是返回值");


        //callable 自定义线程池
        CompletableFuture<String> callableCfMyThread = CompletableFuture.supplyAsync(() -> {
            //int a = 100 / 0;
            return "我是返回值";
        }, poolExecutor);


        //获取返回值的两种方式 get 和 join

        //get 会显示抛出异常
        try {
            System.out.println(callableCf.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        //join 不会显示抛出异常 任务出现异常会导致线程中断  注意
        //System.out.println(callableCfMyThread.join());

        /*---- runnable 和 callable End ----*/


        //thenApply 当返回之后  做这个处理 如果有异常 走不到这个方法
        /*callableCfMyThread.thenApply(r->{
            System.out.println(r);
            return r;
        });*/

        //异常之后 走这个方法 参数e是报错信息 需要重新设置返回值
        /*callableCfMyThread.exceptionally(e->{
            System.out.println(e.toString());

            return "哎呦异常了";
        });*/

        /*try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        //get join getNow
        // 在哪里 get join getNow 只要该completableFuture 方法内部报错 都会阻断该线程
        //System.out.println(callableCfMyThread.getNow("defaultValue")+"------>"+callableCfMyThread.join());
        //System.out.println(callableCfMyThread.complete("123")+"------>"+callableCfMyThread.join());


        //函数式接口 入参是上一个stage的返回值 报错会中断执行
        callableCfMyThread.thenApply((r) -> {
            return "123";
        });


        //函数式接口 入参是上一个stage的返回值 报错会继续执行
        callableCfMyThread.handle((r, e) -> {
            return r;
        });

        //消费型接口 没有返回值但是或接受到 上一个stage的传入参数
        callableCfMyThread.thenAccept((r) -> {
        });

        // applyToEither 时间比较短的 会被返回
        System.out.println(callableCfMyThread.applyToEither(callableCf, c -> {
            return c;
        }).join());


        // thenCombine 获取两个的返回值 并返回一个新的任务值
        System.out.println(callableCfMyThread.thenCombine(callableCf, (a, b) -> {
            System.out.println(a);
            System.out.println(b);
            return "我是我";
        }).join());

        poolExecutor.shutdown();
    }
}
