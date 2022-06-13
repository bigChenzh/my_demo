package cn.chen.mybatis;

import cn.chen.mybatis.binding.MapperRegistry;
import cn.chen.mybatis.dao.IUserDao;
import cn.chen.mybatis.session.SqlSession;
import cn.chen.mybatis.session.defaults.DefaultSqlSessionFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.reflect.Proxy;

@Slf4j
public class ApiTest {

    @Test
    public void test_proxy_class() {
        IUserDao userDao = (IUserDao) Proxy.newProxyInstance(
                ApiTest.class.getClassLoader(),
                new Class[]{IUserDao.class},
                (proxy, method, args) -> "你被代理了！！！！"
        );

        log.info(userDao.queryUserName("123"));
    }


    @Test
    public void test_MapperProxyFactory() {
        //创建注册机
        MapperRegistry mapperRegistry = new MapperRegistry();
        //注册信息
        mapperRegistry.addMappers("cn.chen.mybatis.dao");

        //创建SqlSession工厂
        DefaultSqlSessionFactory sqlSessionFactory = new DefaultSqlSessionFactory(mapperRegistry);
        //通过工厂获取sqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //通过sqlSession获取集体实现代理类
        IUserDao userDao = sqlSession.getMapper(IUserDao.class);

        String s = userDao.queryUserName("123");


        log.info(s);
    }
}
