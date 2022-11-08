package cn.chen.day02.cn.chen.session.defaults;

import cn.chen.day02.cn.chen.binding.MapperRegistry;
import cn.chen.day02.cn.chen.session.SqlSession;

public class DefaultSqlSession implements SqlSession {

    //注册机
    private final MapperRegistry mapperRegistry;

    public DefaultSqlSession(MapperRegistry mapperRegistry){
        this.mapperRegistry = mapperRegistry;
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
        return mapperRegistry.getMapper(type, this);
    }
}
