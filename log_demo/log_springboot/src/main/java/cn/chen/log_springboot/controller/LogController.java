package cn.chen.log_springboot.controller;


import cn.chen.log_springboot.bean.RestBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("log")
@Slf4j
public class LogController {
    public void printLog(){
        log.error("日志级别:error");
        log.warn("日志级别:warn");
        log.info("日志级别:info");
        log.debug("日志级别:debug");
        log.trace("日志级别:trace");
    }

    @RequestMapping("one")
    public RestBean one(){

        long startTime = System.currentTimeMillis();
        printLog();
        long runTime = System.currentTimeMillis() - startTime;

        RestBean restBean = new RestBean();
        restBean.setMsg("运行结束");
        restBean.setRunTime(runTime + "毫秒");

        return restBean;

    }


    @RequestMapping("for")
    public RestBean forOne(){
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            printLog();
        }
        long runTime = System.currentTimeMillis() - startTime;

        RestBean restBean = new RestBean();
        restBean.setMsg("运行结束");
        restBean.setRunTime(runTime + "毫秒");

        return restBean;
    }

}
