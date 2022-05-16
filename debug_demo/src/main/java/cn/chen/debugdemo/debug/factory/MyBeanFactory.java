package cn.chen.debugdemo.debug.factory;

import cn.chen.debugdemo.debug.bean.BJMyBean;
import cn.chen.debugdemo.debug.bean.MyBean;

public class MyBeanFactory {
    public static MyBean getMyBean(){
        return new BJMyBean();
    }
}
