package cn.chen.debugdemo.debug.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @RequestMapping("getUser")
    public String getUser(Long userId){
        return getUserService(userId);
    }

    public String getUserService(Long userId){
        switch (userId.intValue()){
            case 100 :  return "张三";
            case 200 :  return "李四";
        }

        return "NULL";
    }
}
