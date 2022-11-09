package cn.chen.day03.cn.chen.session.defaults;

import cn.chen.day03.cn.chen.binding.MapperRegistry;
import cn.chen.day03.cn.chen.session.Configuration;
import cn.chen.day03.cn.chen.session.SqlSession;
import cn.chen.day03.cn.chen.session.SqlSessionFactory;

/**
 * 默认的sqlSession工厂对象
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    //注册机
    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration){
        this.configuration = configuration;
    }

    /**
     * 返回一个sqlSession 实现
     * @return 返回sqlSession 对象
     *
     */
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }
}
