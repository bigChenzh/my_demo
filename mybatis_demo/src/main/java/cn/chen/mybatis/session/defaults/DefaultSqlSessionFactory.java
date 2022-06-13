package cn.chen.mybatis.session.defaults;

import cn.chen.mybatis.binding.MapperRegistry;
import cn.chen.mybatis.session.Configuration;
import cn.chen.mybatis.session.SqlSession;
import cn.chen.mybatis.session.SqlSessionFactory;

public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
