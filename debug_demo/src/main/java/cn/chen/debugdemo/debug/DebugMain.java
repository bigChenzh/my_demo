package cn.chen.debugdemo.debug;

import cn.chen.debugdemo.DebugDemoApplication;
import cn.chen.debugdemo.debug.bean.FieldBean;
import cn.chen.debugdemo.debug.bean.MyBean;
import cn.chen.debugdemo.debug.factory.MyBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DebugMain {
    public static void main(String[] args) {
//        testBaseOperation();
//        testMethodBreakpoint();
//        testFieldBreakpoint();
//        testThrowException();
//        testExceptionBreakpoint();
//        testDropFrame();
//        testCondition();
//        testForceReturn();
//        testTraceCurrentStreamChain();
//        testEvaluateExpression();
//        testRemoteDebug();
//        testSuspend();
        testSuspend2();
    }


    /**
     * 基础的debug
     */
    public static void testBaseOperation(){
        System.out.println("我是断点1");
        System.out.println("我是断点2");
    }

    /**
     * 方法断点
     * 找不到具体实现类可以在接口方法打上断点就可以追踪到实现类
     */
    public static void testMethodBreakpoint(){
        MyBean myBean = MyBeanFactory.getMyBean();
        myBean.doSomething();
    }

    /**
     * 字段断点
     * 断点在字段在该字段被修改和查看的时候可以进断点
     */
    public static void testFieldBreakpoint(){
        FieldBean fieldBean = new FieldBean();
        fieldBean.modifyName();
        System.out.println(fieldBean);
    }

    /**
     * 抛出异常
     */
    public static void testThrowException(){
        return;
    }

    /**
     * 异常打断
     * 可以添加全局的异常捕获 只要抛出这个异常就会进断点
     */
    public static void testExceptionBreakpoint(){
        System.out.println("业务正常执行");
        System.out.println("我要开始入库了...");
    }

    /**
     * 退帧
     */
    public static void testDropFrame(){
        System.out.println("123123");
        business();
    }

    public static void business(){
        System.out.println("业务1");
        System.out.println("业务2");
    }

    /**
     * 条件断点
     */
    public static void testCondition(){
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(DebugDemoApplication.class);
        applicationContext.getBean("123123");
    }

    /**
     * 强制返回
     */
    public static void testForceReturn(){
        System.out.println("业务正常执行");
        System.out.println("我要开始入库了...");

        writeDb();
    }

    public static void writeDb(){
        System.out.println("没想到吧 我还是执行了");
    }

    /**
     * Stream链条 断点查看
     */
    public static void testTraceCurrentStreamChain(){
        List<String> list = getList();

        List<String> list1 = list.stream()
                .filter(Objects::nonNull)
                .sorted((String::compareTo))
                .distinct().collect(Collectors.toList());
    }

    public static List<String> getList(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add(null);
        strings.add("1");
        strings.add("2");
        strings.add("5");
        strings.add("3");
        strings.add("1");

        return strings;
    }

    /**
     * 计算
     */
    public static void testEvaluateExpression(){
        int age = 48;

        if (age > 18){
            System.out.println("成年了不行");
        }else {
            System.out.println("未成年 可以");
        }
    }

    /**
     * 远程调用 启动项remote jvm debug
     */
    public static void testRemoteDebug(){
        System.out.println("请查看Controller方法 getUser");
    }

    /**
     * 多线程调用
     */
    public static void testSuspend(){
        new Thread(() -> {
            System.out.println("thread1执行");
        }).start();
    }

    /**
     * 多线程调用2
     */
    public static void testSuspend2(){
        new Thread(() -> {
            System.out.println("thread1执行");
        }).start();


        new Thread(() -> {
            System.out.println("thread2执行");
        }).start();

        System.out.println("main执行");
    }

}






