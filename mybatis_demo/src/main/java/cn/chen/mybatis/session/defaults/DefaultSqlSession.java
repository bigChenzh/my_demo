package cn.chen.mybatis.session.defaults;

import cn.chen.mybatis.binding.MapperRegistry;
import cn.chen.mybatis.mapping.MappedStatement;
import cn.chen.mybatis.session.Configuration;
import cn.chen.mybatis.session.SqlSession;

import java.util.Arrays;

public class DefaultSqlSession implements SqlSession {


    private final Configuration configuration;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T selectOne(String statement) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statement);

        return (T) ("你被代理了!!" + statement);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T selectOne(String statement, Object[] parameter) {
        MappedStatement mappedStatement = configuration.getMappedStatement(statement);
        return (T) ("你被代理了!!" + "方法：" + statement + "入参：" + Arrays.toString(parameter) +"执行的sql："+mappedStatement.getSql());
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }
}
