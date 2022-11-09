package cn.chen.day03.cn.chen.builder;

import cn.chen.day03.cn.chen.session.Configuration;

public class BaseBuilder {
    protected Configuration configuration;
    
    public BaseBuilder(Configuration configuration){
        this.configuration = configuration;
    }
}
