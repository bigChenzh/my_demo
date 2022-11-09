package cn.chen.day03.cn.chen.session.defaults;

import cn.chen.day03.cn.chen.binding.MapperRegistry;
import cn.chen.day03.cn.chen.session.Configuration;
import cn.chen.day03.cn.chen.session.SqlSession;

public class DefaultSqlSession implements SqlSession {

    //注册机
    private final Configuration configuration;

    public DefaultSqlSession(Configuration configuration){
        this.configuration = configuration;
    }

    @Override
    public <T> T selectOne(String statement) {
        return null;
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return null;
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }
}
