package cn.chen;

import com.sun.org.apache.xpath.internal.operations.String;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * @author chen
 * @date 2021/12/15 10:46
 * jul是jdk自带的日志框架 默认日志级别INFO
 * logger顶级父类 RootLogger
 */
public class LogTest {
    public void printLog(Logger logger){
        logger.log(Level.SEVERE,"日志级别：{0},日期:{1}",new Object[]{"SEVERE",new Date()});
        logger.log(Level.WARNING,"日志级别：{0},日期:{1}",new Object[]{"WARNING",new Date()});
        logger.log(Level.INFO,"日志级别：{0},日期:{1}",new Object[]{"INFO",new Date()});
        logger.log(Level.CONFIG,"日志级别：{0},日期:{1}",new Object[]{"CONFIG",new Date()});
        logger.log(Level.FINE,"日志级别：{0},日期:{1}",new Object[]{"FINE",new Date()});
        logger.log(Level.FINER,"日志级别：{0},日期:{1}",new Object[]{"FINER",new Date()});
        logger.log(Level.FINEST,"日志级别：{0},日期:{1}",new Object[]{"FINEST",new Date()});
        logger.log(Level.ALL,"日志级别：{0},日期:{1}",new Object[]{"ALL",new Date()});
    }

    @Test
    public void openMain(){
        //通过logger获取Logger对象 只支持字符串
        Logger logger = Logger.getLogger("cn.chen.logTest");

        printLog(logger);
        //默认使用的是jdk中的logging.properties配置文件 配置信息请查看resources下的配置文件
    }


    @Test
    public void properTies() throws IOException {
        InputStream inputStream = LogTest.class.getResourceAsStream("/logging.properties");
        Logger logger = Logger.getLogger("cn.chen.LogTest");
        LogManager logManager = LogManager.getLogManager();
        logManager.readConfiguration(inputStream);
        assert inputStream != null;
        inputStream.close();
        printLog(logger);

    }
}
