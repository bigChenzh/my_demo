package cn.chen;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chen
 * @date 2021/12/15 15:43
 * slf4j是log4j项目组的人出来 单做的支持拓展 需要拓展包 有自己的简单实现 还有企业级的logback作为支持
 */
public class LogTest {
    public void printLog(Logger log){
        log.error("日志级别:error");
        log.warn("日志级别:warn");
        log.info("日志级别:info");
        log.debug("日志级别:debug");
        log.trace("日志级别:trace");
    }

    @Test
    public void openMain(){
        //默认使用的是jul框架 jdk自带的
        Logger logger = LoggerFactory.getLogger(LogTest.class);
        printLog(logger);
    }


    @Test
    public void openMain4F(){
        //默认使用的是jul框架 jdk自带的
        Logger logger = LoggerFactory.getLogger(LogTest.class);
        for (int i = 0; i < 10000; i++) {
            printLog(logger);
        }
    }


}
