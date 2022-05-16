package cn.chen;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.junit.jupiter.api.Test;

/**
 * @author chen
 * @date 2021/12/15 10:46
 * log4j2是 阿帕奇参照了logback的设计思路 更改的log4j版本 速度目前是最快的
 * 实验室数据是logback的18倍 听着玩就好了
 *
 * 他也有自己的日志门面 自己本身就是 但是为了统一 都是使用的slf4j+log4j2的模式
 *
 */
public class LogTest {
    public void printLog(Log log){
        /* *
         * <li>trace (the least serious)</li>
         * <li>debug</li>
         * <li>info</li>
         * <li>warn</li>
         * <li>error</li>
         * <li>fatal (the most serious)</li>
         * */
        log.trace("日志级别:trace");
        log.debug("日志级别:debug");
        log.info("日志级别:info");
        log.warn("日志级别:warn");
        log.error("日志级别:error");
        log.fatal("日志级别:fatal");
    }

    @Test
    public void openMain(){
        Log log = LogFactoryImpl.getLog(LogTest.class);
        printLog(log);
    }


    @Test
    public void properTies() {
        Log log = LogFactoryImpl.getLog(LogTest.class);
        for (int i = 0; i < 100000; i++) {
            printLog(log);
        }

        System.out.println("12313");
    }
}
