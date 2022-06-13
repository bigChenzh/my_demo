package cn.chen.mybatis.session.defaults;

import cn.chen.mybatis.binding.MapperRegistry;
import cn.chen.mybatis.session.Configuration;
import cn.chen.mybatis.session.SqlSession;

public class DefaultSqlSession implements SqlSession {


    private MapperRegistry mapperRegistry;

    public DefaultSqlSession(Configuration configuration) {
        this.mapperRegistry = mapperRegistry;
    }

    @Override
    public <T> T selectOne(String statement) {
        return (T) ("你被代理了!!" + statement);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        return (T) ("你被代理了!!" + "方法：" + statement + "入参：" + parameter);
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return mapperRegistry.getMapper(type, this);
    }
}
