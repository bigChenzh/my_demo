package cn.chen;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;

/**
 * @author chen
 * @date 2021/12/15 15:43
 * jcl是阿帕奇提供的日志门面技术 需要导入包 commons-logging
 * 可惜框架原生不支持拓展 需要重写支持框架的类
 *  不方便 支持的日志框架为
 *  原生jul日志框架  log4j日志框架
 *
 *  使用log4j直接导入 log4j 包即可
 */
public class LogTest {
    public void printLog(Log log){
        log.fatal("日志级别:fatal");
        log.error("日志级别:error");
        log.info("日志级别:info");
        log.debug("日志级别:debug");
    }

    @Test
    public void openMain(){
        //默认使用的是jul框架 jdk自带的
        Log log = LogFactory.getLog(LogTest.class);
        printLog(log);
    }


}
