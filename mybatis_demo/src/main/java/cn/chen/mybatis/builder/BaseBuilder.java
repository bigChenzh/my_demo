package cn.chen.mybatis.builder;


import cn.chen.mybatis.session.Configuration;

/**
 * 构建对象
 * @author chen
 */
public abstract class BaseBuilder {
    protected final Configuration configuration;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
