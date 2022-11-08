package cn.chen.day02.cn.chen.session.defaults;

import cn.chen.day02.cn.chen.binding.MapperRegistry;
import cn.chen.day02.cn.chen.session.SqlSession;
import cn.chen.day02.cn.chen.session.SqlSessionFactory;

/**
 * 默认的sqlSession工厂对象
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    //注册机
    private final MapperRegistry mapperRegistry;

    public DefaultSqlSessionFactory(MapperRegistry mapperRegistry){
        this.mapperRegistry = mapperRegistry;
    }

    /**
     * 返回一个sqlSession 实现
     * @return 返回sqlSession 对象
     *
     */
    @Override
    public SqlSession openSession() {
        return new DefaultSqlSession(mapperRegistry);
    }
}
