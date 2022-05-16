package cn.chen;


import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;

/**
 * @author chen
 * @date 2021/12/15 15:43
 * log4j也是阿帕奇旗下的一个项目 比较好吧 但是也被淘汰了 默认是debug级别
 */
public class LogTest {
    public void printLog(Logger log){
        log.fatal("日志级别:{fatal}");
        log.error("日志级别:{error}");
        log.warn("日志级别:{warn}");
        log.info("日志级别:{info}");
        log.debug("日志级别:{debug}");
    }

    @Test
    public void openMain(){
        //默认使用的是jul框架 jdk自带的
        Logger logger = Logger.getLogger(LogTest.class);
        printLog(logger);
    }

    @Test
    public void rollingOpenMain(){
        //默认使用的是jul框架 jdk自带的
        Logger logger = Logger.getLogger(LogTest.class);
        for (int i = 0; i < 10000; i++) {
            printLog(logger);
        }
    }


}
